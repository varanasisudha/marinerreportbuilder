package generator;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import exception.FileTypeNotSupportedException;
import exception.NoFilesToProcessException;
import impl.CsvRecordParser;
import impl.JsonRecordParser;
import impl.XmlRecordParser;
import model.CsvRecord;
import model.CsvRecordResult;
import parser.FileProcessor;
import util.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ReportGenerator {

    public void trigger(String sourcePath, String destination) throws FileTypeNotSupportedException, InterruptedException, ExecutionException, NoFilesToProcessException {

        File folder = new File(sourcePath);
        File[] files = folder.listFiles();

        if(files == null || files.length == 0) {
            throw new NoFilesToProcessException("No files found in path '" + sourcePath + "' to process. Please add files and retry.");
        }
        List<String> notProcessed = new ArrayList<>();

        ExecutorService processor = Executors.newScheduledThreadPool(FileType.getSupportedTypes().size());

        List<CsvRecord> recordsResult = new ArrayList<>();
        List<Callable<List<CsvRecord>>> fpWorkList = new ArrayList<>();
        try {

            for (File file : files) {
                //read file and build Record object

                Optional<String> ext = Util.getExtension(file.getName());
                if (FileType.getSupportedTypes().contains(ext.get().toLowerCase())) {

                    if (FileType.JSON.name().equalsIgnoreCase(ext.get())) {

                        fpWorkList.add(new FileProcessor(file, new JsonRecordParser()));
                    }

                    if (FileType.CSV.name().equalsIgnoreCase(ext.get())) {
                        fpWorkList.add(new FileProcessor(file, new CsvRecordParser()));
                    }

                    if (FileType.XML.name().equalsIgnoreCase(ext.get())) {

                        fpWorkList.add(new FileProcessor(file, new XmlRecordParser()));
                    }

            } else{
                notProcessed.add(file.getName());
            }
        }

         List<Future<List<CsvRecord>>> futures = processor.invokeAll(fpWorkList);

          for (Future<List<CsvRecord>> future : futures) {
              if(future.get()!=null) {
                  recordsResult.addAll(future.get());
              }
          }

          processor.shutdown();

          buildResultFile(destination, recordsResult, Constants.RESULT_FILE_NAME);

          if(!notProcessed.isEmpty()) {
                throw new FileTypeNotSupportedException("File type not supported" + notProcessed.toString() + ". Supported types are: " + FileType.getSupportedTypes());
          }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public  void buildResultFile(String destinationDir, List<CsvRecord> recordList, String fileName) throws IOException {
        File directory = new File(String.valueOf(destinationDir));

        if(!directory.exists()){
            directory.mkdir();
        }

        try{

            //sort the records by time
            Collections.sort(recordList, new RecordComparator());

            List<CsvRecordResult> resultList = Util.convertCsvToResultCsv(recordList);

            Writer writer = new FileWriter(directory + Constants.PATH_SEPARATOR + fileName);
            CustomMappingStrategy<CsvRecordResult> mappingStrategy = new CustomMappingStrategy<>();
            mappingStrategy.setType(CsvRecordResult.class);

            StatefulBeanToCsv<CsvRecordResult> beanToCsv = new StatefulBeanToCsvBuilder<CsvRecordResult>(writer)
                    .withMappingStrategy(mappingStrategy).withSeparator(',').withApplyQuotesToAll(false).build();
            beanToCsv.write(resultList);
            writer.close();

            Map<String, Long> countByServiceGuid = recordList.stream()
                    .collect(Collectors.groupingBy(CsvRecord :: getServiceGuid, Collectors.counting()));

            System.out.println( "Service-Guid" + "\t" + "\t" + "\t" + "\t" + "\t" + "\t" + ":Count");
            countByServiceGuid.forEach((k,v) -> System.out.println(k + ":" + v));

        }
        catch (IOException| CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e){
            e.printStackTrace();
        }
    }
}

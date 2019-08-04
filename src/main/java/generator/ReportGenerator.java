package generator;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import exception.FileTypeNotSupportedException;
import impl.CsvRecordParser;
import impl.JsonRecordParser;
import impl.XmlRecordParser;
import model.CsvRecord;
import parser.FileProcessor;
import util.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static javafx.application.Platform.exit;

public class ReportGenerator {

    public void trigger(String sourcePath, String destination) throws FileTypeNotSupportedException, InterruptedException, ExecutionException {

        File folder = new File(sourcePath);
        File[] files = folder.listFiles();

        List<String> notProcessed = new ArrayList<>();

        ExecutorService processor = Executors.newScheduledThreadPool(FileType.getSupportedTypes().size());

        List<CsvRecord> recordsResult = new ArrayList<>();
        List<Callable<List<CsvRecord>>> fpWorkList = new ArrayList<>();
        try {

            for (File file : files) {
                //read file and build Record object

                Optional<String> ext = Util.getExtension(file.getName());
                if (FileType.JSON.name().equalsIgnoreCase(ext.get())
            || FileType.CSV.name().equalsIgnoreCase(ext.get()) || FileType.XML.name().equalsIgnoreCase(ext.get()) ) {


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

          buildResultFile(destination, recordsResult, Constants.RESULT_FILE_NAME);
          processor.shutdown();

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

            Writer writer = new FileWriter(directory + "/" + fileName);
            CustomMappingStrategy<CsvRecord> mappingStrategy = new CustomMappingStrategy<>();
            mappingStrategy.setType(CsvRecord.class);

            StatefulBeanToCsv<CsvRecord> beanToCsv = new StatefulBeanToCsvBuilder<CsvRecord>(writer)
                    .withMappingStrategy(mappingStrategy).withSeparator(',').withApplyQuotesToAll(false).build();
            beanToCsv.write(recordList);
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

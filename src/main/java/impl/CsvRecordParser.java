package impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import model.CsvRecord;
import parser.RecordParser;
import util.Constants;
import util.CustomMappingStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvRecordParser implements RecordParser {

    @Override
    public List<CsvRecord> process(File file) {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(Constants.SOURCE_PATH +Constants.PATH_SEPARATOR + file.getName()))) {

            CustomMappingStrategy<CsvRecord> mappingStrategy = new CustomMappingStrategy<>();
            mappingStrategy.setType(CsvRecord.class);

            CSVReader csvReader = new CSVReaderBuilder(br).withSkipLines(1).build();
            CsvToBean csvToBean = new CsvToBean();
            List<CsvRecord> csvRecords = csvToBean.parse(mappingStrategy, csvReader);

            return csvRecords.stream().filter(csvRecord -> csvRecord.getPacketsServiced()!=0)
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }

        return null;
    }
}

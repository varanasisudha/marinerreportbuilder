package impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.CsvRecord;
import model.JsonRecord;
import parser.RecordParser;
import util.Constants;
import util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class JsonRecordParser implements RecordParser {
    @Override
    public List<CsvRecord> process(File file) {


        try (BufferedReader br = Files.newBufferedReader(Paths.get(Constants.SOURCE_PATH + Constants.PATH_SEPARATOR + file.getName()))) {

           Gson gson = new Gson();

            Type jsonRecordType = new TypeToken<Collection<JsonRecord>>(){}.getType();
            Collection<JsonRecord> jsonRecords = gson.fromJson(br, jsonRecordType);
            return Util.convertJsontoCsv(jsonRecords);

        } catch (IOException ex) {

        }

        return null;
    }
}

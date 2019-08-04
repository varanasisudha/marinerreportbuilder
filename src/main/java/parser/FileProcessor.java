package parser;

import model.CsvRecord;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public class FileProcessor implements Callable<List<CsvRecord>> {

    private RecordParser parser;
    private File file;

    public FileProcessor(File file, RecordParser parser) {
        this.parser = parser;
        this.file = file;
    }

    @Override
    public List<CsvRecord> call() throws Exception {

        List<CsvRecord> recordList = parser.process(file);

        return recordList;

    }
}

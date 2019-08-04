package parser;

import model.CsvRecord;

import java.io.File;
import java.util.List;

public interface RecordParser {

    public List<CsvRecord> process(File file);

}

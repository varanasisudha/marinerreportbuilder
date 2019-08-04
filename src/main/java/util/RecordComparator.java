package util;

import model.CsvRecord;

import java.util.Comparator;

public class RecordComparator implements Comparator<CsvRecord> {
    @Override
    public int compare(CsvRecord o1, CsvRecord o2) {
        return o1.getRequestTime().compareTo(o2.getRequestTime());
    }
}

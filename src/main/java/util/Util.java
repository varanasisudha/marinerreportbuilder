package util;

import model.CsvRecord;
import model.JsonRecord;
import model.XmlReport;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Util {

    public static List<CsvRecord> convertJsontoCsv(Collection<JsonRecord> jsonRecords) {
        List<CsvRecord> csvRecords = jsonRecords.stream().filter(jsonRecord -> jsonRecord.getPacketsServiced()!=0)
                .map(jsonRecord -> new CsvRecord(jsonRecord.getMaxHoleSize(), jsonRecord.getPacketsServiced(), jsonRecord.getPacketsRequested(),
                jsonRecord.getRetriesRequest(), jsonRecord.getClientGuid(), jsonRecord.getServiceGuid(), getInetAddress(jsonRecord.getClientAddress()), getZonedDateTime(jsonRecord.getRequestTime())))
                .collect(Collectors.toList());
        return csvRecords;
    }

    private static ZonedDateTime getZonedDateTime(Long time) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.of("Canada/Atlantic"));
        return zdt;
    }

    private static ZonedDateTime getZonedDateTime(String time) {
        return ZonedDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
    }

    private static InetAddress getInetAddress(String s) {
        InetAddress inetAddress = null;
       try {
           inetAddress = InetAddress.getByName(s);
       } catch (UnknownHostException e) {
           System.out.print(e.getMessage());
       }
       return inetAddress;

    }
    public static List<CsvRecord> convertXmltoCsv(List<XmlReport> xmlRecords) {
        List<CsvRecord> csvRecords = xmlRecords.stream().filter(xmlRecord -> xmlRecord.getPacketsServiced()!=0)
                .map(xmlRecord -> new CsvRecord(xmlRecord.getMaxHoleSize(), xmlRecord.getPacketsServiced(), xmlRecord.getPacketsRequested(),
                        xmlRecord.getRetriesRequest(), xmlRecord.getClientGuid(), xmlRecord.getServiceGuid(), getInetAddress(xmlRecord.getClientAddress()),
                        getZonedDateTime(xmlRecord.getRequestTime())))
                .collect(Collectors.toList());
        return csvRecords;
    }

    public static Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains(Constants.DOT))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}

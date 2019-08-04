package impl;

import model.CsvRecord;
import model.XmlRecord;
import parser.RecordParser;
import util.Constants;
import util.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class XmlRecordParser implements RecordParser {

    @Override
    public List<CsvRecord> process(File file) {


        try (BufferedReader br = Files.newBufferedReader(Paths.get(Constants.SOURCE_PATH + Constants.PATH_SEPARATOR  + file.getName()))) {

            JAXBContext jaxbContext = JAXBContext.newInstance(XmlRecord.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            XmlRecord xmlRecord = (XmlRecord) jaxbUnmarshaller.unmarshal(br);

            return Util.convertXmltoCsv(xmlRecord.getReports());

        } catch (IOException | JAXBException ex) {
            System.out.print(ex.getMessage());
        }

        return null;


    }

}

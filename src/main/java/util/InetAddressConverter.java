package util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class InetAddressConverter extends AbstractBeanField {
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(s);
        } catch (UnknownHostException e) {
            System.out.print(e.getMessage());
        }
        return address;
    }
}

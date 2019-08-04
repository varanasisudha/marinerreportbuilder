package model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import util.DateConverter;
import util.InetAddressConverter;

import java.net.InetAddress;
import java.time.ZonedDateTime;

public class CsvRecord {

    @CsvBindByName(column = "max-hole-size", required = true)
    @CsvBindByPosition(position=7)
    private Integer maxHoleSize;

    @CsvBindByName(column = "packets-serviced", required = true)
    @CsvBindByPosition(position=6)
    private Integer packetsServiced;

    @CsvBindByName(column = "packets-requested", required = true)
    @CsvBindByPosition(position=5)
    private Integer packetsRequested;

    @CsvBindByName(column = "retries-request", required = true)
    @CsvBindByPosition(position=4)
    private Integer retriesRequest;


    @CsvBindByName(column = "client-guid", required = true)
    @CsvBindByPosition(position=1)
    private String clientGuid;


    @CsvBindByName(column = "service-guid", required = true)
    @CsvBindByPosition(position=3)
    private String serviceGuid;

    @CsvBindByName(column = "client-address", required = true)
    @CsvCustomBindByPosition(position=0, converter = InetAddressConverter.class)
    private InetAddress clientAddress;

    @CsvBindByName(column = "request-time", required = true)
    @CsvCustomBindByPosition(position = 2, converter = DateConverter.class)
    private ZonedDateTime requestTime;


    public CsvRecord() {

    }
    public CsvRecord(Integer maxHoleSize, Integer packetsServiced, Integer packetsRequested, Integer retriesRequest,
                     String clientGuid, String serviceGuid, InetAddress clientAddress, ZonedDateTime requestTime) {
        this.maxHoleSize = maxHoleSize;
        this.packetsServiced = packetsServiced;
        this.packetsRequested = packetsRequested;
        this.retriesRequest = retriesRequest;
        this.clientGuid = clientGuid;
        this.serviceGuid = serviceGuid;
        this.clientAddress = clientAddress;
        this.requestTime = requestTime;
    }

    public Integer getMaxHoleSize() {
        return maxHoleSize;
    }

    public void setMaxHoleSize(Integer maxHoleSize) {
        this.maxHoleSize = maxHoleSize;
    }

    public Integer getPacketsServiced() {
        return packetsServiced;
    }

    public void setPacketsServiced(Integer packetsServiced) {
        this.packetsServiced = packetsServiced;
    }

    public Integer getPacketsRequested() {
        return packetsRequested;
    }

    public void setPacketsRequested(Integer packetsRequested) {
        this.packetsRequested = packetsRequested;
    }

    public Integer getRetriesRequest() {
        return retriesRequest;
    }

    public void setRetriesRequest(Integer retriesRequest) {
        this.retriesRequest = retriesRequest;
    }

    public String getClientGuid() {
        return clientGuid;
    }

    public void setClientGuid(String clientGuid) {
        this.clientGuid = clientGuid;
    }

    public String getServiceGuid() {
        return serviceGuid;
    }

    public void setServiceGuid(String serviceGuid) {
        this.serviceGuid = serviceGuid;
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(InetAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    public ZonedDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(ZonedDateTime requestTime) {
        this.requestTime = requestTime;
    }
}

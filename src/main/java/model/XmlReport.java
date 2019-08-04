package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "report" )
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlReport {

    @XmlElement(name = "max-hole-size")
    private Integer maxHoleSize;
    @XmlElement(name = "packets-serviced")
    private Integer packetsServiced;
    @XmlElement(name = "packets-requested")
    private Integer packetsRequested;
    @XmlElement(name = "retries-request")
    private Integer retriesRequest;
    @XmlElement(name = "client-guid")
    private String clientGuid;
    @XmlElement(name = "service-guid")
    private String serviceGuid;
    @XmlElement(name = "client-address")
    private String clientAddress;
    @XmlElement(name = "request-time")
    private String requestTime;

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

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
}



package model;

import com.google.gson.annotations.SerializedName;

public class JsonRecord {

    @SerializedName("max-hole-size")
    private Integer maxHoleSize;
    @SerializedName("packets-serviced")
    private Integer packetsServiced;
    @SerializedName("packets-requested")
    private Integer packetsRequested;
    @SerializedName("retries-request")
    private Integer retriesRequest;
    @SerializedName("client-guid")
    private String clientGuid;
    @SerializedName("service-guid")
    private String serviceGuid;

    @SerializedName("client-address")
    private String clientAddress;
    @SerializedName("request-time")
    private Long requestTime;

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

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }
}

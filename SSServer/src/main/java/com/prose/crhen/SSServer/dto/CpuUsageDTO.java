package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CpuUsageDTO {

    private String path;
    private String instanceName;
    private Double cookedValue;
    private Long rawValue;
    private Long secondValue;
    private int multipleCount;
    private Long counterType;
    private String timestamp;
    private Long timestamp100NSec;
    private int status;
    private int defaultScale;
    private Long timeBase;

    @JsonProperty("Path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("InstanceName")
    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    @JsonProperty("CookedValue")
    public Double getCookedValue() {
        return cookedValue;
    }

    public void setCookedValue(Double cookedValue) {
        this.cookedValue = cookedValue;
    }

    @JsonProperty("RawValue")
    public Long getRawValue() {
        return rawValue;
    }

    public void setRawValue(Long rawValue) {
        this.rawValue = rawValue;
    }

    @JsonProperty("SecondValue")
    public Long getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(Long secondValue) {
        this.secondValue = secondValue;
    }

    @JsonProperty("MultipleCount")
    public int getMultipleCount() {
        return multipleCount;
    }

    public void setMultipleCount(int multipleCount) {
        this.multipleCount = multipleCount;
    }

    @JsonProperty("CounterType")
    public Long getCounterType() {
        return counterType;
    }

    public void setCounterType(Long counterType) {
        this.counterType = counterType;
    }

    @JsonProperty("Timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("Timestamp100NSec")
    public Long getTimestamp100NSec() {
        return timestamp100NSec;
    }

    public void setTimestamp100NSec(Long timestamp100NSec) {
        this.timestamp100NSec = timestamp100NSec;
    }

    @JsonProperty("Status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonProperty("DefaultScale")
    public int getDefaultScale() {
        return defaultScale;
    }

    public void setDefaultScale(int defaultScale) {
        this.defaultScale = defaultScale;
    }

    @JsonProperty("TimeBase")
    public Long getTimeBase() {
        return timeBase;
    }

    public void setTimeBase(Long timeBase) {
        this.timeBase = timeBase;
    }
}

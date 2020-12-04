package com.prose.crhen.SSServer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RamDTO {

    private int Capacity;

    @JsonProperty("Capacity")
    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }
}

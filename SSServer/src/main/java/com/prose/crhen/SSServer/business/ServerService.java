package com.prose.crhen.SSServer.business;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ServerService {

    //calculate the storaga ratio of the server
    public double calculateStorageRatio(double storageCapacity, double storageUsed) {
        double storageRatio = (Double.valueOf(storageUsed)/Double.valueOf(storageCapacity))*100;
        storageRatio = round(storageRatio);
        return storageRatio;
    }

    //round the number to two decimals
    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

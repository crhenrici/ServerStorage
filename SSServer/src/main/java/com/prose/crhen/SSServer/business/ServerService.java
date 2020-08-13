package com.prose.crhen.SSServer.business;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ServerService {

    //calculate the storaga ratio of the server
    public static double calculateStorageRatio(int storageCapacity, int storageUsed) {
        double storageRatio = (Double.valueOf(storageUsed)/Double.valueOf(storageCapacity))*100;
        storageRatio = round(storageRatio);
        return storageRatio;
    }

    //round the number to two decimals
    private static double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

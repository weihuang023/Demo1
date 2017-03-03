/*
 * Decompiled with CFR 0_118.
 */
package com.RTTS.NeoLoad;

import java.util.Date;

public class DeliveryDateGenerator {
    @SuppressWarnings("deprecation")
	public String deliveryDateGenerator(int daysInFuture) {
        String deliveryDate = "";
        Date currDate = new Date();
        currDate.setDate(currDate.getDate() + daysInFuture);
        if (currDate.getDay() == 0) {
            currDate.setDate(currDate.getDate() + 1);
        }
        String year = String.format("%02d", currDate.getYear() + 1900);
        deliveryDate = String.valueOf(String.format("%02d", currDate.getMonth() + 1)) + "/" + String.format("%02d", currDate.getDate()) + "/" + year.substring(2, 4);
        return deliveryDate;
    }
}


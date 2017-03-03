/*
 * Decompiled with CFR 0_118.
 */
package com.RTTS.NeoLoad;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DeliveryDateChangeGenerator {
    public String NewDeliveryDate(String existingDate) {
        try {
            int numDaysOffset = 8;
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
            Date currentDateSelected = dateFormat.parse(existingDate);
            Calendar calObjectCurrDateSelected = Calendar.getInstance();
            calObjectCurrDateSelected.setTime(currentDateSelected);
            Calendar calObjectNewDeliveryDate = Calendar.getInstance();
            calObjectNewDeliveryDate.add(5, numDaysOffset);
            int i = 0;
            while (i < 10) {
                if (DeliveryDateChangeGenerator.noTimeCalObject(calObjectCurrDateSelected).getTime() == DeliveryDateChangeGenerator.noTimeCalObject(calObjectNewDeliveryDate).getTime()) {
                    calObjectNewDeliveryDate.add(5, 1);
                } else {
                    if (calObjectNewDeliveryDate.get(7) != 1) break;
                    calObjectNewDeliveryDate.add(5, 1);
                }
                ++i;
            }
            return dateFormat.format(calObjectNewDeliveryDate.getTime());
        }
        catch (Exception e) {
            return "ERROR";
        }
    }

    public static Date noTimeCalObject(Calendar temp) {
        Calendar calendar = temp;
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }
}


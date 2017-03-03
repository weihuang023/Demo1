/*
 * Decompiled with CFR 0_118.
 */
package com.RTTS.NeoLoad;

import java.util.Random;

public class TelephoneGenerator {
    public String phoneNumberGenerator(String AreaCode) {
        String phoneNumber = "";
        Random generator = new Random(System.currentTimeMillis() + (long)Integer.parseInt(AreaCode));
        phoneNumber = String.valueOf(phoneNumber) + AreaCode;
        phoneNumber = String.valueOf(phoneNumber) + String.format("%03d", generator.nextInt(799) + 200);
        phoneNumber = String.valueOf(phoneNumber) + String.format("%04d", generator.nextInt(999));
        return phoneNumber;
    }

    public String phoneExtension() {
        String phoneExtension = "";
        Random generator = new Random(System.currentTimeMillis());
        phoneExtension = String.valueOf(phoneExtension) + String.format("%04d", generator.nextInt(9999) + 1);
        return phoneExtension;
    }
}


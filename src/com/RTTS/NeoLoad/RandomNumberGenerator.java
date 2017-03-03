/*
 * Decompiled with CFR 0_118.
 */
package com.RTTS.NeoLoad;

import java.util.Random;

public class RandomNumberGenerator {
    public String randomNumber(int min, int max) {
        String rand = "";
        Random random = new Random(System.currentTimeMillis());
        rand = "" + (random.nextInt(max - min) + min);
        return rand;
    }
}


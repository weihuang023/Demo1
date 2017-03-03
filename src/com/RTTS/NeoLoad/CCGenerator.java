/*
 * Decompiled with CFR 0_118.
 */
package com.RTTS.NeoLoad;

import java.util.Date;
import java.util.Random;

public class CCGenerator {

	@SuppressWarnings("deprecation")
	public String ccExpGenerator() {
        String exp = "";
        System.out.println("exp "+exp);
        Date ccTempDate = new Date();
        ccTempDate.setDate(ccTempDate.getDate() + 730);
        String ccMonth = String.format("%02d", ccTempDate.getMonth() + 1);
        String ccYear = "" + (ccTempDate.getYear() + 1900);
        ccYear = ccYear.toString().substring(2, 4);
        return String.valueOf(ccMonth) + "/" + ccYear;
    }

    public String ccGenerator(String vUserInfo) {
        int currDigit = 0;
        int ccLength = 0;
        int checksum = 0;
        int temp = 0;
        int startDigit = 2;
        char[] ccNum = new char[20];
        String FullCCNum = "";
        int vUserOffset = 0;
        int counter = 0;
        while (counter < vUserInfo.length()) {
            vUserOffset += vUserInfo.charAt(counter);
            ++counter;
        }
        Random generator = new Random(System.currentTimeMillis() + (long)vUserOffset);
        currDigit = generator.nextInt(4) + 3;
        if (currDigit == 3) {
            ccLength = 15;
            checksum += currDigit;
        } else if (currDigit == 6) {
            ccLength = 16;
            currDigit = 6011;
            checksum += 6;
            startDigit = 5;
        } else if (currDigit == 5) {
            startDigit = 3;
            ++checksum;
            ccLength = 16;
            temp = generator.nextInt(5) + 1;
            checksum += temp;
            currDigit = 50 + temp;
        } else {
            ccLength = 16;
            checksum += 2 * currDigit;
        }
        char[] tempArray = String.valueOf(currDigit).toCharArray();
        int x = 0;
        while (x < tempArray.length) {
            ccNum[x] = tempArray[x];
            ++x;
        }
        int i = startDigit;
        while (i <= ccLength) {
            if (i == 2 && currDigit == 3) {
                currDigit = generator.nextInt(2);
                currDigit = currDigit == 0 ? 4 : 7;
            } else if (i == ccLength) {
                currDigit = 10 - checksum % 10;
                if (currDigit == 10) {
                    currDigit = 0;
                }
            } else {
                currDigit = generator.nextInt(10);
            }
            ccNum[i - 1] = (char)(currDigit + 48);
            if (ccLength == 15 && i % 2 == 0 || ccLength == 16 && i % 2 == 1) {
                temp = currDigit * 2;
                if (temp > 9) {
                    temp -= 9;
                }
            } else {
                temp = currDigit;
            }
            checksum += temp;
            ++i;
        }
        x = 0;
        while (x < ccNum.length) {
            FullCCNum = String.valueOf(FullCCNum) + ccNum[x];
            ++x;
        }
        return FullCCNum.trim();
    }
}


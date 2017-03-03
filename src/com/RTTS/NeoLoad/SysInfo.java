/*
 * Decompiled with CFR 0_118.
 */
package com.RTTS.NeoLoad;

public class SysInfo {
    public String getOSName() {
        return System.getProperty("os.name");
    }

    public String getOSArcitecture() {
        return System.getProperty("os.arch");
    }

    public String getSysUserName() {
        return System.getProperty("user.name");
    }

    public String getSysUserLang() {
        return System.getProperty("user.language");
    }
}


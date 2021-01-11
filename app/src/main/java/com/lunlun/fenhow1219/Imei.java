package com.lunlun.fenhow1219;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class Imei {
    public String aIMEI;
    public int aEMPLOYEE_ID;
    public String aDEVICE_NAME;
    public boolean aISUSING;

    public Imei() {
    }

    public Imei(String aIMEI, int aEMPLOYEE_ID, String aDEVICE_NAME, boolean aISUSING) {
        this.aIMEI = aIMEI;
        this.aEMPLOYEE_ID = aEMPLOYEE_ID;
        this.aDEVICE_NAME = aDEVICE_NAME;
        this.aISUSING = aISUSING;
    }

    public String getaIMEI() {
        return aIMEI;
    }

    public void setaIMEI(String aIMEI) {
        this.aIMEI = aIMEI;
    }

    public int getaEMPLOYEE_ID() {
        return aEMPLOYEE_ID;
    }

    public void setaEMPLOYEE_ID(int aEMPLOYEE_ID) {
        this.aEMPLOYEE_ID = aEMPLOYEE_ID;
    }

    public String getaDEVICE_NAME() {
        return aDEVICE_NAME;
    }

    public void setaDEVICE_NAME(String aDEVICE_NAME) {
        this.aDEVICE_NAME = aDEVICE_NAME;
    }

    public boolean isaISUSING() {
        return aISUSING;
    }

    public void setaISUSING(boolean aISUSING) {
        this.aISUSING = aISUSING;
    }


}

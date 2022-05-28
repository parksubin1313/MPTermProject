package com.example.termproject.domain;

import java.io.Serializable;

public class APIFood implements Serializable {
    public String PRDLST_NM;
    public int BAR_CD;

    public APIFood(String PRDLST_NM, int BAR_CD) {
        this.PRDLST_NM = PRDLST_NM;
        this.BAR_CD = BAR_CD;
    }

    public String getPRDLST_NM() {
        return PRDLST_NM;
    }

    public void setPRDLST_NM(String PRDLST_NM) {
        this.PRDLST_NM = PRDLST_NM;
    }

    public int getBAR_CD() {
        return BAR_CD;
    }

    public void setBAR_CD(int BAR_CD) {
        this.BAR_CD = BAR_CD;
    }
}

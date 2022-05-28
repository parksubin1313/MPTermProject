package com.example.termproject.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class CFoodList implements Serializable {
    public ArrayList<CFood> cFoods;

    public CFoodList() {
    }

    public ArrayList<CFood> getcFoods() {
        return cFoods;
    }

    public void setcFoods(ArrayList<CFood> cFoods) {
        this.cFoods = cFoods;
    }

    //TODO: getItem 등을 해줘야지!
}

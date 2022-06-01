package com.example.termproject.domain;

import java.io.Serializable;

public class ShoppingList implements Serializable {
    public String fName;
    //기본은 0 : unchecked , 체크되면 1 : checked
    //TODO: 0인 애들만 장바구니 위에, 1인 애들만 장바구니 아래 회색으로
    //TODO: 1인 애들은 전체 delete (reset) 가능하도록 버튼 추가
    public int checked;

    public ShoppingList(String fName, int checked) {
        this.fName = fName;
        this.checked = checked;
    }

    public String getName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }
}

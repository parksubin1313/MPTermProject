package com.example.termproject;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.example.termproject.R;

public class CheckableLinearLayout extends LinearLayout implements Checkable {


    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean b) {
        CheckBox cb = (CheckBox) findViewById(R.id.shopping_checkBox);

        if(cb.isChecked() != b){
            cb.setChecked(b);
        }

        //CheckBox 가 아닌 View의 상태 변경
    }

    @Override
    public boolean isChecked() {
        CheckBox cb = (CheckBox) findViewById(R.id.shopping_checkBox);
        return cb.isChecked();
    }

    @Override
    public void toggle() {
        CheckBox cb = (CheckBox) findViewById(R.id.shopping_checkBox);

        setChecked(cb.isChecked());
    }
}

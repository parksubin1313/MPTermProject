package com.example.termproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FoodItemView extends LinearLayout {
    ImageView foodImg;
    TextView foodName, dueDate, quantity;


    public FoodItemView(Context context) {
        super(context);

        init(context);
    }

    public FoodItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }


    // food_item.xml을 inflation
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.food_item, this, true);

        foodImg = (ImageView) findViewById(R.id.foodImg);
        foodName = (TextView) findViewById(R.id.foodName);
        dueDate = (TextView) findViewById(R.id.dueDate);
        quantity = (TextView) findViewById(R.id.quantity);
    }


    public void setFoodImg(ImageView foodImg) {
        this.foodImg = foodImg;
    }

    public void setFoodName(TextView foodName) {
        this.foodName = foodName;
    }

    public void setDueDate(TextView dueDate) {
        this.dueDate = dueDate;
    }

    public void setQuantity(TextView quantity) {
        this.quantity = quantity;
    }
}


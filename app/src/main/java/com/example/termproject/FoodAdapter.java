package com.example.termproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<FoodItem> foodItemArrayList = new ArrayList<FoodItem>();

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView foodName;
        public TextView dueDate;
        public TextView quantity;
        public ImageView foodImg;

        View rootView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//        storageWay = itemView.findViewById(R.id.s)
            foodName = itemView.findViewById(R.id.foodName);
            dueDate = itemView.findViewById(R.id.dueDate);
            quantity = itemView.findViewById(R.id.quantity);
            foodImg = itemView.findViewById(R.id.foodImg);
            rootView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String strText = foodName.getText().toString();
                    Toast.makeText(foodName.getContext(), strText, Toast.LENGTH_SHORT).show();
                }
            });
        }

/*        void onBind(FoodItem data){
            foodName.setText(data.getFoodName());
        }*/
    }

    public FoodAdapter(ArrayList<FoodItem> myDataSet, Context context){
        foodItemArrayList = myDataSet;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodItemArrayList.get(position);

        holder.foodName.setText(foodItem.getFoodName());
        holder.dueDate.setText(foodItem.getDueDate());
        holder.quantity.setText(foodItem.getQuantity());
        holder.foodImg.setImageResource(foodItem.getResId());

    }

    @Override
    public int getItemCount() {
        return foodItemArrayList == null ? 0 : foodItemArrayList.size();
    }

    public void addChat(FoodItem foodItem){
        foodItemArrayList.add(foodItem);
        notifyItemInserted(foodItemArrayList.size()-1);
    }

}

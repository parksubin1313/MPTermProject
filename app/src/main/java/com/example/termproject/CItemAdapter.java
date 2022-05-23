package com.example.termproject;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CItemAdapter extends RecyclerView.Adapter<CItemAdapter.ViewHolder> {

    public ArrayList<CommunityItem> item_list;

    public CItemAdapter(ArrayList<CommunityItem> arrayList){
        item_list = arrayList;
    }

    @NonNull
    @Override
    public CItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_my_communityitem, null);

        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }


    public void onBindViewHolder(CItemAdapter.ViewHolder holder, int position) {
        final int pos = position;

        holder.foodName.setText(item_list.get(position).getFoodName());

        holder.chkSelected.setChecked(item_list.get(position).isSelected());

        holder.chkSelected.setTag(item_list.get(position));

        holder.chkSelected.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                CheckBox cb = (CheckBox) view;
                CommunityItem citem = (CommunityItem) cb.getTag();

                citem.setSelected(cb.isChecked());
                item_list.get(pos).setSelected(cb.isChecked());
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItemFromList(view, pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    private void deleteItemFromList(View v, final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        builder.setMessage("Delete Item?")
                .setCancelable(false)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        item_list.remove(position);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
//        public ImageView img;
        public TextView foodName;
        public CheckBox chkSelected;
        public Button btn_delete;

        public ViewHolder(View itemLayoutView){
            super(itemLayoutView);

//            img = (ImageView) itemLayoutView.findViewById();

            foodName = (TextView) itemLayoutView.findViewById(R.id.txt_Name);

            chkSelected = (CheckBox) itemLayoutView.findViewById(R.id.chk_selected);

            btn_delete = (Button) itemLayoutView.findViewById(R.id.btn_delete);
        }
    }
}

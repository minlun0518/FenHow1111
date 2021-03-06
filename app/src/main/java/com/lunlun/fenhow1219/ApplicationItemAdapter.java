package com.lunlun.fenhow1219;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApplicationItemAdapter extends RecyclerView.Adapter < ApplicationItemAdapter.ViewHolder > {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ApplicationItem> applicationItemList;

    private final String TAG = ApplicationItemAdapter.class.getSimpleName();

    public ApplicationItemAdapter(Context context, List<ApplicationItem> applicationItemList) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.applicationItemList = applicationItemList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageview;
        TextView appname;
        View itemView;
        Button clickbutton;

        public ViewHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            imageview= itemView.findViewById(R.id.imageView2);
            appname=itemView.findViewById(R.id.textView5);
            clickbutton=itemView.findViewById(R.id.clickbutton);
        }
    }

    @Override
    public int getItemCount() {
        return applicationItemList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        LayoutInflater layoutInflater =LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.home_item_itemview,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
        ApplicationItem applicationItem = applicationItemList.get(position);
        viewHolder.appname.setText(applicationItem.appName);
        viewHolder.imageview.setImageResource(applicationItem.imageViewpost);
        viewHolder.clickbutton.setText(applicationItem.appAct);
        viewHolder.clickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(applicationItem.className);
                builder.create().show();
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setIcon(applicationItem.imageViewpost)
                        .setMessage("這是"+applicationItem.appName+"的對話框")
                        .setTitle(applicationItem.appName)
                        .show();
            }
        });
    }
}

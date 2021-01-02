package com.lunlun.fenhow1219;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.lunlun.fenhow1219.ui.home.HomeFragment;
import com.lunlun.fenhow1219.ui.slideshow.SlideshowFragment;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class ApplicationItemAdapter extends RecyclerView.Adapter < ApplicationItemAdapter.ViewHolder > {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ApplicationItem> applicationItemList;
    public HomeFragment homeFragment;

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
        Class className;

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
        View itemView = layoutInflater.inflate(R.layout.itemview,viewGroup,false);
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
                Log.d(TAG,"class = "+applicationItem.className);
                Intent pp = new Intent(context, SignWorkActivity.class);
                view.getContext().startActivity(pp);
            }

        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setIcon(applicationItem.imageViewpost)
                        .setMessage("早安你好")
                        .setTitle(applicationItem.appName)
                        .show();
            }
        });
    }
}

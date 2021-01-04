package com.lunlun.fenhow1219;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter < TaskAdapter.ViewHolder > {
    private Context taskcontext;
    private LayoutInflater tasklayoutInflater;
    private List<com.lunlun.fenhow1219.Task> taskList;

    public TaskAdapter(Context taskcontext, List<com.lunlun.fenhow1219.Task> taskList) {
        this.taskcontext = taskcontext;
        tasklayoutInflater = LayoutInflater.from(taskcontext);
        this.taskList = taskList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView taskimageview;
        TextView taskname;
        View taskitemView;
        TextView tasktask;

        public ViewHolder(View taskItemView){
            super(taskItemView);
            this.taskitemView = itemView;
            taskimageview= taskitemView.findViewById(R.id.imageView3);
            taskname=taskitemView.findViewById(R.id.textView4);
            tasktask=taskItemView.findViewById(R.id.contenttextView);
        }
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        LayoutInflater layoutInflater =LayoutInflater.from(taskcontext);
        View taskitemView = layoutInflater.inflate(R.layout.itim_home_taskitemview,viewGroup,false);
        return new ViewHolder(taskitemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position){
        com.lunlun.fenhow1219.Task task = taskList.get(position);
        viewHolder.taskname.setText(task.taskName);
        viewHolder.taskimageview.setImageResource(task.taskImageView);
        viewHolder.tasktask.setText(task.tasktask);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(taskcontext)
                        .setIcon(task.taskImageView)
                        .setMessage(task.tasktask)
                        .setTitle(task.taskName)
                        .setPositiveButton("已讀",null)
                        .setNeutralButton("cancel/back",null)
                        .show();
            }
        });
    }
}

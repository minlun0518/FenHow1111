package com.lunlun.fenhow1219;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class LoginUserListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<LoginUserList> loginUserList;
    private View view;
    private View view2;
    private Login login;

    public LoginUserListAdapter(Context mContext, List<LoginUserList> loginUserListList) {
        this.mContext = mContext;
        this.loginUserList = loginUserListList;
    }

    public class HotUserViewHolder extends RecyclerView.ViewHolder {
        private int position;
        private String userID;
        private String userEmail;
        private String userName;
        private String userPassword;
        private final TextView mTextViewName;
        private final TextView mTextViewNo;
        private final RecyclerView cycleViewHotUser;
        private final ConstraintLayout constraintLayout;
        private final int color1;
        private final int color2;
        private final int color3;
        private final int color4;


        public HotUserViewHolder(View view) {
            super(view);
            mTextViewName = view.findViewById(R.id.tv_hot_article_title);
            mTextViewNo = view.findViewById(R.id.tv_hot_article_from);
            cycleViewHotUser = view.findViewById(R.id.cycleViewHotUser);

            constraintLayout = view.findViewById(R.id.constraintLayoutCardView);
            color1 = view.getResources().getColor(R.color.waplant_bgyellow);
            color2 = view.getResources().getColor(R.color.waplant_blue);
            color3 = view.getResources().getColor(R.color.waplant_bggreen);
            color4 = view.getResources().getColor(R.color.waplant_gray);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.login_item_public_device, parent, false);
        view2 = LayoutInflater.from(mContext).inflate(R.layout.login_activity, parent, false);
        return new HotUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HotUserViewHolder) holder).mTextViewName.setText(loginUserList.get(position).getUserName());
        ((HotUserViewHolder) holder).mTextViewNo.setText(loginUserList.get(position).getUserID());

        if (position % 4 == 1) {
            ((HotUserViewHolder) holder).constraintLayout.setBackgroundColor(((HotUserViewHolder) holder).color1);
        } else if (position % 4 == 2) {
            ((HotUserViewHolder) holder).constraintLayout.setBackgroundColor(((HotUserViewHolder) holder).color2);
        } else if (position % 4 != 3) {
            ((HotUserViewHolder) holder).constraintLayout.setBackgroundColor(((HotUserViewHolder) holder).color3);
        } else {
            ((HotUserViewHolder) holder).constraintLayout.setBackgroundColor(((HotUserViewHolder) holder).color4);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tietIDorEmail = view2.findViewById(R.id.IDorEmail);
                tietIDorEmail.setText(loginUserList.get(position).getUserID());
                TextView tietPassword = view2.findViewById(R.id.password);
                tietPassword.setText(loginUserList.get(position).getUserPassword());
                Switch sw = view2.findViewById(R.id.sw);
                sw.setChecked(true);
                Snackbar.make(view, "c8 c8 c8 " + loginUserList.get(position).getUserName(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return loginUserList.size();
    }

}

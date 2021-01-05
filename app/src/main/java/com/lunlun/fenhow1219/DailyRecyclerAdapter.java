package com.lunlun.fenhow1219;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DailyRecyclerAdapter extends RecyclerView.Adapter<DailyViewHolder> {
    private Context context;
    public HomeFragment homeFragment;

    public List<Daily> lists;
    private final String TAG = DailyRecyclerAdapter.class.getSimpleName();

    public DailyRecyclerAdapter(Context context2, List<Daily> lists2) {
        this.context = context2;
        this.lists = lists2;
    }

    public DailyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DailyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.list_daily, parent, false));
    }

    public void onBindViewHolder(final DailyViewHolder holder, final int position) {
        int i;
        holder.mWorkOn1.setText(this.lists.get(position).on_time1);
        holder.mWorkOff1.setText(this.lists.get(position).off_time1);
        holder.mWorkOn2.setText(this.lists.get(position).on_time2);
        holder.mWorkOff2.setText(this.lists.get(position).off_time2);
        holder.mWorkDate.setText(MyDateUtils.DayNCKUH.Date2Str("yyyy-MM-dd", this.lists.get(position).work_date));
        holder.mWorkMonth.setText(MyDateUtils.DayNCKUH.ChineseMonth(MyDateUtils.DayNCKUH.Date2Str("MM", this.lists.get(position).work_date)));
        holder.mWorkDay.setText(MyDateUtils.DayNCKUH.Date2Str("dd", this.lists.get(position).work_date));
        holder.mWorkDayName.setText(this.lists.get(position).day_name.trim());
        holder.mWeekday.setText(MyDateUtils.DayNCKUH.ChineseWeekday(this.lists.get(position).weekday.toString()));
        holder.mRange.setText(this.lists.get(position).begin_time + "--" + this.lists.get(position).end_time);
        holder.mClassNo.setText(this.lists.get(position).class_no);
        holder.mDutyGroup.setText(this.lists.get(position).duty_group);
        if (!holder.mWorkOn1.getText().equals("") || !holder.mWorkOff1.getText().equals("")) {
            holder.mLinearLayoutSection1.setVisibility(View.GONE);
        } else {
            holder.mLinearLayoutSection1.setVisibility(View.VISIBLE);
        }
        if (!holder.mWorkOn2.getText().equals("") || !holder.mWorkOff2.getText().equals("")) {
            holder.mLinearLayoutSection2.setVisibility(View.GONE);
        } else {
            holder.mLinearLayoutSection2.setVisibility(View.VISIBLE);
        }
        if (!holder.mWorkOn1.getText().equals("") || !holder.mWorkOff1.getText().equals("") || !holder.mWorkOn2.getText().equals("") || !holder.mWorkOff2.getText().equals("")) {
            holder.mImageViewShowDetail.setVisibility(View.GONE);
        } else {
            holder.mImageViewShowDetail.setVisibility(View.VISIBLE);
        }
        if (this.lists.get(position).weekday.intValue() == 1 || this.lists.get(position).weekday.intValue() == 7 || !this.lists.get(position).day_name.equals("")) {
//            holder.mWorkDay.setTextColor(SupportMenu.CATEGORY_MASK);
//            holder.mWeekday.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
//            holder.mWorkDay.setTextColor(HomeFragmen.getResources().getColor(R.color.colorAccent));
//            holder.mWeekday.setTextColor(DailysFragment.this.getResources().getColor(R.color.colorGrey700));
        }
        TextView textView = holder.mWorkDayName;
        if (this.lists.get(position).day_name.equals("")) {
            i = 8;
        } else {
            i = 0;
        }
        textView.setVisibility(i);
//        DailysFragment.this.initRegRecyclerView(holder.mRecyclerViewDailyReg, this.lists.get(position).regs);
        holder.mRecyclerViewDailyReg.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if (holder.mRecyclerViewDailyReg.getVisibility() != View.GONE || ((Daily) DailyRecyclerAdapter.this.lists.get(position)).regs.size() <= 0) {
//                    holder.mRecyclerViewDailyReg.setVisibility(View.GONE);
//                } else {
//                    holder.mRecyclerViewDailyReg.setVisibility(View.VISIBLE);
//                }
            }
        });
        if (position % 2 == 0) {
            holder.itemView.setBackgroundResource(R.color.list_odd_color);
        } else {
            holder.itemView.setBackgroundResource(R.color.list_even_color);
        }
//        if (DailysFragment.this.expandPos >= 0 && position == DailysFragment.this.expandPos) {
//            holder.mRecyclerViewDailyReg.setVisibility(View.VISIBLE);
//        }
    }

    public int getItemCount() {
        return this.lists.size();
    }
}


class DailyViewHolder extends RecyclerView.ViewHolder {
    TextView mClassNo;
    TextView mDutyGroup;
    ImageView mImageViewShowDetail;
    View mLinearLayoutSection1;
    View mLinearLayoutSection2;
    TextView mRange;
    RecyclerView mRecyclerViewDailyReg;
    TextView mWeekday;
    TextView mWorkDate;
    TextView mWorkDay;
    TextView mWorkDayName;
    TextView mWorkMonth;
    TextView mWorkOff1;
    TextView mWorkOff2;
    TextView mWorkOn1;
    TextView mWorkOn2;

    public DailyViewHolder(View itemView) {
        super(itemView);
        this.mWorkDate = (TextView) itemView.findViewById(R.id.textViewDailyWorkDate);
        this.mWorkMonth = (TextView) itemView.findViewById(R.id.textViewDailyWorkMonth);
        this.mWorkDay = (TextView) itemView.findViewById(R.id.textViewDailyWorkDay);
        this.mWorkDayName = (TextView) itemView.findViewById(R.id.textViewDailyWorkDayName);
        this.mWeekday = (TextView) itemView.findViewById(R.id.textViewDailyWorkWeekday);
        this.mWorkOn1 = (TextView) itemView.findViewById(R.id.textViewDailyWorkOn1);
        this.mWorkOff1 = (TextView) itemView.findViewById(R.id.textViewDailyWorkOff1);
        this.mWorkOn2 = (TextView) itemView.findViewById(R.id.textViewDailyWorkOn2);
        this.mWorkOff2 = (TextView) itemView.findViewById(R.id.textViewDailyWorkOff2);
        this.mClassNo = (TextView) itemView.findViewById(R.id.textViewDailyShift);
        this.mDutyGroup = (TextView) itemView.findViewById(R.id.textViewDailyDutyGroup);
        this.mRecyclerViewDailyReg = (RecyclerView) itemView.findViewById(R.id.recyclerViewDailyReg);
        this.mLinearLayoutSection1 = itemView.findViewById(R.id.LinearLayoutSection1);
        this.mLinearLayoutSection2 = itemView.findViewById(R.id.LinearLayoutSection2);
        this.mImageViewShowDetail = (ImageView) itemView.findViewById(R.id.imageViewShowDetail);
        this.mRange = (TextView) itemView.findViewById(R.id.textViewDailyRange);
    }
}

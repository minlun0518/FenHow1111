package com.lunlun.fenhow1219;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailysFragment extends Fragment {

    public Boolean loading = false;
    public List<Daily> mLists = new ArrayList();
    public RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public SharedPreferences mSpref;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout mSwipeRefreshLayout;
    /* access modifiers changed from: private */
    public View mView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_daily, container, false);

        initRecyclerView();
        return this.mView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initRecyclerView() {
        this.mRecyclerView = (RecyclerView) this.mView.findViewById(R.id.recyclerViewDailys);
        this.mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.mRecyclerView.setAdapter(new DailyRecyclerAdapter(getActivity(), this.mLists));
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                int totalCount = layoutManager.getItemCount();
//                int lastIndex = layoutManager.findLastVisibleItemPosition();
//                if (!DailysFragment.this.loading.booleanValue() && totalCount == lastIndex + 1) {
//                    DailysFragment.this.loadMore(MyDateUtils.DayNCKUH.DateAdd(((Daily) DailysFragment.this.mLists.get(lastIndex)).work_date, -1));
//                }
            }
        });
    }

    public class DailyRecyclerAdapter extends RecyclerView.Adapter<DailyViewHolder> {
        private Context context;

        public List<Daily> lists;

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
                holder.mWorkDay.setTextColor(SupportMenu.CATEGORY_MASK);
                holder.mWeekday.setTextColor(SupportMenu.CATEGORY_MASK);
            } else {
                holder.mWorkDay.setTextColor(DailysFragment.this.getResources().getColor(R.color.colorAccent));
                holder.mWeekday.setTextColor(DailysFragment.this.getResources().getColor(R.color.colorGrey700));
            }
            TextView textView = holder.mWorkDayName;
            if (this.lists.get(position).day_name.equals("")) {
                i = 8;
            } else {
                i = 0;
            }
            textView.setVisibility(i);
            DailysFragment.this.initRegRecyclerView(holder.mRecyclerViewDailyReg, this.lists.get(position).regs);
            holder.mRecyclerViewDailyReg.setVisibility(View.GONE);

            if (position % 2 == 0) {
                holder.itemView.setBackgroundResource(R.color.list_odd_color);
            } else {
                holder.itemView.setBackgroundResource(R.color.list_even_color);
            }

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

    class Daily {
        String begin_time;
        String class_no;
        String day_name;
        String duty_group;
        String end_time;
        String off_time1;
        String off_time2;
        String on_time1;
        String on_time2;
        List<Reg> regs;
        Integer weekday;
        Date work_date;

        public Daily() {
        }
    }

    public void initRegRecyclerView(RecyclerView _regRecyclerView, List<Reg> _list) {
        _regRecyclerView.setHasFixedSize(true);
        _regRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        _regRecyclerView.setItemAnimator(new DefaultItemAnimator());
        _regRecyclerView.setAdapter(new RegRecyclerAdapter(getActivity(), _list));
    }

    /* renamed from: tw.com.eliot.ehr.fragment.DailysFragment$RegRecyclerAdapter */
    public class RegRecyclerAdapter extends RecyclerView.Adapter<RegViewHolder> {
        private Context context;
        private List<Reg> lists;


        public RegRecyclerAdapter(Context context2, List<Reg> lists2) {
            this.context = context2;
            this.lists = lists2;
        }

        public RegViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RegViewHolder(LayoutInflater.from(this.context).inflate(R.layout.list_daily_regs, parent, false));
        }

        public void onBindViewHolder(RegViewHolder holder, int position) {
            holder.mSection.setImageResource(this.lists.get(position).section.equals("1") ? R.drawable.icon_section1 : R.drawable.icon_section2);
            holder.mType.setText(this.lists.get(position).type.equals("A") ? "上班" : this.lists.get(position).type.equals("B") ? "下班" : "");
            holder.mRegDateTime.setText(MyDateUtils.DayNCKUH.Date2Str("HH:mm:ss", this.lists.get(position).reg_datetime));
            holder.mIp.setText(this.lists.get(position).f126ip);
            holder.mSource.setText(this.lists.get(position).source);
            if (this.lists.get(position).type.equals("A")) {
                int _on_color = DailysFragment.this.getResources().getColor(R.color.signin);
                holder.mType.setTextColor(_on_color);
                holder.mRegDateTime.setTextColor(_on_color);
                holder.mIp.setTextColor(_on_color);
                holder.mSource.setTextColor(_on_color);
            } else if (this.lists.get(position).type.equals("B")) {
                int _out_color = DailysFragment.this.getResources().getColor(R.color.signout);
                holder.mType.setTextColor(_out_color);
                holder.mRegDateTime.setTextColor(_out_color);
                holder.mIp.setTextColor(_out_color);
                holder.mSource.setTextColor(_out_color);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                }
            });
        }

        public int getItemCount() {
            return this.lists.size();
        }
    }

    /* renamed from: tw.com.eliot.ehr.fragment.DailysFragment$RegViewHolder */
    class RegViewHolder extends RecyclerView.ViewHolder {
        TextView mIp;
        TextView mRegDateTime;
        ImageView mSection;
        TextView mSource;
        TextView mType;

        public RegViewHolder(View itemView) {
            super(itemView);
            this.mSection = (ImageView) itemView.findViewById(R.id.imageViewRegSection);
            this.mType = (TextView) itemView.findViewById(R.id.textViewRegType);
            this.mRegDateTime = (TextView) itemView.findViewById(R.id.textViewRegDateTime);
            this.mIp = (TextView) itemView.findViewById(R.id.textViewRegIP);
            this.mSource = (TextView) itemView.findViewById(R.id.textViewRegSource);
        }
    }

    static class Reg {

        String f126ip;
        Date reg_datetime;
        String section;
        String source;
        String type;

        public Reg(String _section, String _type, Date _reg_datetime, String _ip, String _source) {
            this.section = _section;
            this.type = _type;
            this.reg_datetime = _reg_datetime;
            this.f126ip = _ip;
            this.source = _source;
        }
    }

}
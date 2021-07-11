package com.lunlun.fenhow1219;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private View root;
    private boolean logon = false;
    private TextView home_name_textView;
    private TextView home_date_textView;
    private RecyclerView noterecyclerView;
    private static final int REQUEST_CODE_LOGIN = 101;

    private TextView textView;
    private Button clickbutton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        findlist();
        findView();
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void findView() {
        root.findViewById(R.id.seeAlltextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AppMenuActivity.class));
            }
        });

        home_name_textView = root.findViewById(R.id.home_name_TextView);
        home_name_textView.setText(setGreetings() + " 測試中   Welcome Back");
        home_date_textView = root.findViewById(R.id.home_date_TextView);
        home_date_textView.setText(MyDateUtils.formatDefaultWithDayOfWeek(Calendar.getInstance()));
        clickbutton = root.findViewById(R.id.clickbutton);
    }

    public String setGreetings() {
        int parseInt = Integer.parseInt(new SimpleDateFormat("HHmm").format(new Date()));
        if (parseInt - 400 < 0 || 1100 - parseInt <= 0) {
            return (parseInt + -1100 < 0 || 1700 - parseInt <= 0) ? "晚安" : "午安";
        }
        return "早安";
    }

    public void findlist() {
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        List<ApplicationItem> applicationItemList = new ArrayList<>();
        applicationItemList.add(new ApplicationItem(1, "打卡出勤系統", R.drawable.icon_immigration, "點我打卡", R.layout.list_daily));
        applicationItemList.add(new ApplicationItem(2, "員工排班系統", R.drawable.icon_app_calendar, "點我排班", R.layout.test));
        applicationItemList.add(new ApplicationItem(3, "會議室簽到系統", R.drawable.icon_app_conversation, "點我簽到", R.layout.test2));
        applicationItemList.add(new ApplicationItem(4, "教學評量系統", R.drawable.icon_app_checklist, "點我評量", R.layout.test));
        applicationItemList.add(new ApplicationItem(5, "採檢及生理量測系統", R.drawable.icon_app_blood_sample, "點我進入", R.layout.test));
        recyclerView.setAdapter(new ApplicationItemAdapter(getActivity(), applicationItemList));

        noterecyclerView = root.findViewById(R.id.notionrecyclerView);
        noterecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "教學評量進度", R.drawable.icon_dr, "12月學習日誌未完成"));
        taskList.add(new Task(2, "明日班表", R.drawable.icon_mon_card, "大夜班"));
        taskList.add(new Task(3, "今日會議", R.drawable.icon_app_meeting, "吃便當"));
        noterecyclerView.setAdapter(new TaskAdapter(getActivity(), taskList));
    }

}

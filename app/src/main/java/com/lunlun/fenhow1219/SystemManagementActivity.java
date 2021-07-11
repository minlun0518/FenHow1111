package com.lunlun.fenhow1219;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class SystemManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_management_activity);
        setSupportActionBar(findViewById(R.id.toolbar));
//        SystemManagementActivity.this.setTitle("權限管理系統");
        FragmentManager fm = SystemManagementActivity.this.getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.LinearLayoutFragment, new SystemManagementFragment(), "fragment_sign_work").commit();


    }

}
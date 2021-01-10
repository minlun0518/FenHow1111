package com.lunlun.fenhow1219;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;
import com.lunlun.fenhow1219.AppMenuFragment;

public class AppMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    public View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.app_menu_activity);

        this.mView = LayoutInflater.from(this).inflate(R.layout.app_menu_activity, (ViewGroup) null);
        setContentView(this.mView);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.LinearLayoutFragment, new AppMenuFragment(), "fragment_sign_work").commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_menu_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.app_menu_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.app_menu_nav_view);
        navigationView.setItemIconTintList((ColorStateList)null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_explanation:
//                startActivity(new Intent(this, ExplanationActivity.class));
                return true;
            default:
                return true;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ((DrawerLayout) findViewById(R.id.app_menu_drawer_layout)).closeDrawer(Gravity.LEFT);
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        switch (id) {
            case R.id.nav_lab:
                startActivity(new Intent(this, WelcomeActivity.class));
                return true;
            case R.id.nav_critical_value:
                fm.beginTransaction().replace(R.id.LinearLayoutFragment, new HomeFragment(), "fragment_home").commit();
                return true;
            case R.id.nav_meeting:
                startActivity(new Intent(this, MeetingActivity.class));
                return true;
            case R.id.nav_SignWork:
                startActivity(new Intent(this, SignWorkActivity.class));
                return true;
            case R.id.nav_staff_scheduling:
                fm.beginTransaction().replace(R.id.LinearLayoutFragment, new Account_Fragment(), "fragment_account").commit();
                return true;
            case R.id.nav_pgy:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.nav_environmental_inspection:
                fm.beginTransaction().replace(R.id.LinearLayoutFragment, new HomeFragment(), "fragment_app").commit();
            default:
                return true;
        }
    }
}
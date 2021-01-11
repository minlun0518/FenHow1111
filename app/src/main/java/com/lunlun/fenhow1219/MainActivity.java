package com.lunlun.fenhow1219;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    public boolean logon =false;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE = 101;
    private TextView mTVWcode;
    private TextView mTVUserName;
    private TextView mTVUserWorkDepartmentName;
    private TextView mTVUserPosName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_app_menu,R.id.nav_account,R.id.nav_setting)
                .setDrawerLayout(drawer)
                .build();

        navigationView.setItemIconTintList((ColorStateList)null);
        navigationView.setNavigationItemSelectedListener(this);

        this.mTVWcode = (TextView) findViewById(R.id.tvWcode);
        this.mTVUserName = (TextView) findViewById(R.id.tvUserName);
        this. mTVUserWorkDepartmentName = (TextView) findViewById(R.id.tvWorkDepartmentName);
        this.mTVUserPosName = (TextView) findViewById(R.id.tvUserPosName);

    }

//    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_explanation:
//                startActivity(new Intent(this, ExplanationActivity.class));
                return true;
            case R.id.action_system_management:
                startActivity(new Intent(this, SystemManagementActivity.class));
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(Gravity.LEFT);
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        switch (id) {
            case R.id.nav_login:
                startActivity(new Intent(this, WelcomeActivity.class));
                return true;
            case R.id.nav_home:
                fm.beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment(), "fragment_home").commit();
                return true;
            case R.id.nav_account:
                fm.beginTransaction().replace(R.id.nav_host_fragment, new Account_Fragment(), "fragment_account").commit();
                return true;
            case R.id.nav_setting:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.nav_app_menu:
                startActivity(new Intent(this, AppMenuActivity.class));
                return true;
            default:
                return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                logon=true;
                String user_name = getSharedPreferences("login", MODE_PRIVATE).getString("wname", "低否");
//                TextView nav_head_tv_name =findViewById(R.id.nav_head_tv_name);
                Log.d("RESULT",user_name);
//                nav_head_tv_name.setText(user_name);

                InputStream mInputStream = getResources().openRawResource(R.raw.mydate);
                BufferedReader mBufferedReader = new BufferedReader(new InputStreamReader(mInputStream));
                String mdata;

                StringBuilder mStringBuilder = new StringBuilder();
                try {
                    while ((mdata = mBufferedReader.readLine()) != null) {
                        mStringBuilder.append(mdata);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JSONObject mjsonObject ;
                try {
                    mjsonObject = new JSONObject(mStringBuilder.toString());
                    this.mTVWcode.setText(mjsonObject.getString("wcode"));
                    this.mTVUserName.setText(mjsonObject.getString("wname"));
                    this.mTVUserWorkDepartmentName.setText(mjsonObject.getString("work_dept_name"));
                    this.mTVUserPosName.setText(mjsonObject.getString("pos_name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else {
                Toast.makeText(this, "再見", Toast.LENGTH_LONG).show();
            }
        }
    }
}
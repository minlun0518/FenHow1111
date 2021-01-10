package com.lunlun.fenhow1219;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class SignWorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_work_activity);
        setSupportActionBar(findViewById(R.id.toolbar));
        FragmentManager fm = SignWorkActivity.this.getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.LinearLayoutFragment, new SignWorkFragment(), "fragment_sign_work").commit();
    }

}
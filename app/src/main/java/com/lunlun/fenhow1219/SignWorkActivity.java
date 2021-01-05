package com.lunlun.fenhow1219;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class SignWorkActivity extends AppCompatActivity {

    public TextView mTextViewLoadingInfo;
//    UpdateManager mUpdateManager = new UpdateManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_work);
//        MySingleton.getInstance(this);

//        setSupportActionBar(findViewById(R.id.toolbar));

        initView();
//        postCreate();

//        SignWorkActivity.this.mTextViewLoadingInfo.setVisibility(View.GONE);
        FragmentManager fm = SignWorkActivity.this.getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.LinearLayoutFragment, new SignWorkFragment(), "fragment_sign_work").commit();
    }

    private void initView() {
        this.mTextViewLoadingInfo = (TextView) findViewById(R.id.textViewLoadingInfo);
    }

    private void postCreate() {
//        new AsyncTaskVersionCheck().execute(new String[0]);
    }

    /* renamed from: tw.com.eliot.ehr.activity.SignWorkActivity$AsyncTaskVersionCheck */
    private class AsyncTaskVersionCheck extends AsyncTask<String, Void, Void> {
        private int _hasNewVersion;

        private AsyncTaskVersionCheck() {
            this._hasNewVersion = 0;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            SignWorkActivity.this.mTextViewLoadingInfo.setText("程式版本檢查中.....");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void o) {
            super.onPostExecute(o);
//            if (this._hasNewVersion == 2) {
//                SignWorkActivity.this.startActivity(new Intent(SignWorkActivity.this, HomeActivity.class));
                SignWorkActivity.this.finish();
//            } else if (MySingleton.getInstance(SignWorkActivity.this.getApplication()).getSharedPreferences().getString("wcode", "").equals("")) {
//                SignWorkActivity.this.startActivity(new Intent(SignWorkActivity.this, HomeActivity.class));
//                SignWorkActivity.this.finish();
//            } else {
//                new AsyncTaskLogin().execute(new Object[0]);
//            }
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(String... params) {
            try {
//                this._hasNewVersion = SignWorkActivity.this.mUpdateManager.checkUpdateInfo();
                return null;
            } catch (Exception e) {
                return null;
            }
        }
    }

    /* renamed from: tw.com.eliot.ehr.activity.SignWorkActivity$AsyncTaskLogin */
    private class AsyncTaskLogin extends AsyncTask {
        private Exception exceptionToBeThrow;
        private String mErrorMessage;

        private AsyncTaskLogin() {
            this.mErrorMessage = "";
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            SignWorkActivity.this.mTextViewLoadingInfo.setText("身份驗證中.....");
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] params) {
//            try {
//                SharedPreferences spref = MySingleton.getInstance(SignWorkActivity.this.getApplicationContext()).getSharedPreferences();
//                String _wcode = spref.getString("wcode", "");
//                String _password = spref.getString(EmailAuthProvider.PROVIDER_ID, "");
//                String _mobileid = spref.getString("mobileid", "");
//                String _token = spref.getString("token", "");
//                String _token_id = spref.getString("token_id", "");
//                Long _token_create_time = Long.valueOf(spref.getLong("token_create_time", 0));
//                String _employeeStatus = "";
//                JSONObject _jsonObject = null;
//                if (_wcode == "") {
//                    _employeeStatus = "new";
//                } else {
//                    try {
//                        Response response = new OkHttpClient.Builder().connectionPool(MySingleton.pool).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(SignWorkActivity.this.getString(C1787R.string.site_address) + "Mobile/Login").post(new FormBody.Builder().add("wcode", _wcode).add(EmailAuthProvider.PROVIDER_ID, _password).add("mobileid", _mobileid).add("token", _token).add("token_id", _token_id).add("token_create_time", _token_create_time.toString()).build()).build()).execute();
//                        if (response.code() == 200) {
//                            ((Ehr) SignWorkActivity.this.getApplicationContext()).identityConfirmOk = true;
//                            _jsonObject = new JSONObject(response.body().string());
//                        }
//                    } catch (UnknownHostException e) {
//                        this.mErrorMessage = "網路錯誤!!! 請確認是否連接網路!!!";
//                        return null;
//                    } catch (Exception e2) {
//                        this.mErrorMessage = "身分驗證錯誤!!!";
//                        return null;
//                    }
//                }
//                if (((Ehr) SignWorkActivity.this.getApplicationContext()).identityConfirmOk) {
//                    try {
//                        ((Ehr) SignWorkActivity.this.getApplicationContext()).employeeKind = _jsonObject.optString("employee_kind");
//                        SharedPreferences.Editor editor = spref.edit();
//                        editor.putString("wcode", _jsonObject.optString("wcode"));
//                        editor.putString("wname", _jsonObject.optString("wname"));
//                        editor.putString("mobileid", _jsonObject.optString("mobileid"));
//                        editor.putString("dept_code", _jsonObject.optString("dept_code"));
//                        editor.putString("dept_name", _jsonObject.optString("dept_name"));
//                        editor.putString("work_dept_code", _jsonObject.optString("work_dept_code"));
//                        editor.putString("work_dept_name", _jsonObject.optString("work_dept_name"));
//                        editor.putString("pos_code", _jsonObject.optString("pos_code"));
//                        editor.putString("pos_name", _jsonObject.optString("pos_name"));
//                        editor.putLong("reg_time", _jsonObject.optLong("reg_time"));
//                        editor.putLong("start_time", _jsonObject.optLong("start_time"));
//                        editor.putBoolean("allow_mobile_signin", _jsonObject.optBoolean("allow_mobile_signin"));
//                        editor.putString("employee_kind", _jsonObject.optString("employee_kind"));
//                        editor.putString("pwd_expired_date", _jsonObject.optString("pwd_expired_date"));
//                        editor.putInt("pwd_expired_days", _jsonObject.optInt("pwd_expired_days"));
//                        if (_jsonObject.optBoolean("save_token")) {
//                            editor.putString("token", "");
//                            editor.putString("token_id", "");
//                            editor.putLong("token_create_time", 0);
//                        }
//                        editor.putLong("user_identity_time", new GregorianCalendar().getTimeInMillis());
//                        editor.commit();
//                    } catch (Exception ex) {
//                        this.exceptionToBeThrow = ex;
//                        _employeeStatus = "employee_data_error";
//                    }
//                }
//                if (_employeeStatus != "") {
//                    Bundle _bundle = new Bundle();
//                    _bundle.putString("user_status", _employeeStatus);
//                    Intent intent = new Intent(SignWorkActivity.this, LoginActivity.class);
//                    intent.putExtras(_bundle);
//                    SignWorkActivity.this.startActivity(intent);
//                    SignWorkActivity.this.finish();
//                }
//            } catch (Exception ex2) {
//                this.exceptionToBeThrow = ex2;
//            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object o) {
            super.onPostExecute(o);
//            if (this.exceptionToBeThrow != null) {
//                this.mErrorMessage = "系統初始化失敗!!! 請關閉程式，重新啟動";
//                SignWorkActivity.this.mTextViewLoadingInfo.setText(this.mErrorMessage);
//                SignWorkActivity.this.mTextViewLoadingInfo.setTextColor(Color.rgb(255, 0, 0));
//                return;
//            }
//            SignWorkActivity.this.mTextViewLoadingInfo.setVisibility(View.GONE);
//            FragmentManager fm = SignWorkActivity.this.getSupportFragmentManager();
//            if (fm.getFragments() == null) {
//                fm.beginTransaction().replace(R.id.LinearLayoutFragment, new SignWorkFragment(), "fragment_sign_work").commit();
//            }
        }
    }
}
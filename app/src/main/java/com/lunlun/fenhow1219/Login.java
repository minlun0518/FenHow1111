package com.lunlun.fenhow1219;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Login extends AppCompatActivity {
    private BiometricPromptManager mManager;
    private static final String TAG = Login.class.getSimpleName();
    private static final int REQUEST_CODE = 101;

    boolean isMail = false;
    private Switch sw;

    private String IMEINumber;
    private TextView imei;
    private TextInputEditText textInputEditTextIDorEmail, textInputEditTextPassword;
    private Button buttonLogin;
    private TextView textViewSignUp;
    private ProgressBar progressBar;
    private TextInputLayout textInputLayout;
    private String userInput;
    private String password;
    private CheckBox rememberme_checkBox;
    private ImageView touchID;
    private ImageView faceID;
    private AlertDialog.Builder builder;
    private AlertDialog.Builder builder2;

    private String[] mimeiTestList;
    private int mdeviceCont;
    private boolean[] mImeiTestCheck;
    private List<Imei> imeiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        findViews();
        handleSSLHandshake();
        checkDivicd();
    }

    //辨識裝置
    private void checkDivicd() {
        getImei();
        Log.d(TAG, "DeviceManage : " + IMEINumber);
        if (!IMEINumber.equals("")) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[1];
                    field[0] = "imei_p";
                    String[] data = new String[1];
                    data[0] = IMEINumber;
                    PutData putData = new PutData(getString(R.string.imeiPublic_php), "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Get IMEI Public Success")) {
                                isPublicDevice();
                            } else {
                                isPriviteDevice();
                                imei.setText(IMEINumber);
                            }
                        }
                    }
                }
            });
        }
    }

    //確認身分
    public void runrun(String userType, String urlType) {
        userInput = String.valueOf(textInputEditTextIDorEmail.getText());
        password = String.valueOf(textInputEditTextPassword.getText());

        if (!userInput.equals("") && !password.equals("")) {
            progressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[2];
                    field[0] = userType;
                    field[1] = "password";
                    String[] data = new String[2];
                    data[0] = userInput;
                    data[1] = password;
                    PutData putData = new PutData(urlType, "POST", field, data);
                    if (putData.startPut()) {
                        Log.d(TAG, "userType: " + userType + " / " + userInput);
                        Log.d(TAG, "urlType: " + urlType);
                        Log.d(TAG, "password: " + password);
                        if (putData.onComplete()) {
                            progressBar.setVisibility(View.GONE);
                            String result = putData.getResult();
                            Log.d(TAG, "result: " + result);
                            if (result.equals("Login Success")) {
                                Toast.makeText(getApplicationContext(), "登入成功", Toast.LENGTH_LONG).show();
                                getIntent().putExtra("USER_NAME", textInputEditTextIDorEmail.getText().toString());
                                setResult(Activity.RESULT_OK, getIntent());
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "帳號或密碼錯誤", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "資料不得為空", Toast.LENGTH_LONG).show();
        }
    }

    //私人設備數量
    public void checkImeiCont() {
        InputStream in = getResources().openRawResource(R.raw.imei_private);
        BufferedReader rd = new BufferedReader(new InputStreamReader(in));
        String line;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mdeviceCont = 0;
        mimeiTestList = new String[3];
        imeiList = new ArrayList<>();

        try {
            JSONArray mJsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < mJsonArray.length(); i++) {
                JSONObject jsonObject = mJsonArray.getJSONObject(i);
                String aIMEI = jsonObject.getString("IMEI");
                int aEMPLOYEE_id = jsonObject.getInt("EMPLOYEE_ID");
                String aDEVICE_name = jsonObject.getString("DEVICE_NAME");
                boolean aISUSING = jsonObject.getBoolean("ISUSING");
                imeiList.add(new Imei(aIMEI, aEMPLOYEE_id, aDEVICE_name, aISUSING));
                Log.d(TAG, "第" + i + "筆資料的 IMEI=" + aIMEI + "，EMPLOYEE_id= " + aEMPLOYEE_id + "，DEVICE_name=" + aDEVICE_name + "，ISUSING = " + aISUSING);
                if (aISUSING == true) {
                    mdeviceCont++;
                    mimeiTestList[i] = "裝置名稱: " + aDEVICE_name + " (序號為: " + aIMEI + " )";
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (mdeviceCont > 2) {
            builder = new AlertDialog.Builder(Login.this);
            builder.setIcon(R.drawable.icon_stop);
            builder.setTitle("您已綁定三個裝置,請選擇刪除裝置");
            mImeiTestCheck = new boolean[mimeiTestList.length];
            builder.setMultiChoiceItems(mimeiTestList, mImeiTestCheck, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                    mImeiTestCheck[i] = isChecked;
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(Login.this, "刪除成功", Toast.LENGTH_SHORT).show();
                    // !!這裡要補資料庫刪除IMEI
                    //UPDATE IMEI_PRIVATE SET ISUSING = fals WHERE IMEI = selectaIMEI;
                    // !!將本次imei加入資料庫
                    if (imeiList.contains(IMEINumber)) {
                        //UPDATE IMEI_PRIVATE SET ISUSING = true WHERE IMEI = selectaIMEI;
                    } else {
                        //INSERT INTO IMEI_PRIVATE VALUES (IMEI,employeeID,null,true);
                    }
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }else {
            //INSERT INTO IMEI_PRIVATE VALUES (IMEI,employeeID,null,true);
        }
    }

    private void findViews() {
        textInputEditTextIDorEmail = findViewById(R.id.IDorEmail);
        textInputEditTextPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progress);
        textInputLayout = findViewById(R.id.textInputLayoutEmployeeId);
        textInputEditTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        //切換登入方式
        sw = findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isMail = isChecked;
                textInputLayout.setHint(isMail ? getString(R.string.employeeid) : getString(R.string.email));
            }
        });

        CheckBox cb = findViewById(R.id.seepw);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //顯示密碼
                    textInputEditTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //隱藏密碼
                    textInputEditTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        //記住我
        rememberme_checkBox = findViewById(R.id.rememberme);

        //點此註冊
        textViewSignUp = findViewById(R.id.signUpText);
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
                finish();
            }
        });

        //登入
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMail) {
                    runrun("employee_id", getString(R.string.idlogin_php));
                    rememberme_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                SharedPreferences setting = getSharedPreferences("test", MODE_PRIVATE);
                                textInputEditTextIDorEmail.setText(setting.getString("PREF_USERID",userInput));
                            }
                        }
                    });
                } else {
                    runrun("email", getString(R.string.login_php));
                }
            }
        });

        //指紋解鎖
        touchID = findViewById(R.id.touchidimageButton);
        findViewById(R.id.touchidimageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useFingerID();
            }
        });

        //臉部辨識
        faceID = findViewById(R.id.faceidimageButton);
        findViewById(R.id.faceidimageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "目前不支援臉部辨識", Snackbar.LENGTH_LONG).show();
            }
        });

        //選擇題
        builder2 = new AlertDialog.Builder(Login.this);
        builder2.setIcon(R.drawable.icon_stop);
        builder2.setTitle("請選擇登入裝置");
        builder2.setNegativeButton("公務機", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                isPublicDevice();
                dialogInterface.dismiss();
            }
        });
        builder2.setPositiveButton("私人手機", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                isPriviteDevice();
                dialogInterface.dismiss();
            }
        });
        builder2.create().show();
    }

    public void isPublicDevice() {
        imei.setVisibility(View.INVISIBLE);
        showLoginUserCard();
        touchID.setVisibility(View.INVISIBLE);
        faceID.setVisibility(View.INVISIBLE);
    }

    public void isPriviteDevice() {
        try {
            mManager = BiometricPromptManager.from(Login.this);
        } catch (Exception e) {
            Log.d(TAG, "BiometricPromptManager : " + e);
        }
        checkImeiCont();
    }

    public void useFingerID() {
        if (mManager.isBiometricPromptEnable()) {
            mManager.authenticate(new BiometricPromptManager.OnBiometricIdentifyCallback() {
                @Override
                public void onUsePassword() {
                    Toast.makeText(Login.this, "使用密碼", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSucceeded() {
                    verifiedsuccessfully();
                    Toast.makeText(Login.this, "辨識成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailed() {
                    Toast.makeText(Login.this, "辨識失敗", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(int code, String reason) {
                    Toast.makeText(Login.this, "錯誤", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel() {
                    Toast.makeText(Login.this, "取消辨識", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //最近登入
    public void showLoginUserCard() {
        List<LoginUserList> loginUserList = new ArrayList<>();
        loginUserList.add(new LoginUserList(1, "45478", null, "史奴比", "5665443"));
        loginUserList.add(new LoginUserList(2, "59487", null, "LanLan", "4267654"));
        loginUserList.add(new LoginUserList(3, "94520", null, "好想兔", "4654756"));
        loginUserList.add(new LoginUserList(4, "00708", null, "查理", "5478769"));
        loginUserList.add(new LoginUserList(5, "56720", null, "派大星", "658484"));

        LinearLayoutManager manager = new LinearLayoutManager(Login.this);
        RecyclerView recyclerView = findViewById(R.id.cycleViewHotUser);
        recyclerView.setLayoutManager(manager);
        LoginUserListAdapter listAdapter = new LoginUserListAdapter(this, loginUserList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildPosition(view) != (loginUserList.size() - 1)) {
                    outRect.bottom = -330;
                }
            }
        });
    }

    //忽略https的證書校驗
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

    //取得imei
    public void getImei() {
        imei = findViewById(R.id.ed_imei);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            IMEINumber = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            Log.d(TAG, "使用模擬器中，到IMEI = " + IMEINumber);
        } else {
            final TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephony.getDeviceId() != null) {
                IMEINumber = mTelephony.getDeviceId();
                Log.d(TAG, "mTelephony.getDeviceId()" + IMEINumber);
            } else {
                IMEINumber = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                Log.d(TAG, "Settings.Secure.ANDROID_ID" + IMEINumber + " / ");
            }
        }
    }

    public void verifiedsuccessfully() {
//        getIntent().putExtra("LOGIN_IMEI",imei.toString());
        getIntent().putExtra("LOGIN_IMEI", IMEINumber);
//        getIntent().putExtra("LOGIN_ID",textInputEditTextIDorEmail.toString());
        getIntent().putExtra("LOGIN_ID", "A0708");
        setResult(RESULT_OK, getIntent());
//        startActivity(MainActivity);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
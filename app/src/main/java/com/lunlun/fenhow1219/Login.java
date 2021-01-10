package com.lunlun.fenhow1219;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Login extends AppCompatActivity {
    private BiometricPromptManager mManager;
    private static final String TAG = Log.class.getSimpleName();
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
    private boolean rememberme_checkBox_statue;
    private String userInput;
    private String password;
    private CheckBox rememberme_checkBox;
    private ImageView touchID;
    private ImageView faceID;
    private LinearLayout mlinearLayout;
    private AlertDialog.Builder builder;

    private SharedPreferences spref;
    private MyDBHelper dbHelper;
    private List<Imei> imeiList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        dbHelper = new MyDBHelper(Login.this);
        dbHelper.getWritableDatabase();

        try {
            mManager = BiometricPromptManager.from(this);
        } catch (Exception e) {
            Log.d(TAG, "mManager = BiometricPromptManager.from(this);" + e);
        }

        handleSSLHandshake();
        findViews();
        getImei();
        checkDivicd();
        checkImeiNum();
    }

    private void checkDivicd() {
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
                                imei.setText("最近登入");
                                touchID.setVisibility(View.INVISIBLE);
                                faceID.setVisibility(View.INVISIBLE);
                                lololo();
                            } else {
                                mlinearLayout.setVisibility(View.INVISIBLE);
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
//                                loginSuccess();
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

    public List<Imei> getall () {
        SQLiteDatabase db =dbHelper.getReadableDatabase();
        String[] cc={"id","device"};
        Cursor cursor=db.query("UserImei",cc,null,null,null,null,null);
        List<Imei> imeiList = new ArrayList <>();
        while (cursor.moveToNext()) {
            int id=cursor.getInt(0);
            String de=cursor.getString(1);
            Imei imei =new Imei(id,de);
            imeiList.add(imei);
        }
        cursor.close();
        return imeiList;
    }

    //私機數量
    public void checkImeiNum() {
        //!! 這裡要補條件 if (數量>3) {

        SQLiteDatabase db =dbHelper.getReadableDatabase();
        String[] cc={"id","device"};
        Cursor cursor=db.query("UserImei",cc,null,null,null,null,null);
        imeiList = new ArrayList <>();
        while (cursor.moveToNext()) {
            int id=cursor.getInt(0);
            String de=cursor.getString(1);
            Imei imei =new Imei(id,de);
            imeiList.add(imei);
        }
        cursor.close();

        builder = new AlertDialog.Builder(Login.this);
        builder.setIcon(R.drawable.icon_stop);
        builder.setTitle("您已綁定三個裝置,請選擇刪除裝置");
        builder.setSingleChoiceItems(R.array.device_imei_test, 0, new DialogInterface.OnClickListener() {            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                List<String> list = Arrays.asList((getResources().getStringArray(R.array.device_imei_test)));
            Toast.makeText(Login.this, list.get(i), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Login.this, "OKOK", Toast.LENGTH_SHORT).show();
                // !!這裡要補資料庫刪除IMEI
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private void findViews() {
        textInputEditTextIDorEmail = findViewById(R.id.IDorEmail);
        textInputEditTextPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progress);
        textInputLayout = findViewById(R.id.textInputLayoutEmployeeId);
        textInputEditTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mlinearLayout = (LinearLayout) findViewById(R.id.linear);

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
                // TODO Auto-generated method stub
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
//        rememberme_checkBox_statue = getSharedPreferences("test", MODE_PRIVATE).getBoolean("RREF_REMEMBER", false);
//        if (rememberme_checkBox_statue) {
//            SharedPreferences setting = getSharedPreferences("test", MODE_PRIVATE);
//            textInputEditTextIDorEmail.setText(setting.getString("PREF_USERID", ""));
//        }

        //點此註冊
        textViewSignUp = findViewById(R.id.signUpText);
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
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
                if (mManager.isBiometricPromptEnable()) {
                    mManager.authenticate(new BiometricPromptManager.OnBiometricIdentifyCallback() {
                        @Override
                        public void onUsePassword() {
                            Toast.makeText(Login.this, "使用密碼", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSucceeded() {
                            loginSuccess();
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
        });

        //臉部辨識
        faceID = findViewById(R.id.faceidimageButton);
        findViewById(R.id.faceidimageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "目前不支援臉部辨識", Snackbar.LENGTH_LONG).show();
            }
        });
    }


    //最近登入
    public void lololo() {

        List<HotUserModel> hotUserList = new ArrayList<>();
        hotUserList.add(new HotUserModel(1, "45478", null, "ChiaW", "000000"));
        hotUserList.add(new HotUserModel(2, "59487", null, "LanLan", "000000"));
        hotUserList.add(new HotUserModel(3, "94520", null, "Jolin", "000000"));
        hotUserList.add(new HotUserModel(4, "00708", null, "TomsTost", "000000"));
        hotUserList.add(new HotUserModel(5, "56720", null, "菇腦絲", "000000"));

        for (int i = 0; i < hotUserList.size(); i++) {
            View inflate2 = LayoutInflater.from(getBaseContext()).inflate(R.layout.login_item_public_device, mlinearLayout, false);
            CardView cardView = (CardView) inflate2.findViewById(R.id.cd_hot_article);
            ConstraintLayout constraintLayout = (ConstraintLayout) inflate2.findViewById(R.id.constraintLayout);
            String userName = hotUserList.get(i).getUserName();
            String userID = "員編:" + hotUserList.get(i).getUserID();
            ((TextView) inflate2.findViewById(R.id.tv_hot_article_title)).setText(userName);
            ((TextView) inflate2.findViewById(R.id.tv_hot_article_from)).setText(userID);

            int i2 = i % 4;
            if (i2 == 1) {
                constraintLayout.setBackground(getBaseContext().getDrawable(R.drawable.background_img_card_blue));
            } else if (i2 == 2) {
                constraintLayout.setBackground(getBaseContext().getDrawable(R.drawable.background_img_card_pink));
            } else if (i2 != 3) {
                constraintLayout.setBackground(getBaseContext().getDrawable(R.drawable.background_img_card_org));
            } else {
                constraintLayout.setBackground(getBaseContext().getDrawable(R.drawable.background_img_card_green));
            }
            mlinearLayout.addView(inflate2);
        }

        //要補自動帶入資料

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

    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    //確認身分後 帶資料
    private void loginSuccess() {
        setResult(RESULT_OK);
        Date rightNow = Calendar.getInstance().getTime();
        Log.d(TAG, "rememberme_checkBox_statue is :" + rememberme_checkBox_statue);

        SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
        pref.edit()
                .putString("PREF_IMEI", IMEINumber)
                .putString("PREF_USERID", "A0708")
                .putString("mobileid", IMEINumber)
                .putString("PREF_PASSWORD", "000000")
                .putString("wcode", "A0708")
                .putString("wname", "努力的孩子")
                .putLong("reg_time", dateToLong(rightNow))
                .putString("dept_code", "A")
                .putString("dept_name", "藥劑科")
                .putString("work_dept_code", "work_dept_code=A")
                .putString("work_dept_name", "work_dept_name=藥劑科")
                .putString("pos_code", "p")
                .putString("pos_name", "藥師")//職稱
                .putBoolean("allow_mobile_signin", true)
                .putString("employee_kind", "路人")
                .putString("pwd_expired_date", "2020/01/09")
                .putInt("pwd_expired_days", 800)
                .apply();
        Log.d(TAG, "settingpref is :" + rememberme_checkBox_statue + " " + IMEINumber + " " + userInput + " " + password);
        Log.d(TAG, "start_time" + dateToLong(rightNow));

//        JSONObject jsonObject = new JSONObject();
//        SharedPreferences.Editor editor = Login.this.spref.edit();
//        editor.putString("mobileid", jsonObject.optString("mobileid"));
//        editor.putString(EmailAuthProvider.PROVIDER_ID, password);
//        editor.putLong("reg_time", dateToLong(rightNow));
//        editor.putLong("start_time", dateToLong(rightNow));
//        editor.putBoolean("allow", false);

    }

    public void verifiedsuccessfully() {
//        getIntent().putExtra("LOGIN_IMEI",imei.toString());
        getIntent().putExtra("LOGIN_IMEI", IMEINumber);
//        getIntent().putExtra("LOGIN_ID",textInputEditTextIDorEmail.toString());
        getIntent().putExtra("LOGIN_ID", "A0708");
        setResult(RESULT_OK, getIntent());
//        startActivity(MainActivity);
//        MainActivity().
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
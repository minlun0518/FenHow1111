package com.lunlun.fenhow1219;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.security.BasicPermission;

public class AppPermissionActivity extends AppCompatActivity {
    private static final String TAG = AppPermissionActivity.class.getSimpleName();
    protected static final int RESULT_SPEECH = 1;
    /* access modifiers changed from: private */
    public String doctorBBCall;
    private String doctorName;
    /* access modifiers changed from: private */
    public String hospMk;
    private Button mButtonMic;
    private Button mButtonSendSMS;
    /* access modifiers changed from: private */
    public CoordinatorLayout mCoordinatorLayout;
    /* access modifiers changed from: private */
    public EditText mEditTextMessage;
    private ScrollView mScrollViewSMS;
    /* access modifiers changed from: private */
    public TextView mTextViewDefaultMsg1;
    /* access modifiers changed from: private */
    public TextView mTextViewDefaultMsg2;
    /* access modifiers changed from: private */
    public TextView mTextViewDefaultMsg3;
    /* access modifiers changed from: private */
    public TextView mTextViewDefaultMsg4;
    /* access modifiers changed from: private */
    public TextView mTextViewDefaultMsg5;
    private TextView mTextViewDoctorBBCall;
    private TextView mTextViewDoctorName;

    private NotificationManager notifManager;
    private String[] mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_permission);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            this.hospMk = b.getString("hosp_mk");
            this.doctorName = b.getString("doctor_name");
            this.doctorBBCall = b.getString("doctor_bbcall");
        }
        initView();

        this.mScrollViewSMS.scrollTo(0, 500);

    }

    private void initView() {
        this.mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutSendSMS);
        this.mButtonSendSMS = (Button) findViewById(R.id.buttonSendSMS);
        this.mButtonMic = (Button) findViewById(R.id.buttonMic);
        this.mEditTextMessage = (EditText) findViewById(R.id.editTextMessage);
        this.mTextViewDefaultMsg1 = (TextView) findViewById(R.id.textViewDefaultMsg1);
        this.mTextViewDefaultMsg2 = (TextView) findViewById(R.id.textViewDefaultMsg2);
        this.mTextViewDefaultMsg3 = (TextView) findViewById(R.id.textViewDefaultMsg3);
        this.mTextViewDefaultMsg4 = (TextView) findViewById(R.id.textViewDefaultMsg4);
        this.mTextViewDefaultMsg5 = (TextView) findViewById(R.id.textViewDefaultMsg5);
        this.mTextViewDoctorName = (TextView) findViewById(R.id.textViewName);
        this.mTextViewDoctorBBCall = (TextView) findViewById(R.id.textViewUserID);
        this.mScrollViewSMS = (ScrollView) findViewById(R.id.scrollViewSMS);
        this.mTextViewDoctorName.setText(this.doctorName);
        this.mTextViewDoctorBBCall.setText(this.doctorBBCall);
        this.mButtonSendSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String _message = AppPermissionActivity.this.mEditTextMessage.getText().toString().trim();
                if (_message.equals("")) {
                    Toast.makeText(AppPermissionActivity.this, "請輸入要傳送的訊息!!!",Toast.LENGTH_SHORT).show();
                    AppPermissionActivity.this.mEditTextMessage.requestFocus();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AppPermissionActivity.this);
                builder.setMessage((CharSequence) "確認要送出訊息嗎?");
                builder.setCancelable(false);
                builder.setPositiveButton((CharSequence) "是", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        createNotification("mess.toString()");
                        String _psn_code = ((Ehr) AppPermissionActivity.this.getApplication()).wcode.substring(4);
                        mess=new String[]{AppPermissionActivity.this.hospMk, AppPermissionActivity.this.doctorBBCall, _message, _psn_code};
                        Log.d(TAG,"2222"+mess);

                        new AsyncTaskSendSMS().execute(new String[]{AppPermissionActivity.this.hospMk, AppPermissionActivity.this.doctorBBCall, _message, _psn_code});
                    }
                });
                builder.setNegativeButton((CharSequence) "否", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                builder.create().show();
            }
        });
        this.mTextViewDefaultMsg1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppPermissionActivity.this.insertDefaultMsg(AppPermissionActivity.this.mTextViewDefaultMsg1.getText().toString());
            }
        });
        this.mTextViewDefaultMsg2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppPermissionActivity.this.insertDefaultMsg(AppPermissionActivity.this.mTextViewDefaultMsg2.getText().toString());
            }
        });
        this.mTextViewDefaultMsg3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppPermissionActivity.this.insertDefaultMsg(AppPermissionActivity.this.mTextViewDefaultMsg3.getText().toString());
            }
        });
        this.mTextViewDefaultMsg4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppPermissionActivity.this.insertDefaultMsg(AppPermissionActivity.this.mTextViewDefaultMsg4.getText().toString());
            }
        });
        this.mTextViewDefaultMsg5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppPermissionActivity.this.insertDefaultMsg(AppPermissionActivity.this.mTextViewDefaultMsg5.getText().toString());
            }
        });
        this.mButtonMic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
                intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "en-US");
                try {
                    AppPermissionActivity.this.startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(AppPermissionActivity.this.getApplicationContext(), "很抱歉!!! 您的設備不支授語音輸入!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void createNotification(String aMessage) {
        final int NOTIFY_ID = 0; // ID of notification
        String id = getString(R.string.default_notification_channel_id); // default_channel_id
        String title = getString(R.string.default_notification_channel_title); // Default Channel
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (notifManager == null) {
            notifManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, id);
            intent = new Intent(this, SystemManagementActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            builder.setContentTitle("權限請求")  // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {
            builder = new NotificationCompat.Builder(this);
            intent = new Intent(this, SystemManagementActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            builder.setContentTitle("權限請求")                           // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }

    public void insertDefaultMsg(String _msg) {
        StringBuilder sBuilder = new StringBuilder(this.mEditTextMessage.getText().toString());
        int _pos_start = this.mEditTextMessage.getSelectionStart();
        sBuilder.replace(_pos_start, this.mEditTextMessage.getSelectionEnd(), _msg);
        this.mEditTextMessage.setText(sBuilder);
        this.mEditTextMessage.setSelection(_msg.length() + _pos_start);
    }

    private class AsyncTaskSendSMS extends AsyncTask<String,Void,Void> {
        JSONObject _result;

        private AsyncTaskSendSMS() {
            this._result = null;
        }

        public Void doInBackground(String... params) {
            try {
//                Response response = new OkHttpClient.Builder()
//                        .connectionPool(MySingleton.pool)
//                        .connectTimeout(30, TimeUnit.SECONDS)
//                        .readTimeout(30, TimeUnit.SECONDS).build()
//                        .newCall(new Request.Builder()
//                                .url(AppPermissionActivity.this.getString(R.string.site_address) + "mobile/apprequest")
//                                .post(new FormBody.Builder()
//                                        .add("hosp_mk", params[0])
//                                        .add("bbcall", params[1])
//                                        .add("call_message", params[2])
//                                        .add("call_psn_id", params[3])
//                                        .build())
//                                .build())
//                        .execute();
//                if (response.code() != 200) {
//                    return null;
//                }
//                this._result = new JSONObject(response.body().string());
                return null;
            } catch (Exception e) {
                return null;
            }
        }

        public void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (this._result != null) {
                try {
                    Snackbar.make((View) AppPermissionActivity.this.mCoordinatorLayout, (CharSequence) this._result.getString("message"), BaseTransientBottomBar.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Log.d(TAG,""+e);
                }
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == -1 && data != null) {
                    insertDefaultMsg(data.getStringArrayListExtra("android.speech.extra.RESULTS").get(0));
                    return;
                }
                return;
            default:
                return;
        }
    }

}
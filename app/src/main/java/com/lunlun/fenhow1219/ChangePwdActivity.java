package com.lunlun.fenhow1219;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangePwdActivity extends AppCompatActivity {
    boolean _running = false;
    private Button mButtonCancel;
    private Button mButtonClose;
    private Button mButtonConfirm;
    private EditText mEditTextPwdConfirm;
    private EditText mEditTextPwdNew;
    /* access modifiers changed from: private */
    public TextView mTextViewChangePwdError;
    /* access modifiers changed from: private */
    public SharedPreferences spref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        initView();
        getWindow().setSoftInputMode(5);
    }

    private void initView() {
//        this.spref = MySingleton.getInstance(getApplication()).getSharedPreferences();
        this.mButtonConfirm = (Button) findViewById(R.id.buttonConfirm);
        this.mButtonCancel = (Button) findViewById(R.id.buttonCancel);
        this.mButtonClose = (Button) findViewById(R.id.buttonClose);
        this.mEditTextPwdNew = (EditText) findViewById(R.id.editTextPwdNew);
        this.mEditTextPwdConfirm = (EditText) findViewById(R.id.editTextPwdConfirm);
        this.mTextViewChangePwdError = (TextView) findViewById(R.id.textViewChangePwdError);

        this.mButtonConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!ChangePwdActivity.this._running) {
                    ChangePwdActivity.this.doChange();
                }
            }
        });
        this.mButtonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChangePwdActivity.this.finish();
            }
        });
        this.mButtonClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChangePwdActivity.this.finish();
            }
        });
    }

    public void doChange() {
        this.mEditTextPwdNew.setError((CharSequence) null);
        this.mEditTextPwdConfirm.setError((CharSequence) null);
        boolean cancel = false;
        View focusView = null;
        String _pwdNew = this.mEditTextPwdNew.getText().toString().trim();
        String _pwdConfirm = this.mEditTextPwdConfirm.getText().toString().trim();
        if (_pwdNew.equals("")) {
            this.mEditTextPwdNew.setError("新密碼不可以空白!!!");
            focusView = this.mEditTextPwdNew;
            cancel = true;
        } else if (_pwdNew.length() < 6) {
            this.mEditTextPwdNew.setError("新密碼不可以少於6碼!!!");
            focusView = this.mEditTextPwdNew;
            cancel = true;
        } else if (!_pwdNew.matches(".*\\d+.*") || !_pwdNew.matches(".*[a-zA-Z]+.*") || !_pwdNew.matches(".*\\W+.*")) {
            this.mEditTextPwdNew.setError("新密碼不符合上述的條件!!!");
            focusView = this.mEditTextPwdNew;
            cancel = true;
        }
        if (_pwdConfirm.equals("")) {
            this.mEditTextPwdConfirm.setError("確認密碼不可以空白!!!");
            focusView = this.mEditTextPwdConfirm;
            cancel = true;
        } else if (_pwdConfirm.length() < 6) {
            this.mEditTextPwdConfirm.setError("確認密碼不可以少於6碼!!!");
            focusView = this.mEditTextPwdConfirm;
            cancel = true;
        } else if (!_pwdConfirm.matches(".*\\d+.*") || !_pwdConfirm.matches(".*[a-zA-Z]+.*") || !_pwdConfirm.matches(".*\\W+.*")) {
            this.mEditTextPwdConfirm.setError("確認密碼不符合上述的條件!!!");
            focusView = this.mEditTextPwdConfirm;
            cancel = true;
        }
        if (!cancel && !_pwdNew.equals(_pwdConfirm)) {
            this.mEditTextPwdConfirm.setError("新密碼和確認密碼不同!!!");
            focusView = this.mEditTextPwdConfirm;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
            return;
        }
        new AsyncTaskChangePwd().execute(new String[]{_pwdNew, _pwdConfirm});
    }

    private class AsyncTaskChangePwd extends AsyncTask<String, Void, String> {
        String _errMsg;
        String _pwd_new;

        private AsyncTaskChangePwd() {
            this._errMsg = "";
            this._pwd_new = "";
        }

        public void onPreExecute() {
            super.onPreExecute();
            ChangePwdActivity.this._running = true;
            this._errMsg = "";
            ChangePwdActivity.this.mTextViewChangePwdError.setText("");
        }

        public void onPostExecute(String _str) {
            super.onPostExecute(_str);
            ChangePwdActivity.this._running = false;
            if (!this._errMsg.equals("")) {
                ChangePwdActivity.this.mTextViewChangePwdError.setText(this._errMsg);
            } else if (_str.indexOf("限制違規") >= 0) {
                ChangePwdActivity.this.mTextViewChangePwdError.setText("密碼格式錯誤!!!");
            } else if (_str.indexOf("更改成功") >= 0) {
                SharedPreferences.Editor editor = ChangePwdActivity.this.spref.edit();
//                editor.putString(EmailAuthProvider.PROVIDER_ID, this._pwd_new);
                editor.commit();
                ChangePwdActivity.this.startActivity(new Intent(ChangePwdActivity.this, HomeFragment.class));
                ChangePwdActivity.this.finish();
            } else {
                ChangePwdActivity.this.mTextViewChangePwdError.setText("密碼修改失敗!!!");
            }
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... params) {
            this._pwd_new = params[0];
            try {

//                if (response.code() == 200) {
//                    return response.body().string();
//                }
//                this._errMsg = "密碼變更失敗!!!(" + response.code() + ")";
                return null;
            } catch (Exception ex) {
                this._errMsg = ex.getMessage();
                return null;
            }
        }

        public void onCancelled() {
            super.onCancelled();
            ChangePwdActivity.this._running = false;
        }
    }


}
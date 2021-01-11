package com.lunlun.fenhow1219;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Account_Fragment extends Fragment {
    private static final String TAG = Account_Fragment.class.getSimpleName();

    private Button mButtonPwdChange;
    private Switch mSwitchAutoSignIn;
    private Button mButtonUserDeviceRegister;

    private TextView mTextViewUserDepartmentName;
    private TextView mTextViewUserName;
    private TextView mTextViewUserPosName;
    private TextView mTextViewUserWorkDepartmentName;
    private TextView mTextViewWcode;

    private EditText mEditTextPwdConfirm;
    private EditText mEditTextPwdNew;
    public TextView mTextViewChangePwdError;

    private View root;
    private View root2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_account, container, false);
        root2 = inflater.inflate(R.layout.change_password_dialog, container, false);

        initview();
        return root;
    }

    private void initview() {
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

        this.mTextViewWcode = (TextView) root.findViewById(R.id.textViewWcode);
        this.mTextViewUserName = (TextView) root.findViewById(R.id.textViewUserName);
//        this.mTextViewUserDepartmentName = (TextView) root.findViewById(R.id.textViewUserDepartmentName);
        this.mTextViewUserWorkDepartmentName = (TextView) root.findViewById(R.id.textViewUserWorkDepartmentName);
        this.mTextViewUserPosName = (TextView) root.findViewById(R.id.textViewUserPosName);
        int _days=0;

        this.mButtonUserDeviceRegister = (Button) root.findViewById(R.id.buttonUserDeviceRegister);
        this.mButtonPwdChange = (Button) root.findViewById(R.id.buttonPwdChange);
        this.mSwitchAutoSignIn = (Switch) root.findViewById(R.id.switchAutoSignIn);

        JSONObject mjsonObject = null;
        try {
            mjsonObject = new JSONObject(mStringBuilder.toString());
            this.mTextViewWcode.setText(mjsonObject.getString("wcode"));
            this.mTextViewUserName.setText(mjsonObject.getString("wname"));
//            this.mTextViewUserDepartmentName.setText(mjsonObject.getString("dept_name"));
            this.mTextViewUserWorkDepartmentName.setText(mjsonObject.getString("work_dept_name"));
            this.mTextViewUserPosName.setText(mjsonObject.getString("pos_name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //自動簽到/打卡
        this.mSwitchAutoSignIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        //如果是私人設備-需要綁定裝置
        //mButtonUserDeviceRegister.setVisibility(true);
        this.mButtonUserDeviceRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("綁定私人設備");
                builder.setMessage("請自訂裝置名稱:");
                EditText mEditTextDeviceName = new EditText(getContext());
                builder.setView(mEditTextDeviceName);
                builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //!!將名稱存入設定
                        Toast.makeText(getContext(), mEditTextDeviceName.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });

        //變更密碼
        mButtonPwdChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextPwdNew = (EditText) root2.findViewById(R.id.editTextPwdNew);
                mEditTextPwdConfirm = (EditText) root2.findViewById(R.id.editTextPwdConfirm);
                mTextViewChangePwdError = (TextView) root2.findViewById(R.id.textViewChangePwdError);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("變更密碼");
                builder.setView(root2);
                builder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Account_Fragment.this.doChange();
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
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
    }

}
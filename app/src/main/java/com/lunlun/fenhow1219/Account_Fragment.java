package com.lunlun.fenhow1219;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

public class Account_Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button mButtonPwdChange;
    private Switch mSwitchAutoSignIn;
    private Button mButtonUserRegister;
    private View mLinearLayoutChangePassword;
    private Switch mSwitchCreateSigninShortcutAuto;
    private TextView mTextViewPwdInfo;
    private TextView mTextViewRegisterInfo;
    private TextView mTextViewRegisterNotice;
    private TextView mTextViewUserDepartmentName;
    private TextView mTextViewUserName;
    private TextView mTextViewUserPosName;
    private TextView mTextViewUserWorkDepartmentName;
    private TextView mTextViewWcode;
    private View root;
    private View root2;

    private Button mButtonCancel;
    private Button mButtonClose;
    private Button mButtonConfirm;
    private EditText mEditTextPwdConfirm;
    private EditText mEditTextPwdNew;
    public TextView mTextViewChangePwdError;
    boolean _running = false;


    public Account_Fragment() {
        // Required empty public constructor
    }

    public static Account_Fragment newInstance(String param1, String param2) {
        Account_Fragment fragment = new Account_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_account, container, false);
        root2 = inflater.inflate(R.layout.activity_change_pwd, container, false);

        mButtonPwdChange = (Button) root.findViewById(R.id.buttonPwdChange);

        this.mTextViewWcode = (TextView) root.findViewById(R.id.textViewWcode);
        this.mTextViewUserName = (TextView) root.findViewById(R.id.textViewUserName);
        this.mTextViewUserDepartmentName = (TextView) root.findViewById(R.id.textViewUserDepartmentName);
        this.mTextViewUserWorkDepartmentName = (TextView) root.findViewById(R.id.textViewUserWorkDepartmentName);
        this.mTextViewUserPosName = (TextView) root.findViewById(R.id.textViewUserPosName);
        this.mTextViewRegisterInfo = (TextView) root.findViewById(R.id.textViewRegisterInfo);
//        this.mTextViewRegisterNotice = (TextView) root.findViewById(R.id.textViewRegisterNotice);
        this.mTextViewPwdInfo = (TextView) root.findViewById(R.id.textViewPwdInfo);
        this.mButtonUserRegister = (Button) root.findViewById(R.id.buttonUserRegister);
        this.mButtonPwdChange = (Button) root.findViewById(R.id.buttonPwdChange);
        this.mSwitchCreateSigninShortcutAuto = (Switch) root.findViewById(R.id.switchCreateSigninShortcutAuto);
        this.mSwitchAutoSignIn = (Switch) root.findViewById(R.id.switchAutoSignIn);
        this.mLinearLayoutChangePassword = root.findViewById(R.id.linearLayoutChangePassword);
        initview();

        return root;
    }

    private void initview() {
        this.mSwitchCreateSigninShortcutAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        this.mSwitchAutoSignIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


        mButtonPwdChange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                getActivity().startActivity(new Intent(getActivity(), ChangePwdActivity.class));

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Theme_Design_BottomSheetDialog);
                builder.setView(root2);
                builder.create().show();

                mButtonConfirm = (Button) root2.findViewById(R.id.buttonConfirm);
                mButtonCancel = (Button) root2.findViewById(R.id.buttonCancel);
                mButtonClose = (Button) root2.findViewById(R.id.buttonClose);
                mEditTextPwdNew = (EditText) root2.findViewById(R.id.editTextPwdNew);
                mEditTextPwdConfirm = (EditText) root2.findViewById(R.id.editTextPwdConfirm);
                mTextViewChangePwdError = (TextView) root2.findViewById(R.id.textViewChangePwdError);
                mButtonConfirm.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (!Account_Fragment.this._running) {
                            Account_Fragment.this.doChange();
                        }
                    }
                });
                mButtonCancel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                });
                mButtonClose.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                    }
                });

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
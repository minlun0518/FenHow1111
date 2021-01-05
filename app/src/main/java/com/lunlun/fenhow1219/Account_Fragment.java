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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Account_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Account_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Account_Fragment.
     */
    // TODO: Rename and change types and number of parameters
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

//        final SharedPreferences spref = MySingleton.getInstance(getContext()).getSharedPreferences();

        this.mSwitchCreateSigninShortcutAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                SharedPreferences.Editor editor = spref.edit();
//                editor.putBoolean("create_signin_shortcut_auto", isChecked);
//                editor.putBoolean("isSignInInstalled", false);
//                editor.commit();
//                if (isChecked) {
//                    ((Ehr) getActivity().getApplicationContext()).addSigninShortcut();
//                }
            }
        });
        this.mSwitchAutoSignIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            }
        });
//        this.mTextViewUserName.setText(spref.getString("wname", ""));
//        this.mTextViewUserDepartmentName.setText(spref.getString("dept_name", ""));
//        this.mTextViewUserPosName.setText(spref.getString("pos_name", ""));
//        this.mTextViewPwdInfo.setText(spref.getString("pwd_expired_date", "--"));
//        int _days = spref.getInt("pwd_expired_days", 0);
//        if (_days <= 7) {
//            this.mTextViewPwdInfo.setTextColor(-3592148);
//        } else if (_days <= 14) {
//            this.mTextViewPwdInfo.setTextColor(-1272033);
//        } else if (_days <= 30) {
//            this.mTextViewPwdInfo.setTextColor(-13520683);
//        } else {
//            this.mTextViewPwdInfo.setTextColor(-12280508);
//        }
//        this.mSwitchCreateSigninShortcutAuto.setChecked(spref.getBoolean("create_signin_shortcut_auto", true));

//        this.mSwitchAutoSignIn.setChecked(spref.getBoolean("auto_signin", false));
//        if (spref.getLong("reg_time", 0) != 0) {
//            this.mTextViewRegisterInfo.setText(Func.C2273DT.Long2Str(Long.valueOf(spref.getLong("reg_time", 0)), "yyyy/MM/dd HH:mm:ss") + " 完成行動裝置註冊\n" + Func.C2273DT.Long2Str(Long.valueOf(spref.getLong("start_time", 0)), "yyyy/MM/dd HH:mm:ss") + " 後, 可以開始使用此行動裝置簽到退!!!");
//            this.mTextViewRegisterInfo.setTextColor(Color.rgb(30, 30, 30));
//            this.mTextViewRegisterNotice.setVisibility(8);
//        } else {
//            this.mTextViewRegisterInfo.setTextColor(SupportMenu.CATEGORY_MASK);
//            this.mTextViewRegisterInfo.setText("尚未註冊，如要註冊，請按右邊按鈕註冊");
//            this.mTextViewRegisterNotice.setVisibility(0);
//        }
//        this.wcode = spref.getString("wcode", "");
//        this.password = spref.getString(EmailAuthProvider.PROVIDER_ID, "");
//        this.mobileid = spref.getString("mobileid", "null").toLowerCase();
//        if (this.mobileid.equals("") || this.mobileid.equals("null")) {
//            this.mobileid = Func.SerialId(this);
//        }
//        loadImage(this.wcode);
//        this.mButtonUserRegister.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                new AsyncTaskRegister().execute(new String[]{UserActivity.this.wcode, UserActivity.this.password, UserActivity.this.mobileid});
//            }
//        });

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
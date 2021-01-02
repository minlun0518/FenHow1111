package com.lunlun.fenhow1219.ui.gallery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lunlun.fenhow1219.ChangePwdActivity;
import com.lunlun.fenhow1219.Ehr;
import com.lunlun.fenhow1219.MySingleton;
import com.lunlun.fenhow1219.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
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


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

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
                getActivity().startActivity(new Intent(getActivity(), ChangePwdActivity.class));
            }
        });


    }

}
package com.lunlun.fenhow1219;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignInFragment extends Fragment {

    private boolean isAruba = false;
    private boolean isConnectWifi = false;

    public Button mButtonSignIn;

    public Button mButtonSignOut;
    private Button mButtonUserRegister;
    private Button mButtonWifiSetting;
//    private CoordinatorLayout mCoordinatorLayout;
    private ImageView mImageViewWifi;
    private View mLayoutSigninButton;
    private View mLinearLayoutPwdInfo;

    public View mLinearLayoutSection1;

    public View mLinearLayoutSection2;

    public RadioButton mRadioButtonSection1;

    public RadioButton mRadioButtonSection2;

    public RadioButton mRadioButtonToday;

    public RadioButton mRadioButtonYesterday;
    private View mRegisterMobileLayout;
    private View mSigninLayout;

    public TextView mTextViewBeginTime1;

    public TextView mTextViewBeginTime2;

    public TextView mTextViewEndTime1;

    public TextView mTextViewEndTime2;
    private TextView mTextViewPwdInfo;

    public TextView mTextViewShiftInfo;

    public TextView mTextViewSignInNotes;
    private TextView mTextViewWifiError;
    private TextView mTextViewWifiName;
    private TextView mTextViewWifiStatus;

    public Date mToday;

    public View mView;

    public Date mYesterday;

    public boolean signin_effective = false;

    public Snackbar snackbar;

//    public SharedPreferences spref = MySingleton.getInstance(getActivity()).getSharedPreferences();
    private Boolean weak_signal = true;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_sign_in, container, false);
        return this.mView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        this.mButtonWifiSetting = (Button) this.mView.findViewById(R.id.buttonWifiSetting);
        this.mTextViewWifiName = (TextView) this.mView.findViewById(R.id.textViewWifiName);
        this.mTextViewWifiError = (TextView) this.mView.findViewById(R.id.textViewWifiError);
        this.mTextViewWifiStatus = (TextView) this.mView.findViewById(R.id.textViewWifiStatus);
        this.mButtonSignIn = (Button) this.mView.findViewById(R.id.buttonSignIn);
        this.mButtonSignOut = (Button) this.mView.findViewById(R.id.buttonSignOut);
        this.mRadioButtonToday = (RadioButton) this.mView.findViewById(R.id.radioButtonToday);
        this.mRadioButtonYesterday = (RadioButton) this.mView.findViewById(R.id.radioButtonYesterday);
        this.mTextViewSignInNotes = (TextView) this.mView.findViewById(R.id.textViewSignInNotes);
        this.mTextViewShiftInfo = (TextView) this.mView.findViewById(R.id.textViewShiftInfo);
        this.mTextViewBeginTime1 = (TextView) this.mView.findViewById(R.id.textViewBeginTime1);
        this.mTextViewEndTime1 = (TextView) this.mView.findViewById(R.id.textViewEndTime1);
        this.mTextViewBeginTime2 = (TextView) this.mView.findViewById(R.id.textViewBeginTime2);
        this.mTextViewEndTime2 = (TextView) this.mView.findViewById(R.id.textViewEndTime2);
        this.mTextViewPwdInfo = (TextView) this.mView.findViewById(R.id.textViewPwdInfo);
        this.mRadioButtonSection1 = (RadioButton) this.mView.findViewById(R.id.radioButtonSection1);
        this.mRadioButtonSection2 = (RadioButton) this.mView.findViewById(R.id.radioButtonSection2);
//        this.mCoordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayoutMain);
        this.mSigninLayout = this.mView.findViewById(R.id.signinLayout);
        this.mRegisterMobileLayout = this.mView.findViewById(R.id.registerMobileLayout);
        this.mLinearLayoutSection1 = this.mView.findViewById(R.id.LinearLayoutSection1);
        this.mLinearLayoutSection2 = this.mView.findViewById(R.id.LinearLayoutSection2);
        this.mLinearLayoutPwdInfo = this.mView.findViewById(R.id.LinearLayoutPwdInfo);
        this.mButtonUserRegister = (Button) this.mView.findViewById(R.id.buttonUserRegister);
        this.mImageViewWifi = (ImageView) this.mView.findViewById(R.id.imageViewWifi);
        this.mLayoutSigninButton = this.mView.findViewById(R.id.layoutSigninButton);
        this.mRadioButtonToday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        this.mRadioButtonYesterday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
//        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "iconfont.ttf");
//        this.mButtonSignIn.setTypeface(typeface);
//        this.mButtonSignOut.setTypeface(typeface);
    }

    private void setListener() {
        this.mButtonWifiSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                SignInFragment.this.getActivity().startActivity(new Intent("android.net.wifi.PICK_WIFI_NETWORK"));
            }
        });
        this.mButtonSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                SignInFragment.this.signIn("A");
            }
        });
        this.mButtonSignOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                SignInFragment.this.signIn("B");
            }
        });
        this.mButtonUserRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

    public void onResume() {
        boolean z = false;
        super.onResume();

    }

    public void onPause() {
        super.onPause();

    }
}
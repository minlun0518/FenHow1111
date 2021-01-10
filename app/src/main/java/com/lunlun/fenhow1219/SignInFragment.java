package com.lunlun.fenhow1219;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.internal.view.SupportMenu;
import androidx.fragment.app.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
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
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignInFragment extends Fragment {

    private boolean isAruba = false;
    private boolean isConnectWifi = false;
    public Button mButtonSignIn;
    public Button mButtonSignOut;
    private Button mButtonUserRegister;
    private Button mButtonWifiSetting;
    private CoordinatorLayout mCoordinatorLayout;
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
    private Boolean weak_signal = true;
    private WifiReceiver mWifiReceiver;
    final String TAG=SignInFragment.class.getSimpleName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.sign_work_fragment_sign_in, container, false);
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
        this.mCoordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.coordinatorLayoutMain);
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
                SignInFragment.this.mRadioButtonToday.setText(new SimpleDateFormat("MM-dd").format(SignInFragment.this.mToday));
            }
        });
        this.mRadioButtonYesterday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SignInFragment.this.mRadioButtonYesterday.setText(new SimpleDateFormat("MM-dd").format(SignInFragment.this.mYesterday));
            }
        });

        Calendar calendar = Calendar.getInstance();
        mToday=calendar.getTime();
        calendar.setTime(SignInFragment.this.mToday);
        calendar.add(5, -1);
        Date unused2 = SignInFragment.this.mYesterday = calendar.getTime();
        SignInFragment.this.mRadioButtonToday.setText(new SimpleDateFormat("MM-dd").format(SignInFragment.this.mToday));
        SignInFragment.this.mRadioButtonYesterday.setText(new SimpleDateFormat("MM-dd").format(SignInFragment.this.mYesterday));

        String _date = "";
        if (SignInFragment.this.mRadioButtonToday.isChecked()) {
            _date = new SimpleDateFormat("yyyy-MM-dd").format(SignInFragment.this.mToday);
        } else if (SignInFragment.this.mRadioButtonYesterday.isChecked()) {
            _date = new SimpleDateFormat("yyyy-MM-dd").format(SignInFragment.this.mYesterday);
        }
    }

    private void setListener() {
        this.mButtonWifiSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SignInFragment.this.getActivity().startActivity(new Intent("android.net.wifi.PICK_WIFI_NETWORK"));
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
        super.onResume();
        this.mWifiReceiver = new WifiReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        filter.addAction("android.net.wifi.RSSI_CHANGED");
        getActivity().registerReceiver(this.mWifiReceiver, filter);
    }

    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(this.mWifiReceiver);
    }

    public void wifiReceive(Context context) {
        Object wifiService = context.getSystemService(Context.WIFI_SERVICE);
        if (wifiService != null) {
            WifiManager wifiManager = (WifiManager) wifiService;
            String _errorMsg = "";
            this.mTextViewWifiStatus.setText("");
            if (wifiManager != null) {
                if (!wifiManager.isWifiEnabled()) {
                    _errorMsg = "請先開啟Wi-Fi連線!!!";
                    this.mImageViewWifi.setImageResource(R.drawable.icon_wifi0);
                    this.mTextViewWifiName.setText("");
                    this.isConnectWifi = false;
                } else {
                    WifiInfo info = wifiManager.getConnectionInfo();
                    if (info.getBSSID() == null) {
                        _errorMsg = "未開啟Wi-Fi";
                        Log.d(TAG,"info.getBSSID()"+info.getBSSID());
                        this.mTextViewWifiName.setText("");
                        this.mImageViewWifi.setImageResource(R.drawable.icon_wifi0);
                        this.isConnectWifi = false;
                    } else if (info.getNetworkId() < 0) {
                        _errorMsg = "未選取Wi-Fi";
                        Log.d(TAG,"info.getNetworkId()_____________________"+info.getNetworkId());
                        this.mTextViewWifiName.setText("");
                        this.mImageViewWifi.setImageResource(R.drawable.icon_wifi0);
                        this.isConnectWifi = false;
                    } else {
                        this.isConnectWifi = true;
                        this.mTextViewWifiName.setTextColor(-12478911);
                        this.mTextViewWifiName.setText(info.getSSID().replace("\"", ""));
                        String _ip = Formatter.formatIpAddress(info.getIpAddress());
                        String _ssid = this.mTextViewWifiName.getText().toString().toLowerCase();
                        if (_ip.equals("")) {
                            this.isAruba = false;
                            _errorMsg = "連線中.....";
                        } else if (_ip.startsWith("192.168")) {
                            if (_ssid.equals("Minlun") || _ssid.startsWith("Min")) {
                                this.isAruba = true;
                                this.mTextViewWifiStatus.setText(_ip);
                            } else {
                                this.isAruba = false;
                                _errorMsg ="連錯人了";
                            }
                        } else if (_ssid.startsWith("FJU")) {
                            this.isAruba = true;
                            this.mTextViewWifiStatus.setText(_ip);
                        } else {
                            this.isAruba = false;
                            _errorMsg = "需連接FJU才可以使用簽到退功能";
                        }
                        this.mImageViewWifi.setImageResource(R.drawable.icon_wifi0);
                        double strength = (double) WifiManager.calculateSignalLevel(info.getRssi(), 11);
                        if (strength > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            if (strength <= 2.0d) {
                                this.mImageViewWifi.setImageResource(R.drawable.wifi1);
                            } else if (strength <= 5.0d) {
                                this.mImageViewWifi.setImageResource(R.drawable.wifi2);
                            } else if (strength <= 7.0d) {
                                this.mImageViewWifi.setImageResource(R.drawable.wifi3);
                            } else if (strength <= 10.0d) {
                                this.mImageViewWifi.setImageResource(R.drawable.wifi4);
                            }
                        }
                        if (strength <= 1.0d) {
                            this.weak_signal = true;
                            _errorMsg = "請至Wi-Fi訊號較強處簽到退!!!.....";
                        } else {
                            this.weak_signal = false;
                        }
                    }
                }
                if (!_errorMsg.equals("")) {
                    this.mTextViewWifiError.setTextColor(SupportMenu.CATEGORY_MASK);
                    this.mTextViewWifiError.setText(_errorMsg);
                    this.mTextViewWifiError.setVisibility(View.VISIBLE);
                } else {
                    this.mTextViewWifiError.setVisibility(View.GONE);
                }
                this.mTextViewWifiStatus.setVisibility(this.mTextViewWifiStatus.getText().equals("") ? View.GONE : View.VISIBLE);
            } else {
                this.isConnectWifi = false;
                this.mTextViewWifiName.setTextColor(SupportMenu.CATEGORY_MASK);
                this.mTextViewWifiName.setText("???");
                this.mImageViewWifi.setImageResource(R.drawable.icon_wifi0);
            }

        }
    }

    public class WifiReceiver extends BroadcastReceiver {
        public WifiReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            SignInFragment.this.wifiReceive(context);
        }
    }



}
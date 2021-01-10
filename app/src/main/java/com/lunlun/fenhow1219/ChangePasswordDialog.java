package com.lunlun.fenhow1219;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class ChangePasswordDialog extends DialogFragment {

    public static final int STATE_NORMAL = 1;
    public static final int STATE_FAILED = 2;
    public static final int STATE_ERROR = 3;
    public static final int STATE_SUCCEED = 4;
    private TextView mStateTv;
    private TextView mUsePasswordBtn;
    private TextView mCancelBtn;
    private Activity mActivity;
    private OnChangePasswordDialogActionCallback mDialogActionCallback;

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

    public interface OnChangePasswordDialogActionCallback {
        void onDialogDismiss();
        void onUsePassword();
        void onCancel();
    }

    public static ChangePasswordDialog newInstance() {
        ChangePasswordDialog dialog = new ChangePasswordDialog();
        return dialog;
    }

    public void setOnChangePasswordDialogActionCallback( OnChangePasswordDialogActionCallback callback) {
        mDialogActionCallback = callback;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupWindow(getDialog().getWindow());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_change_pwd, container);

        RelativeLayout rootView = view.findViewById(R.id.root_view);
        rootView.setClickable(false);

        this.mButtonConfirm = (Button) view.findViewById(R.id.buttonConfirm);
        this.mButtonCancel = (Button) view.findViewById(R.id.buttonCancel);
        this.mButtonClose = (Button) view.findViewById(R.id.buttonClose);
        this.mEditTextPwdNew = (EditText) view.findViewById(R.id.editTextPwdNew);
        this.mEditTextPwdConfirm = (EditText) view.findViewById(R.id.editTextPwdConfirm);
        this.mTextViewChangePwdError = (TextView) view.findViewById(R.id.textViewChangePwdError);

//        mUsePasswordBtn.setVisibility(View.GONE);
        mUsePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDialogActionCallback != null) {
                    mDialogActionCallback.onUsePassword();
                }
                dismiss();
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDialogActionCallback != null) {
                    mDialogActionCallback.onCancel();
                }
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(R.color.bg_biometric_prompt_dialog);
        }
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (mDialogActionCallback != null) {
            mDialogActionCallback.onDialogDismiss();
        }
    }

    private void setupWindow(Window window) {
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.CENTER;
            lp.dimAmount = 0;
            lp.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(lp);
            window.setBackgroundDrawableResource(R.color.bg_biometric_prompt_dialog);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    public void setState(int state) {
        switch (state) {
            case STATE_NORMAL:
                mStateTv.setTextColor(ContextCompat.getColor(mActivity, R.color.text_quaternary));
                mStateTv.setText(mActivity.getString(R.string.biometric_dialog_state_normal));
                mCancelBtn.setVisibility(View.VISIBLE);
                break;
            case STATE_FAILED:
                mStateTv.setTextColor(ContextCompat.getColor(mActivity, R.color.text_red));
                mStateTv.setText(mActivity.getString(R.string.biometric_dialog_state_failed));
                mCancelBtn.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR:
                mStateTv.setTextColor(ContextCompat.getColor(mActivity, R.color.text_red));
                mStateTv.setText(mActivity.getString(R.string.biometric_dialog_state_error));
                mCancelBtn.setVisibility(View.GONE);
                break;
            case STATE_SUCCEED:
                mStateTv.setTextColor(ContextCompat.getColor(mActivity, R.color.text_green));
                mStateTv.setText(mActivity.getString(R.string.biometric_dialog_state_succeeded));
                mCancelBtn.setVisibility(View.VISIBLE);

                mStateTv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, 500);
                break;
        }
    }

}


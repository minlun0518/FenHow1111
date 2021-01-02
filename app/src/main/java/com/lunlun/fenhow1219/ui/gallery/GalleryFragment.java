package com.lunlun.fenhow1219.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lunlun.fenhow1219.ChangePwdActivity;
import com.lunlun.fenhow1219.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private Button mButtonPwdChange;
    private Switch mSwitchAutoSignIn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        mButtonPwdChange = (Button) root.findViewById(R.id.buttonPwdChange);
        initview();


        return root;
    }

    private void initview() {
        mButtonPwdChange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ChangePwdActivity.class));
            }
        });
    }

}
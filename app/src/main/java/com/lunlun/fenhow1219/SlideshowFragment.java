package com.lunlun.fenhow1219;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.lunlun.fenhow1219.AppPermissionActivity;
import com.lunlun.fenhow1219.R;

public class SlideshowFragment extends Fragment {

    private View root;
    private TableLayout tableLayout;
    private CustomGrid medadapter;
    private GridView grid;
    private CustomGrid adminadapter;
    private GridView adgrid;
    private CustomGrid zuadapter;
    private GridView zugrid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        medadapter = new CustomGrid(getActivity(), medApp, medimageId,medStatue,medLock);
        grid = (GridView) root.findViewById(R.id.grid);
        grid.setAdapter(medadapter);
        medadapter.gridViewClick(grid);

        adminadapter = new CustomGrid(getActivity(), adminApp, adminimageId,adminStatue,adminLock);
        adgrid = (GridView) root.findViewById(R.id.adminGrid);
        adgrid.setAdapter(adminadapter);
        adminadapter.gridViewClick(adgrid);

        zuadapter = new CustomGrid(getActivity(), zuApp, zuimageId,zuStatue,zuLock);
        zugrid = (GridView) root.findViewById(R.id.zuGrid);
        zugrid.setAdapter(zuadapter);
        zuadapter.gridViewClick(zugrid);
        return root;
    }

    private String[] medApp = {"採檢及生理量測系統", "危險值通知系統"};
    private int[] medimageId = {R.drawable.icon_app_blood_sample, R.drawable.icon_app_warning};
    private boolean[] medStatue={true,false};
    private boolean[] medLock={true,false};

    private String[] adminApp = {"出勤打卡系統", "員工排班系統","會議室簽到系統","住院醫師教學評量系統"};
    private int[] adminimageId = {R.drawable.icon_checklist, R.drawable.icon_calendar,R.drawable.icon_conversation,R.drawable.icon_realdr};
    private boolean[] adminStatue={true,false,true,false};
    private boolean[] adminLock={true,false,false,false};

    private String[] zuApp = {"環境巡檢系統"};
    private int[] zuimageId = {R.drawable.icon_policeman};
    private boolean[] zuStatue={false};
    private boolean[] zuLock={false};

    class  CustomGrid extends BaseAdapter {
        private Context context;
        private final String[] appName;
        private final int[] appImageId;
        private final boolean[] appStatue;
        private final boolean[] appLock;

        public CustomGrid(Context context, String[] appName, int[] appImageId,boolean[] appStatue,boolean[] appLock) {
            this.context = context;
            this.appName = appName;
            this.appImageId = appImageId;
            this.appStatue = appStatue;
            this.appLock=appLock;
        }

        @Override
        public int getCount() {
            return appName.length;
        }

        @Override
        public Object getItem(int position) {
            return appName[position];
        }

        @Override
        public long getItemId(int position) {
            return appImageId[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ColorMatrix matrix=new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter=new ColorMatrixColorFilter(matrix);
            if (row == null){
                row = getLayoutInflater().inflate(R.layout.item_slideshow_app, null);
                ImageView image = (ImageView) row.findViewById(R.id.grid_image);
                TextView text = (TextView) row.findViewById (R.id.grid_text);
                ImageView statue= (ImageView) row.findViewById(R.id.gridStatue);
                text.setText (appName[position]);
                if(!appStatue[position]) {
                    image.setImageResource(appImageId[position]);
                    image.setColorFilter(filter);
                    statue.setImageResource(R.drawable.icon_stop);
                }else {
                    image.setImageResource(appImageId[position]);
                    if(appLock[position]){
                        statue.setImageResource(R.drawable.icon_pin);
                        text.setTextColor(getResources().getColor(R.color.cathayhospital3));
                    }
                }

            }
            return row;
        }

        private void gridViewClick(GridView gridView) {
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), AppPermissionActivity.class);
                    if(!appStatue[position]){
                        new AlertDialog.Builder(getActivity())
                                .setIcon(appImageId[position])
                                .setTitle(appName[position])
                                .setMessage("您目前無法使用此功能，是否項資訊室請求權限?")
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(intent);
                                    }
                                })
                                .setNeutralButton("否",null)
                                .show();
                    }else {
                        Snackbar.make(view, appName[position], Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
//                        Intent login = new Intent(getContext(), Login.class);
////                        startActivity(login);
                    }
                }
            });
        }

    }
}
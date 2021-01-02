package com.lunlun.fenhow1219;

import com.lunlun.fenhow1219.ui.home.HomeFragment;

public class ApplicationItem extends HomeFragment {
    public int position;
    public String appName;
    public int imageViewpost;
    public String appAct;
    public Class className;

    public ApplicationItem(int position, String appName, int imageViewpost,String appAct,Class className){
        this.position=position;
        this.appName=appName;
        this.imageViewpost=imageViewpost;
        this.appAct=appAct;
        this.className=className;
    }
}
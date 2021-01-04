package com.lunlun.fenhow1219;

public class ApplicationItem extends HomeFragment {
    public int position;
    public String appName;
    public int imageViewpost;
    public String appAct;
    public int className;

    public ApplicationItem(int position, String appName, int imageViewpost, String appAct, int className){
        this.position=position;
        this.appName=appName;
        this.imageViewpost=imageViewpost;
        this.appAct=appAct;
        this.className=className;
    }
}
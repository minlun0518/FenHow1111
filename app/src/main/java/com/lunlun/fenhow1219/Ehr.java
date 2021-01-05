package com.lunlun.fenhow1219;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Ehr extends Application {
    public String employeeKind = "";
    public boolean identityConfirmOk = false;
    private SharedPreferences mSpref;
    public String mobileid = "";
    public boolean onService = false;
    public String wcode = "";

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        Multi.install(this);
    }

    public void addShortcut() {
        this.mSpref = MySingleton.getInstance(getApplicationContext()).getSharedPreferences();
        if (!this.mSpref.getBoolean("isAppInstalled", false)) {
            Intent shortcutIntent = new Intent(getApplicationContext(), HomeFragment.class);

            shortcutIntent.setAction("android.intent.action.MAIN");
            Intent addIntent = new Intent();
            addIntent.putExtra("android.intent.extra.shortcut.INTENT", shortcutIntent);
            addIntent.putExtra("android.intent.extra.shortcut.NAME", "粉好");
            addIntent.putExtra("duplicate", false);
//            addIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.png_ehr));
            addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(addIntent);
            SharedPreferences.Editor editor = this.mSpref.edit();
            editor.putBoolean("isAppInstalled", true);
            editor.commit();
        }
    }

    public void addSigninShortcut() {
        this.mSpref = MySingleton.getInstance(getApplicationContext()).getSharedPreferences();
        if (!this.mSpref.getBoolean("isSignInInstalled", false) && this.mSpref.getBoolean("create_signin_shortcut_auto", true)) {
            Intent shortcutIntent = new Intent(getApplicationContext(), SignWorkActivity.class);
//            shortcutIntent.setFlags(268468224);
            shortcutIntent.setAction("android.intent.action.MAIN");
            Intent addIntent = new Intent();
            addIntent.putExtra("android.intent.extra.shortcut.INTENT", shortcutIntent);
            addIntent.putExtra("android.intent.extra.shortcut.NAME", "粉好");
            addIntent.putExtra("duplicate", false);
            addIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.icon__signin));
            addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(addIntent);
            SharedPreferences.Editor editor = this.mSpref.edit();
            editor.putBoolean("isSignInInstalled", true);
            editor.commit();
        }
    }

    public void removeSigninShortcut() {
        Intent shortcutIntent = new Intent(getApplicationContext(), SignWorkActivity.class);
        shortcutIntent.setAction("android.intent.action.MAIN");
        Intent removeIntent = new Intent();
        removeIntent.putExtra("android.intent.extra.shortcut.INTENT", shortcutIntent);
        removeIntent.putExtra("android.intent.extra.shortcut.NAME", "粉好");
        removeIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        sendBroadcast(removeIntent);
    }
}


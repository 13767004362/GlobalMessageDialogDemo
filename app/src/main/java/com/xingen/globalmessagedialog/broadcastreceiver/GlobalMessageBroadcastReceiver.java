package com.xingen.globalmessagedialog.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.xingen.globalmessagedialog.activity.GlobalMessageDialogActivity;
import com.xingen.globalmessagedialog.utils.ActivityUtils;

/**
 * Created by ${新根} on 2017/12/19.
 * blog博客:http://blog.csdn.net/hexingen
 *
 * 广播开启一个Dialog的Activity
 */

public class GlobalMessageBroadcastReceiver extends BroadcastReceiver {
    private static String action=GlobalMessageBroadcastReceiver.class.getName();
    @Override
    public void onReceive(Context context, Intent intent) {
          ActivityUtils.startActivity(context,GlobalMessageDialogActivity.class);
    }
    public void register(Context context){
        context.registerReceiver(this,new IntentFilter(action));
    }
    public void unRegister(Context context){
        context.unregisterReceiver(this);
    }
    public static void sendBroadcast(Context context){
        context.sendBroadcast(new Intent(action));
    }
}

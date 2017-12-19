package com.xingen.globalmessagedialog.service;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import com.xingen.globalmessagedialog.broadcastreceiver.GlobalMessageBroadcastReceiver;
import com.xingen.globalmessagedialog.activity.GlobalMessageDialogActivity;
import com.xingen.globalmessagedialog.notification.NotificationBuidler;
import com.xingen.globalmessagedialog.utils.ActivityUtils;
import com.xingen.globalmessagedialog.utils.ProcessUtils;

/**
 * Created by ${新根} on 2017/12/19.
 * blog博客:http://blog.csdn.net/hexingen
 *
 * 后期请求网络的服务
 */

public class NetWorkService extends IntentService {
    private static final String TAG=NetWorkService.class.getSimpleName();
    private NotificationManager notificationManager;
    private Vibrator vibrator;
    private GlobalMessageBroadcastReceiver messageBroadcastReceiver;
    public NetWorkService() {
        super(TAG);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        this.messageBroadcastReceiver=new GlobalMessageBroadcastReceiver();
        this.messageBroadcastReceiver.register(this);
        this.notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        this.vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            //让线程沉睡2秒，模拟网络请求
            Thread.sleep(2*1000);
            doAction();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static  void startService(Context context){
        Intent intent=new Intent(context,NetWorkService.class);
        intent.setPackage("com.xingen.globalmessagedialog");
        context.startService(intent);
    }
    public void doAction(){
        vibrator.vibrate(1000);
         if (ProcessUtils.checkAppProcessState(this,ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)){
           if (!ProcessUtils.isCurrentActivityState(this, ActivityUtils.getName(GlobalMessageDialogActivity.class))){
               GlobalMessageBroadcastReceiver.sendBroadcast(this);
           };
         }else{
             notificationManager.notify(110, NotificationBuidler.createMessageNotifaction(this,"您的程序来消息了","点击查看"));
         }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.messageBroadcastReceiver!=null){
            this.messageBroadcastReceiver.unRegister(this);
        }
    }
}

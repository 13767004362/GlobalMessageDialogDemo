package com.xingen.globalmessagedialog.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by ${新根} on 2017/12/19.
 * blog博客:http://blog.csdn.net/hexingen
 */

public class ProcessUtils {
    /**
     * 用途：判断当前app的进程所属于的状态
     *
     *RunningAppProcessInfo类中的一下状态：
     *
     * 1.IMPORTANCE_FOREGROUND ：这个进程在运行前台的UI，即用户在和屏幕UI进行交互。
     *
     * 2.IMPORTANCE_BACKGROUND ：这个进程包含可以展开的后台代码。
     *
     * 3.IMPORTANCE_FOREGROUND_SERVICE：该进程正在运行前台的服务，例如：用户没立即在运用程序中，但是同时该运用程序还在播放音乐。
     *
     * 4.IMPORTANCE_TOP_SLEEPING：该进程在运行前台的服务，但是手机处于睡眠状态，因此用户是不可见的。
     *           这意味着用户不是真的意识到到这进程，因为用户没有看到或者与进程互动，但是它也是很重要的。因为它是他们期望的返回，一次解锁设备。
     *
     * 5.IMPORTANCE_VISIBLE ：
     *
     *手机测试结果：
     *     1.正在运行app的activity的（包含锁屏）时候属于IMPORTANCE_FOREGROUND 。
     *
     *     2.运行activity的app，然后按home键，时候处于IMPORTANCE_BACKGROUND。
     *
     *     3.销毁了全部的activity，还保留service的时候属于IMPORTANCE_BACKGROUND。
     *
     *     4.只保留service，然后锁屏的时候属于IMPORTANCE_BACKGROUND。
     *
     *     5.运行当前app，然后切换到其他运用的时候属于IMPORTANCE_BACKGROUND。
     *
     * @param context
     * @return
     */
    public static boolean checkAppProcessState(Context context, final  int code ){
        ActivityManager activityManager=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //返回在设备上运行的运用程序进程的列表
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos= activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo:appProcessInfos){
            if(runningAppProcessInfo.processName.equals(context.getPackageName())){//匹配到当前app的进程。
                if(runningAppProcessInfo.importance== code){//进程是否处于指定进程
                    return  true;
                }else{
                    return    false;
                }
            }else continue;
        }
        return  false;
    }
    /**
     * //RunningTaskInfo.topActivity是activity栈中栈顶的activity，即当前屏幕上的activity的名字
     * //名字：com.xxxx.qmclient.activity.DialogActivity
     * 检查某个类是否处于栈顶
     * @param context
     * @param activityName
     * @return
     */
    public static  boolean isCurrentActivityState(Context context,String activityName){
        ActivityManager activityManager=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos=  activityManager.getRunningTasks(1);
        for ( ActivityManager.RunningTaskInfo runningTaskInfo :runningTaskInfos){
            if(activityName.equals(runningTaskInfo.topActivity.getClassName())){
                return  true;
            }else continue;
        }
        return false;
    }

}

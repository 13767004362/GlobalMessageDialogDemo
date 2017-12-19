package com.xingen.globalmessagedialog.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ${新根} on 2017/12/19.
 * blog博客:http://blog.csdn.net/hexingen
 */

public class ActivityUtils {
    /**
     * 非Activity开启，需要添加Intent.FLAG_ACTIVITY_NEW_TASK。
     * @param context
     * @param mClass
     */
    public static  void startActivity(Context context,Class<?> mClass){
        Intent intent=new Intent(context,mClass);
        if (!(context instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     *  通过包名+类名组件
     * @param mClass
     * @return
     */
    public static  String getPackagePathActivityName(Class<?> mClass){
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(mClass.getPackage().getName());
        stringBuffer.append(".");
        stringBuffer.append(mClass.getSimpleName());
       return stringBuffer.toString();
    }

    /**
     * 直接过取带包路径的类名
     * @param mClass
     * @return
     */
    public  static String getName(Class<?> mClass){
        return  mClass.getName();
    }

}

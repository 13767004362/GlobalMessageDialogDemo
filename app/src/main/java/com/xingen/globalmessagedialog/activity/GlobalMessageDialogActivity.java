package com.xingen.globalmessagedialog.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.xingen.globalmessagedialog.R;

/**
 * Created by ${新根} on 2017/12/19.
 * blog博客:http://blog.csdn.net/hexingen
 * 一个全局的消息Dialog,不需要开启系统级别的Dilaog.
 */

public class GlobalMessageDialogActivity extends AppCompatActivity {
    private AlertDialog  messageDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMessageDialog();
    }

    private void showMessageDialog() {
        if (messageDialog==null){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("全局消息弹窗");
            builder.setMessage("从任何一个后台服务或者通用模块中弹窗一个消息，显示在当前的界面中");
            builder.setCancelable(false);

            builder.setPositiveButton("确定",( dialog,which)->{
                this.dimissMessageDialog();
                this.finish();
            });

            messageDialog=builder.create();
        }
        if (!messageDialog.isShowing()){
            messageDialog.show();
        }
    }
    private void dimissMessageDialog(){
        if (messageDialog!=null&&messageDialog.isShowing()){
            messageDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dimissMessageDialog();
    }
}

package com.contacts.lhj.presenter.called;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import com.contacts.lhj.jennifercontacts.GlobalViewVariable;
import com.contacts.lhj.model.called.CalledCacheOperation;
import com.contacts.lhj.view.called.CallHistoryFragment;

/**
 * 作者：lhj on 2016/8/1 11:40
 * 邮箱：hujiang_2015@yeah.net
 * function:通话记录主导器
 */
public class CalledPresenter {
    /**
     * 初始化通讯录数据
     *
     * @param contentResolver ContentResolver
     */
    public static void calledInit(ContentResolver contentResolver) {
        CalledCacheOperation.selectCalledCacheFromPhone(contentResolver);

    }

    /**
     * 刷新通讯录View
     */
    public static void updateCalledView() {
        CallHistoryFragment callHistoryFragment = GlobalViewVariable.getCallHistoryFragment();
        if (callHistoryFragment != null)
            callHistoryFragment.updateUI();

    }

    /**
     * 调用系统api拨打电话
     *
     * @param telephone 拨打的电话号码
     */
    public static void callTelephone(Context context, String telephone) {
        Intent intent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + telephone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            context.startActivity(intent);
        }

    }
}

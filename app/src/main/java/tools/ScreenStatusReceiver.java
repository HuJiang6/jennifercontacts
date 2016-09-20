package tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 作者：lhj on 2016/5/4 16:43
 * 邮箱：hujiang_2015@yeah.net
 * function: 屏幕状态广播
 */
public class ScreenStatusReceiver extends BroadcastReceiver {

    private String TAG = "ScreenStatusReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            screenOn();
        } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            screenOff();
        } else if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            userPresent();
        }

    }

    /**
     * 用户解锁
     */
    private void userPresent() {
        Log.d(TAG, "userPresent");
    }

    /**
     * 屏幕休眠
     */
    private void screenOff() {
        Log.d(TAG, "screenOff");
    }

    /**
     * 屏幕开屏
     */
    private void screenOn() {
        Log.d(TAG, "screenOn");

    }

}

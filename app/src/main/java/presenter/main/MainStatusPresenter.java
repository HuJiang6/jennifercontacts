package presenter.main;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.contacts.lhj.jennifercontacts.GlobalConstant;
import com.contacts.lhj.jennifercontacts.GlobalVariable;

import tools.PermissionsManage;
import view.main.MainActivity;

/**
 * 作者：lhj on 2016/8/22 10:45
 * 邮箱：hujiang_2015@yeah.net
 * function:主界面状态主导器
 */
public class MainStatusPresenter {

    /**
     * 启动主界面开始
     */
    public static void mainStart(Activity activity) {
        startMainService(activity);
        requestFrameWorkPermission(activity);
    }

    /**
     * 权限申请
     * @param activity Activity
     */
    private static void requestFrameWorkPermission(Activity activity) {
        PermissionsManage permissionsManage = PermissionsManage.
                From(GlobalConstant.Application.PERMISSIONS, activity);
        String[] noPermissins = permissionsManage.checkPermissionSituation();
        if (noPermissins.length > 0) {
            Boolean isAllTrue = permissionsManage.shouldShowRequestPermissionRationale(noPermissins);
            Boolean isFirstOpen = GlobalVariable.getCustomizeSharedPreferences().
                    getSharedPreferences().
                    getBoolean(GlobalConstant.Application.IS_FIRST_OPEN_KEY, true);
            if (isFirstOpen) {
                ActivityCompat.requestPermissions(activity, noPermissins, MainActivity.REQUESTCODE);
            } else {
                if (isAllTrue) { //展示请求权限的理由
                    Toast.makeText(activity, "不给权限玩不爽哦", Toast.LENGTH_SHORT).show();
                } else {
                    ActivityCompat.requestPermissions(activity, noPermissins, MainActivity.REQUESTCODE);
                }
            }
        }
    }

    private static void startMainService(Activity activity) {
        Intent intent = new Intent(activity, MainService.class);
        activity.startService(intent);
    }

    /**
     * 主界面退出
     */
    public static void mainEnd(Activity activity) {
    }
}

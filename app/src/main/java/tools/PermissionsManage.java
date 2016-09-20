package tools;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：lhj on 2016/8/26 10:31
 * 邮箱：hujiang_2015@yeah.net
 * function:权限管理工具类
 */
public class PermissionsManage {

    private String[] requestPermissions;
    private Activity activity;
    private static PermissionsManage permissionsManage;

    private PermissionsManage(String[] requestPermissions, Activity activity) {
        this.requestPermissions = requestPermissions;
        this.activity = activity;
    }

    public static PermissionsManage From(String[] requestPermissions, Activity activity) {
        if (permissionsManage == null) {
            permissionsManage = new PermissionsManage(requestPermissions, activity);
        }
        return permissionsManage;

    }

    /**
     * 检查授权状态
     *
     * @return 未授权的请求授权项（size等于0表示全部都已经授权），授权项没返回的说明是授权成功的。
     */
    public String[] checkPermissionSituation() {
        List<String> noPermission = new ArrayList<>();
        for (String permission :
                this.requestPermissions) {
            Boolean isAuthorize = (
                    ActivityCompat.checkSelfPermission(this.activity, permission)
                            == PackageManager.PERMISSION_GRANTED);
            if (!isAuthorize) {
                noPermission.add(permission);
            }
        }
        int size = noPermission.size();
        String[] noPermissionStr = new String[size];
        for (int i = 0; i < size; i++) {
            noPermissionStr[i] = noPermission.get(i);
        }
        return noPermissionStr;

    }

    /**
     * 是否应该展示请求权限的理由
     *
     * @param requestPermissions - 请求权限数组
     * @return true - all can show permission rationale UI.
     *          false - least one is not allow permission rationale UI.
     * Boolean（public static boolean shouldShowRequestPermissionRationale(
     * android.app.Activity activity, java.lang.String permission)）
     * true 对应的情况是用户拒绝了请求的权限，但是没有勾选不再提醒（第一次请求时是没有不再提醒选
     * 项的，不过返回一样为true），所以建议应该每次说明理由。
     * false 应用安装后第一次访问，直接返回false。但是请求权限时用户勾选了不在提醒选项下次请求权限
     * 也会返回false，所以建议第一次不用说明，后面都需要说明。
     */
    public Boolean shouldShowRequestPermissionRationale(
            String[] requestPermissions) {
        for (String permission :
                requestPermissions) {
            Boolean shouldExplain = ActivityCompat.shouldShowRequestPermissionRationale(
                    this.activity,
                    permission);
            if (!shouldExplain) {
                return false;
            }
        }
        return true;

    }
}

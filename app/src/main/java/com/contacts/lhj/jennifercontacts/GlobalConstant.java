package com.contacts.lhj.jennifercontacts;

import android.Manifest;
import android.app.Activity;

/**
 * 作者：lhj on 2016/8/20 14:46
 * 邮箱：hujiang_2015@yeah.net
 * function:
 */
public class GlobalConstant {

    public static class SharedPreferences {
        public static final String NAME = "jennifer";
        public static final int MODE = Activity.MODE_PRIVATE;
    }

    public static class Application {
        /**
         * 第一次打开app时用于存放于sharedPreference的key
         */
        public static final String IS_FIRST_OPEN_KEY = "isFirstOpen";

        public static final String[] PERMISSIONS = {
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.READ_SMS,
                Manifest.permission.CALL_PHONE
        };
    }
}

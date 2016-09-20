package com.contacts.lhj.jennifercontacts;

import tools.CustomizeSharedPreferences;

/**
 * 作者：lhj on 2016/8/20 14:40
 * 邮箱：hujiang_2015@yeah.net
 * function:
 */
public class GlobalVariable {
    private static CustomizeSharedPreferences customizeSharedPreferences;

    public static CustomizeSharedPreferences getCustomizeSharedPreferences() {
        return customizeSharedPreferences;
    }

    public static void setCustomizeSharedPreferences(CustomizeSharedPreferences customizeSharedPreferences) {
        GlobalVariable.customizeSharedPreferences = customizeSharedPreferences;
    }
}

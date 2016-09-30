package com.contacts.lhj.jennifercontacts;

import android.app.Application;

import com.contacts.lhj.model.contacts.ContactsBean;
import tools.CustomizeSharedPreferences;

/**
 * 作者：lhj on 2016/8/8 18:04
 * 邮箱：hujiang_2015@yeah.net
 * function:
 */
public class ContactsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GlobalVariable.setCustomizeSharedPreferences(
                new CustomizeSharedPreferences(this,
                        GlobalConstant.SharedPreferences.NAME,
                        GlobalConstant.SharedPreferences.MODE));
        ContactsBean.insertCharIndexToDataSource();
    }

}

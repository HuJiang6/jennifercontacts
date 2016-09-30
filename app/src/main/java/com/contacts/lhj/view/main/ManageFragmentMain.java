package com.contacts.lhj.view.main;

import android.support.v4.app.Fragment;

import com.contacts.lhj.view.called.CallHistoryFragment;
import com.contacts.lhj.view.contacts.ContactsFragment;
import com.contacts.lhj.view.messages.MessageFragment;

import java.util.ArrayList;

/**
 * 作者：lhj on 2016/7/30 15:19
 * 邮箱：hujiang_2015@yeah.net
 * function:主界面viewpager中的fragment管理
 */
public class ManageFragmentMain {
    private ArrayList<Fragment> arrayLists;

    public ManageFragmentMain() {
        arrayLists = new ArrayList<>();
        arrayLists.add(CallHistoryFragment.getInstance());
        arrayLists.add(ContactsFragment.getInstance());
        arrayLists.add(MessageFragment.getInstance());
    }

    public ArrayList<Fragment> getArrayLists() {
        return arrayLists;
    }
}

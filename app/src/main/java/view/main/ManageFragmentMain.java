package view.main;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

import view.contacts.ContactsFragment;
import view.called.CallHistoryFragment;
import view.messages.MessageFragment;

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

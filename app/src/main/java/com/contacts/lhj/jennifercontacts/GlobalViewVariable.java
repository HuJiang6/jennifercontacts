package com.contacts.lhj.jennifercontacts;


import com.contacts.lhj.view.called.CallHistoryFragment;
import com.contacts.lhj.view.contacts.ContactsFragment;
import com.contacts.lhj.view.messages.MessageFragment;

/**
 * 作者：lhj on 2016/8/1 11:26
 * 邮箱：hujiang_2015@yeah.net
 * function:常用的View对象(全局View变量)
 */
public class GlobalViewVariable {

    private static ContactsFragment contactsFragment;
    private static CallHistoryFragment callHistoryFragment;
    private static MessageFragment messageFragment;


    public static ContactsFragment getContactsFragment() {
        return contactsFragment;
    }

    public static void setContactsFragment(ContactsFragment contactsFragment) {
        GlobalViewVariable.contactsFragment = contactsFragment;
    }

    public static CallHistoryFragment getCallHistoryFragment() {
        return callHistoryFragment;
    }

    public static void setCallHistoryFragment(CallHistoryFragment callHistoryFragment) {
        GlobalViewVariable.callHistoryFragment = callHistoryFragment;
    }

    public static MessageFragment getMessageFragment() {
        return messageFragment;
    }

    public static void setMessageFragment(MessageFragment messageFragment) {
        GlobalViewVariable.messageFragment = messageFragment;
    }
}

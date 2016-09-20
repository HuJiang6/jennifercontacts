package presenter.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import model.contacts.ContactCacheOperation;
import view.contacts.ContactPersonalHomeActivity;
import view.contacts.ContactsFragment;
import com.contacts.lhj.jennifercontacts.GlobalViewVariable;

/**
 * 作者：lhj on 2016/8/1 11:40
 * 邮箱：hujiang_2015@yeah.net
 * function:通讯录主导器
 */
public class ContactsPresenter {
    /**
     * 初始化通讯录数据
     * @param contentResolver ContentResolver
     */
    public static void contactsInit(ContentResolver contentResolver) {
        ContactCacheOperation.selectContactCacheFromPhone(contentResolver);

    }

    /**
     * 刷新通讯录View
     */
    public static void updateContactsView() {
        ContactsFragment contactsFragment = GlobalViewVariable.getContactsFragment();
        if (contactsFragment != null)
            contactsFragment.updateUI();

    }

    public static void turnToContactPersonalInfoActivity(Context context, String nick) {
        Intent intent = new Intent(context, ContactPersonalHomeActivity.class);
        intent.putExtra("telephone", nick);
        context.startActivity(intent);

    }
}

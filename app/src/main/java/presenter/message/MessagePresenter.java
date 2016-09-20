package presenter.message;

import android.content.ContentResolver;

import model.message.MessageCacheOperation;
import view.messages.MessageFragment;
import com.contacts.lhj.jennifercontacts.GlobalViewVariable;

/**
 * 作者：lhj on 2016/8/1 11:40
 * 邮箱：hujiang_2015@yeah.net
 * function:短信主导器
 */
public class MessagePresenter {
    /**
     * 初始化短信数据
     * @param contentResolver ContentResolver
     */
    public static void messageInit(ContentResolver contentResolver) {
        MessageCacheOperation.selectMessageCacheFromPhone(contentResolver);

    }

    /**
     * 刷新短信View
     */
    public static void updateMessageView() {
        MessageFragment messageFragment = GlobalViewVariable.getMessageFragment();
        if (messageFragment != null)
            messageFragment.updateUI();

    }
}

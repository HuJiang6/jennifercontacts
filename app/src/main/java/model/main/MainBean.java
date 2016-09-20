package model.main;

import com.contacts.lhj.jennifercontacts.R;

/**
 * 作者：lhj on 2016/7/30 15:59
 * 邮箱：hujiang_2015@yeah.net
 * function:普通bean 其他不属于三大类的普通数据
 */
public class MainBean {

    public static String[] getMainTableLayoutStrings() {
        return new String[]{ "Called", "Contact", "Message" };
    }

    public static int[] getMainTableLayoutImageView() {
        return new int[]{
                R.drawable.ic_menu_manage,
                R.drawable.ic_menu_manage,
                R.drawable.ic_menu_manage
        };
    }
}

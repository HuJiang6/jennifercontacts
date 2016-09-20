package model.contacts;

import java.util.LinkedList;

/**
 * 作者：lhj on 2016/8/1 09:30
 * 邮箱：hujiang_2015@yeah.net
 * function:通讯录数组bean
 * attention:使用该类时必须先初始化 即调用方法initContactsBean（）；
 */
public class ContactsBean {

    private static LinkedList<ContactBean> contactsCache;

    /**
     * 初始化通讯录数组 插入字母索引
     */
    public static void insertCharIndexToDataSource() {
        /**
         * 字母表
         */
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        contactsCache = new LinkedList<>();
        for (int i = 0; i < alphabet.length(); i++) {
            contactsCache.addLast(new ContactBean(ContactBean.CHARACTER_TYPE, alphabet.charAt(i)));
        }

    }

    public static LinkedList<ContactBean> getContactsCache() {
        return contactsCache;
    }

    /**
     * 加入一个联系人
     * @param contactBean ContactBean
     */
    public static void addContactBean(ContactBean contactBean) {
        char firstChar = contactBean.getHeadChar();
        int pos = getLastContactBeanPositionByFirstChar(firstChar);
        if (pos == -1) {
            contactsCache.addLast(contactBean);
        } else {
            contactsCache.add(pos, contactBean);
        }

    }

    /**
     * 通过首字母获取该首字母的最后一个元素在contactsCache数组中的位置+1
     * @param firstChar 查询首字母
     * @return 该首字母最后一个元素所在位置+1 不存在返回-1（这时出现异常了）
     */
    private static int getLastContactBeanPositionByFirstChar(char firstChar) {
        int size = contactsCache.size() - 1;
        for (int i = size; i >= 0; --i) {
            ContactBean contactBean = contactsCache.get(i);
            char c = contactBean.getHeadChar();
            if (firstChar == c)
                return ++i;
        }
        return -1;

    }

    /**
     * 通过电话号码查询备注昵称
     *
     * @param telephone 电话号码
     * @return if exist 电话号码备注昵称 else 电话号码
     */
    public static String getNameByNumber(String telephone) {
        if (contactsCache != null) {
            for (ContactBean contacter :
                    contactsCache) {
                String telephone1 = contacter.getTelephone();
                if (telephone.equals(telephone1))
                    return contacter.getRemarks();
            }
        }
        return telephone;

    }

    /**
     * 通讯录备注提重（只允许一个备注名称）
     */
    public static void remarksRepeatTidy() {
        String remarkTarget = "start";
        LinkedList<ContactBean> tidyContacts = new LinkedList<>();
        if (contactsCache != null) {
            for (ContactBean contact :
                    contactsCache) {
                String remark = contact.getRemarks();
                if (remark.equals(remarkTarget)) {

                } else {
                    tidyContacts.addLast(contact);
                }
            }
        }
    }
}

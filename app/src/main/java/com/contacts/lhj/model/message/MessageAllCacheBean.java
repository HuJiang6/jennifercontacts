package com.contacts.lhj.model.message;

import java.util.ArrayList;

import com.contacts.lhj.presenter.message.MessagePresenter;

/**
 * 作者：lhj on 2016/8/1 10:08
 * 邮箱：hujiang_2015@yeah.net
 * function:所有短信对象bean实体类
 */
public class MessageAllCacheBean {

    /**
     * 短信缓存数据源
     */
    private static ArrayList<MessageItemCacheBean> cacheBeen;

    public static ArrayList<MessageItemCacheBean> getCacheBeen() {
        if (cacheBeen == null) {
            return new ArrayList<>();
        } else {
            return cacheBeen;
        }

    }

    public static void initBean() {
        cacheBeen = new ArrayList<>();
    }

    public static void addOneMessage(String telephone, String date, String text, int type) {
        MessageBody message = new MessageBody(date, text, type);
        int position = getIndexCacheBean(telephone);
        if (position == -1) {
            MessageItemCacheBean itemBean = new MessageItemCacheBean(telephone, message);
            cacheBeen.add(itemBean);
        } else {
            MessageItemCacheBean itemBean = cacheBeen.get(position);
            itemBean.addLatestMessage(message);
        }
        MessagePresenter.updateMessageView();

    }

    /**
     * 查询该号码所在数据源的索引
     * @param telephone 查询电话号码
     * @return 不存在返回-1 存在返回索引
     */
    private static int getIndexCacheBean(String telephone) {
        for (int i = 0; i < cacheBeen.size(); i++) {
            MessageItemCacheBean itemBean = cacheBeen.get(i);
            String itemPhone = itemBean.getTelephone();
            if (itemPhone.equals(telephone)) {
                return i;
            }
        }
        return -1;

    }
}

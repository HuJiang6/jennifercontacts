package model.message;

import java.util.ArrayList;

import model.contacts.ContactsBean;

/**
 * 作者：lhj on 2016/8/1 09:30
 * 邮箱：hujiang_2015@yeah.net
 * function:一行短信缓存bean
 */
public class MessageItemCacheBean {

    /**
     * 一个号码的最后一条消息
     */
    private MessageBody lastMessage;
    /**
     * 所有的消息
     */
    private ArrayList<MessageBody> allMessage;
    /**
     * 该条缓存消息的所属电话号码
     */
    private String telephone;

    public MessageItemCacheBean(String telephone, MessageBody message) {
        setTelephone(telephone);
        setLastMessage(message);
        ArrayList<MessageBody> messages = new ArrayList<>();
        messages.add(message);
        setAllMessage(messages);
    }

    public String getTelephone() {
        return telephone;
    }

    public String getName() {
        return ContactsBean.getNameByNumber(telephone);
    }

    public void addLatestMessage(MessageBody messageBody) {
        setLastMessage(messageBody);
        this.allMessage.add(messageBody);
    }

    public MessageBody getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageBody lastMessage) {
        this.lastMessage = lastMessage;
    }

    public ArrayList<MessageBody> getAllMessage() {
        return allMessage;
    }

    public void setAllMessage(ArrayList<MessageBody> allMessage) {
        this.allMessage = allMessage;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}

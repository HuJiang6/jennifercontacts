package com.contacts.lhj.model.message;

/**
 * 作者：lhj on 2016/9/14 11:17
 * 邮箱：hujiang_2015@yeah.net
 * function:一条消息的消息内容
 */
public class MessageBody {
    private String date;
    private String text;
    /**
     * 1的时候为对方发的短信 其他的为自己发送出去的短信
     */
    private int type;

    public MessageBody(String date, String text, int type) {
        setDate(date);
        setText(text);
        setType(type);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

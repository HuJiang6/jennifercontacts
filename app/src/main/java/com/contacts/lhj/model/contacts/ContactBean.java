package com.contacts.lhj.model.contacts;

import tools.StringHelper;

/**
 * 作者：lhj on 2016/8/1 10:08
 * 邮箱：hujiang_2015@yeah.net
 * function:联系人对象bean
 */
public class ContactBean {

    public final static int CONTACT_TYPE = 2;
    public final static int CHARACTER_TYPE = 1;
    /**
     * 类型 暂时区分字母与联系人
     * 1 字母
     * 2 联系人
     */
    private int type;
    /**
     * 索引字母
     */
    private char character;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 头像
     */
    private Long avatar;

    public ContactBean(int type, char character) {
        setType(type);
        setCharacter(character);
    }

    public ContactBean(int type, String remarks, String telephone, Long avatar) {
        setType(type);
        setRemarks(remarks);
        setTelephone(telephone);
        setAvatar(avatar);
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getAvatar() {
        return avatar;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    /**
     * @return 中文首字母（只有一个）
     */
    public char getHeadChar() {
        if (remarks == null)
            return character;
        else
            return StringHelper.getHeadChar(remarks).charAt(0);

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}

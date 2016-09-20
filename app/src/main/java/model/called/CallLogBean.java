package model.called;

import com.contacts.lhj.jennifercontacts.R;

/**
 * 作者：lhj on 2016/8/1 15:30
 * 邮箱：hujiang_2015@yeah.net
 * function: 单个通话记录bean(实体类)【通话记录的一行】
 */
public class CallLogBean {
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 号码
     */
    private String number;
    /**
     * 日期
     */
    private String date;
    /**
     * 来电:1，拨出:2,未接:3
     */
    private int type;
    /**
     * 通话次数
     */
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        switch (type) {
            case 1:
                this.type = R.drawable.ic_calllog_incomming_normal;
                break;
            case 2:
                this.type = R.drawable.ic_calllog_outgoing_nomal;
                break;
            case 3:
                this.type = R.drawable.ic_calllog_missed_normal;
                break;
            default:
                break;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

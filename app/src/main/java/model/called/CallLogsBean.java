package model.called;

import java.util.LinkedList;

import presenter.called.CalledPresenter;

/**
 * 作者：lhj on 2016/8/1 15:36
 * 邮箱：hujiang_2015@yeah.net
 * function:整个通话记录的实体类
 */
public class CallLogsBean {

    private static LinkedList<CallLogBean> callLogBeens;


    public static LinkedList<CallLogBean> getCallLogBeens() {
        if (callLogBeens == null)
            callLogBeens = new LinkedList<>();
        return callLogBeens;

    }

    public static void setCallLogBeens(LinkedList<CallLogBean> callLogBeens) {
        CallLogsBean.callLogBeens = callLogBeens;
        CalledPresenter.updateCalledView();

    }
}

package tools;

/**
 * 作者：lhj on 2016/8/9 17:04
 * 邮箱：hujiang_2015@yeah.net
 * function:监听SideBar的接口
 */
public interface OnTouchListenChangeForSideBar {
    /**
     * 放开触摸
     *
     * @param s 触摸位置所在的字母
     */
    void onTouchUp(String s);

    /**
     * 触摸状态（一直在触摸）
     *
     * @param s 触摸位置所在的字母
     */
    void onTouching(String s);

    /**
     * 失去焦点
     * The current gesture has been aborted. You will not receive any more points in it.
     * You should treat this as an up event, but not perform any action that you normally would.
     *
     * @param s 触摸位置所在的字母
     */
    void onTouchingCancel(String s);
}

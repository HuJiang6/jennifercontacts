package tools;

/**
 * 作者：lhj on 2016/8/22 09:54
 * 邮箱：hujiang_2015@yeah.net
 * function:
 */
public interface ScreenStatusListener {
    /**
     * 屏幕开屏
     */
    void screenOn();

    /**
     * 屏幕休眠
     */
    void screenOff();

    /**
     * 用户解锁
     */
    void userPresent();
}

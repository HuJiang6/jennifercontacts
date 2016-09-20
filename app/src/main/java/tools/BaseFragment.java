package tools;

import android.support.v4.app.Fragment;

/**
 * 作者：lhj
 * 创建日期：2016/3/29
 * 功能：将Fragment可见与不可见的时候的状态接口留出来（比如取消预加载、延迟加载的时候可以用到）
 * 邮箱：hujiang_2015@yeah.net
 */
public abstract class BaseFragment extends Fragment {
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见时的回调方法
     */
    protected abstract void onVisible();

    /**
     * 不可见时的回调方法
     */
    protected abstract void onInvisible();
}

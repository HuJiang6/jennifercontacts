package view.main;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.contacts.lhj.jennifercontacts.GlobalConstant;
import com.contacts.lhj.jennifercontacts.GlobalVariable;
import com.contacts.lhj.jennifercontacts.R;

import java.util.List;

/**
 * 作者：lhj on 2016/8/20 11:13
 * 邮箱：hujiang_2015@yeah.net
 * function:引导页ViewPager适配器
 */
public class GuideViewPagerAdapter extends PagerAdapter {

    private List<Integer> views;
    private GuideActivity guideActivity;
    private LayoutInflater inflater;

    public GuideViewPagerAdapter(List<Integer> views, GuideActivity guideActivity) {
        this.views = views;
        this.guideActivity = guideActivity;
        inflater = LayoutInflater.from(this.guideActivity);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View currentView = inflater.inflate(views.get(position), container, false);
        container.addView(currentView);
        if (position == views.size() - 1) {
            ImageView mStartWeiboImageButton = (ImageView) container
                    .findViewById(R.id.iv_start_weibo);
            mStartWeiboImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 设置已经引导
                    setGuided();
                    guideActivity.jumpToGestureLoginActivity();
                }

            });
        }
        return currentView;

    }

    private void setGuided() {
        GlobalVariable.getCustomizeSharedPreferences()
                .putBooleanToSharedPreferences(
                        GlobalConstant.Application.IS_FIRST_OPEN_KEY,
                        false);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return views.size();
    }

    /**
     * 判断是否由对象生成界面
     * Determines whether a page View is associated with a specific key object
     * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view   Page View to check for association with <code>object</code>
     * @param object Object to check for association with <code>view</code>
     * @return true if <code>view</code> is associated with the key object <code>object</code>
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View currentView = inflater.inflate(views.get(position), container, false);
        container.removeView(currentView);
    }
}

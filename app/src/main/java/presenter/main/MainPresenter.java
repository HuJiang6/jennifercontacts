package presenter.main;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;

import model.main.MainBean;
import tools.Item;

/**
 * 作者：lhj on 2016/7/30 16:34
 * 邮箱：hujiang_2015@yeah.net
 * function:主界面主导器
 */
public class MainPresenter {

    public static void mainTableLayoutPresenter(TabLayout tabLayout, Context context) {
        int tabCount = tabLayout.getTabCount();
        String[] strings = MainBean.getMainTableLayoutStrings();
        int[] images = MainBean.getMainTableLayoutImageView();

        if (tabCount != strings.length) {
            throw new IllegalArgumentException("Tab count is not equal the string's length");
        } else {
            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                assert tab != null;
                tab.setCustomView(Item.getItemOfImageViewAddTextView(strings[i], images[i], context));
            }
        }

        TabLayout.Tab tab = tabLayout.getTabAt(0);
        assert tab != null;
        View view = tab.getCustomView();
        assert view != null;
        view.setSelected(true);

    }
}

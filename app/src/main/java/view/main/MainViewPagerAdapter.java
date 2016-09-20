package view.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 作者：lhj on 2016/7/30 15:18
 * 邮箱：hujiang_2015@yeah.net
 * function:主界面viewpager适配器
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayLists;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        ManageFragmentMain manageFragmentMain = new ManageFragmentMain();
        arrayLists = manageFragmentMain.getArrayLists();
    }

    @Override
    public Fragment getItem(int position) {
        return arrayLists.get(position);
    }

    @Override
    public int getCount() {
        return arrayLists.size();
    }

}

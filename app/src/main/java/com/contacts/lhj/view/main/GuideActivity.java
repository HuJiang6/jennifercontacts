package com.contacts.lhj.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.contacts.lhj.jennifercontacts.BaseActivity;
import com.contacts.lhj.jennifercontacts.R;

import java.util.LinkedList;
import java.util.List;

/**
 * 新注册引导页
 */
public class GuideActivity extends BaseActivity {

    private List<Integer> viewPagerData;
    private ImageView[] dots;
    private int currentDotIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        LinearLayout guideLinear = (LinearLayout) findViewById(R.id.guide_linear);
        ViewPager guideViewPager = (ViewPager) findViewById(R.id.guide_viewpager);

        initViewPager(guideViewPager);
        initLinearLayout(guideLinear);

    }

    public void jumpToGestureLoginActivity() {
        Intent intent = new Intent(GuideActivity.this, GestureLoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void initViewPager(ViewPager guideviewpager) {
        viewPagerData = getLayoutData();
        GuideViewPagerAdapter guideViewPagerAdapter = new GuideViewPagerAdapter(viewPagerData, this);
        assert guideviewpager != null;
        guideviewpager.setAdapter(guideViewPagerAdapter);
        guideviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSelectDotState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initLinearLayout(LinearLayout guideLinear) {
        int dotsNumber = viewPagerData.size();
        dots = new ImageView[dotsNumber];

        for (int i = 0; i < dotsNumber; i++) {
            dots[i] = (ImageView) guideLinear.getChildAt(i);
            dots[i].setEnabled(true);
        }
        //设置默认选中点位置
        currentDotIndex = 0;
        dots[currentDotIndex].setEnabled(false);
    }

    private void setSelectDotState(int position) {
        dots[position].setEnabled(false);
        dots[currentDotIndex].setEnabled(true);
        currentDotIndex = position;
    }

    private List<Integer> getLayoutData() {
        List<Integer> integers = new LinkedList<>();
        integers.add(R.layout.what_new_one);
        integers.add(R.layout.what_new_two);
        integers.add(R.layout.what_new_three);
        integers.add(R.layout.what_new_four);
        return integers;

    }
}

package tools;

import android.view.View;

/**
 * 作者：lhj on 2016/7/18 15:51
 * 邮箱：hujiang_2015@yeah.net
 * function:The Interface of RecycleView Item Listener
 */
public interface RecycleViewOnItemClickListener {

    void onItemClick(View view, int position);
    void onItemLongClick(View view, int position);

}

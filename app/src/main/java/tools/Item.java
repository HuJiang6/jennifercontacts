package tools;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.contacts.lhj.jennifercontacts.R;

/**
 * 作者：lhj on 2016/7/30 16:00
 * 邮箱：hujiang_2015@yeah.net
 * function:主界面tableLayout控件Item获取管理
 */
public class Item {
    /**
     * 获取一个由ImageView+TextView（线性垂直布局）组成的Item
     * @param s tab title
     * @param image tab image
     * @param context Context
     * @return View
     */
    public static View getItemOfImageViewAddTextView(String s, int image, Context context) {
        LinearLayout linearLayout = new LinearLayout(context);

        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(image);
        linearLayout.addView(imageView);

        TextView textView = new TextView(context);
        textView.setText(s);
        textView.setGravity(Gravity.CENTER);
        linearLayout.addView(textView);

        return linearLayout;

    }

}

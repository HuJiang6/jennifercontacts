package tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者：lhj on 2016/8/9 16:07
 * 邮箱：hujiang_2015@yeah.net
 * function:带A-Z索引的控件
 */
public class SideBar extends View {

    private OnTouchListenChangeForSideBar listener;
    /**
     * 字母表
     */
    private String[] alphabet = {"☆", "#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    /**
     * 选中标识
     * >0表示选中的位置 -1表示未选中
     */
    private int choose = -1;
    /**
     * 控件的宽度
     */
    private int width;
    /**
     * 每一个字母的高度
     */
    private int singleHeight;
    /**
     * 控件的高度
     */
    private int height;

    public SideBar(Context context) {
        super(context);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        height = getHeight();
        width = getWidth();
        singleHeight = height / alphabet.length;

        drawAlphabet(canvas);

    }

    /**
     * 绘制字母表
     *
     * @param canvas Canvas
     */
    private void drawAlphabet(Canvas canvas) {
        Paint paint = new Paint();
        for (int i = 0; i < alphabet.length; i++) {
            paint.setAntiAlias(true);
            paint.setTextSize(30);
            if (!isInEditMode()) {
                paint.setColor(Color.parseColor("#838383"));
            }
            if (i == choose) { //被选中
                paint.setColor(Color.parseColor("#f0f1c207"));
                paint.setFakeBoldText(true);
            }
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width / 2 - paint.measureText(alphabet[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(alphabet[i], xPos, yPos, paint);
            paint.reset();

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        /**
         * 触摸事件的y坐标
         */
        float y = event.getY();
        int oldChoose = choose;
        /**
         * 点击字母所在位置
         * 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
         */
        int c = (int) (y / height * alphabet.length);
        if (c > alphabet.length)
            c -= 1;
        String Tag = "MotionEvent";
        Log.d(Tag, String.valueOf(action));
        switch (action) {
            case MotionEvent.ACTION_UP: //放开
                if (listener != null) {
                    listener.onTouchUp(alphabet[c]);
                }
                unSelected();
                break;
            case MotionEvent.ACTION_MOVE: //移动
                if (oldChoose != c) {
                    if (listener != null) {
                        listener.onTouching(alphabet[c]);
                    }
                    choose = c;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL: //失去焦点
                if (listener != null) {
                    listener.onTouchingCancel(alphabet[c]);
                }
                unSelected();
                break;
            default:
                break;
        }
        return true;

    }

    private void unSelected() {
        setBackgroundColor(0x00000000);
        choose = -1;
        invalidate();
    }

    public void setListener(OnTouchListenChangeForSideBar listener) {
        this.listener = listener;
    }
}

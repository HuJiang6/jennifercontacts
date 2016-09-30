package com.contacts.lhj.model.message;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 作者：lhj on 2016/8/1 09:53
 * 邮箱：hujiang_2015@yeah.net
 * function:短信缓存数据操作
 */
public class MessageCacheOperation {

    /**
     * 从手机数据库中查询短信缓存数据
     */
    public static void selectMessageCacheFromPhone(ContentResolver contentResolver) {
        AsyncQueryHandler asyncQueryHandler = new MessageAsyncQueryHandler(contentResolver);

        Uri uri = Uri.parse("content://sms");
        String[] projection = new String[]{"date", "address", "person", "body", "type"};
        asyncQueryHandler.startQuery(0, null, uri, projection,
                null, null, "date asc");

    }

    private static class MessageAsyncQueryHandler extends AsyncQueryHandler {
        public MessageAsyncQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            if (cursor != null) {
                int count = cursor.getCount();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
                if (count > 0) {
                    MessageAllCacheBean.initBean();
                    cursor.moveToFirst();
                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToPosition(i);
                        String date = sdf.format(new Date(cursor.getLong(cursor
                                .getColumnIndex("date"))));
                        String text = cursor.getString(cursor.getColumnIndex("body"));
                        int type = cursor.getInt(cursor.getColumnIndex("type"));
                        String number = cursor.getString(cursor.getColumnIndex("address"));
                        String person = cursor.getString(cursor.getColumnIndex("person"));

                        MessageAllCacheBean.addOneMessage(number, date, text, type);
                    }
                }
            }

            super.onQueryComplete(token, cookie, cursor);

        }

    }
}

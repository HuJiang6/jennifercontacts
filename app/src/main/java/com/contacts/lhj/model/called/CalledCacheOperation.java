package com.contacts.lhj.model.called;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

/**
 * 作者：lhj on 2016/8/1 09:53
 * 邮箱：hujiang_2015@yeah.net
 * function:通话记录缓存数据操作
 */
public class CalledCacheOperation {
    /**
     * 从手机数据库中查询通话记录缓存数据
     */
    public static void selectCalledCacheFromPhone(ContentResolver contentResolver) {
        AsyncQueryHandler asyncQueryHandler = new CalledAsyncQueryHandler(contentResolver);
        Uri uri = android.provider.CallLog.Calls.CONTENT_URI;

        // 查询的列
        String[] projection = {
                CallLog.Calls.DATE, // 日期
                CallLog.Calls.NUMBER, // 号码
                CallLog.Calls.TYPE, // 类型
                CallLog.Calls.CACHED_NAME, // 名字
                CallLog.Calls._ID // id
        };

        asyncQueryHandler.startQuery(0, null, uri, projection, null, null,
                CallLog.Calls.DEFAULT_SORT_ORDER);

    }

    private static class CalledAsyncQueryHandler extends AsyncQueryHandler {
        public CalledAsyncQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                LinkedList<CallLogBean> callLogs = new LinkedList<>();
                SimpleDateFormat sfd = new SimpleDateFormat("MM-dd hh:mm", Locale.CHINA);
                Date date;
                cursor.moveToFirst(); // 游标移动到第一项
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    date = new Date(cursor.getLong(cursor
                            .getColumnIndex(CallLog.Calls.DATE)));
                    String number = cursor.getString(cursor
                            .getColumnIndex(CallLog.Calls.NUMBER));
                    int type = cursor.getInt(cursor
                            .getColumnIndex(CallLog.Calls.TYPE));
                    String cachedName = cursor.getString(cursor
                            .getColumnIndex(CallLog.Calls.CACHED_NAME));//缓存的名称与电话号码，如果它存在
                    int id = cursor.getInt(cursor
                            .getColumnIndex(CallLog.Calls._ID));

                    CallLogBean callLogBean = new CallLogBean();
                    callLogBean.setId(id);
                    callLogBean.setNumber(number);
                    callLogBean.setName(cachedName);
                    if (null == cachedName || "".equals(cachedName)) {
                        callLogBean.setName(number);
                    }
                    callLogBean.setType(type);
                    callLogBean.setDate(sfd.format(date));

                    callLogs.add(callLogBean);
                }
                if (callLogs.size() > 0)
                    CallLogsBean.setCallLogBeens(callLogs);
            }
            super.onQueryComplete(token, cookie, cursor);

        }

    }
}

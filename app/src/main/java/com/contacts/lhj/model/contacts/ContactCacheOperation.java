package com.contacts.lhj.model.contacts;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.contacts.lhj.presenter.contacts.ContactsPresenter;

/**
 * 作者：lhj on 2016/8/1 09:53
 * 邮箱：hujiang_2015@yeah.net
 * function:通讯录缓存数据操作
 */
public class ContactCacheOperation {

    /**
     * 查询字段
     */
    public final static String[] FIELD = {
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.DATA1,
            ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.PHOTO_ID,
            ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY
    };

    /**
     * 从手机数据库中查询通讯录缓存数据
     */
    public static void selectContactCacheFromPhone(ContentResolver contentResolver) {
        AsyncQueryHandler asyncQueryHandler = new ContactAsyncQueryHandler(contentResolver);
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // 按照sort_key升序查詢
        asyncQueryHandler.startQuery(0, null, uri, FIELD, null, null,
                "sort_key COLLATE LOCALIZED asc");

    }

    private static class ContactAsyncQueryHandler extends AsyncQueryHandler {

        public ContactAsyncQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    String name = cursor.getString(1);
                    String number = cursor.getString(2);
                    String sortKey = cursor.getString(3);
                    int contactId = cursor.getInt(4);
                    Long photoId = cursor.getLong(5);
                    String lookUpKey = cursor.getString(6);

                    String Tag = "cursor+";
                    Log.d(Tag, "sortKey"+sortKey);
                    Log.d(Tag, "contactId"+contactId);
                    Log.d(Tag, "photoId="+photoId);
                    Log.d(Tag, "lookUpKey="+lookUpKey);
                    ContactsBean.addContactBean(
                            new ContactBean(ContactBean.CONTACT_TYPE, name, number, photoId));
                    ContactsPresenter.updateContactsView();

                }

            }
            super.onQueryComplete(token, cookie, cursor);

        }

    }
}

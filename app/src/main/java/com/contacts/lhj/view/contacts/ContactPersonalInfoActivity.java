package com.contacts.lhj.view.contacts;

import android.os.Bundle;

import com.contacts.lhj.jennifercontacts.BaseActivity;
import com.contacts.lhj.jennifercontacts.R;

public class ContactPersonalInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //数据已经全部查出，就在短信列表，只需要提重然后放入本窗口即可
        setContentView(R.layout.activity_contact_personal_infor);
        String telephone = (String) this.getIntent().getExtras().get("telephone");

    }
}

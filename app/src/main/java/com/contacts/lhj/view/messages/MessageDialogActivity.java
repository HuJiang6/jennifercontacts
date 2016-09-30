package com.contacts.lhj.view.messages;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.contacts.lhj.jennifercontacts.BaseActivity;
import com.contacts.lhj.jennifercontacts.R;
import com.contacts.lhj.model.message.MessageAllCacheBean;
import com.contacts.lhj.model.message.MessageItemCacheBean;
import com.contacts.lhj.presenter.called.CalledPresenter;
import com.contacts.lhj.presenter.contacts.ContactsPresenter;

import java.util.ArrayList;

import tools.RecycleViewOnItemClickListener;

public class MessageDialogActivity extends BaseActivity {

    private MessageDialogRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_message_dialog);
        int position = (int) this.getIntent().getExtras().get("position");
        ArrayList<MessageItemCacheBean> cacheBeanArrayList = MessageAllCacheBean.getCacheBeen();
        MessageItemCacheBean itemCacheBean = cacheBeanArrayList.get(position);

        initToolBar(this, itemCacheBean);
        initRecyclerView(itemCacheBean);
        initEditViewLin();
    }

    private void initRecyclerView(MessageItemCacheBean itemCacheBean) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMessageDialog);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerViewAdapter = new MessageDialogRecyclerViewAdapter(this, itemCacheBean);
        recyclerView.setAdapter(this.recyclerViewAdapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(
//                this, DividerItemDecoration.VERTICAL_LIST));
        recyclerViewOnclickListen();
    }

    private void recyclerViewOnclickListen() {
        this.recyclerViewAdapter.setListener(new RecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, "onItemClick+position"+position, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Snackbar.make(view, "onItemLongClick+position"+position, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void initEditViewLin() {
        AppCompatImageButton buttonMore =
                (AppCompatImageButton) findViewById(R.id.imageMoreMessageDialog);
        AppCompatImageButton buttonSend =
                (AppCompatImageButton) findViewById(R.id.imageSendMessageDialog);
        buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "onItemClick+buttonMore", Snackbar.LENGTH_SHORT).show();
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "onItemClick+buttonSend", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolBar(final Context context, MessageItemCacheBean itemCacheBean) {
        final String name = itemCacheBean.getName();
        final String phone = itemCacheBean.getTelephone();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMessageDialog);
        toolbar.setNavigationIcon(R.drawable.back);//导航图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle(name);//设置主标题
        toolbar.setSubtitle(phone);//设置子标题
        toolbar.inflateMenu(R.menu.menu_message_dialog);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.call_menu_message_dialog) {
                    CalledPresenter.callTelephone(context, phone);
                } else if (menuItemId == R.id.personal_home_message_dialog) {
                    ContactsPresenter.turnToContactPersonalInfoActivity(context, name);
                } else {
                    Log.d("123", "else");
                }
                return true;
            }
        });
    }
}

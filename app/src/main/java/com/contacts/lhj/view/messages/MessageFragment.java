package com.contacts.lhj.view.messages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contacts.lhj.jennifercontacts.GlobalViewVariable;
import com.contacts.lhj.jennifercontacts.R;
import com.contacts.lhj.presenter.message.MessagePresenter;

import tools.DividerItemDecoration;
import tools.RecycleViewOnItemClickListener;

/**
 * 作者：lhj on 2016/7/30 15:32
 * 邮箱：hujiang_2015@yeah.net
 * function:信息
 */
public class MessageFragment extends Fragment {
    private static Fragment instance;
    private MessageRecyclerViewAdapter messageRecyclerViewAdapter;

    public static Fragment getInstance() {
        if (null == instance) {
            instance = new MessageFragment();
        }
        return instance;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MessagePresenter.messageInit(this.getContext().getContentResolver());

        View view = inflater.inflate(R.layout.recycler_view, container, false);
        RecyclerView recyclerViewContacts =
                (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewContacts.setLayoutManager(linearLayoutManager);
        messageRecyclerViewAdapter = new MessageRecyclerViewAdapter(getContext());
        recyclerViewContacts.setAdapter(messageRecyclerViewAdapter);
        recyclerViewContacts.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerViewOnclickListen();

        return view;

    }

    private void recyclerViewOnclickListen() {
        this.messageRecyclerViewAdapter.setListener(new RecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, "onItemClick+position"+position, Snackbar.LENGTH_SHORT).show();
                turnToMessageDialogActivity(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Snackbar.make(view, "onItemLongClick+position"+position, Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    private void turnToMessageDialogActivity(int position) {
        Intent intent = new Intent(getActivity(), MessageDialogActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Live", "MessageFragment onCreate");
        GlobalViewVariable.setMessageFragment(this);

    }

    public void updateUI() {
        if (messageRecyclerViewAdapter != null) {
            messageRecyclerViewAdapter.messageBeenInit();
            messageRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}

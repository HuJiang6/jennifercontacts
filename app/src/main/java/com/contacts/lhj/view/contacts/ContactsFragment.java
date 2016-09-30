package com.contacts.lhj.view.contacts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.contacts.lhj.jennifercontacts.GlobalViewVariable;
import com.contacts.lhj.jennifercontacts.R;
import com.contacts.lhj.presenter.contacts.ContactsPresenter;

import tools.DividerItemDecoration;
import tools.OnTouchListenChangeForSideBar;
import tools.RecycleViewOnItemClickListener;
import tools.SideBar;


/**
 * 作者：lhj on 2016/7/30 15:31
 * 邮箱：hujiang_2015@yeah.net
 * function:通讯录
 */
public class ContactsFragment extends Fragment {
    private static Fragment instance;
    private ContactsRecyclerViewAdapter contactsRecyclerViewAdapter;
    private RecyclerView contactsRecyclerView;
    private TextView contactsCharShow;
    private SideBar contactsSidebar;

    public static Fragment getInstance() {
        if (null == instance) {
            instance = new ContactsFragment();
        }
        return instance;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("Live", "ContactsFragment onCreateView");
        ContactsPresenter.contactsInit(this.getContext().getContentResolver());

        View view = inflater.inflate(R.layout.contacts_view, container, false);
        this.contactsSidebar = (SideBar) view.findViewById(R.id.contactsSidebar);
        this.contactsCharShow = (TextView) view.findViewById(R.id.contactsCharShow);
        this.contactsRecyclerView = (RecyclerView) view.findViewById(R.id.contactsRecyclerView);

        contactsRecyclerViewDeal();
        contactsSidebarDeal();

        return view;

    }

    private void contactsSidebarDeal() {
        this.contactsSidebar.setListener(new OnTouchListenChangeForSideBar() {
            @Override
            public void onTouchUp(String s) {
                if (contactsRecyclerViewAdapter != null) {
                    contactsCharShow.setVisibility(View.INVISIBLE);
                    int position = contactsRecyclerViewAdapter.getPositionForSelected(s.charAt(0));
                    if (position == -1)
                        return;
                    contactsRecyclerView.scrollToPosition(position);

                }
            }

            @Override
            public void onTouching(String s) {
                setContactsCharShow(s);
            }

            @Override
            public void onTouchingCancel(String s) {
                contactsCharShow.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void contactsRecyclerViewDeal() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        StaggeredGridLayoutManager stagger = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        this.contactsRecyclerView.setLayoutManager(linearLayoutManager);
        contactsRecyclerViewAdapter = new ContactsRecyclerViewAdapter(getContext());
        this.contactsRecyclerView.setAdapter(contactsRecyclerViewAdapter);
        this.contactsRecyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerViewOnclickListen();

    }

    private void recyclerViewOnclickListen() {
        this.contactsRecyclerViewAdapter.setListener(new RecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, "onItemClick+position"+position, Snackbar.LENGTH_SHORT).show();
                String nick = contactsRecyclerViewAdapter.getRemarks(position);
                ContactsPresenter.turnToContactPersonalInfoActivity(instance.getContext(), nick);

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Snackbar.make(view, "onItemLongClick+position"+position, Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Live", "ContactsFragment onCreate");
        GlobalViewVariable.setContactsFragment(this);

    }

    public void updateUI() {
        if (contactsRecyclerViewAdapter != null) {
            contactsRecyclerViewAdapter.ContactersInit();
            contactsRecyclerViewAdapter.notifyDataSetChanged();
        }

    }

    private void setContactsCharShow(String s) {
        this.contactsCharShow.setText(s);
        this.contactsCharShow.setVisibility(View.VISIBLE);

    }
}

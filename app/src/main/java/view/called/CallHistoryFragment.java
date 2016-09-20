package view.called;

import android.content.Intent;
import android.net.Uri;
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

import tools.DividerItemDecoration;
import com.contacts.lhj.jennifercontacts.R;
import tools.RecycleViewOnItemClickListener;

import presenter.called.CalledPresenter;
import com.contacts.lhj.jennifercontacts.GlobalViewVariable;

/**
 * 作者：lhj on 2016/7/30 15:31
 * 邮箱：hujiang_2015@yeah.net
 * function:通话记录
 */
public class CallHistoryFragment extends Fragment {

    private static Fragment instance;
    private CalledRecyclerViewAdapter calledRecyclerViewAdapter;

    public static Fragment getInstance() {
        if (null == instance) {
            instance = new CallHistoryFragment();
        }
        return instance;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("Live", "CallHistoryFragment onCreateView");
        CalledPresenter.calledInit(this.getContext().getContentResolver());

        View view = inflater.inflate(R.layout.recycler_view, container, false);
        RecyclerView recyclerViewContacts =
                (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewContacts.setLayoutManager(linearLayoutManager);
        calledRecyclerViewAdapter = new CalledRecyclerViewAdapter(getContext());
        recyclerViewContacts.setAdapter(calledRecyclerViewAdapter);
        recyclerViewContacts.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerViewOnclickListen();

        return view;

    }

    private void recyclerViewOnclickListen() {
        this.calledRecyclerViewAdapter.setListener(new RecycleViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String telephone = calledRecyclerViewAdapter.getTelephone(position);
                CalledPresenter.callTelephone(instance.getContext(), telephone);
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
        Log.d("Live", "CallHistoryFragment onCreate");
        GlobalViewVariable.setCallHistoryFragment(this);

    }

    public void updateUI() {
        if (calledRecyclerViewAdapter != null) {
            calledRecyclerViewAdapter.callLogBeenInit();
            calledRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}

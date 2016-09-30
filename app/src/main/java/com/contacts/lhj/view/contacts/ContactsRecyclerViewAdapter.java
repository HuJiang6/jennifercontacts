package com.contacts.lhj.view.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.contacts.lhj.jennifercontacts.R;
import com.contacts.lhj.model.contacts.ContactBean;
import com.contacts.lhj.model.contacts.ContactsBean;

import java.util.LinkedList;

import tools.RecycleViewOnItemClickListener;

/**
 * 作者：lhj on 2016/8/1 10:43
 * 邮箱：hujiang_2015@yeah.net
 * function:通讯录RecyclerView适配器
 */
public class ContactsRecyclerViewAdapter
        extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactsViewHolder> {

    private String Tag = "ContactsRecyclerViewAdapter";
    private Context context;
    private LayoutInflater inflater;
    private LinkedList<ContactBean> contactBeen;
    private RecycleViewOnItemClickListener listener;

    public ContactsRecyclerViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.contactBeen = new LinkedList<>();

    }

    @Override
    public int getItemViewType(int position) {
        Log.d(Tag, "getItemViewType+position="+position);
        return contactBeen.get(position).getType();
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(Tag, "onCreateViewHolder+viewType="+viewType);
        View view = null;
        switch (viewType){
            case ContactBean.CHARACTER_TYPE:
                view = inflater.inflate(R.layout.item_recycler_contacts_character, parent, false);
                break;
            case ContactBean.CONTACT_TYPE:
                view = inflater.inflate(R.layout.item_recycler_contacts, parent, false);
                break;
            default:
                break;
        }

        return new ContactsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        Log.d(Tag, "onBindViewHolder+position="+position);
        int type = contactBeen.get(position).getType();
        switch (type) {
            case ContactBean.CHARACTER_TYPE:
                String c = String.valueOf(contactBeen.get(position).getCharacter());
                holder.textView.setText(c);
                holder.textView.setTextSize(15);
                break;
            case ContactBean.CONTACT_TYPE:
                String remark = contactBeen.get(position).getRemarks();
                holder.textView.setText(remark);
                holder.textView.setTextSize(20);
                break;
            default:
                break;
        }
        listenerSet(holder, position);

    }

    private void listenerSet(ContactsViewHolder holder, final int position) {
        if (this.listener != null) {
            holder.item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(v, position);
                    return true;
                }
            });
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        Log.d(Tag, "getItemCount");
        return contactBeen.size();
    }

    /**
     * 初始化数据源（获取通讯录缓存）
     */
    public void ContactersInit() {
        this.contactBeen = ContactsBean.getContactsCache();

    }

    /**
     * 获取字符c所在的数据源的位置（通过数据源的联系人昵称首字母）
     * @param c 查询字符
     * @return 所在数据源位置
     */
    public int getPositionForSelected(char c) {
        for (int i = 0; i < contactBeen.size(); i++) {
            ContactBean contact = contactBeen.get(i);
            if (contact.getHeadChar() == c)
                return i;
        }
        return -1;

    }

    /**
     * 获取数据源位置position的联系人的昵称
     * @param position 查询位置
     * @return 电话号码
     */
    public String getRemarks(int position) {
        return contactBeen.get(position).getRemarks();
    }

    public void setListener(RecycleViewOnItemClickListener listener) {
        this.listener = listener;
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        public LinearLayout item;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textViewItemContacts);
            this.item = (LinearLayout) itemView.findViewById(R.id.itemContacts);
        }

    }
}

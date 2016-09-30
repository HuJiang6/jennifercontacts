package com.contacts.lhj.view.messages;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.contacts.lhj.jennifercontacts.R;
import com.contacts.lhj.model.message.MessageAllCacheBean;
import com.contacts.lhj.model.message.MessageBody;
import com.contacts.lhj.model.message.MessageItemCacheBean;

import java.util.ArrayList;

import tools.RecycleViewOnItemClickListener;

/**
 * 作者：lhj on 2016/8/1 10:43
 * 邮箱：hujiang_2015@yeah.net
 * function:通话记录RecyclerView适配器
 */
public class MessageRecyclerViewAdapter
        extends RecyclerView.Adapter<MessageRecyclerViewAdapter.ContactsViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<MessageItemCacheBean> allCacheBean;
    private RecycleViewOnItemClickListener listener;

    public MessageRecyclerViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.allCacheBean = new ArrayList<>();

    }

    public void setListener(RecycleViewOnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler_message, parent, false);

        return new ContactsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        MessageItemCacheBean cacheBean = allCacheBean.get(position);
        MessageBody body = cacheBean.getLastMessage();

        String text = body.getText();
        holder.textBottom.setText(text);

        String name = cacheBean.getName();
        holder.textTop.setText(name);

        String date = body.getDate();
        holder.messageCreateTime.setText(date);

        String phone = cacheBean.getTelephone();
        holder.messageSenderPhone.setText(phone);

        int type = body.getType();
        setTypeBack(holder, type);

        listenerSet(holder, position);
    }

    private void setTypeBack(ContactsViewHolder holder, int type) {
        int layoutID;
        if (type == 1) {
            layoutID = R.drawable.ic_calllog_incomming_normal;
            holder.messageType.setImageResource(layoutID);
        } else {
            layoutID = R.drawable.ic_calllog_outgoing_nomal;
            holder.messageType.setImageResource(layoutID);
        }
        holder.messageType.setImageResource(layoutID);
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
        return this.allCacheBean.size();
    }

    public void messageBeenInit() {
        this.allCacheBean = MessageAllCacheBean.getCacheBeen();
    }

    public String getTelephne(int position) {
        return this.allCacheBean.get(position).getTelephone();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout item;
        private AppCompatImageView messageType;
        private AppCompatTextView messageCreateTime;
        private AppCompatTextView messageSenderPhone;
        private AppCompatTextView textTop;
        private AppCompatTextView textBottom;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            this.messageCreateTime = (AppCompatTextView) itemView.findViewById(R.id.messageCreateTime);
            this.messageSenderPhone = (AppCompatTextView) itemView.findViewById(R.id.messageSenderPhone);
            this.messageType = (AppCompatImageView) itemView.findViewById(R.id.messageType);
            this.textTop = (AppCompatTextView) itemView.findViewById(R.id.textTop);
            this.textBottom = (AppCompatTextView)
                    itemView.findViewById(R.id.textBottom);
            this.item = (LinearLayout) itemView.findViewById(R.id.itemMessage);
        }

    }
}

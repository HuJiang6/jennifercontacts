package view.messages;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.contacts.lhj.jennifercontacts.R;

import java.util.ArrayList;

import model.message.MessageAllCacheBean;
import model.message.MessageBody;
import model.message.MessageItemCacheBean;
import tools.RecycleViewOnItemClickListener;

/**
 * 作者：lhj on 2016/8/1 10:43
 * 邮箱：hujiang_2015@yeah.net
 * function:单人通话记录RecyclerView适配器
 */
public class MessageDialogRecyclerViewAdapter
        extends RecyclerView.Adapter<MessageDialogRecyclerViewAdapter.ContactsViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<MessageBody> allItemMessage;
    private MessageBody messageBody;
    private RecycleViewOnItemClickListener listener;

    public MessageDialogRecyclerViewAdapter(Context context, MessageItemCacheBean itemCacheBean) {
        this.inflater = LayoutInflater.from(context);
        this.allItemMessage = itemCacheBean.getAllMessage();

    }

    public void setListener(RecycleViewOnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        this.messageBody = this.allItemMessage.get(position);
        return this.messageBody.getType();
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = inflater.inflate(R.layout.item_message_receive, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_message_send, parent, false);
        }

        return new ContactsViewHolder(view, viewType);

    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {

        String date = this.messageBody.getDate();
        String content = this.messageBody.getText();
        int type = this.messageBody.getType();

        if (type == 1) {
            holder.dateReceiveTextView.setText(date);
            holder.contentReceiveTextView.setText(content);
        } else {
            holder.contentSendTextView.setText(content);
            holder.dateSendTextView.setText(date);
            holder.stateSendTextView.setText("送达");
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
        return this.allItemMessage.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView contentReceiveTextView;
        private AppCompatTextView contentSendTextView;
        private AppCompatTextView stateSendTextView;
        private AppCompatTextView dateSendTextView;
        private AppCompatTextView dateReceiveTextView;
        private RelativeLayout item;

        public ContactsViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 1) {
                this.contentReceiveTextView =
                        (AppCompatTextView) itemView.findViewById(R.id.contentMessageReceiveItem);
                this.dateReceiveTextView =
                        (AppCompatTextView) itemView.findViewById(R.id.dateMessageReceiveItem);
                this.item = (RelativeLayout) itemView.findViewById(R.id.itemMessageDialog);
            } else {
                this.contentSendTextView =
                        (AppCompatTextView) itemView.findViewById(R.id.contentMessageSendItem);
                this.stateSendTextView =
                        (AppCompatTextView) itemView.findViewById(R.id.stateMessageSendItem);
                this.dateSendTextView =
                        (AppCompatTextView) itemView.findViewById(R.id.dateMessageSendItem);
                this.item = (RelativeLayout) itemView.findViewById(R.id.itemMessageDialog);
            }
        }

    }
}

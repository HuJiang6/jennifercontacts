package view.called;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.contacts.lhj.jennifercontacts.R;
import tools.RecycleViewOnItemClickListener;

import java.util.LinkedList;

import model.called.CallLogBean;
import model.called.CallLogsBean;

/**
 * 作者：lhj on 2016/8/1 10:43
 * 邮箱：hujiang_2015@yeah.net
 * function:通话记录RecyclerView适配器
 */
public class CalledRecyclerViewAdapter
        extends RecyclerView.Adapter<CalledRecyclerViewAdapter.ContactsViewHolder> {

    private LayoutInflater inflater;
    private LinkedList<CallLogBean> callLogBeens;
    private RecycleViewOnItemClickListener listener;

    public CalledRecyclerViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.callLogBeens = new LinkedList<>();

    }

    public void setListener(RecycleViewOnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recycler_called, parent, false);

        return new ContactsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        CallLogBean callLogBean = callLogBeens.get(position);
        String number = callLogBean.getNumber();
        holder.textBottom.setText(number);

        String name = callLogBean.getName();
        holder.textTop.setText(name);

        String time = callLogBean.getDate();
        holder.callLastTime.setText(time);

        int type = callLogBean.getType();
        holder.callType.setImageResource(type);
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
        return callLogBeens.size();
    }

    /**
     * 获取数据源中对应位置通话记录的对应电话号码
     * @param position 位置
     * @return 电话号码
     */
    public String getTelephone(int position) {
        return callLogBeens.get(position).getNumber();
    }

    public void callLogBeenInit() {
        this.callLogBeens = CallLogsBean.getCallLogBeens();

    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        private android.support.v7.widget.AppCompatImageView callType;
        private android.support.v7.widget.AppCompatTextView callLastTime;
        private android.support.v7.widget.AppCompatTextView textTop;
        private android.support.v7.widget.AppCompatTextView textBottom;
        private LinearLayout item;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            this.callLastTime = (AppCompatTextView) itemView.findViewById(R.id.callLastTime);
            this.callType = (AppCompatImageView) itemView.findViewById(R.id.callType);
            this.textTop = (AppCompatTextView) itemView.findViewById(R.id.textTop);
            this.textBottom = (AppCompatTextView)
                    itemView.findViewById(R.id.textBottom);
            this.item = (LinearLayout) itemView.findViewById(R.id.itemCalled);

        }

    }
}

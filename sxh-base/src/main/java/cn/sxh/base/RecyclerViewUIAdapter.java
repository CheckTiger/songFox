package cn.sxh.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewUIAdapter extends RecyclerView.Adapter<RecyclerViewUIAdapter.VerticalViewHolder> {

    private Context mContext;
    private List<String> mList = new ArrayList<>();
    private int ItemType = 0;

    public RecyclerViewUIAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_fragment_ui_recycleview_item, parent, false);
        return new VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalViewHolder holder, final int position) {
        holder.mTextView.setSelected(true);
        holder.mTextView.setText(mList.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.dispatchListener(position,mList.get(position));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        public VerticalViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
        }
    }

    public interface OnRecyclerViewItemClickListener{
        void dispatchListener(int position, String title);
    }

    public OnRecyclerViewItemClickListener listener;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.listener = onRecyclerViewItemClickListener;
    }

    public void setItemType(int itemType) {
        this.ItemType = itemType;
    }

}

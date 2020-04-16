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

public class RecyclerViewVerticalAdapter extends RecyclerView.Adapter<RecyclerViewVerticalAdapter.VerticalViewHolder> {

    private Context mContext;
    private List<String> mList = new ArrayList<>();

    public RecyclerViewVerticalAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_fragment_vertical_recycleview_item, parent, false);
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
                    listener.dispatchListener(position);
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

    public interface OnRecyelerViewItemClickListener{
        void dispatchListener(int position);
    }

    public OnRecyelerViewItemClickListener listener;

    public void setOnRecyelerViewItemClickListener(OnRecyelerViewItemClickListener onRecyelerViewItemClickListener) {
        this.listener = onRecyelerViewItemClickListener;
    }

}

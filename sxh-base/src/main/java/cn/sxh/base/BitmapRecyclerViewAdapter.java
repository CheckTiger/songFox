package cn.sxh.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BitmapRecyclerViewAdapter extends RecyclerView.Adapter<BitmapRecyclerViewAdapter.BitmapViewHolder> {

    private Context mContext;
    private List<Integer> mList = new ArrayList<>();

    public void setList(List<Integer> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public BitmapRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BitmapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bitmap_fragment_item_layout, parent, false);
        return new BitmapRecyclerViewAdapter.BitmapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BitmapViewHolder holder, int position) {
        holder.mImageView.setBackgroundResource(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class BitmapViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        public BitmapViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
        }
    }
}

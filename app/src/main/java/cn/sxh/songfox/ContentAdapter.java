package cn.sxh.songfox;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author：JianFeng
 * @date：2019/3/26 16:17
 * @description：
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ItemViewHolder> {


    private Context context;
    private List<Entity> datas;
    public List<ItemViewHolder> mViewHolderList = new ArrayList<>();
    private int offestX = 0;
    private OnContentScrollListener onContentScrollListener;

    public interface OnContentScrollListener {
        void onScroll(int offestX);
    }

    public void setOnContentScrollListener(OnContentScrollListener onContentScrollListener) {
        this.onContentScrollListener = onContentScrollListener;
    }


    public ContentAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<Entity> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_content, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, int i) {
        //右边滑动部分
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        itemViewHolder.rvItemRight.setLayoutManager(linearLayoutManager);
        itemViewHolder.rvItemRight.setHasFixedSize(true);
        RightScrollAdapter rightScrollAdapter = new RightScrollAdapter(context);
        rightScrollAdapter.setDatas(datas);
        itemViewHolder.rvItemRight.setAdapter(rightScrollAdapter);
        //缓存当前holder
        if (!mViewHolderList.contains(itemViewHolder)) {
            mViewHolderList.add(itemViewHolder);
        }
        //滑动单个ytem的rv时,右侧整个区域的联动
        itemViewHolder.horItemScrollview.setOnCustomScrollChangeListener(new CustomHorizontalScrollView.OnCustomScrollChangeListener() {
            @Override
            public void onCustomScrollChange(CustomHorizontalScrollView listener, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                for (int i = 0; i < mViewHolderList.size(); i++) {
                    ItemViewHolder touchViewHolder = mViewHolderList.get(i);
                    if (touchViewHolder != itemViewHolder) {
                        touchViewHolder.horItemScrollview.scrollTo(scrollX, 0);
                    }
                }
                //记录滑动距离,便于处理下拉刷新之后的还原操作
                if (null != onContentScrollListener) onContentScrollListener.onScroll(scrollX);
                offestX = scrollX;
            }
        });
        //由于viewHolder的缓存,在1级缓存取出来是2个viewholder,并且不会被重新赋值,所以这里需要处理缓存的viewholder的位移
        itemViewHolder.horItemScrollview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!itemViewHolder.isLayoutFinish()) {
                    itemViewHolder.horItemScrollview.scrollTo(offestX, 0);
                    itemViewHolder.setLayoutFinish(true);
                }
            }
        });

        itemViewHolder.itemView.setOnClickListener(v1 -> {
            Entity entity = datas.get(i);
            if (listener != null) {
                listener.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }

    public List<ItemViewHolder> getViewHolderCacheList() {
        return mViewHolderList;
    }

    public int getOffestX() {
        return offestX;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvLeftTitle;
        TextView tvLeftCode;
        RecyclerView rvItemRight;
        LinearLayout rvLinearLayout;
        public CustomHorizontalScrollView horItemScrollview;
        private boolean isLayoutFinish;//自定义字段,用于标记layout

        public boolean isLayoutFinish() {
            return isLayoutFinish;
        }

        public void setLayoutFinish(boolean layoutFinish) {
            isLayoutFinish = layoutFinish;
        }

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            rvLinearLayout = itemView.findViewById(R.id.ll_content);
            rvItemRight = itemView.findViewById(R.id.rv_item_right);
            horItemScrollview = itemView.findViewById(R.id.hor_item_scrollview);
        }
    }




    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }




    public interface OnItemLongClickListener {
        void onClick(int position);
    }

    private OnItemLongClickListener longClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }



}

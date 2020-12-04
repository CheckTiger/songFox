package cn.sxh.songfox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.sxh.songfox.R;

/**
 * @package-name: cn.sxh.songfox.adapter
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/8/23 0023 : 14 :18
 * @project-name: songFox
 */
public class UtilsFragmentAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> list;
    private LayoutInflater inflater;

    public UtilsFragmentAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tools_fragment_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        initInfo(holder, position);
        initUIListener(holder, position);
        return convertView;
    }

    private void initInfo(ViewHolder holder, int position) {
        if (listener != null) {
            holder.mLinearLayout.setOnClickListener(view ->listener.onLinearLayoutClick(holder,position));
        }
    }

    private void initUIListener(ViewHolder holder, int position) {
        holder.title_name.setText(list.get(position));
    }

    public class ViewHolder{
        private TextView title_name;
        private LinearLayout mLinearLayout;

        ViewHolder(View view) {
            title_name = view.findViewById(R.id.tools_fragment_item_text);
            mLinearLayout = view.findViewById(R.id.all_fragment_linearLayout);
        }
    }

    private OnLinearLayoutListener listener;
    public interface OnLinearLayoutListener{
        void onLinearLayoutClick(ViewHolder holder, int position);
    }
    public void setOnLinearLayoutListener(OnLinearLayoutListener layoutListener) {
        this.listener = layoutListener;
    }
}

package cn.sxh.songfox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.sxh.songfox.R;

public class CoolWidgetAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> list;
    private LayoutInflater mInflater;

    public CoolWidgetAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
        this.mInflater = LayoutInflater.from(mContext);
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
            convertView = mInflater.inflate(R.layout.widget_fragment_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        initInfo(holder, position);
        return convertView;
    }

    private void initInfo(ViewHolder holder, int position) {
        holder.title_name.setText(list.get(position));
    }


    public class ViewHolder {
        private TextView title_name;

        ViewHolder(View view) {
            title_name = view.findViewById(R.id.widget_fragment_item_text);
        }
    }


}

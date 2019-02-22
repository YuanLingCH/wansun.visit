package wansun.visit.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wansun.visit.android.R;

/**
 * 外访单adapter
 * Created by User on 2019/1/11.
 */

public class visitOrderAdapter extends BaseAdapter {
    Context mconext;
    List data;
    LayoutInflater inflater;

    public visitOrderAdapter(Context mconext, List data) {
        this.mconext = mconext;
        this.data = data;
        inflater=LayoutInflater.from(mconext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= inflater.inflate(R.layout.visit_order_item,parent,false);
            convertView.setTag(holder);

        }

            holder= (ViewHolder) convertView.getTag();


        return convertView;
    }
    class  ViewHolder {
        TextView tv_search_item;

    }

}

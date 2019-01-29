package wansun.visit.android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;

import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.bean.geogCodeBean;

/**
 * Created by User on 2019/1/11.
 */

public class geogCodeAdapter extends BaseAdapter {
    Context mconext;
    List<geogCodeBean> data;
    LayoutInflater inflater;

    public geogCodeAdapter(Context mconext, List<geogCodeBean> data) {
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
            convertView= inflater.inflate(R.layout.geog_item,parent,false);
            holder.tv_search_item= (TextView) convertView.findViewById(R.id.tv_search_item);
            convertView.setTag(holder);
        }

        holder= (ViewHolder) convertView.getTag();
        geogCodeBean geogCodeBean = data.get(position);
        LatLng location = geogCodeBean.getLocation();
        double longitude = location.longitude;
        double latitude = location.latitude;
        Log.e("TAG","点击adapter"+"latitude"+latitude+"longitude"+longitude);

        holder.tv_search_item.setText(geogCodeBean.getAddress()+geogCodeBean.getName());
        return convertView;
    }
    class  ViewHolder {
        TextView tv_search_item;

    }


}

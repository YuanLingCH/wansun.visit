package wansun.visit.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.bean.visitItemBean;

/**
 * 外访单地址adapter
 * Created by User on 2019/1/11.
 */

public class visitOrderrAddressAdapter extends BaseAdapter {
    Context mconext;
    List<visitItemBean.DataBeanX.DataBean.UrgeVisitItemsBean> data;
    LayoutInflater inflater;

    public visitOrderrAddressAdapter(Context mconext, List <visitItemBean.DataBeanX.DataBean.UrgeVisitItemsBean>data) {
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
            convertView= inflater.inflate(R.layout.visit_order_address_item,parent,false);
            holder.tv_visit_applyTime= (TextView) convertView.findViewById(R.id.tv_visit_applyTime);
            holder.tv_visit_visit_begin_time= (TextView) convertView.findViewById(R.id.tv_visit_visit_begin_time);
            holder.tv_visit_visitEndTime= (TextView) convertView.findViewById(R.id.tv_visit_visitEndTime);
            holder.tv_visit_visitCompleteTime= (TextView) convertView.findViewById(R.id.tv_visit_visitCompleteTime);
            holder.tv_visit_applicantName= (TextView) convertView.findViewById(R.id.tv_visit_applicantName);
            holder.tv_visit_visitors= (TextView) convertView.findViewById(R.id.tv_visit_visitors);
            holder.tv_visit_address= (TextView) convertView.findViewById(R.id.tv_visit_address);
            holder.tv_visitArea= (TextView) convertView.findViewById(R.id.tv_visitArea);
            convertView.setTag(holder);

        }
        visitItemBean.DataBeanX.DataBean.UrgeVisitItemsBean dataBean = data.get(position);
        holder= (ViewHolder) convertView.getTag();
        holder.tv_visit_applyTime.setText("申请时间："+dataBean.getApplyTime());
        holder.tv_visit_visit_begin_time.setText("外访开始时间："+dataBean.getVisitBeginTime());
        holder.tv_visit_visitEndTime.setText("外访结束时间："+dataBean.getVisitEndTime());
       // Long applyTime = dataBean.getApplyTime()/1000;
       // String applyTimeone= unixTime.stampToTime(applyTime);
        holder.tv_visit_visitCompleteTime.setText("截至时间："+  dataBean.getVisitCompleteTime());
        holder.tv_visit_applicantName.setText("申请人:"+dataBean.getApplicantName());
      //  Object visitCompleteDate = dataBean.getVisitCompleteDate()/1000;
        holder.tv_visit_visitors.setText("外访人："+dataBean.getVisitors());
      //  Long visitBeginTime = dataBean.getVisitBeginTime()/1000;
      //  String visitBeginTimeone= unixTime.stampToTime(visitBeginTime);
        holder.tv_visitArea.setText("外访区域："+dataBean.getVisitArea());
        holder.tv_visit_address.setText("家庭地址："+dataBean.getAddress());

        return convertView;
    }
    class  ViewHolder {
        TextView tv_visit_applyTime,tv_visit_visit_begin_time,tv_visit_visitEndTime,tv_visit_visitCompleteTime,tv_visit_applicantName,tv_visit_visitors;
        TextView tv_visit_address,tv_visitArea;
    }

}

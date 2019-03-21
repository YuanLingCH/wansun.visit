package wansun.visit.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.bean.caseVistRecordBean;
import wansun.visit.android.utils.unixTime;

/**
 * 案件详细外访记录adapter
 * Created by User on 2019/1/11.
 */

public class caseVisitRecordAdapter extends BaseAdapter {
    Context mconext;
    List<caseVistRecordBean.DataBean> data;
    LayoutInflater inflater;

    public caseVisitRecordAdapter(Context mconext, List <caseVistRecordBean.DataBean>data) {
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
            convertView= inflater.inflate(R.layout.case_visit_record_item,parent,false);
            holder.tv_case_visit_operate_type_text= (TextView) convertView.findViewById(R.id.tv_case_visit_operate_type_text);
            holder.tv_case_visit_operator_name= (TextView) convertView.findViewById(R.id.tv_case_visit_operator_name);
            holder.tv_case_case_operate_time= (TextView) convertView.findViewById(R.id.tv_case_case_operate_time);
            holder.tv_case_operate_operate_content= (TextView) convertView.findViewById(R.id.tv_case_operate_operate_content);

            convertView.setTag(holder);

        }

        caseVistRecordBean.DataBean Bean = data.get(position);
        holder= (ViewHolder) convertView.getTag();
        holder.tv_case_visit_operate_type_text.setText("操作类型："+Bean.getOperateTypeText());
        holder.tv_case_visit_operator_name.setText("操作人："+Bean.getOperatorName());
        long operateTime = Bean.getOperateTime()/1000;
        String promiseTime= unixTime.stampToTime(operateTime);
        holder.tv_case_case_operate_time.setText("操作时间："+promiseTime);
        holder.tv_case_operate_operate_content.setText("操作内容："+Bean.getOperateContent());
        return convertView;
    }
    class  ViewHolder {
        TextView tv_case_visit_operate_type_text,tv_case_visit_operator_name,tv_case_case_operate_time,tv_case_operate_operate_content;

    }

}

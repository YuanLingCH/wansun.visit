package wansun.visit.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.bean.casePhoneBean;
import wansun.visit.android.utils.logUtils;

/**
 * 案件电话详细adapter
 * Created by User on 2019/1/11.
 */

public class casePhoneDetailsAdapter extends BaseAdapter {
    Context mconext;
    List<casePhoneBean.DataBean> data;
    LayoutInflater inflater;

    public casePhoneDetailsAdapter(Context mconext, List <casePhoneBean.DataBean>data) {
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
            convertView= inflater.inflate(R.layout.case_phone_details_item,parent,false);
            holder.tv_case_phone_number= (TextView) convertView.findViewById(R.id.tv_case_phone_number);
            holder.tv_case_phone_name= (TextView) convertView.findViewById(R.id.tv_case_phone_name);
            holder.tv_case_phone_relation= (TextView) convertView.findViewById(R.id.tv_case_phone_relation);
            holder.tv_case_phone_phoneTypeText= (TextView) convertView.findViewById(R.id.tv_case_phone_phoneTypeText);
            holder.tv_case_phone_remark= (TextView) convertView.findViewById(R.id.tv_case_phone_remark);
            convertView.setTag(holder);
        }
        casePhoneBean.DataBean phoneDetailsBean = data.get(position);
        holder= (ViewHolder) convertView.getTag();
        logUtils.d("adapter案件电话"+phoneDetailsBean.getName());
        holder.tv_case_phone_name.setText("联系人："+phoneDetailsBean.getName());
        holder.tv_case_phone_number.setText("电话号码："+phoneDetailsBean.getPhoneNumber());
        holder.tv_case_phone_relation.setText("与债务人关系："+phoneDetailsBean.getRelation());
        holder.tv_case_phone_phoneTypeText.setText("电话类型："+phoneDetailsBean.getPhoneTypeText());
        holder.tv_case_phone_remark.setText("备注："+phoneDetailsBean.getRemark());

        return convertView;
    }
    class  ViewHolder {
        TextView tv_case_phone_name,tv_case_phone_number,tv_case_phone_relation,tv_case_phone_phoneTypeText,tv_case_phone_remark;

    }

}

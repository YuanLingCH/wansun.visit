package wansun.visit.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.bean.visitItemBean;
import wansun.visit.android.utils.unixTime;

/**
 * 外访单adapter
 * Created by User on 2019/1/11.
 */

public class visitOrderAdapter extends BaseAdapter {
    Context mconext;
    List<visitItemBean.DataBean> data;
    LayoutInflater inflater;
    boolean flag;  //ture 显示图片 为完成
    public visitOrderAdapter(Context mconext, List <visitItemBean.DataBean>data,boolean flag) {
        this.mconext = mconext;
        this.data = data;
        inflater=LayoutInflater.from(mconext);
        this.flag=flag;
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
            holder.tv_visit_apply_debtor_name= (TextView) convertView.findViewById(R.id.tv_visit_apply_debtor_name);
            holder.tv_visit_apply_gender_text= (TextView) convertView.findViewById(R.id.tv_visit_apply_gender_text);
            holder.tv_visit_apply_last_arrear_amount= (TextView) convertView.findViewById(R.id.tv_visit_apply_last_arrear_amount);
            holder.tv_visit_apply_collect_amount= (TextView) convertView.findViewById(R.id.tv_visit_apply_collect_amount);
            holder.tv_visit_apply_time= (TextView) convertView.findViewById(R.id.tv_visit_apply_time);
          //  holder.tv_visit_completeDate= (TextView) convertView.findViewById(R.id.tv_visit_completeDate);
            holder.tv_visit_begin_time= (TextView) convertView.findViewById(R.id.tv_visit_begin_time);
            holder.tv_visit_end_time= (TextView) convertView.findViewById(R.id.tv_visit_end_time);
            holder.tv_visit_bapply_case_type= (TextView) convertView.findViewById(R.id.tv_visit_bapply_case_type);
            holder.tv_visit_apply_urgeStatusText= (TextView) convertView.findViewById(R.id.tv_visit_apply_urgeStatusText);
            holder.tv_visit_applyOrgName= (TextView) convertView.findViewById(R.id.tv_visit_applyOrgName);
            holder.tv_visitOrgName= (TextView) convertView.findViewById(R.id.tv_visitOrgName);
            holder.tv_visitors= (TextView) convertView.findViewById(R.id.tv_visitors);
            holder.tv_visitGoal= (TextView) convertView.findViewById(R.id.tv_visitGoal);
            holder.tv_visit_customerName= (TextView) convertView.findViewById(R.id.tv_visit_customerName);
            holder.tv_visit_caseCode= (TextView) convertView.findViewById(R.id.tv_visit_caseCode);
            holder.tv_visit_batchCode= (TextView) convertView.findViewById(R.id.tv_visit_batchCode);
            holder.tv_visitArea= (TextView) convertView.findViewById(R.id.tv_visitArea);
            holder.tv_address= (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_visitReason= (TextView) convertView.findViewById(R.id.tv_visitReason);
            holder.tv_remark= (TextView) convertView.findViewById(R.id.tv_remark);
            holder.iv_flag= (ImageView) convertView.findViewById(R.id.iv_flag);
            convertView.setTag(holder);

        }
        visitItemBean.DataBean dataBean = data.get(position);
        holder= (ViewHolder) convertView.getTag();
        holder.tv_visit_apply_debtor_name.setText("债务人："+dataBean.getName());
        holder.tv_visit_apply_gender_text.setText("性别："+dataBean.getGenderText());
        holder.tv_visit_apply_last_arrear_amount.setText("还款总金额："+dataBean.getCaseTotalRepaymentAmount());
       // Long applyTime = dataBean.getApplyTime()/1000;
       // String applyTimeone= unixTime.stampToTime(applyTime);
        holder.tv_visit_apply_time.setText("申请时间："+ unixTime.stampToTime( dataBean.getApplyTime()/1000));
        holder.tv_visit_apply_collect_amount.setText("案件催收金额:"+dataBean.getCaseTotalUrgeAmount());
      //  Object visitCompleteDate = dataBean.getVisitCompleteDate()/1000;
      //  holder.tv_visit_completeDate.setText("外访完成时间："+dataBean.getVisitCompleteDate());
       Long visitBeginTime = dataBean.getVisitBeginTime()/1000;
    String visitBeginTimeone= unixTime.stampToTime(visitBeginTime);
    holder.tv_visit_begin_time.setText("外访开始："+visitBeginTimeone);
       Long visitEndTime = dataBean.getVisitEndTime()/1000;
      String visitEndTimeone= unixTime.stampToTime(visitEndTime);
      holder.tv_visit_end_time.setText("外访结束："+visitEndTimeone);
      //  holder.tv_visit_bapply_case_type.setText("案件类型："+dataBean.getCaseType());
        holder.tv_visit_apply_urgeStatusText.setText("催收状态："+dataBean.getCaseUrgeStatusText());
        holder.tv_visit_applyOrgName.setText("申请机构："+dataBean.getApplyOrgName());
        holder.tv_visitOrgName.setText("外访机构："+dataBean.getVisitOrgName());
        holder.tv_visitors.setText("外访人："+dataBean.getVisitors());
      //  holder.tv_visitGoal.setText("外访目标："+dataBean.getVisitGoal());
        holder.tv_visit_customerName.setText("客户名称："+dataBean.getCustomerName());
        holder.tv_visit_caseCode.setText("案件编号："+dataBean.getCaseCode());
        holder.tv_visit_batchCode.setText("批次编号："+dataBean.getBatchCode());
     //   holder.tv_visitArea.setText("外访区域："+dataBean.getVisitArea());
        holder.tv_address.setText("地址："+dataBean.getAddress());
        holder.tv_visitReason.setText("外访理由："+dataBean.getVisitReason());
        holder.tv_remark.setText("备注："+dataBean.getRemark());
        if (flag){
            holder.iv_flag.setVisibility(View.VISIBLE);
        }else {
            holder.iv_flag.setVisibility(View.VISIBLE);
            holder.iv_flag.setImageResource(R.mipmap.doing);
        }
        return convertView;
    }
    class  ViewHolder {
        TextView tv_visit_apply_debtor_name,tv_visit_apply_gender_text,tv_visit_apply_last_arrear_amount,tv_visit_apply_time,tv_visit_apply_collect_amount;
        TextView tv_visit_begin_time,tv_visit_end_time,tv_visit_bapply_case_type,tv_visit_apply_urgeStatusText,tv_visit_applyOrgName;
        TextView tv_visitOrgName,tv_visitors,tv_visitGoal,tv_visit_customerName,tv_visit_caseCode,tv_visit_batchCode,tv_visitArea,tv_address,tv_visitReason;
        TextView tv_remark;
        ImageView iv_flag;
    }

    /**
     * 添加列表项
     */
    public  void  addItem( visitItemBean.DataBean bean){
        data.add(bean);
    }


}

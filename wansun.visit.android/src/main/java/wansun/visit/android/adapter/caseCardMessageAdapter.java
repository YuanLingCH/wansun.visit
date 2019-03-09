package wansun.visit.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.bean.caseCardMessageBean;

/**
 * 卡信息adapter
 * Created by User on 2019/1/11.
 */

public class caseCardMessageAdapter extends BaseAdapter {
    Context mconext;
    List<caseCardMessageBean.DataBean> data;
    LayoutInflater inflater;

    public caseCardMessageAdapter(Context mconext, List <caseCardMessageBean.DataBean>data) {
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
            convertView= inflater.inflate(R.layout.case_card_message_item,parent,false);
            holder.tv_case_card_case_amount= (TextView) convertView.findViewById(R.id.tv_case_card_case_amount);
            holder.tv_case_cadr_back_cmount= (TextView) convertView.findViewById(R.id.tv_case_cadr_back_cmount);
            holder.tv_case_card_arrears= (TextView) convertView.findViewById(R.id.tv_case_card_arrears);
            holder.tv_case_caed_overdue_days= (TextView) convertView.findViewById(R.id.tv_case_caed_overdue_days);
            holder.tv_case_card_overdue_installment= (TextView) convertView.findViewById(R.id.tv_case_card_overdue_installment);
            holder.tv_case_caed_defaultRatings= (TextView) convertView.findViewById(R.id.tv_case_caed_defaultRatings);
            holder.tv_case_card_hand= (TextView) convertView.findViewById(R.id.tv_case_card_hand);
            holder.tv_case_caed_account= (TextView) convertView.findViewById(R.id.tv_case_caed_account);
            holder.tv_case_cardNo= (TextView) convertView.findViewById(R.id.tv_case_cardNo);
            convertView.setTag(holder);

        }
        caseCardMessageBean.DataBean cardBean = data.get(position);
        holder= (ViewHolder) convertView.getTag();
        holder.tv_case_card_case_amount.setText("委案欠款："+cardBean.getCaseAmount());
        holder.tv_case_cadr_back_cmount.setText("回款金额："+cardBean.getBackAmount());
        holder.tv_case_card_arrears.setText("欠款金额："+cardBean.getArrears());
        holder.tv_case_caed_overdue_days.setText("逾期天数："+cardBean.getOverdueDays());
        holder.tv_case_card_overdue_installment.setText("逾期付款："+cardBean.getOverdueInstallment());
        holder.tv_case_caed_defaultRatings.setText("拖欠级别："+cardBean.getDefaultRatings());
        holder.tv_case_card_hand.setText("手次："+cardBean.getHand());
        holder.tv_case_caed_account.setText("账户："+cardBean.getAccount());
        holder.tv_case_cardNo.setText("卡号："+cardBean.getCardNo());
        return convertView;
    }
    class  ViewHolder {
        TextView tv_case_card_case_amount,tv_case_cadr_back_cmount,tv_case_card_arrears,tv_case_caed_overdue_days,tv_case_card_overdue_installment;
        TextView tv_case_caed_defaultRatings,tv_case_card_hand,tv_case_caed_account,tv_case_cardNo;

    }

}

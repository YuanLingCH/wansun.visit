package wansun.visit.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.bean.caseFileQueryBean;
import wansun.visit.android.utils.unixTime;

/**
 * 案件文件查询adapter
 * Created by User on 2019/1/11.
 */

public class caseFileQueryAdapter extends BaseAdapter {
    Context mconext;
    List<caseFileQueryBean.DataBean> data;
    LayoutInflater inflater;

    public caseFileQueryAdapter(Context mconext, List <caseFileQueryBean.DataBean>data) {
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

            convertView= inflater.inflate(R.layout.case_file_query_item,parent,false);
            holder.tv_case_file_link_type= (TextView) convertView.findViewById(R.id.tv_case_file_link_type);
            holder.tv_case_file_upload_time= (TextView) convertView.findViewById(R.id.tv_case_file_upload_time);
            holder.tv_case_file_name= (TextView) convertView.findViewById(R.id.tv_case_file_name);
            holder.tv_case_file_path = (TextView) convertView.findViewById(R.id.tv_case_file_path);
            holder.tv_case_file_size= (TextView) convertView.findViewById(R.id.tv_case_file_size);
            convertView.setTag(holder);

        }
        caseFileQueryBean.DataBean urgeRecord  = data.get(position);
        holder= (ViewHolder) convertView.getTag();
        holder.tv_case_file_link_type.setText("关联类型："+urgeRecord.getLinkTypeText());
        long promiseDate = urgeRecord.getUploadTime()/1000;
        String promiseTime= unixTime.stampToTime(promiseDate );
        holder.tv_case_file_upload_time.setText("上传时间："+ promiseTime);
        holder.tv_case_file_name.setText("文件名称："+urgeRecord.getSourceFileName());

        holder.tv_case_file_path.setText("文件路径："+urgeRecord.getFilePath());
        if (urgeRecord.getFileSize()!=0){
            double i = urgeRecord.getFileSize() / 1024;
            double size = i / 1024;
            DecimalFormat df = new DecimalFormat("#.00");
            holder.tv_case_file_size.setText("文件大小："+df.format(size)+"M");
        }



        return convertView;
    }
    class  ViewHolder {
        TextView tv_case_file_link_type,tv_case_file_upload_time,tv_case_file_name,tv_case_file_path,tv_case_file_size;

    }

}

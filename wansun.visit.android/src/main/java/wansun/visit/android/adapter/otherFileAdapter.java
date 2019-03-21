package wansun.visit.android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.config.AppConfig;
import wansun.visit.android.db.fileInfo;
import wansun.visit.android.utils.CommonUtil;
import wansun.visit.android.utils.unixTime;

/**
 * 案件文件查询adapter
 * Created by User on 2019/1/11.
 */

public class otherFileAdapter extends BaseAdapter {
    Context mconext;
    private List<fileInfo> data ;
    LayoutInflater inflater;
    ListView lv;
    public otherFileAdapter(Context mconext, List<fileInfo> data, ListView lv) {
        this.mconext = mconext;
        this.data = data;
        this.lv=lv;
        inflater=LayoutInflater.from(mconext);
        this.lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:

                        updateUI();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    private void updateUI() {
        this.notifyDataSetChanged();
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
            convertView= inflater.inflate(R.layout.attachment_item,parent,false);
            holder.iv= (ImageView) convertView.findViewById(R.id.imgAttachment);
            holder.tvAttachmentFullName= (TextView) convertView.findViewById(R.id.tvAttachmentFullName);
            holder.tvAttachmentType= (TextView) convertView.findViewById(R.id.tvAttachmentType);
            holder.tvAttachmentTakeOperateDate= (TextView) convertView.findViewById(R.id.tvAttachmentTakeOperateDate);
            holder.tv_id= (TextView) convertView.findViewById(R.id.tv_id);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();

        }
        fileInfo bean = data.get(position);
        String path = bean.getPath();
        Long id = bean.getId();
        File file = new File(path);

        if (file.exists()) {
            holder.tvAttachmentFullName.setText("存储路径："+path);
            String operateTime= unixTime.stampToTime(bean.getTime()/1000 );
            holder.tvAttachmentTakeOperateDate.setText("操作时间："+operateTime);
            holder.tv_id.setText(id+"");
         if (path.endsWith(".jpg")||path.endsWith(".png")) // 图像文件
            {
                Log.v(AppConfig.TAG, ".jpg");
                holder.iv.setImageBitmap(CommonUtil.getImageThumbnail(path, 100, 100));
                holder.tvAttachmentType.setText("文件类型："+"图像文件");
            }
            else if (path.endsWith(".mp4"))// 视频文件
            {
                Log.v(AppConfig.TAG, ".mp4");
                holder.iv.setImageBitmap(CommonUtil.getVideoThumbnail(path, 100, 100));
                holder.tvAttachmentType.setText("文件类型："+"视频文件");
            }

   }
        return convertView;
    }
    class  ViewHolder {
        ImageView iv;
        TextView tvAttachmentFullName,tvAttachmentType,tvAttachmentTakeOperateDate,tv_id;

    }

}

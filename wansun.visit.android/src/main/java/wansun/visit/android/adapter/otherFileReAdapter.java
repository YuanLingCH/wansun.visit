package wansun.visit.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.config.AppConfig;
import wansun.visit.android.db.fileInfo;
import wansun.visit.android.ui.activity.playPictureAndVideoActivity;
import wansun.visit.android.utils.ToastUtil;
import wansun.visit.android.utils.logUtils;
import wansun.visit.android.utils.unixTime;

/**
 * Created by User on 2019/3/15.
 */

public class otherFileReAdapter extends RecyclerView.Adapter<otherFileReAdapter.ViewHolder> implements View.OnClickListener{

    Context mconext;
    private List<fileInfo> data ;
    LayoutInflater inflater;
    boolean flag;

    public otherFileReAdapter(Context mconext, List<fileInfo> data,boolean flag) {
        this.mconext = mconext;
        this.data = data;
        inflater=LayoutInflater.from(mconext);
        this.flag=flag;
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnItemClickListener{
        void onItemClick(long id,int postion);
    }

    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    @Override
    public otherFileReAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.attachment_item,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(otherFileReAdapter.ViewHolder holder, final int position) {
        fileInfo bean = data.get(position);
        final String path = bean.getPath();
        final Long id = bean.getId();
        File file = new File(path);
        if (file.exists()) {
            holder.tvAttachmentFullName.setText("存储路径："+path);
            String operateTime= unixTime.stampToTime(bean.getTime()/1000 );
            holder.tvAttachmentTakeOperateDate.setText("操作时间："+operateTime);
            holder.tv_id.setText(id+"");
            if (path.endsWith(".jpg")||path.endsWith(".png")) // 图像文件
            {
                Log.v(AppConfig.TAG, ".jpg");
                Glide.with(mconext).load(file).into(holder.iv);
               // holder.iv.setImageBitmap(CommonUtil.getImageThumbnail(path, 100, 100));
                holder.tvAttachmentType.setText("文件类型："+"图像文件");
            } else if (path.endsWith(".mp4"))// 视频文件
            {
                Log.v(AppConfig.TAG, ".mp4");
                Glide.with(mconext).load(file).into(holder.iv);
                holder.tvAttachmentType.setText("文件类型："+"视频文件");
            }else if (path.endsWith(".mp3")){

            }else {
                holder.iv.setImageResource(R.mipmap.file);
                holder.tvAttachmentType.setText("文件类型："+"普通文件");
            }

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener!=null){
                    mItemClickListener.onItemClick(id,position);
                }
            }
        });
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUtils.d("点击了图片"+path);
                if (path.endsWith(".mp3")||path.endsWith(".mp4")||path.endsWith(".jpg")||path.endsWith(".png")){
                    Intent intent=new Intent(mconext, playPictureAndVideoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("path",path);
                    mconext.startActivity(intent);
                }else {
                    ToastUtil.showToast(mconext,"普通文件不能查看");
                }

            }
        });
        if (flag){
            holder.imgNotification.setImageResource(R.mipmap.greentown);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv,imgNotification;
        TextView tvAttachmentFullName,tvAttachmentType,tvAttachmentTakeOperateDate,tv_id;
        public ViewHolder(View itemView) {
            super(itemView);

           iv= (ImageView) itemView.findViewById(R.id.imgAttachment);
         tvAttachmentFullName= (TextView) itemView.findViewById(R.id.tvAttachmentFullName);
          tvAttachmentType= (TextView) itemView.findViewById(R.id.tvAttachmentType);
           tvAttachmentTakeOperateDate= (TextView) itemView.findViewById(R.id.tvAttachmentTakeOperateDate);
            tv_id= (TextView) itemView.findViewById(R.id.tv_id);
            imgNotification= (ImageView) itemView.findViewById(R.id.imgNotification);

        }
    }

}

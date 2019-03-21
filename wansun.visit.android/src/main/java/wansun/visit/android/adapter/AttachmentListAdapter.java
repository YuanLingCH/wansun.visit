package wansun.visit.android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import wansun.visit.android.R;
import wansun.visit.android.bean.fileInfoCustomerBean;
import wansun.visit.android.config.AppConfig;
import wansun.visit.android.utils.CommonUtil;
import wansun.visit.android.utils.logUtils;

public class AttachmentListAdapter extends BaseAdapter
{

	private Context mContext;
	private List<fileInfoCustomerBean> mDatas ;
	LayoutInflater inflater;

	public AttachmentListAdapter(Context mContext,List <fileInfoCustomerBean>mDatas)
	{
		super();
		this.mContext = mContext;
		this.mDatas=mDatas;
		inflater=LayoutInflater.from(mContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView==null){
			holder=new ViewHolder();
			convertView= inflater.inflate(R.layout.attachment_item,parent,false);
			holder.iv= (ImageView) convertView.findViewById(R.id.imgAttachment);


			convertView.setTag(holder);
		}
		fileInfoCustomerBean bean = mDatas.get(position);
		holder= (ViewHolder) convertView.getTag();
			String path = bean.getPath();
		logUtils.d("adaptermDatas.size()"+mDatas.size());
			logUtils.d("adapter测试数据"+path);
			File file = new File(path);
			if (file.exists())
			{
				if (path.endsWith(".jpg")) // 图像文件
				{
					Log.v(AppConfig.TAG, ".jpg");
					holder.iv.setImageBitmap(CommonUtil.getImageThumbnail(path, 100, 100));
				}
				else if (path.endsWith(".mp4"))// 视频文件
				{
					Log.v(AppConfig.TAG, ".mp4");
					holder.iv.setImageBitmap(CommonUtil.getVideoThumbnail(path, 100, 100));
				}
			}


		// Log.v(AppConfig.TAG, "VisitListAdapter.getView: visit.getAddress = "
		// + visit.getAddress());

	/*	if (convertView == null)
		{*/
	/*		Log.v(AppConfig.TAG, "convertView is null.");
			view = LayoutInflater.from(mContext).inflate(R.layout.attachment_item, null);

			TextView vAttachmentTakeOperator = (TextView) view.findViewById(R.id.tvAttachmentTakeOperator);
			vAttachmentTakeOperator.setText("操作者　：" + CommonUtil.getSysUserByUserId(attachment.getTake_operator()).getLogin_name());
			TextView vAttachmentTakeOperateDate = (TextView) view.findViewById(R.id.tvAttachmentTakeOperateDate);
			vAttachmentTakeOperateDate.setText("操作时间：" + StringUtil.formateDateTime(attachment.getTake_date()));
			TextView vAttachmentType = (TextView) view.findViewById(R.id.tvAttachmentType);
			vAttachmentType.setText("资料类型：" + CommonUtil.getSysDictItemByItemCodeAndItemValue("AttachmentType", attachment.getAttachment_type()).getItem_name());
			TextView vAttachmentDesc = (TextView) view.findViewById(R.id.tvAttachmentDesc);
			vAttachmentDesc.setText(attachment.getAttachment_desc());
			TextView vAttachmentFullName = (TextView) view.findViewById(R.id.tvAttachmentFullName);
			vAttachmentFullName.setText(attachment.getAttachment_full_name());

			View notification = (View) view.findViewById(R.id.icAttachmentNotification);
			if (attachment.getIs_uploaded().equals("0"))
			{
				notification.setVisibility(View.VISIBLE);
			}
			else
			{
				notification.setVisibility(View.INVISIBLE);
			}

			ImageView img = (ImageView) view.findViewById(R.id.imgAttachment);
			String path = attachment.getAttachment_full_name();
			Log.v(AppConfig.TAG, "path = " + path);
			File file = new File(path);
			if (file.exists())
			{
				if (path.endsWith(".jpg")) // 图像文件
				{
					Log.v(AppConfig.TAG, ".jpg");
					img.setImageBitmap(CommonUtil.getImageThumbnail(path, 100, 100));
				}
				else if (path.endsWith(".mp4"))// 视频文件
				{
					Log.v(AppConfig.TAG, ".mp4");
					img.setImageBitmap(CommonUtil.getVideoThumbnail(path, 100, 100));
				}
			}*/
/*		}
		else
		{
			view = convertView;
		}*/

		return convertView;
	}


	class  ViewHolder{
		ImageView iv;
	}


}

package wansun.visit.android.config;

import android.os.Environment;

public interface AppConfig
{

	public static class ATTACHMENT_TYPE
	{
		public static final int IMAGE = 10;
		public static final int RECORD = 20;
		public static final int VIDEO = 30;
		public static final int FILE = 40;
	}

	public static class ATTACHMENT_CONTEXT_MENU
	{
		public static final int IMAGE = 10;
		public static final int RECORD = 20;
		public static final int VIDEO = 30;
		public static final int FILE = 40;
	}

	public static class ENTRY_DETAIL_TYPE
	{
		public static final int VISITING = 10;
		public static final int NOT_VISITING = 20;
	}

	public static final String TAG = "wsvisit";

	public static final int VERSION = 1;

	public static final String VERSION_CODE = "v1.1";

	public static final int PAGE_SIZE = 20;

	// 存储路径
	public static final String VISIT_DATA_PATH = Environment.getExternalStorageDirectory()+ "/"+"wansun.visit.android";
	// 临时文件存贮路径
//	public static final String IMAGE_TEMP_FILE = VISIT_DATA_PATH + (System.currentTimeMillis())+".jpg";
	public static final String IMAGE_TEMP_FILE = VISIT_DATA_PATH ;
	// 临时文件存贮路径
	public static final String VIDEO_TEMP_FILE = VISIT_DATA_PATH + "/video.mp4";
	// 临时文件存贮路径
	public static final String RECORD_TEMP_FILE = VISIT_DATA_PATH + "/record.mp3";

	// Environment.getExternalStorageDirectory()
}

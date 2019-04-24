package wansun.visit.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

import wansun.visit.android.config.AppConfig;
import wansun.visit.android.global.waifangApplication;

/**
 * Created by User on 2019/3/14.
 */

public class CommonUtil {
    /**
     * 根据指定的图像路径和大小来获取缩略图 此方法有两点好处： 1. 使用较小的内存空间，第一次获取的bitmap实际上为null，只是为了读取宽度和高度， 第二次读取的bitmap是根据比例压缩过的图像，第三次读取的bitmap是所要的缩略图。 2. 缩略图对于原图像来讲没有拉伸，这里使用了2.2版本的新工具ThumbnailUtils，使
     * 用这个工具生成的图像不会被拉伸。
     *
     * @param imagePath
     *            图像的路径
     * @param width
     *            指定输出图像的宽度
     * @param height
     *            指定输出图像的高度
     * @return 生成的缩略位图
     */
    public static Bitmap getImageThumbnail(String imagePath, int width, int height)
    {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false; // 设为 false
        // 计算缩放比
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / width;
        int beHeight = h / height;
        int be = 1;
        if (beWidth < beHeight)
        {
            be = beWidth;
        }
        else
        {
            be = beHeight;
        }
        if (be <= 0)
        {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }


    /**
     * 获取视频的缩略图 先通过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图。 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的值，这样会节省内存。
     *
     * @param videoPath
     *            视频的路径
     * @param width
     *            指定输出视频缩略图的宽度
     * @param height
     *            指定输出视频缩略图的高度度
     * @param
     *
     * @return 指定大小的视频缩略图
     */
    public static Bitmap getVideoThumbnail(String videoPath, int width, int height)
    {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Images.Thumbnails.MICRO_KIND);
        if (bitmap != null)
        {
            Log.v(AppConfig.TAG, "getVideoThumbnail: bitmap.getRowBytes = " + bitmap.getRowBytes());
        }
        Log.v(AppConfig.TAG, "getVideoThumbnail: width = " + width);
        Log.v(AppConfig.TAG, "getVideoThumbnail: height = " + height);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }
        public  static  String getImie(){
            TelephonyManager telephonyManager=(TelephonyManager) waifangApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
            final String imei=telephonyManager.getDeviceId();
            return imei;
        }
    public static String getCurrentDateTimeString()
    {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(System.currentTimeMillis()));
    }

    public static Bitmap CreateWatermark(String mark)
    {
        int w = 2000, h = 150;
        Bitmap waterMark = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        Canvas canvasTemp = new Canvas(waterMark);
        Paint p = new Paint();
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.BOLD);
        p.setColor(Color.YELLOW);
        p.setTypeface(font);
        p.setTextSize(50);
        canvasTemp.drawText(mark, 0, 100, p);
        return waterMark;
    }

    public static Bitmap CreateBitmapWithWatermark(Bitmap src, Bitmap watermark)
    {

        Log.v(AppConfig.TAG, "CreateBitmapWithWatermark - entry.");

        if (src == null)
        {

            return null;

        }

        int w = src.getWidth();

        int h = src.getHeight();

        int ww = watermark.getWidth();

        int wh = watermark.getHeight();

        Bitmap destMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图

        Canvas cv = new Canvas(destMap);

        cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src

        cv.drawBitmap(watermark, w/2-5 , h/2-5 , null);// 在src的右下角画入水印

        cv.save(Canvas.ALL_SAVE_FLAG);// 保存

        cv.restore();// 存储

        return destMap;

    }

    // 位图存为图片
    public static Boolean saveImage(Bitmap bitmap, String path)
    {
        boolean success = false;

        try
        {
            File file = new File(path);
            File folder = new File(file.getParent());
            if (!folder.exists())
            {
                folder.mkdirs();
            }
            BufferedOutputStream bos;
            bos = new BufferedOutputStream(new FileOutputStream(file));
            success = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            success = false;
        }

        return success;
    }



        /**
         * 采样率压缩
         *
         * @param bitmap
         * @param sampleSize 采样率为2的整数倍，非整数倍四舍五入，如4的话，就是原图的1/4
         * @return 尺寸变化
         */
        public static Bitmap getBitmap(Bitmap bitmap, int sampleSize) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = sampleSize;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            Bitmap bit = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            Log.i("info", "图片大小：" + bit.getByteCount());//2665296  10661184
            return bit;


    }
}

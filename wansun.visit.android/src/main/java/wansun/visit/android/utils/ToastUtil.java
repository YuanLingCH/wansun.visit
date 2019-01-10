package wansun.visit.android.utils;

import android.content.Context;
import android.widget.Toast;

import wansun.visit.android.R;


public class ToastUtil
{

	private static Toast mToast;

	public static void showToast(Context context, String str)
	{

		if (mToast == null)
		{
			mToast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
		}
		else
		{
			mToast.setText(str);
		}
		mToast.show();
	}

	public static void showToast(Context context, int stringId)
	{

		Toast.makeText(context, stringId, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context context, String str, int duration)
	{
		Toast.makeText(context, str, duration).show();
	}

	public static void showNetUnavailingToast(Context context)
	{

		Toast.makeText(context, R.string.network_unavailing, Toast.LENGTH_SHORT).show();
	}

	public static void showRequestErrorToast(Context context)
	{
		Toast.makeText(context, R.string.request_error, Toast.LENGTH_SHORT).show();
	}

}

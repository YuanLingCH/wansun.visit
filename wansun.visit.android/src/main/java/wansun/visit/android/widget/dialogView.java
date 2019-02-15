package wansun.visit.android.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;

import wansun.visit.android.R;

/**
 * Created by User on 2019/1/14.
 * 自定义对话框
 */

public class dialogView extends Dialog {
    Context context;

    public dialogView(@NonNull Context context) {
        super(context);
    }

    public dialogView(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.infowindow_layout, null);
        this.setContentView(layout);

    }
}

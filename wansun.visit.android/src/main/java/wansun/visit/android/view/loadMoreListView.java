package wansun.visit.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import wansun.visit.android.R;

/**
 * 加载更多的listview
 * Created by User on 2019/4/3.
 */

public class loadMoreListView extends ListView implements AbsListView.OnScrollListener{
    LayoutInflater inflater;
    View footView=null;
    //判断是否滚动最后一行
    private boolean isLastRow = false;
    //实现接口加载更多的数据
    public  onLoadMoreListenner moreListenner;
    public  void  setLoadMoreListnner(onLoadMoreListenner moreListenner){
        this.moreListenner=moreListenner;
    }
    public  interface onLoadMoreListenner{
        void  loadMore();
    }


    public loadMoreListView(Context context) {
        this(context,null);
    }

    public loadMoreListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public loadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
    inflater=LayoutInflater.from(getContext());
        footView=inflater.inflate(R.layout.load_more_layout,null);
        this.addFooterView(footView);
        footView.setVisibility(GONE);
        setOnScrollListener(this);

    }

    /**
     * 滑动状态的改变
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //最后一条数据并且手指停止滑动
        if (isLastRow&&scrollState==OnScrollListener.SCROLL_STATE_IDLE){
        footView.setVisibility(VISIBLE);
            isLastRow=false;
            if (moreListenner!=null){
                moreListenner.loadMore(); //加载更多数据

            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //判断是否滚到最后一行
        if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
            isLastRow = true;
        }
    }
}

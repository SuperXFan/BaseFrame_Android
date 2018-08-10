package cc.ewell.common.base.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cc.ewell.common.R;

/**
 * Created by SuperFan on 2016/9/14.
 */
public abstract class BaseRecycleAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {

    private Activity activity;
    private RecyclerView recyclerView;

    private View emptyView, errorView;
    private View headerView, footerLoadingView, footerNoMoreDataView, footerErrorView;

    public BaseRecycleAdapter(int layoutResId, List<T> data, Activity activity, RecyclerView recyclerView) {
        super(layoutResId, data);
        this.activity = activity;
        this.recyclerView = recyclerView;
        initViews();
    }

    public Activity getActivity() {
        return activity;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, T t) {
        bindViewConvert(baseViewHolder, t);
    }

    protected abstract void bindViewConvert(BaseViewHolder baseViewHolder, T t);


    protected void initViews() {

        //整个列表View
        emptyView = activity.getLayoutInflater().inflate(R.layout.empty_view_layout, (ViewGroup) recyclerView.getParent(), false);
        errorView = activity.getLayoutInflater().inflate(R.layout.error_view_layout, (ViewGroup) recyclerView.getParent(), false);

        //header view
        headerView = activity.getLayoutInflater().inflate(R.layout.header_view_layout, (ViewGroup) recyclerView.getParent(), false);

        //footer view
        footerLoadingView = activity.getLayoutInflater().inflate(R.layout.footer_loading_layout, (ViewGroup) recyclerView.getParent(), false);
        setFooterLoading(footerLoadingView);
        footerNoMoreDataView = activity.getLayoutInflater().inflate(R.layout.no_more_data_layout, (ViewGroup) recyclerView.getParent(), false);
        setFooterNoMoreDataView(footerNoMoreDataView);
        footerErrorView = activity.getLayoutInflater().inflate(R.layout.footer_error_layout, (ViewGroup) recyclerView.getParent(), false);
        setFooterErrorView(footerErrorView);

    }


    //设置headerView
    public void setCustomHeaderView(View headerView) {
        this.headerView = headerView;
        showHeaderView();
    }

    //显示header
    public void showHeaderView() {
        if (headerView != null) {
            removeHeaderView(headerView);
            if (headerView.getParent() != null) {
                ((LinearLayout) headerView.getParent()).removeView(headerView);
            }
            addHeaderView(this.headerView);
        }
    }

    //隐藏header
    public void hideHeaderView() {
        if (headerView != null) {
            removeHeaderView(headerView);
        }
    }

    //设置footer加载进度  显示由base内部控制
    public void setFooterLoading(View footerLoadingView) {
        this.footerLoadingView = footerLoadingView;
        setFooterLoading(this.footerLoadingView);
    }

    //设置没有更多数据的footer
    public void setFooterNoMoreDataView(View footerNoMoreDataView) {
        this.footerNoMoreDataView = footerNoMoreDataView;
    }

    //显示没有更多数据的footer
    public void showFooterNoMoreDataView() {
        if (footerNoMoreDataView != null) {
            if (footerNoMoreDataView.getParent() != null) {
                ((LinearLayout) footerNoMoreDataView.getParent()).removeView(footerNoMoreDataView);
            }
            addFooterView(footerNoMoreDataView);
        }
    }

    //设置加载更多失败的footer
    public void setFooterErrorView(View footerErrorView) {
        this.footerErrorView = footerErrorView;
        setFooterErrorView(this.footerErrorView);
    }

    //显示加载更多数据失败的footer
    public void showFooterErrorView() {
        if (footerErrorView != null) {
            showFooterErrorView();
        }
    }

    //显示空页
    public void showEmptyView() {
        if (emptyView != null) {
            setEmptyView(emptyView);
            recyclerView.setAdapter(this);
        }
    }

    //显示错误页
    public void showErrorView() {
        if (errorView != null) {
            setEmptyView(errorView);
            recyclerView.setAdapter(this);
        }
    }

    public void setWhiteEmptyView(int resourceId) {
        emptyView = activity.getLayoutInflater().inflate(resourceId, (ViewGroup) recyclerView.getParent(), false);
    }
}

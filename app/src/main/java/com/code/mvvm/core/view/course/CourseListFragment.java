package com.code.mvvm.core.view.course;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.code.mvvm.base.BaseListFragment;
import com.code.mvvm.config.Constants;
import com.code.mvvm.core.data.pojo.course.CourseInfoVo;
import com.code.mvvm.core.data.pojo.course.CourseListVo;
import com.code.mvvm.core.vm.CourseViewModel;
import com.code.mvvm.util.AdapterPool;
import com.trecyclerview.listener.OnItemClickListener;
import com.trecyclerview.multitype.MultiTypeAdapter;
import com.trecyclerview.pojo.FootVo;
import com.trecyclerview.pojo.HeaderVo;

/**
 * @author：tqzhang on 18/5/2 19:40
 */
public class CourseListFragment extends BaseListFragment<CourseViewModel> implements OnItemClickListener {
    private String mCatalogId;

    public static CourseListFragment newInstance() {
        return new CourseListFragment();
    }

    @Override
    protected Object getStateEventKey() {
        return Constants.EVENT_KEY_COURSE_LIDT_STATE;
    }

    @Override
    protected String getStateEventTag() {
        return mCatalogId;
    }

    @Override
    protected void dataObserver() {
        if (getArguments() != null) {
            mCatalogId = getArguments().getString("f_catalog_id", null);
        }

        registerObserver(Constants.EVENT_KEY_COURSE_LIDT,mCatalogId, CourseListVo.class).observe(this, courseListVo -> {
            if (courseListVo != null && courseListVo.data != null) {
                lastId = courseListVo.data.get(courseListVo.data.size() - 1).courseid;
                setData(courseListVo.data);
            }

        });
    }


    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return oldItems.get(position) instanceof FootVo
                        || oldItems.get(position) instanceof HeaderVo ?
                        2 : 1;
            }
        });
        return layoutManager;
    }

    @Override
    protected MultiTypeAdapter createAdapter() {
        MultiTypeAdapter adapter=AdapterPool.newInstance().getCourseListAdapter(getActivity());
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    protected void onStateRefresh() {
        super.onStateRefresh();
        getNetWorkData();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getNetWorkData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        getNetWorkData();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        getNetWorkData();
    }

    public void getNetWorkData() {
        mViewModel.getCourseList(mCatalogId, lastId);
    }

    @Override
    public void onItemClick(View view, int i, Object object) {
        if (object != null) {
            if (object instanceof CourseInfoVo) {
                Intent intent = new Intent(activity, VideoDetailsActivity.class);
                intent.putExtra("course_id", ((CourseInfoVo) object).courseid);
                activity.startActivity(intent);
            }

        }
    }
}

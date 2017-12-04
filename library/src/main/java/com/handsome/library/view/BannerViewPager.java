package com.handsome.library.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.handsome.library.adapter.BannerAdapter;
import com.handsome.library.utils.BannerScroller;

import java.lang.reflect.Field;

/**
 * Created by handsomexu on 2017/12/3.
 */

public class BannerViewPager extends ViewPager {
    private Context mContext;
    private BannerAdapter mBannerAdapter;
    private static final int SCROLL_MSG = 0x099;
    private int scroll_time = 5000;
    private BannerScroller mBannerScroller;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setCurrentItem(getCurrentItem() + 1);
            startRoll();
        }
    };

    public BannerViewPager(Context context) {
        this(context, null);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        setScroller();
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(BannerAdapter adapter) {
        this.mBannerAdapter = adapter;
        setAdapter(new BannerPagerAdapter());

        if (mBannerAdapter.autoScroll()) {
            startRoll();
        }
    }

    /**
     * 设置切换的时间间隔
     *
     * @param duration
     */
    public void setDuration(int duration) {
        mBannerScroller.setScrollerDuration(duration);
    }

    /**
     * 开启无限循环
     */
    private void startRoll() {
        mHandler.removeMessages(SCROLL_MSG);
        mHandler.sendEmptyMessageDelayed(SCROLL_MSG, scroll_time);
    }

    /**
     * 设置Scroller
     */
    private void setScroller() {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mBannerScroller = new BannerScroller(mContext);
            mScroller.set(this, mBannerScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回收Handler
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(SCROLL_MSG);
        mHandler = null;
    }

    /**
     * ViewPager适配器
     */
    private class BannerPagerAdapter extends PagerAdapter implements OnClickListener {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View bannerView = mBannerAdapter.getView(position % mBannerAdapter.getCount());
            bannerView.setTag(position % mBannerAdapter.getCount());
            bannerView.setOnClickListener(this);
            container.addView(bannerView);
            return bannerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            mBannerAdapter.onClick(position);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        /**
         * 滑动监听
         */
        if (!mBannerAdapter.autoScroll()) {
            return super.onTouchEvent(ev);
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeMessages(SCROLL_MSG);
                break;
            case MotionEvent.ACTION_MOVE:
                mHandler.removeMessages(SCROLL_MSG);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.removeMessages(SCROLL_MSG);
                mHandler.sendEmptyMessageDelayed(SCROLL_MSG, scroll_time);
                break;
        }
        return super.onTouchEvent(ev);
    }
}

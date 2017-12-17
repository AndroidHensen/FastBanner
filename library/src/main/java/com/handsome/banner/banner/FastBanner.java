package com.handsome.banner.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handsome.banner.R;
import com.handsome.banner.adapter.BannerAdapter;
import com.handsome.banner.view.BannerViewPager;
import com.handsome.banner.view.IndicationView;

/**
 * Created by handsomexu on 2017/12/3.
 */

public class FastBanner extends RelativeLayout {

    private BannerViewPager fb__banner;
    private TextView fb__title;
    private LinearLayout fb__indication;

    private BannerAdapter mAdapter;
    private Context mContext;

    private Drawable mIndicationOnDrawable;
    private Drawable mIndicationOffDrawable;

    private int mCurrentPosition = 0;
    private int mGravity = 1;
    private int mIndicationSize = 8;
    private int mIndicationGap = 4;
    private float mRatio = 0;


    public FastBanner(Context context) {
        this(context, null);
    }

    public FastBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        View bannerView = inflate(context, R.layout.fast_banner_view, this);
        fb__banner = (BannerViewPager) bannerView.findViewById(R.id.fb__viewpager);
        fb__title = (TextView) bannerView.findViewById(R.id.fb__title);
        fb__indication = (LinearLayout) bannerView.findViewById(R.id.fb__indication);

        initAttribute(attrs);
    }

    /**
     * 初始化自定义属性
     *
     * @param attrs
     */
    private void initAttribute(AttributeSet attrs) {
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.FastBanner);
        mIndicationOnDrawable = ta.getDrawable(R.styleable.FastBanner_fb_indication_on);
        if (mIndicationOnDrawable == null) {
            mIndicationOnDrawable = new ColorDrawable(Color.RED);
        }
        mIndicationOffDrawable = ta.getDrawable(R.styleable.FastBanner_fb_indication_off);
        if (mIndicationOffDrawable == null) {
            mIndicationOffDrawable = new ColorDrawable(Color.WHITE);
        }
        mGravity = ta.getInt(R.styleable.FastBanner_fb_indication_gravity, mGravity);
        mIndicationSize = (int) ta.getDimension(R.styleable.FastBanner_fb_indication_size, dp2px(mIndicationSize));
        mIndicationGap = (int) ta.getDimension(R.styleable.FastBanner_fb_indication_gap, dp2px(mIndicationGap));
        mRatio = ta.getFloat(R.styleable.FastBanner_fb_banner_ratio, mRatio);
        ta.recycle();
    }

    /**
     * 获取位置属性
     */
    private int getIndicationGravity() {
        switch (mGravity) {
            case -1:
                return Gravity.LEFT;
            case 0:
                return Gravity.CENTER;
            case 1:
                return Gravity.RIGHT;
        }
        return Gravity.RIGHT;
    }

    /**
     * 初始化指示器
     *
     * @param context
     */
    private void initIndication(Context context) {
        int count = mAdapter.getCount();

        fb__indication.setGravity(getIndicationGravity());

        for (int i = 0; i < count; i++) {
            IndicationView indicationView = new IndicationView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mIndicationSize, mIndicationSize);
            params.leftMargin = params.rightMargin = mIndicationGap;
            indicationView.setLayoutParams(params);

            if (i == 0) {
                indicationView.setDrawable(mIndicationOnDrawable);
            } else {
                indicationView.setDrawable(mIndicationOffDrawable);
            }
            fb__indication.addView(indicationView);
        }
    }


    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(BannerAdapter adapter) {
        this.mAdapter = adapter;
        fb__banner.setAdapter(adapter);

        initIndication(mContext);
        initListener();
        //default
        indicationSelect(0);
    }

    /**
     * 自适应高度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (mRatio == 0) {
            return;
        }

        int width = getMeasuredWidth();
        int height = (int) (width / mRatio);
        getLayoutParams().height = height;
    }

    /**
     * 初始化轮播监听
     */
    private void initListener() {
        fb__banner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                indicationSelect(position);
            }
        });
    }

    /**
     * 指示器点的变化
     *
     * @param position
     */
    private void indicationSelect(int position) {
        IndicationView oldIndicationView = (IndicationView) fb__indication.getChildAt(mCurrentPosition);
        oldIndicationView.setDrawable(mIndicationOffDrawable);
        mCurrentPosition = position % mAdapter.getCount();

        IndicationView newIndicationView = (IndicationView) fb__indication.getChildAt(mCurrentPosition);
        newIndicationView.setDrawable(mIndicationOnDrawable);

        //title
        if (mAdapter.getTitle(mCurrentPosition) != null) {
            fb__title.setVisibility(VISIBLE);
            fb__title.setText(mAdapter.getTitle(mCurrentPosition));
        }
    }

    /**
     * dp2px
     *
     * @param pxValue
     * @return
     */
    private int dp2px(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue * scale + 0.5f);
    }

    /**
     * 设置切换的时间间隔
     *
     * @param duration
     */
    public void setDuration(int duration) {
        fb__banner.setDuration(duration);
    }


}

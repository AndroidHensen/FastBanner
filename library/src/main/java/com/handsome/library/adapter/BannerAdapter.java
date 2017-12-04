package com.handsome.library.adapter;

import android.view.View;

/**
 * Created by handsomexu on 2017/12/3.
 */

public abstract class BannerAdapter {
    public abstract View getView(int position);

    public abstract int getCount();

    public String getTitle(int position) {
        return null;
    }

    public boolean autoScroll() {
        return true;
    }

    public abstract void onClick(int position);
}

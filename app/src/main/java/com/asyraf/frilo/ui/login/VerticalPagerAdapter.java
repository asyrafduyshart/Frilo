package com.asyraf.frilo.ui.login;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asyraf.frilo.R;
import com.asyraf.frilo.util.ViewUtil;

import static com.asyraf.frilo.util.ViewUtil.setupItem;

/**
 * Created by Asyraf Duyshart on 3/1/17.
 */

public class VerticalPagerAdapter extends PagerAdapter {

    private final ViewUtil.LibraryObject[] TWO_WAY_LIBRARIES = new ViewUtil.LibraryObject[]{
            new ViewUtil.LibraryObject(
                    R.mipmap.ic_launcher,
                    "Fintech"
            ),
            new ViewUtil.LibraryObject(
                    R.mipmap.ic_launcher,
                    "Delivery"
            ),
            new ViewUtil.LibraryObject(
                    R.mipmap.ic_launcher,
                    "Social network"
            ),
            new ViewUtil.LibraryObject(
                    R.mipmap.ic_launcher,
                    "E-commerce"
            ),
            new ViewUtil.LibraryObject(
                    R.mipmap.ic_launcher,
                    "Wearable"
            ),
            new ViewUtil.LibraryObject(
                    R.mipmap.ic_launcher,
                    "Internet of things"
            )
    };

    private LayoutInflater mLayoutInflater;

    public VerticalPagerAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return TWO_WAY_LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.item_login_promotion, container, false);

        setupItem(view, TWO_WAY_LIBRARIES[position]);

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}

package com.seoulapp.ssg.ui.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seoulapp.ssg.R;

/**
 * Created by Boram Moon on 2016-10-06.
 */

public class VolunteerPagerAdapter extends PagerAdapter {

    private LayoutInflater mInflater;

    public VolunteerPagerAdapter(Context c){
        super();
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = null;
        if(position==0){
            v = mInflater.inflate(R.layout.inflate_one, null);
            v.findViewById(R.id.imgView01);
        }
        else if(position==1){
            v = mInflater.inflate(R.layout.inflate_two, null);
            v.findViewById(R.id.imgView02);

        }else{
            v = mInflater.inflate(R.layout.inflate_three, null);
            v.findViewById(R.id.imgView03);
        }

        ((ViewPager)container).addView(v, 0);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }
    @Override
    public boolean isViewFromObject(View pager, Object obj) {
        return pager == obj;
    }
    @Override public void restoreState(Parcelable arg0, ClassLoader arg1) {}
    @Override public Parcelable saveState() { return null; }
    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }
    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

}

package com.djj.example.wocao;

import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.djj.jazzyviewpager.JazzyViewPager;

/**
 * Created by djj on 2016/12/13.
 */

public class MyExtendFragmentStatePagerAdapter extends ExtendFragmentStatePagerAdapter {
    public static final String TAG = DjjFragmentStatePagerAdapter.class.getSimpleName();
    private JazzyViewPager mJazzy;

    public MyExtendFragmentStatePagerAdapter(FragmentManager fm, JazzyViewPager v) {
        super(fm);
        mJazzy = v;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /*addcount++;*/
        //Log.d("instantiateitem", "position=" + position);
        // return super.instantiateItem(container, position);
        Object obj = super.instantiateItem(container, position);
        mJazzy.setObjectForPosition(obj, position);
        return obj;
    }

}

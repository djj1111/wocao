package com.djj.example.wocao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ViewGroup;

import com.djj.jazzyviewpager.JazzyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djj on 2016/12/4.
 */

public class MyFragmentStatePagerAdapter extends DjjFragmentStatePagerAdapter {
    public static final String TAG = DjjFragmentStatePagerAdapter.class.getSimpleName();
    private static int addcount = 0, delcount = 0, newcount = 0;
    //private ArrayList<Fragment> rmFragments=new ArrayList<>();
    private Fragment currentFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    private JazzyViewPager mJazzy;

    public MyFragmentStatePagerAdapter(FragmentManager fm, JazzyViewPager v) {
        super(fm);
        mJazzy = v;
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        super.setRemovePosition(mFragments.indexOf(fragment));
        mFragments.remove(fragment);
        //rmFragments.add(fragment);
    }

    public int getFramentposition(Fragment fragment) {
        return mFragments.indexOf(fragment);
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
    }

    public void clear() {
        for (Fragment fragment : mFragments) {
            if (fragment != null && fragment.isAdded()) {
                fragment.onDestroy();
            }
        }
        mFragments.clear();
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    @Override
    public int getItemPosition(Object object) {
        //return super.getItemPosition(object);
       /* if (object instanceof Fragment){
            return mFragments.indexOf(object)>=0?mFragments.indexOf(object):PagerAdapter.POSITION_NONE;
        }
        else{
            return PagerAdapter.POSITION_UNCHANGED;
        }*/
        /*int index=mFragments.indexOf(object);
        if (index<0) return POSITION_NONE;*///POSITION_UNCHANGED;
        return POSITION_NONE;
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

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        /*delcount++;
        Log.d("destroyitem","delcount="+delcount+"position="+position);*/
        //if (mFragments.indexOf(object)==-1) ((Fragment)object).clear();
        /*if (position<){
            for (;position<mFragments.size();position++){

            }
        }*/
        Log.d("destroyitem", "position=" + position);
        super.destroyItem(container, position, object);
        /*if (mFragments.indexOf(object)==-1) {
            Log.d("removeitem","position="+position);
            super.removeItem(position);
           }*/
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentFragment = (Fragment) object;
        Log.d("fuckkkkk", "current=" + mFragments.indexOf(object));
        super.setPrimaryItem(container, position, object);
    }

}
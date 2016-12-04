package com.djj.example.wocao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djj on 2016/12/4.
 */

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = MyFragmentStatePagerAdapter.class.getSimpleName();

    private List<Fragment> mFragments = new ArrayList<>();

    public MyFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        mFragments.remove(fragment);
    }

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }

    public void clear() {
        for (Fragment fragment : mFragments) {
            if (fragment != null && fragment.isAdded()) {
                fragment.onDestroy();
            }
        }
        mFragments.clear();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d(TAG, "instantiateItem position = " + position);
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
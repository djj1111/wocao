package com.djj.example.wocao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djj on 2016/12/4.
 */

public class MyFragmentStatePagerAdapter extends FragmnetAutoPagerAdapter {
    public static final String TAG = FragmnetAutoPagerAdapter.class.getSimpleName();
    private static int addcount = 0, delcount = 0, newcount = 0;
    //private ArrayList<MyFragment> rmFragments=new ArrayList<>();
    public MyFragment currentFragment;
    private ArrayList<MyFragment> tmFragments = new ArrayList<MyFragment>();

    public MyFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(MyFragment fragment) {
        tmFragments.add(fragment);
    }

    public void removeFragment(MyFragment fragment) {
        tmFragments.remove(fragment);
        //rmFragments.add(fragment);
    }

    public int getFramentposition(MyFragment fragment) {
        return tmFragments.indexOf(fragment);
    }

    public List<MyFragment> getFragments() {
        return tmFragments;
    }

    public void setFragments(ArrayList<MyFragment> fragments) {
        tmFragments = fragments;
    }

    public void clear() {
        for (MyFragment fragment : tmFragments) {
            if (fragment != null && fragment.isAdded()) {
                fragment.onDestroy();
            }
        }
        tmFragments.clear();
    }

    @Override
    public int getItemPosition(Object object) {
        //return super.getItemPosition(object);
       /* if (object instanceof MyFragment){
            return tmFragments.indexOf(object)>=0?tmFragments.indexOf(object):PagerAdapter.POSITION_NONE;
        }
        else{
            return PagerAdapter.POSITION_UNCHANGED;
        }*/
        /*int index=tmFragments.indexOf(object);
        if (index<0) return POSITION_NONE;*///POSITION_UNCHANGED;
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        /*addcount++;*/
        Log.d("instantiateitem", "position=" + position);


        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return tmFragments.get(position);
    }

    @Override
    public int getCount() {
        return tmFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        /*delcount++;
        Log.d("destroyitem","delcount="+delcount+"position="+position);*/
        //if (tmFragments.indexOf(object)==-1) ((MyFragment)object).clear();
        /*if (position<){
            for (;position<tmFragments.size();position++){

            }
        }*/
        Log.d("destroyitem", "position=" + position);
        super.destroyItem(container, position, object);
        /*if (tmFragments.indexOf(object)==-1) {
            Log.d("removeitem","position="+position);
            super.removeItem(position);
           }*/
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentFragment = (MyFragment) object;
        Log.d("fuckkkkk", "current=" + tmFragments.indexOf(object));
        super.setPrimaryItem(container, position, object);
    }

}
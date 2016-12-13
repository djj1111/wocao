package com.djj.example.wocao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by djj on 2016/12/13.
 */

public class ExtendFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = FragmentStatePagerAdapter.class.getSimpleName();
    private Fragment currentFragment;
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    //private JazzyViewPager mJazzy;
    //private DualHashBidiMap positionMap=new DualHashBidiMap();
    private LinkedList<Integer> positionMap = new LinkedList<>();
    private int lastPosition = -1;
    private boolean mRemove = false;
    private int removePosition;

    public ExtendFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
        //mJazzy = v;
    }

    public void addFragment(Fragment fragment) {
        this.addFragment(fragment, mFragments.size());
    }

    public void addFragment(Fragment fragment, int position) {
        int maxPosition = mFragments.size();
        if (positionMap.isEmpty()) {
            positionMap.add(lastPosition + 1);
        } else {
            if (position < 0 || position > maxPosition) position = maxPosition;
            /*if (position==maxPosition){
                positionMap.add(positionMap.getLast()+1);
            }else{
                positionMap.add(position,positionMap.getLast()+1);
            }*/
            positionMap.add(position, Math.max(positionMap.getLast(), lastPosition) + 1);
        }
        /*if (positionMap.isEmpty()){
            positionMap.put(0,0);
        }else {
            if (position<0 || position > maxPosition) position=maxPosition;
            if (position==maxPosition){
                positionMap.put(position,(int)positionMap.get(position-1)+1);
            }else{
                int lastMapPosition=(int)positionMap.get(maxPosition-1);
                for (int i=maxPosition-1;i>=position;i--){
                    positionMap.put(i+1,(int)positionMap.get(i));
                }
                positionMap.put(maxPosition,lastMapPosition);
                positionMap.put(position,lastMapPosition+1);
            }
        }*/
        mFragments.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        removePosition = mFragments.indexOf(fragment);
        mFragments.remove(fragment);
        mRemove = true;
        //rmFragments.add(fragment);
    }

    public void removeIndex(int position) {
        if (positionMap.isEmpty() || position < 0) return;
        lastPosition = positionMap.getLast();
        Log.d("asdfasd", "lastpo=" + lastPosition + "   curpo=" + position);
        positionMap.remove(position);
    }

    public int getFramentPosition(Fragment fragment) {
        return mFragments.indexOf(fragment);
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }

    /*public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
    }*/

    /*public void clear() {
        for (Fragment fragment : mFragments) {
            if (fragment != null && fragment.isAdded()) {
                fragment.onDestroy();
            }
        }
        mFragments.clear();
    }*/

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
        Log.d("instantiateitem", "position=" + position);
        // return super.instantiateItem(container, position);
        if (mRemove) {
            removeIndex(removePosition);
            mRemove = false;
        }

        Object obj = super.instantiateItem(container, positionMap.get(position));
        //mJazzy.setObjectForPosition(obj, position);
        return obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, positionMap.get(position), object);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("asdfasdfasdfadsfasdf", "ap=" + positionMap.indexOf(position) + "   bp=" + position);


        return mFragments.get(positionMap.indexOf(position));
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentFragment = (Fragment) object;
        //Log.d("fuckkkkk", "current=" + mFragments.indexOf(object));
        super.setPrimaryItem(container, position, object);
    }
}

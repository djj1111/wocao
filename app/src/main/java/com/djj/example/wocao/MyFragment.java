package com.djj.example.wocao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by djj on 2016/12/4.
 */

public class MyFragment extends Fragment {

    public final static String TAG = "MyFragment";
    public final static String SaveInstanceStateEXTRA = "SaveInstanceStateEXTRA";

    //String mData;
    TestTable mTestTable;

    public static MyFragment getInstances(TestTable t) {
        MyFragment myFragment = new MyFragment();
        //myFragment.mData = data;
        myFragment.mTestTable=t;
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // Log.d(TAG, "onCreateView  mData = " + mData);
       // String str = "";
        if (savedInstanceState != null) {
            mTestTable.setId(savedInstanceState.getInt("id"));
            mTestTable.setName(savedInstanceState.getString("name"));
            mTestTable.setAddress(savedInstanceState.getString("address"));
            mTestTable.setPhone(savedInstanceState.getString("phone"));
        }

        // 这里必须是null
        View item = inflater.inflate(R.layout.fragment_item, null);
        TextView textid = (TextView) item.findViewById(R.id.tvid);
        TextView texttext = (TextView) item.findViewById(R.id.tvtext);
        TextView textip = (TextView) item.findViewById(R.id.tvip);
        TextView texttime = (TextView) item.findViewById(R.id.tvtime);
        textid.setText(String.valueOf(mTestTable.getId()));
        texttext.setText(mTestTable.getName());
        textip.setText(mTestTable.getAddress());
        texttime.setText(mTestTable.getPhone());
        /*if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }*/
        return item;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString(SaveInstanceStateEXTRA, mData + "_save");
        outState.putInt("id",mTestTable.getId());
        outState.putString("name",mTestTable.getName());
        outState.putString("address",mTestTable.getAddress());
        outState.putString("phone",mTestTable.getPhone());
       // Log.d(TAG, "onSaveInstanceState  mData = " + mData);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // Log.d(TAG, "onDestroy mData = " + mData);
    }

}

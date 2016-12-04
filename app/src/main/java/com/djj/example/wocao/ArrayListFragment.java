package com.djj.example.wocao;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by djj on 2016/12/4.
 */

public class ArrayListFragment extends ListFragment {
    private int mNum;
    public ArrayList<String> list = new ArrayList<String>();

    /**
     * 创建一个计算Fragment页面的实例，将怒num作为一个参数。
     * （Create a new instance of
     * CountingFragment, providing "num" as an argument.）
     */
    public static ArrayListFragment newInstance(int num) {

        ArrayListFragment f = new ArrayListFragment();
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    /**
     * 当调用该方法时，检索此实例的数量的参数。
     * （When creating, retrieve this instance's number from
     * its arguments.）
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    /**
     * Fragment的UI只显示它所在的页码。
     * （The Fragment's UI is just a simple text view
     * showing its instance number.）
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container,
                false);
        TextView tvid = (TextView) view.findViewById(R.id.tvid);
        tvid.setText("Fragment #" + mNum);
        TextView tvip = (TextView) view.findViewById(R.id.tvip);
        tvip.setText("Fragment #" + mNum);
        TextView tvtext = (TextView) view.findViewById(R.id.tvtext);
        tvtext.setText("Fragment #" + mNum);
        TextView tvtime = (TextView) view.findViewById(R.id.tvtime);
        tvtime.setText("Fragment #" + mNum);

        return view;
    }

    /*@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getData()));
    }

    *//**
     * 在每一个Fragment列表中展示的数据。
     *//*
    private ArrayList<String> getData() {
        for (int i = 0; i < 20; i++) {
            list.add("nihao" + i);
        }
        return list;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), "您点击了"+position, 0).show();
    }
    */
}
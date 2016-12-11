package com.djj.example.wocao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by djj on 2016/12/4.
 */

public class MyFragment extends Fragment {

    public final static String TAG = "MyFragment";

    private TestTable mTestTable;
    private View item;
    private EditText textid, texttext, textip, texttime;

    public static MyFragment getInstances() {
        MyFragment myFragment = new MyFragment();
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        mTestTable = b.getParcelable("setTestTable");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // Log.d(TAG, "onCreateView  mData = " + mData);
       // String str = "";
        /*if (savedInstanceState != null) {
            mTestTable=new TestTable();
            mTestTable.setId(savedInstanceState.getInt("id"));
            mTestTable.setName(savedInstanceState.getString("name"));
            mTestTable.setAddress(savedInstanceState.getString("address"));
            mTestTable.setPhone(savedInstanceState.getString("phone"));
        }*/

        // 这里必须是null
        //Log.d("Myfragment","count="+count);
        //Toast.makeText(getActivity(),"serials="+mTestTable.getSerialsnum(), Toast.LENGTH_SHORT).show();
        item = inflater.inflate(R.layout.fragment_item, container, false);
        textid = (EditText) item.findViewById(R.id.tvid);
        texttext = (EditText) item.findViewById(R.id.tvtext);
        textip = (EditText) item.findViewById(R.id.tvip);
        texttime = (EditText) item.findViewById(R.id.tvtime);
        textid.setEnabled(false);
        texttext.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
//
                mTestTable.setName(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });
        textip.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
//
                mTestTable.setAddress(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });
        texttime.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                mTestTable.setPhone(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });
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
        /*outState.putInt("id",mTestTable.getId());
        outState.putString("name",mTestTable.getName());
        outState.putString("address",mTestTable.getAddress());
        outState.putString("phone",mTestTable.getPhone());*/
       // Log.d(TAG, "onSaveInstanceState  mData = " + mData);
    }

    @Override
    public void onDestroyView() {
        //((ViewGroup)item.getParent()).removeView(item);
        /*textid.setEnabled(false);
        textip.setEnabled(false);
        texttext.setEnabled(false);
        texttime.setEnabled(false);
        item.setEnabled(false);*/
        super.onDestroyView();
    }

    public void clear() {
        textid.setText("");
        textip.setText("");
        texttime.setText("");
        texttext.setText("");
        //mTestTable=null;
        //item=null;
    }

    public View getrootview() {
        return item;
    }

    @Override
    public void onDestroy() {
        /*Bundle b=this.getArguments();
        b.remove("setTestTable");
        b.putParcelable("setTestTable",mTestTable);
        this.setArguments(b);*/
       /* */
        //mTestTable=null;
        super.onDestroy();
       // Log.d(TAG, "onDestroy mData = " + mData);
    }

    public Bundle getmTestTable() {
        Bundle b = new Bundle();
        b.putParcelable("getTestTable", mTestTable);
        return b;
    }
    /*public void setmTestTable(TestTable testTable){
        mTestTable=testTable;
    }*/

}

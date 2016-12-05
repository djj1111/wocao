package com.djj.example.wocao;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by djj on 2016/12/4.
 */


public class MyFragmentActivity extends FragmentActivity {

    private ArrayList<TestTable> tablelist;
    private DbManager db;
    private MyFragmentStatePagerAdapter mFragmentStatePagerAdapter;
    private ViewPager mViewPager;
    private void add(){
        TestTable table = new TestTable();

        try {
            db.saveBindingId(table);
        } catch (DbException e) {
            e.printStackTrace();
        }
        tablelist.add(table);
        MyFragment fragment=MyFragment.getInstances(table);
        mFragmentStatePagerAdapter.addFragment(fragment);
        int position=mFragmentStatePagerAdapter.getItemPosition(fragment);
        mViewPager.setCurrentItem(position);
    }
    private void initshow(){
        if (tablelist.isEmpty()) {add();}
        else{
            for (TestTable t : tablelist){
                mFragmentStatePagerAdapter.addFragment(MyFragment.getInstances(t));
            }
        }

    }
    private void delete(){
        MyFragment fragment=(MyFragment) mFragmentStatePagerAdapter.getItem(mViewPager.getCurrentItem());
        TestTable t= fragment.getmTestTable();
        if (t!=null) {
            try {
                db.delete(t);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    @Event(value = R.id.button_add, type = View.OnClickListener.class)
    private void bt1Click(View v) {
        add();
    }
    @Event(value = R.id.button_delete, type = View.OnClickListener.class)
    private void bt2Click(View v) {
        delete();
    }
    @Event(value = R.id.button_save, type = View.OnClickListener.class)
    private void bt3Click(View v) {
        for(TestTable t : tablelist){
            try {
                db.update(t, "name","address","phone");
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        x.view().inject(this);
        mFragmentStatePagerAdapter=new MyFragmentStatePagerAdapter(getSupportFragmentManager());
        mViewPager=(ViewPager) findViewById(R.id.viewpager);
       // Log.d("fuck","asdfasdfasdfasfdasfafda");
        db_init();
        //tablelist=new ArrayList<>();
        /*if (tablelist.isEmpty()){
           TestTable t=new TestTable();
            t.setId(3);
            t.setPhone("38373893");
            t.setAddress("addr");
            t.setName("hu");
            tablelist.add(t);
        }*/
        //Log.d("fuck","asdfasdfasdfasfdasfafdaassss");

        initshow();
        mViewPager.setAdapter(mFragmentStatePagerAdapter);
    }

    private void db_init(){
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("test.db")
                // 不设置dbDir时, 默认存储在app的私有目录.
                .setDbVersion(2)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        // or
                        // db.dropDb();
                    }
                });
        db = x.getDb(daoConfig);
        try {
            tablelist = (ArrayList<TestTable>) db.findAll(TestTable.class);
            if (tablelist==null) tablelist=new ArrayList<>();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}

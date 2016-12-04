package com.djj.example.wocao;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by djj on 2016/12/4.
 */

public class MyFragmentActivity extends FragmentActivity {
    private ArrayList<TestTable> tablelist;
    private DbManager db;
    private MyFragmentStatePagerAdapter mFragmentStatePagerAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        //x.view().inject(this);
        mFragmentStatePagerAdapter=new MyFragmentStatePagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager=(ViewPager) findViewById(R.id.viewpager);
        Log.d("fuck","asdfasdfasdfasfdasfafda");
        db_init();
        tablelist=new ArrayList<>();
        if (tablelist.isEmpty()){
           TestTable t=new TestTable();
            t.setId(3);
            t.setPhone("38373893");
            t.setAddress("addr");
            t.setName("hu");
            tablelist.add(t);
        }
        Log.d("fuck","asdfasdfasdfasfdasfafdaassss");
            for (TestTable t : tablelist){
                mFragmentStatePagerAdapter.addFragment(MyFragment.getInstances(t));
            }


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

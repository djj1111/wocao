package com.djj.example.wocao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.Toast;

import com.djj.jazzyviewpager.JazzyViewPager;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djj on 2016/12/4.
 */


public class MyFragmentActivity extends FragmentActivity {

    private ArrayList<TestTable> tablelist;
    private DbManager db;
    private MyFragmentStatePagerAdapter mFragmentStatePagerAdapter;
    private FragmentStatePagerAdapter ff;
    private JazzyViewPager mViewPager;
    private int addcount = 0, delcount = 0;

    private void add() {
        TestTable table = new TestTable();
        table.setIsadd(true);
        tablelist.add(table);
        MyFragment fragment = MyFragment.getInstances();
        Bundle b = new Bundle();
        b.putParcelable("setTestTable", table);
        fragment.setArguments(b);
        mFragmentStatePagerAdapter.addFragment(fragment);
        mFragmentStatePagerAdapter.notifyDataSetChanged();
        ;
        /*int i=mFragmentStatePagerAdapter.getItemPosition(fragment);
        addcount++;
        Log.d("add","addcount="+addcount);*/

        mViewPager.setCurrentItem(mFragmentStatePagerAdapter.getCount() - 1, false);
        /*int position=mFragmentStatePagerAdapter.getItemPosition(fragment);
        mViewPager.setCurrentItem(position);*/
    }

    private void delete() {
        int count = mFragmentStatePagerAdapter.getCount();
        if (count == 0) {
            Toast.makeText(MyFragmentActivity.this, "没有内容，不能再删除了！", Toast.LENGTH_SHORT).show();
            return;
        }
        MyFragment fragment = (MyFragment) mFragmentStatePagerAdapter.getCurrentFragment();
        TestTable table = fragment.getmTestTable().getParcelable("getTestTable");
        table.setIsdelete(true);
        for (TestTable t : tablelist) {
            if (t.getSerialsnum() == table.getSerialsnum()) t = table;
        }
        int position = mFragmentStatePagerAdapter.getFramentposition(fragment);
        /*if (position < count - 1) {
            position += 1;
        } else if (position > 0) {
            position -= 1;
        }*/
        //mViewPager.setCurrentItem(position);
        mFragmentStatePagerAdapter.removeFragment(fragment);
        mFragmentStatePagerAdapter.notifyDataSetChanged();
    }

    private void update() {
      /*  if (t!=null) {
            try {
                db.delete(t);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }*/
        try {
            List<Fragment> listFragment = mFragmentStatePagerAdapter.getFragments();
            for (Fragment f : listFragment) {
                TestTable f_t = ((MyFragment) f).getmTestTable().getParcelable("getTestTable");
                for (TestTable t : tablelist) {
                    if (t.getSerialsnum() == f_t.getSerialsnum()) {
                        t = f_t;
                        Toast.makeText(MyFragmentActivity.this, "address=" + t.getAddress() + "isadd=" + t.getIsadd() + "isde=" + t.getIsdelete(), Toast.LENGTH_SHORT).show();
                        break;
                    }

                }
            }

            for (TestTable t : tablelist) {
                if (t.getIsadd() && !t.getIsdelete()) {
                    db.saveBindingId(t);
                } else if (t.getIsdelete() && !t.getIsadd()) {
                    db.delete(t);
                } else if (!t.getIsdelete() && !t.getIsadd()) {
                    db.update(t, "name", "address", "phone");
                }
            }

        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    private void init() {
        if (tablelist.isEmpty()) {
            add();
        } else {
            for (TestTable t : tablelist) {
                MyFragment fragment = MyFragment.getInstances();
                Bundle b = new Bundle();
                b.putParcelable("setTestTable", t);
                fragment.setArguments(b);
                mFragmentStatePagerAdapter.addFragment(fragment);
            }
            mFragmentStatePagerAdapter.notifyDataSetChanged();
        }

    }

    @Event(value = R.id.button_add, type = View.OnClickListener.class)
    private void bt1Click(View v) {
        v.setEnabled(false);
        add();
        v.setEnabled(true);
    }

    @Event(value = R.id.button_delete, type = View.OnClickListener.class)
    private void bt2Click(View v) {
        v.setEnabled(false);
        delete();
        v.setEnabled(true);
    }

    @Event(value = R.id.button_save, type = View.OnClickListener.class)
    private void bt3Click(View v) {
        v.setEnabled(false);
        update();
        v.setEnabled(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        x.view().inject(this);
        mViewPager = (JazzyViewPager) findViewById(R.id.viewpager);
        mViewPager.setTransitionEffect(JazzyViewPager.TransitionEffect.Tablet);
        db_init();
        mFragmentStatePagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), mViewPager);
        mViewPager.setAdapter(mFragmentStatePagerAdapter);
        mViewPager.setPageMargin(30);
        init();
        /*mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                *//*View view = mViewPager.getFocusedChild();
                MyFragment fragment = (MyFragment) mFragmentStatePagerAdapter.getCurrentFragmentbyrootview(view);
                int position1 = mFragmentStatePagerAdapter.getFramentposition(fragment);
                Toast.makeText(MyFragmentActivity.this, "onPageSelected选中了" + position1, Toast.LENGTH_SHORT).show();*//*
            }
        });*/
        /*mViewPager.setPageTransformer(true,new ViewPager.PageTransformer(){
            @Override
            public void transformPage(View page, float position) {

            }
        });*/

    }

    private void db_init() {
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
            if (tablelist == null) tablelist = new ArrayList<>();
            if (!tablelist.isEmpty()) {
                for (TestTable t : tablelist) {
                    t.setIsadd(false);
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}

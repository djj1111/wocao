package com.djj.example.wocao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    float x1 = 0, x2 = 0;
    /*@ViewInject(R.id.bt1)
    private Button bt1;
    @ViewInject(R.id.bt2)
    private Button bt2;
    @ViewInject(R.id.bt3)
    private Button bt3;*/
    private DbManager db;
    @ViewInject(R.id.tv1)
    private TextView tv1;
    @ViewInject(R.id.tv2)
    private TextView tv2;
    @ViewInject(R.id.tv3)
    private TextView tv3;
    @ViewInject(R.id.et1)
    private EditText et1;
    @ViewInject(R.id.et2)
    private EditText et2;
    @ViewInject(R.id.et3)
    private EditText et3;
    private ArrayList<TestTable> tablelist;
    private int curPointer;

    @Event(value = R.id.bt1, type = View.OnClickListener.class)
    private void bt1onClick(View v) {
        TestTable table = new TestTable();
        table.setName(et1.getText().toString());
        table.setAddress(et2.getText().toString());
        table.setPhone(et3.getText().toString());
        try {
            db.saveBindingId(table);
            tablelist.add(table);
            curPointer = tablelist.size() - 1;
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Event(value = R.id.bt2, type = View.OnClickListener.class)
    private void bt2onClick(View v) {
        //tablelist=new ArrayList<>();
        if (!tablelist.isEmpty()) {
            tv1.setText(tablelist.get(curPointer).getName());
            tv2.setText(tablelist.get(curPointer).getAddress());
            tv3.setText(tablelist.get(curPointer).getPhone());
        }
    }

    @Event(value = R.id.bt3, type = View.OnClickListener.class)
    private void bt3onClick(View v) {
        //ArrayList<TestTable>tablelist=new ArrayList<>();
        if (!tablelist.isEmpty()) {
            try {
                db.delete(tablelist.get(curPointer));
                tablelist.remove(curPointer);
                if (curPointer > tablelist.size() && !tablelist.isEmpty())
                    curPointer = tablelist.size() - 1;
            } catch (DbException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (tablelist.isEmpty()) return true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            x2 = event.getX();
            if (x2 < x1 && curPointer < tablelist.size() - 1) {
                curPointer += 1;
                tv1.setText(tablelist.get(curPointer).getName());
                tv2.setText(tablelist.get(curPointer).getAddress());
                tv3.setText(tablelist.get(curPointer).getPhone());
            } else if (x2 > x1 && curPointer > 0) {
                curPointer -= 1;
                tv1.setText(tablelist.get(curPointer).getName());
                tv2.setText(tablelist.get(curPointer).getAddress());
                tv3.setText(tablelist.get(curPointer).getPhone());
            }
        }
        super.dispatchTouchEvent(event);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);

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
        } catch (DbException e) {
            e.printStackTrace();
        }

        curPointer = 0;

    }

}

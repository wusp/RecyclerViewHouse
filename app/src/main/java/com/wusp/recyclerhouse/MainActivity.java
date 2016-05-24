package com.wusp.recyclerhouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private BaseLinearRecyclerAdapter baseRecyclerAdapter;
    private RecyclerItemClickListener itemClickListener;
    private ItemTouchHelper itemTouchHelper;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        baseRecyclerAdapter = new BaseLinearRecyclerAdapter();
        list = new ArrayList<>();
        samplePackage();
        baseRecyclerAdapter.setmDataList(list);
        baseRecyclerAdapter.enableSwipeOut(true, LinearLayoutManager.VERTICAL);
        recyclerView.setAdapter(baseRecyclerAdapter);
        itemTouchHelper = new ItemTouchHelper(baseRecyclerAdapter.getItemMoveCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            int pos = new Random().nextInt(list.size());
            list.remove(pos);
            baseRecyclerAdapter.notifyItemRemoved(pos);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void samplePackage(){
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
    }
}

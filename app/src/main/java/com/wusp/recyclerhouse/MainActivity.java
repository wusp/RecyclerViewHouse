package com.wusp.recyclerhouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
        //1.Set up LayoutManager which every RecyclerViews must have one to manage item layout.
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //2.Prepare data adapter and data set.
        baseRecyclerAdapter = new BaseLinearRecyclerAdapter();
        list = new ArrayList<>();
        samplePackage();
        baseRecyclerAdapter.setmDataList(list);
        //3.Enable swipe out function, user can use this to remove item.
        baseRecyclerAdapter.enableSwipeOut(true, LinearLayoutManager.VERTICAL);
        //4.Enable move and swap function, user can use this to change item relative position.
        baseRecyclerAdapter.enableDataSwap(true);
        recyclerView.setAdapter(baseRecyclerAdapter);
        //If you want to enable 3 or 4 function, you need to set a callback to ItemTouchHelper, and let
        //ItemTouchHelper to watch the RecyclerView.
        itemTouchHelper = new ItemTouchHelper(baseRecyclerAdapter.getItemMoveCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
        //5. RecyclerView OnClickListener
        itemClickListener = new RecyclerItemClickListener(this);
        itemClickListener.setOnItemClickListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                //When you click RecyclerView items, will recall this.
            }
        });
        recyclerView.addOnItemTouchListener(itemClickListener);
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

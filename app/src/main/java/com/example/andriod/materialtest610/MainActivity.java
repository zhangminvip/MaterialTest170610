package com.example.andriod.materialtest610;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.support.v7.appcompat.R.styleable.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private NavigationView navView;
    private  ActionBar actionBar;
    private RecyclerView recyclerView;
    private List<Fruit> mFruitList = new ArrayList<>();
    private FruitAdapter mAdapter;

    private Fruit[] mFruits = {
            new Fruit("Apple",R.drawable.apple),
            new Fruit("Banana",R.drawable.banana),
            new Fruit("Orange",R.drawable.orange),
            new Fruit("Watermelon",R.drawable.watermelon),
            new Fruit("Pear",R.drawable.pear),
            new Fruit("Grape",R.drawable.grape),
            new Fruit("Pineapple",R.drawable.pineapple),
            new Fruit("Strawberry",R.drawable.strawberry),
            new Fruit("Cherry",R.drawable.cherry),
            new Fruit("Mango",R.drawable.mango),

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initFruit();

    }

    /*
    * 初始化水果的集合
    *
    * */
    private void initFruit(){
        mFruitList.clear();

        for(int i = 0; i<50; i++){
            Random random = new Random();
            int index = random.nextInt(mFruits.length);
            mFruitList.add(mFruits[index]);
        }


    }



    /*
    * 初始化视图
    * */
    public void initView(){

        fab = (FloatingActionButton)findViewById(R.id.fab);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.nav_view) ;

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

    }




    /*
    * 初始化事件
    *
    * */
    public void initEvent(){
        actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Data deleted",Snackbar.LENGTH_SHORT)
                        .setAction("Undo",new View.OnClickListener(){
                           @Override
                            public void onClick(View v){
                               Toast.makeText(MainActivity.this,"Data restored",
                                       Toast.LENGTH_SHORT).show();
                           }
                        }).show();
            }
        });



        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item)
            {

                switch (item.getItemId()){
                    case R.id.nav_friends:
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(intent);
                        break;
                    default:
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new FruitAdapter(mFruitList);
        recyclerView.setAdapter(mAdapter);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar,menu);

        return true;
    }


    /*
    * 菜单点击事件
    * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }


}

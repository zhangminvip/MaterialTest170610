package com.example.andriod.materialtest610;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static android.support.v7.appcompat.R.styleable.MenuItem;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private NavigationView navView;
    private  ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        initView();
        initEvent();


        actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

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
    }

    /*
    * 初始化视图
    * */
    public void initView(){
         fab = (FloatingActionButton)findViewById(R.id.fab);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.nav_view) ;

    }


    /*
    * 初始化事件
    *
    * */
    public void initEvent(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"FAB clicked",Toast.LENGTH_SHORT).show();
            }
        });
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

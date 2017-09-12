package com.example.kongsun.schoolguide.activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.kongsun.schoolguide.R;
import com.example.kongsun.schoolguide.fragment.HomeFragment;
import com.example.kongsun.schoolguide.fragment.NewsFragment;
import com.example.kongsun.schoolguide.fragment.UniversityFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ប្តូរពណ៌ Status Bar
        //បានតែចំពោះ Version 21 ឡើង ឬ Lollipop
        if (Build.VERSION.SDK_INT>=21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));
        }

        //រៀបចំ Toolbar និងដាក់ឈ្មោះ
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.toolbar));
        setSupportActionBar(toolbar);
        getSupportActionBar();
        setTitle("SchoolGuide");

        //Set Onclick Navigation Drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set DrawerToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //នៅពេលដែលបើកកម្មវីធីមក វានឹងបង្ហាញផ្ទាំង Home មុនគេ
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        int lastSeletedMenu = sharedPreferences.getInt("last-selected-menu",R.id.nv_home);
        MenuItem selectedItem = navigationView.getMenu().findItem(lastSeletedMenu);
        selectedItem.setCheckable(true);
        onNavigationItemSelected(selectedItem);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId())
        {
            case R.id.nv_home:
                Toast.makeText(getApplication(),"Home",Toast.LENGTH_SHORT).show();
                getSupportActionBar();
                setTitle("Home");
                onHomeClick();
                break;
            case R.id.nv_school:
                Toast.makeText(getApplication(),"PublicUniversity",Toast.LENGTH_SHORT).show();
                getSupportActionBar();
                setTitle("PublicUniversity");
                onSchoolClick();
                break;
            case R.id.nv_news:
                Toast.makeText(getApplication(),"News",Toast.LENGTH_SHORT).show();
                getSupportActionBar();
                setTitle("News");
                onNewsClick();
                break;

        }

        return true;
    }

    private void onNewsClick() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewsFragment newsFragment = new NewsFragment();
        fragmentTransaction.replace(R.id.main_frame,newsFragment);
        fragmentTransaction.commit();
    }

    private void onSchoolClick() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        UniversityFragment universityFragment = new UniversityFragment();
        fragmentTransaction.replace(R.id.main_frame,universityFragment);
        fragmentTransaction.commit();
    }

    private void onHomeClick() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.main_frame, homeFragment);
        fragmentTransaction.commit();
    }
}

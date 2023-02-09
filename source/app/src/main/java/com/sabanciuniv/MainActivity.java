package com.sabanciuniv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.sabanciuniv.model.NewsCategory;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private NewsRepository repo;
    private ExecutorService srv;
    private List<NewsCategory> cat;

    Handler categoryHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Log.d("dev", "Getting categories to set the tabs...");
            cat = (List<NewsCategory>) message.obj;
            for (int i = 0; i < cat.size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(cat.get(i).getName()));
            }
            TabAdapter adapter = new TabAdapter(MainActivity.this, tabLayout.getTabCount(), cat);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(0);
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.mipmap.ic_launcher);
        initViews();
    }

    private void initViews() {
        // initialise the viewpager2
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        repo = new NewsRepository();
        srv = ((NewsApplication)getApplication()).srv;
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
        repo.getCategories(srv, categoryHandler);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("dev", "On resume of MainActivity...");
    }
}

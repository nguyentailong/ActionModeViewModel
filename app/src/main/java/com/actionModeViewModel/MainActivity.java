package com.actionModeViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.actionmode.viewmodel.R;
import com.actionModeViewModel.listviewFragment.ListViewFragment;
import com.actionModeViewModel.recyclerFragment.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity implements Callback {
    public ActionMode mActionMode;
    private ViewPagerAdapter adapter;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);//Set up View Pager

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);//setting tab over viewpager
        startActionMode();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ListViewFragment(), "ListView");
        adapter.addFrag(new RecyclerViewFragment(), "RecyclerView");
        viewPager.setAdapter(adapter);
        //1
        //2
        //3
    }

    public void startActionMode() {
        mActionMode = startSupportActionMode(this);
        mMainViewModel.getSelectedItems().observe(this, itemModels -> {
            if (mActionMode == null) {
                return;
            }
            mActionMode.setTitle(String.valueOf(itemModels.size()) + " selected");
            mActionMode.setSubtitle("ViewModel");
        });
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.findItem(R.id.action_copy).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.findItem(R.id.action_forward).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                mActionMode.finish();//Finish action mode
                break;
            case R.id.action_copy:
                mActionMode.finish();//Finish action mode
                break;
            case R.id.action_forward:
                mActionMode.finish();//Finish action mode
                break;


        }
        return false;
    }


    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mMainViewModel.reset();
        mActionMode = null;
    }
}

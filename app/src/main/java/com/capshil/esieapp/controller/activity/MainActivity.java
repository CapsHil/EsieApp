package com.capshil.esieapp.controller.activity;

import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

import com.capshil.esieapp.R;
import com.capshil.esieapp.controller.delegate.MainDelegate;
import com.capshil.esieapp.controller.fragment.BeerFragment;
import com.capshil.esieapp.controller.fragment.MapFragment;
import com.capshil.esieapp.controller.fragment.TemperatureFragment;
import com.capshil.esieapp.controller.fragment.HomeFragment;
import com.capshil.esieapp.controller.fragment.NotificationsFragment;
import com.capshil.esieapp.controller.listener.DrawerItemClickListener;
import com.capshil.esieapp.view.adapter.DrawerAdapter;
import com.capshil.esieapp.view.element.DrawerItem;

public class MainActivity extends BaseActivity {

    @Override
    protected void onResume() {
        super.onResume();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mNavigationArray = new ArrayList<>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mNavigationArray.add(new DrawerItem(R.string.home, R.drawable.ic_launcher));
        mNavigationArray.add(new DrawerItem(R.string.notifications, R.drawable.ic_launcher));
        mNavigationArray.add(new DrawerItem(R.string.beers, R.drawable.ic_launcher));
        mNavigationArray.add(new DrawerItem(R.string.temperature, R.drawable.ic_launcher));
        mNavigationArray.add(new DrawerItem(R.string.map, R.drawable.ic_launcher));
        mDrawerList.setAdapter(new DrawerAdapter(this,R.layout.drawer_item, mNavigationArray));
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener(this));
        MainDelegate.getInstance().replaceFragment(R.id.content_frame,new HomeFragment(),this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void selectItem(int position) {
        Fragment fragment;
        switch(position) {
            case 1:
                fragment = new NotificationsFragment();
                break;
            case 2:
                fragment = new BeerFragment();
                break;
            case 3:
                fragment = new TemperatureFragment();
                break;
            case 4:
                fragment = new MapFragment();
                break;
            default:
                fragment = new HomeFragment();
                break;
        }
        MainDelegate.getInstance().replaceFragment(R.id.content_frame,fragment,this);
        if(position > 0) {
            mDrawerList.setItemChecked(position, true);
            setTitle(getResources().getString(mNavigationArray.get(position).getNameId()));
        }
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
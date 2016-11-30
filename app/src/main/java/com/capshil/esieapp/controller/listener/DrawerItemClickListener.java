package com.capshil.esieapp.controller.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.capshil.esieapp.controller.activity.MainActivity;

public class DrawerItemClickListener implements ListView.OnItemClickListener {
    private MainActivity mainActivity;

    public DrawerItemClickListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mainActivity.selectItem(position);
    }
}

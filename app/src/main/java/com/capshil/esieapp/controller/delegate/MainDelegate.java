package com.capshil.esieapp.controller.delegate;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

public class MainDelegate {
    private static volatile MainDelegate instance = null;

    private MainDelegate() {
        super();
    }

    public final static MainDelegate getInstance() {
        if (MainDelegate.instance == null) {
            synchronized(MainDelegate.class) {
                if (MainDelegate.instance == null)
                    MainDelegate.instance = new MainDelegate();
            }
        }
        return MainDelegate.instance;
    }

    public void replaceFragment(int container, Fragment fragment, Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(container, fragment)
                .commit();
    }
}

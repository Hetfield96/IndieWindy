package com.siroytman.indiewindymobile.services;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class FragmentService {
//    private final FragmentActivity activity;
//    private final FragmentManager fragmentManager;
//
//    public FragmentService(FragmentActivity activity) {
//        this.activity = activity;
//        fragmentManager = activity.getSupportFragmentManager();
//    }

    public static void replaceFragment(FragmentManager fragmentManager, int container, Fragment f){
        fragmentManager.beginTransaction().replace(container, f).commit();
    }

    public static void replaceFragment(Fragment fragment, int container, Fragment f){
        fragment.getChildFragmentManager().beginTransaction().replace(container, f).commit();
    }

//    public void removeFrag(Fragment f){
//        FragmentTransacion ft = activity.getSupportFragmentManager().beginTransaction();
//        //some work
//    }
//
//    public void addFrag(Fragment f, int container){
//        FragmentTransacion ft = activity.getSupportFragmentManager().beginTransaction();
//        //some work
//    }
}

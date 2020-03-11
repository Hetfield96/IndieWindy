package com.siroytman.indiewindymobile.services;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class FragmentService {
    public static void replaceFragment(FragmentActivity activity, int container, Fragment f){
        activity.getSupportFragmentManager().beginTransaction().replace(container, f).commit();
    }

    public static void replaceFragment(Fragment fragment, int container, Fragment f){
        fragment.getChildFragmentManager().beginTransaction().replace(container, f).commit();
    }

}

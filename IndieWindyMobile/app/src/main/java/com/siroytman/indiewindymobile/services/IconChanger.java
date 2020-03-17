package com.siroytman.indiewindymobile.services;

import android.widget.ImageView;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.interfaces.ILinkEmpty;

public class IconChanger {

    public static void setAddStateIcon(ILinkEmpty link, ImageView addButton) {
        if(link.isEmpty()){
            setIconAdd(addButton);
        } else {
            setIconCheck(addButton);
        }
    }

    public static void setIconCheck(ImageView addButton) {
        addButton.setImageResource(R.drawable.ic_check);
    }

    public static void setIconAdd(ImageView addButton) {
        addButton.setImageResource(R.drawable.ic_add);
    }
}

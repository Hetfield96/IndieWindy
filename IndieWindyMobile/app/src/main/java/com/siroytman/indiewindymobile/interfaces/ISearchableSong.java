package com.siroytman.indiewindymobile.interfaces;

import com.siroytman.indiewindymobile.model.UserSongLink;

import java.util.ArrayList;

public interface ISearchableSong {
    void songsFoundViewUpdate(ArrayList<UserSongLink> links);
}

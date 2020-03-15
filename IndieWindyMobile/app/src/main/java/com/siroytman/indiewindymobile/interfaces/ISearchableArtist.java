package com.siroytman.indiewindymobile.interfaces;

import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.model.UserSongLink;

import java.util.ArrayList;

public interface ISearchableArtist {
    void artistsFoundViewUpdate(ArrayList<UserArtistLink> links);
}

package com.siroytman.indiewindymobile.interfaces;

import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.model.UserArtistLink;

import java.util.ArrayList;

public interface ISearchableAlbum {
    void albumsFoundViewUpdate(ArrayList<UserAlbumLink> links);
}

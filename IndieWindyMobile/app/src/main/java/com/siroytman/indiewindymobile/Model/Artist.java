package com.siroytman.indiewindymobile.model;

public class Artist {
    public int id;

    public String name;

    public String imageUrl;

    public Album[] album;

    public ArtistConcertLink[] artistConcertLink;

    public Song[] song;

    public UserArtistLinkSubscriptions[] userArtistLinkSubscriptions;
}

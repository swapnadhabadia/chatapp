package com.app.haptikchat.model;

/**
 * Created by swapna on 5/3/17.
 */

public class FavouritePojo {

    public String userName;
    public int position;
    public Boolean favourite;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

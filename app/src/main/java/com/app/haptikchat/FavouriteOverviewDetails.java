package com.app.haptikchat;

/**
 * Created by swapna on 5/3/17.
 */
public class FavouriteOverviewDetails {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String userName;
    public int position;

    @Override
    public String toString() {
        return "Messages{" +
                "userName='" + userName + '\'' +
                ", position=" + position +

                '}';
    }
}

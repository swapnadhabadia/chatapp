package com.app.haptikchat;

/**
 * Created by swapna on 5/3/17.
 */
public class chatSummeryDbDetails {

    public String userName;
    public int totalMessages;
    public String images;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(int totalMessages) {
        this.totalMessages = totalMessages;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "userName='" + userName + '\'' +
                ", mTotalTimeTaken=" + totalMessages +
                ", images=" + images +
                '}';
    }
}

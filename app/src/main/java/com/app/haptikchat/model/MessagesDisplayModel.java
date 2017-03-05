package com.app.haptikchat.model;

/**
 * Created by swapna on 5/3/17.
 */
public class MessagesDisplayModel {

    String ID;
    String userName;
    String messages;
    String image;
    String name;


    public MessagesDisplayModel() {

    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }




    public MessagesDisplayModel(String userName, String messages, String image, String name) {
        this.userName=userName;
        this.messages=messages;
        this.image=image;
        this.name=name;

    }



    @Override
    public String toString() {
        return "Messages{" +
                "userName='" + userName + '\'' +
                ", messages=" + messages +
                + '\'' +
                ", name=" + name +
                '}';
    }
}

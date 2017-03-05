package com.app.haptikchat.database;

import android.content.Context;

import com.app.haptikchat.model.MessagesDisplayModel;

import java.util.ArrayList;

/**
 * Created by swapna on 11/1/17.
 */

public class MessagesStore {


    public Context context;
    private ArrayList<MessagesDisplayModel> MessagesDisplayModelArrayList=new ArrayList<>();
    MessageDatabaseHandler notificationDatabaseHandler;
    public MessagesStore(Context context) {
        this.context=context;
        notificationDatabaseHandler = new MessageDatabaseHandler(context, null, null, 1);
    }


    public ArrayList<MessagesDisplayModel> getAllUserChatData()
    {

        MessagesDisplayModelArrayList =notificationDatabaseHandler.getAllNotificationData();
        return MessagesDisplayModelArrayList;
    }

    public void setAllNotificationData(String userName, String message, String image, String name, String numfav)
    {

        notificationDatabaseHandler.insertData(new MessagesDisplayModel(userName,message,image,name));
    }

    /*public void updateNotificationData(String numfav)
    {
       *//* NotificationDatabaseHandler notificationDatabaseHandler=new NotificationDatabaseHandler(context,null,null,1);
        arrayListOfNotification =notificationDatabaseHandler.getAllNotificationData();
        return arrayListOfNotification;*//*
        notificationDatabaseHandler.(new MessagesDisplayModel(userName,message,image,name,numfav));
    }*/


}

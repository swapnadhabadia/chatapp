package com.app.haptikchat.database;

import android.content.Context;
import android.util.Log;

import com.app.haptikchat.model.ChatSummeryDbDetails;
import com.app.haptikchat.model.FavTotalModel;
import com.app.haptikchat.model.MessagesDisplayModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapna on 11/1/17.
 */

public class MessagesStore {


    public Context context;
     ArrayList<MessagesDisplayModel> MessagesDisplayModelArrayList=new ArrayList<>();
    MessageDatabaseHandler notificationDatabaseHandler;
    List<ChatSummeryDbDetails> MessagesDisplayModelArrayList1;
     List<FavTotalModel> mFavTotalModelList;
     String TAG="MessagesStore";

    public MessagesStore(Context context) {
        this.context=context;
        notificationDatabaseHandler = new MessageDatabaseHandler(context, null, null, 1);
    }

    public MessagesStore() {

    }


    public ArrayList<MessagesDisplayModel> getAllUserChatData()
    {

        MessagesDisplayModelArrayList =notificationDatabaseHandler.getAllNotificationData();
        return MessagesDisplayModelArrayList;
    }

    public void setAllNotificationData(String userName, String message, String image, String name,String timeStamp,int me, int numfav)
    {

        notificationDatabaseHandler.insertData(new MessagesDisplayModel(userName,message,image,name,timeStamp,me,numfav));
    }

    public List<ChatSummeryDbDetails> getChatDetail() {
        MessagesDisplayModelArrayList1 =notificationDatabaseHandler.getOverviewOfApis();
        return MessagesDisplayModelArrayList1;

    }

    public void updateHandler(MessagesDisplayModel messagesDisplayModel) {

        notificationDatabaseHandler.updateNotificationData(messagesDisplayModel);
    }

    public List<FavTotalModel> getFavDetail() {
        mFavTotalModelList =notificationDatabaseHandler.getTotalFavMessages();
        Log.i(TAG, "getFavDetail: "+mFavTotalModelList);
        return mFavTotalModelList;


    }

    /*public void updateNotificationData(String numfav)
    {
       *//* NotificationDatabaseHandler notificationDatabaseHandler=new NotificationDatabaseHandler(context,null,null,1);
        arrayListOfNotification =notificationDatabaseHandler.getAllNotificationData();
        return arrayListOfNotification;*//*
        notificationDatabaseHandler.(new MessagesDisplayModel(userName,message,image,name,numfav));
    }*/


}

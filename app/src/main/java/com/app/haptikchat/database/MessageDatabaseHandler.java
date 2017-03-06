package com.app.haptikchat.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.haptikchat.model.ChatSummeryDbDetails;
import com.app.haptikchat.model.FavTotalModel;
import com.app.haptikchat.model.MessagesDisplayModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapna on 5/3/17.
 */

public class MessageDatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "messageManager.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    private static final String CREATE_TABLE = "CREATE TABLE " + MessageSDbSchema.ListofTable.NAME + "("
            + MessageSDbSchema.ListofTable.Columns.ID + " INTEGER PRIMARY KEY autoincrement, "
            + MessageSDbSchema.ListofTable.Columns.USER_NAME + " TEXT,"
            + MessageSDbSchema.ListofTable.Columns.MESSAGES + " TEXT,"
            + MessageSDbSchema.ListofTable.Columns.IMAGE + " TEXT,"
            + MessageSDbSchema.ListofTable.Columns.MYFAV + " INTEGER,"
            + MessageSDbSchema.ListofTable.Columns.DATE + " STRING,"
            + MessageSDbSchema.ListofTable.Columns.ME + " TEXT,"
            + MessageSDbSchema.ListofTable.Columns.NAME + " TEXT)";
    private String TAG="MessageDatabaseHandler";


    public MessageDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MessageDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MessageSDbSchema.ListofTable.NAME);
        // Create tables again
        onCreate(db);
    }

    public void insertData(MessagesDisplayModel messagesDisplayModel) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageSDbSchema.ListofTable.Columns.USER_NAME, messagesDisplayModel.getUserName());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.MESSAGES, messagesDisplayModel.getMessages());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.NAME, messagesDisplayModel.getName());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.IMAGE, messagesDisplayModel.getImage());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.MYFAV, messagesDisplayModel.getMyFav());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.DATE, messagesDisplayModel.getTimeStamp());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.ME, messagesDisplayModel.getMe());
        database.insert(MessageSDbSchema.ListofTable.NAME, null, contentValues);
        database.close();
    }

    public ArrayList<MessagesDisplayModel> getAllNotificationData()
    {
        database=this.getReadableDatabase();
        Cursor cursor=database.query(MessageSDbSchema.ListofTable.NAME,null,null,null,null,null,null);

        ArrayList<MessagesDisplayModel> notificationModelArrayList=new ArrayList<>();

        MessagesDisplayModel messagesDisplayModel;
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();

            messagesDisplayModel = new MessagesDisplayModel();
            messagesDisplayModel.setID(cursor.getString(0));
            messagesDisplayModel.setUserName(cursor.getString(1));
            messagesDisplayModel.setMessages(cursor.getString(2));
            messagesDisplayModel.setImage(cursor.getString(3));
            messagesDisplayModel.setMyFav(cursor.getInt(4));
            messagesDisplayModel.setTimeStamp(cursor.getString(5));
            messagesDisplayModel.setMe(cursor.getInt(6));
            messagesDisplayModel.setName(cursor.getString(7));
            notificationModelArrayList.add(messagesDisplayModel);
        }
        cursor.close();
       // database.close();
        // NotificationStore.setAllNotificationData(notificationModelArrayList);

        return notificationModelArrayList;
    }

    public void dropTableIfExists() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(MessageSDbSchema.ListofTable.NAME, null, null);
    }
    public void updateNotificationData(MessagesDisplayModel messagesDisplayModel) {
        database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MessageSDbSchema.ListofTable.Columns.USER_NAME, messagesDisplayModel.getUserName());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.MESSAGES, messagesDisplayModel.getMessages());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.NAME, messagesDisplayModel.getName());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.IMAGE, messagesDisplayModel.getImage());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.MYFAV, messagesDisplayModel.getMyFav());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.DATE, messagesDisplayModel.getTimeStamp());
        contentValues.put(MessageSDbSchema.ListofTable.Columns.ME, messagesDisplayModel.getMe());

        database.update(MessageSDbSchema.ListofTable.NAME, contentValues, MessageSDbSchema.ListofTable.Columns.ID + " = ?", new String[]{messagesDisplayModel.getID()});
        database.close();
    }

    public List<ChatSummeryDbDetails> getOverviewOfApis() {
        List<ChatSummeryDbDetails> apiOverviewDetailsList = new ArrayList<>();
        database=getReadableDatabase();
        Log.i(TAG, "getOverviewOfApis: ");
        String mSelectComplexQuery = "SELECT " +MessageSDbSchema.ListofTable.Columns.IMAGE +","+MessageSDbSchema.ListofTable.Columns.NAME +","

                + " COUNT(" +"distinct " + MessageSDbSchema.ListofTable.Columns.MESSAGES + ")"
                + " FROM " + MessageSDbSchema.ListofTable.NAME  + " GROUP BY " + MessageSDbSchema.ListofTable.Columns.NAME;

        Cursor cursor = database.rawQuery(mSelectComplexQuery, null);
        try {
            if(cursor !=null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    ChatSummeryDbDetails overviewDetails = new ChatSummeryDbDetails();
                    overviewDetails.setImages(cursor.getString(0));
                    overviewDetails.setUserName(cursor.getString(1));
                    overviewDetails.setTotalMessages(cursor.getInt(2));
                    apiOverviewDetailsList.add(overviewDetails);
                    cursor.moveToNext();
                }

            }
        } finally {
            cursor.close();
            database.close();
        }
        return apiOverviewDetailsList;
    }


    public List<FavTotalModel> getTotalFavMessages() {

        List<FavTotalModel> favTotalModelArrayList = new ArrayList<>();

        database=getReadableDatabase();
        Log.i(TAG, "getOverviewOfApis: ");


        String mSelectComplexQuery = "SELECT " +MessageSDbSchema.ListofTable.Columns.NAME+","

                + " COUNT (" +MessageSDbSchema.ListofTable.Columns.MESSAGES + ")"
                + " FROM " + MessageSDbSchema.ListofTable.NAME  + " WHERE " + MessageSDbSchema.ListofTable.Columns.MYFAV+" = "+1 +" GROUP BY "+MessageSDbSchema.ListofTable.Columns.NAME;

        Cursor cursor = database.rawQuery(mSelectComplexQuery, null);
        try {
            if(cursor !=null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    FavTotalModel favTotalModel = new FavTotalModel();
                    favTotalModel.setUserName(cursor.getString(0));
                    favTotalModel.setFavTotal(cursor.getInt(1));
                    favTotalModelArrayList.add(favTotalModel);
                    cursor.moveToNext();
                }

            }
        } finally {
            cursor.close();
            database.close();
        }

        return favTotalModelArrayList;
    }
}
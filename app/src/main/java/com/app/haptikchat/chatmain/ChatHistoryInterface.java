package com.app.haptikchat.chatmain;

import android.content.Context;

import com.app.haptikchat.model.MessageModel;

import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public interface ChatHistoryInterface {

    void showProgressDialog(String message, boolean indeterminate, boolean isCancelable);

    void hideProgressDialog();

    void setChatdata(Context context, List<MessageModel> messages, Integer count);
}

package com.app.haptikchat.chatmain;

import android.content.Context;

import com.app.haptikchat.model.MessageModel;

import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class ChatHistoryPresenter implements ChatHistoryInterface {

    private final ChatHistoryInterface chatHistoryInterface;
    private final ChatHistoryInteractor chatHistoryInteractor;

    public ChatHistoryPresenter(Context context, ChatHistoryInterface chatHistoryInterface) {
        this.chatHistoryInterface = chatHistoryInterface;
        chatHistoryInteractor = new ChatHistoryInteractor(this);
    }

    public void getChatResponse(Context context) {
        chatHistoryInteractor.getChatDetailResponse(context);
    }


    @Override
    public void showProgressDialog(String message, boolean indeterminate, boolean isCancelable) {
        chatHistoryInterface.showProgressDialog(message,indeterminate,isCancelable);
    }

    @Override
    public void hideProgressDialog() {
        chatHistoryInterface.hideProgressDialog();
    }

    @Override
    public void setChatdata(Context contexts, List<MessageModel> messages, Integer count) {
        chatHistoryInterface.setChatdata(contexts, messages, count);
    }


}

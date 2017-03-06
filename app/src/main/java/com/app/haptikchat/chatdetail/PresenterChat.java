package com.app.haptikchat.chatdetail;

import android.content.Context;
import android.util.Log;

import com.app.haptikchat.chatdetail.history.HistoryFragmentInterface;
import com.app.haptikchat.chatdetail.summary.SummaryFragmentInterface;
import com.app.haptikchat.model.ChatSummeryDbDetails;
import com.app.haptikchat.model.FavTotalModel;

import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class PresenterChat implements HistoryFragmentInterface, SummaryFragmentInterface {

    private HistoryFragmentInterface historyFragmentInterface;
    private InteractorChat interactorChat;
    private SummaryFragmentInterface summaryFragmentInterface;
    private String TAG = "ChatHistoryPresenter";

    public PresenterChat(Context context, HistoryFragmentInterface historyFragmentInterface) {
        this.historyFragmentInterface = historyFragmentInterface;
        interactorChat = new InteractorChat(context, this);
    }

    public PresenterChat(Context context, SummaryFragmentInterface summaryFragmentInterface) {
        this.summaryFragmentInterface = summaryFragmentInterface;
        interactorChat = new InteractorChat(context, this);
    }


    public void getChatResponse(Context context) {
        interactorChat.getChatDetailResponse(context);
    }


    @Override
    public void showProgressDialog(String message, boolean indeterminate, boolean isCancelable) {
        if (historyFragmentInterface != null) {
            historyFragmentInterface.showProgressDialog(message, indeterminate, isCancelable);
        }
    }

    @Override
    public void hideProgressDialog() {
        if (historyFragmentInterface != null) {
            historyFragmentInterface.hideProgressDialog();
        }
    }

    @Override
    public void setChatData(Context contexts) {
        if (historyFragmentInterface != null) {
            historyFragmentInterface.setChatData(contexts);
            Log.i(TAG, "chatHistoryInterface: ");
        }
    }


    public void getChatDetail(Context context) {
        interactorChat.getChatDetailSummeryResponse(context);
    }

    @Override
    public void setChatSummeryData(Context context, List<ChatSummeryDbDetails> chatSummeryDbDetailsList, List<FavTotalModel> favTotalModelsList) {
        if (summaryFragmentInterface != null) {
            summaryFragmentInterface.setChatSummeryData(context, chatSummeryDbDetailsList, favTotalModelsList);
            Log.i(TAG, "chatSummeryInterface: " + chatSummeryDbDetailsList);
        }

    }
}

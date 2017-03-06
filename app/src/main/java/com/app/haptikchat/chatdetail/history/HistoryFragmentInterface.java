package com.app.haptikchat.chatdetail.history;

import android.content.Context;

/**
 * Created by swapna on 4/3/17.
 */
public interface HistoryFragmentInterface {

    void showProgressDialog(String message, boolean indeterminate, boolean isCancelable);

    void hideProgressDialog();

    void setChatData(Context contexts);
}

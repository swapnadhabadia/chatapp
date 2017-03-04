package com.app.haptikchat;

import android.content.Context;

import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public interface HomeFragmentInterface {

    void showProgressDialog(String message, boolean indeterminate, boolean isCancelable);

    void hideProgressDialog();

    void setChatdata(Context context, List<Message> messages, Integer count);
}

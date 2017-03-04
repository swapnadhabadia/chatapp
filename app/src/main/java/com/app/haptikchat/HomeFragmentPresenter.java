package com.app.haptikchat;

import android.content.Context;

import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class HomeFragmentPresenter implements HomeFragmentInterface {

    private final HomeFragmentInterface homeFragmentInterface;
    private final HomeFragmentInteractor homeFragmentInteractor;

    public HomeFragmentPresenter(Context context, HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
        homeFragmentInteractor = new HomeFragmentInteractor(this);
    }

    public void getChatResponse(Context context) {
        homeFragmentInteractor.getChatDetailResponse(context);
    }


    @Override
    public void showProgressDialog(String message, boolean indeterminate, boolean isCancelable) {
        homeFragmentInterface.showProgressDialog(message,indeterminate,isCancelable);
    }

    @Override
    public void hideProgressDialog() {
        homeFragmentInterface.hideProgressDialog();
    }

    @Override
    public void setChatdata(Context contexts, List<Message> messages, Integer count) {
        homeFragmentInterface.setChatdata(contexts, messages, count);
    }


}

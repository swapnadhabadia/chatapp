package com.app.haptikchat.chatmain;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.haptikchat.R;
import com.app.haptikchat.networking.APIRequest;
import com.app.haptikchat.networking.Connectivity;
import com.app.haptikchat.networking.VolleyUtil;
import com.app.haptikchat.model.ApiModel;
import com.app.haptikchat.model.MessageModel;
import com.app.haptikchat.others.Constants;
import com.app.haptikchat.others.URLConstants;

/**
 * Created by swapna on 4/3/17.
 */
public class ChatHistoryInteractor {

    private final ChatHistoryPresenter chatHistoryPresenter;

    public ChatHistoryInteractor(ChatHistoryPresenter chatHistoryPresenter) {
        this.chatHistoryPresenter = chatHistoryPresenter;

    }

    public void getChatDetailResponse(final Context context) {

        try {

            if (Connectivity.isConnected(context)) {
                chatHistoryPresenter.showProgressDialog(context.getResources().getString(R.string.loading), true, false);

                final APIRequest.Builder<ApiModel> builder = new APIRequest.Builder<>(context, Request.Method.GET,
                        ApiModel.class, URLConstants.BASE_URL,
                        new Response.Listener<ApiModel>() {
                            @Override
                            public void onResponse(ApiModel response) {
                                chatHistoryPresenter.hideProgressDialog();
                                if (response != null) {
                                    if (response.count>1) {
                                        if (response.messages != null) {
                                            if(response.messages.size()>0)
                                            {
                                                for (int i = 0; i < response.messages.size(); i++) {
                                                    MessageModel messageModel = response.messages.get(i);
                                                    String nameForType=response.messages.get(0).getUsername();
                                                    if(messageModel.getUsername().equalsIgnoreCase(nameForType))
                                                    {
                                                        messageModel.userme= Constants.HomeAdapterRowViewType.USER;
                                                    }
                                                    else
                                                    {
                                                        messageModel.userme=Constants.HomeAdapterRowViewType.OTHERS;
                                                    }
                                                }
                                                chatHistoryPresenter.setChatdata(context,response.messages, response.count);
                                            }

                                        } else {

                                           /* Log.i(TAG, "onResponse home: no data found");*/
                                        }
                                    }
                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        chatHistoryPresenter.hideProgressDialog();
                        if (error.networkResponse == null) {
                           // homeFragmentPresenterInterface.showUnableToReachServerErrorLayout();
                        }
                       // Log.d(TAG, "onErrorResponse() called with: " + "error = [" + error + "]");
                    }
                });
                APIRequest request = builder.build();
                VolleyUtil.getInstance(context).addToRequestQueue(request);
            }

        }catch (Exception e)
        {

        }
    }
}

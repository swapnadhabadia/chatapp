package com.app.haptikchat.chatdetail;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.haptikchat.R;
import com.app.haptikchat.model.ChatSummeryDbDetails;
import com.app.haptikchat.database.MessagesStore;
import com.app.haptikchat.model.FavTotalModel;
import com.app.haptikchat.model.MessagesDisplayModel;
import com.app.haptikchat.networking.APIRequest;
import com.app.haptikchat.networking.Connectivity;
import com.app.haptikchat.networking.VolleyUtil;
import com.app.haptikchat.model.ChatResponseModel;
import com.app.haptikchat.model.MessageModel;
import com.app.haptikchat.utils.Constants;
import com.app.haptikchat.utils.URLConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class InteractorChat {

    private final PresenterChat presenterChat;
    private MessagesStore messagesStore;
    private String TAG="ChatHistoryInteractor";

    public InteractorChat(Context context, PresenterChat presenterChat) {

        this.presenterChat = presenterChat;

        /*MessageDatabaseHandler messageDatabaseHandler = new MessageDatabaseHandler(context, null, null, 1);
        messageDatabaseHandler.dropTableIfExists();*/
        messagesStore=new MessagesStore(context);
    }

    public void getChatDetailResponse(final Context context) {

        try {


            if (Connectivity.isConnected(context)) {
                presenterChat.showProgressDialog(context.getResources().getString(R.string.loading), true, false);




                final APIRequest.Builder<ChatResponseModel> builder = new APIRequest.Builder<>(context, Request.Method.GET,
                        ChatResponseModel.class, URLConstants.BASE_URL,
                        new Response.Listener<ChatResponseModel>() {
                            @Override
                            public void onResponse(ChatResponseModel response) {
                                presenterChat.hideProgressDialog();
                                if (response != null) {
                                    if (response.count>1) {
                                        if (response.messages != null) {
                                            if(response.messages.size()>0)
                                            {
                                                ArrayList<MessagesDisplayModel> messagesDisplayModelArrayList=messagesStore.getAllUserChatData();
                                                if(messagesDisplayModelArrayList!=null&&messagesDisplayModelArrayList.size()>0)
                                                {
                                                    Log.i(TAG, "onResponse: ");
                                                }
                                                else
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

                                                        messagesStore.setAllNotificationData(messageModel.getUsername(),messageModel.getBody(), messageModel.getImageUrl(),messageModel.getName(),messageModel.getMessageTime(),messageModel.userme,0);

                                                    }

                                                    Log.i(TAG, "onResponse: for");
                                                }



                                                presenterChat.setChatData(context);

                                                List<ChatSummeryDbDetails> chatSummeryDbDetailsList =  messagesStore.getChatDetail();
                                                Log.i(TAG, "getChatDetailResponse: "+chatSummeryDbDetailsList.size());

                                                List<FavTotalModel> favTotalModelsList =  messagesStore.getFavDetail();
                                                Log.i(TAG, "getChatDetailResponse: "+favTotalModelsList.toString());

                                                presenterChat.setChatSummeryData(context,chatSummeryDbDetailsList,favTotalModelsList);
                                            }

                                        }
                                    }
                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        presenterChat.hideProgressDialog();
                        if (error.networkResponse == null) {
                           // homeFragmentPresenterInterface.showUnableToReachServerErrorLayout();
                        }

                    }
                });
                APIRequest request = builder.build();
                VolleyUtil.getInstance(context).addToRequestQueue(request);
            }

        }catch (Exception e)
        {
            Log.i(TAG, "getChatDetailResponse: "+e.getMessage());
        }
    }

    public void getChatDetailSummeryResponse(Context context) {
        List<ChatSummeryDbDetails> chatSummeryDbDetailsList =  messagesStore.getChatDetail();
        Log.i(TAG, "getChatDetailSummeryResponse: "+chatSummeryDbDetailsList.toString());

        List<FavTotalModel> favTotalModelsList =  messagesStore.getFavDetail();
        Log.i(TAG, "getChatDetailResponse: "+favTotalModelsList.toString());

        presenterChat.setChatSummeryData(context,chatSummeryDbDetailsList,favTotalModelsList);
    }
}

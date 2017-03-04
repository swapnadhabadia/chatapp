package com.app.haptikchat;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by swapna on 4/3/17.
 */
public class HomeFragmentInteractor {

    private final HomeFragmentPresenter homeFragmentPresenter;

    public HomeFragmentInteractor(HomeFragmentPresenter homeFragmentPresenter) {
        this.homeFragmentPresenter = homeFragmentPresenter;

    }

    public void getChatDetailResponse(final Context context) {

        try {

            if (Connectivity.isConnected(context)) {
                homeFragmentPresenter.showProgressDialog(context.getResources().getString(R.string.loading), true, false);

                final APIRequest.Builder<Example> builder = new APIRequest.Builder<>(context, Request.Method.GET,
                        Example.class, URLConstants.BASE_URL,
                        new Response.Listener<Example>() {
                            @Override
                            public void onResponse(Example response) {
                                homeFragmentPresenter.hideProgressDialog();
                                if (response != null) {
                                    if (response.count>1) {
                                        if (response.messages != null) {
                                            if(response.messages.size()>0)
                                            {
                                                for (int i = 0; i < response.messages.size(); i++) {
                                                    Message message = response.messages.get(i);
                                                    String nameForType=response.messages.get(0).getUsername();
                                                    /*Collections.sort(message, new Comparator<String>() {
                                                        DateFormat f = new SimpleDateFormat("dd/MM/yyyy 'T'hh:mm a");
                                                        @Override
                                                        public int compare(String o1, String o2) {
                                                            try {
                                                                return f.parse(o1).compareTo(f.parse(o2));
                                                            } catch (ParseException e) {
                                                                throw new IllegalArgumentException(e);
                                                            }
                                                        }
                                                    });*/
                                                  //  http://stackoverflow.com/questions/16809962/java-sorting-array-by-time
                                                    if(message.getUsername().equalsIgnoreCase(nameForType))
                                                    {
                                                        message.userme=Constants.HomeAdapterRowViewType.USER;
                                                    }
                                                    else
                                                    {
                                                        message.userme=Constants.HomeAdapterRowViewType.OTHERS;
                                                    }
                                                }
                                                homeFragmentPresenter.setChatdata(context,response.messages, response.count);
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
                        homeFragmentPresenter.hideProgressDialog();
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

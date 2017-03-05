package com.app.haptikchat.chatmain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.haptikchat.R;
import com.app.haptikchat.chatSummeryDbDetails;
import com.app.haptikchat.adapeter.RecylerViewTotalMessagesAdapter;
import com.app.haptikchat.database.MessageDatabaseHandler;
import com.app.haptikchat.model.MessagesDisplayModel;
import com.app.haptikchat.database.MessagesStore;
import com.app.haptikchat.model.FavouritePojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class ChatSummeryFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private String TAG="TwwoFragment";
    private RecylerViewTotalMessagesAdapter mRecylerViewTotalMessagesAdapter;
    private ArrayList<FavouritePojo> pojoArrayList;

    public static ChatSummeryFragment newInstance(String tab_id) {
        ChatSummeryFragment fragment = new ChatSummeryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_id", tab_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_tab_chat_recycler, container, false);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.chatRecyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        pojoArrayList = new ArrayList<FavouritePojo>();
        MessagesStore messagesStore=new MessagesStore(getActivity());
        ArrayList<MessagesDisplayModel> messagesDisplayModelArrayList=messagesStore.getAllUserChatData();
        Log.i(TAG, "onClick: "+messagesDisplayModelArrayList.toString());

        MessageDatabaseHandler messageDatabaseHandler = new MessageDatabaseHandler(getContext(), null, null, 2);

        List<chatSummeryDbDetails> chatSummeryDbDetailsList = messageDatabaseHandler.getOverviewOfApis();
        Log.i(TAG, "onClick: "+chatSummeryDbDetailsList.toString()+chatSummeryDbDetailsList.size());

        mRecylerViewTotalMessagesAdapter=new RecylerViewTotalMessagesAdapter(getActivity(),chatSummeryDbDetailsList);
        mRecyclerView.setAdapter(mRecylerViewTotalMessagesAdapter);
        return rootview;
    }

    /*@Override
    public void onHomeItemClick(String name, int position, boolean b) {
        *//*MessageDatabaseHandler payloadDataBaseHelper = new MessageDatabaseHandler(getContext(), null, null, 2);

        List<FavouriteOverviewDetails> apiOverviewDetailsList = payloadDataBaseHelper.getOverviewOfApis();*//*
        FavouritePojo favouritePojo = new FavouritePojo();
        favouritePojo.setUserName(name);
        favouritePojo.setPosition(position);
        favouritePojo.setFavourite(b);

        // Lets pass that POJO to our ArrayList which contains undergraduates as type
        pojoArrayList.add(favouritePojo);

    }*/
}

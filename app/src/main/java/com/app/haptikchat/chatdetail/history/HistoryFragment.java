package com.app.haptikchat.chatdetail.history;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.haptikchat.R;
import com.app.haptikchat.adapter.RecyclerViewHistoryAdapter;
import com.app.haptikchat.chatdetail.PresenterChat;
import com.app.haptikchat.chatdetail.summary.RefreshFragmentInterface;
import com.app.haptikchat.database.MessagesStore;
import com.app.haptikchat.model.MessagesDisplayModel;
import com.app.haptikchat.networking.Connectivity;

import java.util.ArrayList;

/**
 * Created by swapna on 4/3/17.
 */
public class HistoryFragment extends Fragment implements HistoryFragmentInterface,RefreshFragmentInterface {

     RecyclerView mRecyclerView;
    RecyclerViewHistoryAdapter mRecyclerViewHistoryAdapter;
    PresenterChat presenterChat;
    private LinearLayoutManager mLayoutManager;
    private String TAG="ChatHistoryFragment";
    private MessagesStore messagesStore;

    public static HistoryFragment newInstance(String tab_id) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_id", tab_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_chat_recycler, container, false);

        messagesStore=new MessagesStore(getActivity());

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.chatRecyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<MessagesDisplayModel> messagesDisplayModelArrayList=messagesStore.getAllUserChatData();
        if(messagesDisplayModelArrayList!=null&&messagesDisplayModelArrayList.size()>0)
        {
            mRecyclerViewHistoryAdapter =new RecyclerViewHistoryAdapter(getActivity());
            mRecyclerView.setAdapter(mRecyclerViewHistoryAdapter);
        }
        else
        {
            presenterChat = new PresenterChat(getActivity(),this);
            checkInternetConnectivityAndProceed();
        }

        return rootView;
    }
    private void checkInternetConnectivityAndProceed() {
        if(Connectivity.isConnected(getContext())) {
            presenterChat.getChatResponse(getActivity());
        }

    }

    @Override
    public void showProgressDialog(String message, boolean indeterminate, boolean isCancelable) {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setChatData(Context contexts) {

        mRecyclerViewHistoryAdapter =new RecyclerViewHistoryAdapter(getActivity());
        mRecyclerView.setAdapter(mRecyclerViewHistoryAdapter);
    }


    @Override
    public void refreshData() {

    }
}

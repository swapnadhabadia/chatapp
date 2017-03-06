package com.app.haptikchat.chatdetail.summary;

import android.content.Context;
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
import com.app.haptikchat.adapter.RecyclerViewMessagesAdapter;
import com.app.haptikchat.chatdetail.PresenterChat;
import com.app.haptikchat.model.ChatSummeryDbDetails;
import com.app.haptikchat.database.MessagesStore;
import com.app.haptikchat.model.FavTotalModel;
import com.app.haptikchat.model.MessagesDisplayModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class SummaryFragment extends Fragment  implements SummaryFragmentInterface,RefreshFragmentInterface {

    private RecyclerView mRecyclerView;
     LinearLayoutManager mLayoutManager;
    private String TAG = "SummeryFragment";
    private RecyclerViewMessagesAdapter mRecyclerViewMessagesAdapter;
     PresenterChat presenterChat;


    public static SummaryFragment newInstance(String tab_id) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_id", tab_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_chat_count_recycler, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.chatRecyclerviewcount);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);



        MessagesStore messagesStore = new MessagesStore(getActivity());
        ArrayList<MessagesDisplayModel> messagesDisplayModelArrayList = messagesStore.getAllUserChatData();
        if(messagesDisplayModelArrayList!=null&&messagesDisplayModelArrayList.size()>0)
        {
            List<ChatSummeryDbDetails> chatSummeryDbDetailsList =  messagesStore.getChatDetail();
            Log.i(TAG, "onClick: "+chatSummeryDbDetailsList.toString());

            List<FavTotalModel> favTotalModelsList =  messagesStore.getFavDetail();
            Log.i(TAG, "onClick: "+favTotalModelsList.toString());

            mRecyclerViewMessagesAdapter = new RecyclerViewMessagesAdapter(getContext(), chatSummeryDbDetailsList, favTotalModelsList);
            mRecyclerView.setAdapter(mRecyclerViewMessagesAdapter);
        }
        else
        {
            presenterChat = new PresenterChat(getActivity(),this);
            presenterChat.getChatResponse(getActivity());
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void setChatSummeryData(Context context, List<ChatSummeryDbDetails> chatSummeryDbDetailsList, List<FavTotalModel> favTotalModelsList) {
        Log.i(TAG, "setChatSummeryData: "+chatSummeryDbDetailsList.size()+favTotalModelsList);
        mRecyclerViewMessagesAdapter = new RecyclerViewMessagesAdapter(getContext(), chatSummeryDbDetailsList,favTotalModelsList);
        mRecyclerView.setAdapter(mRecyclerViewMessagesAdapter);
    }

    @Override
    public void refreshData() {
        Log.i(TAG, "refreshData: ");
        MessagesStore messagesStore = new MessagesStore(getActivity());
        ArrayList<MessagesDisplayModel> messagesDisplayModelArrayList = messagesStore.getAllUserChatData();
        if(messagesDisplayModelArrayList!=null&&messagesDisplayModelArrayList.size()>0)
        {
            List<ChatSummeryDbDetails> chatSummeryDbDetailsList =  messagesStore.getChatDetail();
            Log.i(TAG, "onClick: "+chatSummeryDbDetailsList.toString());

            List<FavTotalModel> favTotalModelsList =  messagesStore.getFavDetail();
            Log.i(TAG, "onClick: "+favTotalModelsList.toString());

            mRecyclerViewMessagesAdapter = new RecyclerViewMessagesAdapter(getContext(), chatSummeryDbDetailsList, favTotalModelsList);
            mRecyclerView.setAdapter(mRecyclerViewMessagesAdapter);
        }
        else
        {
            presenterChat = new PresenterChat(getActivity(),this);
            presenterChat.getChatResponse(getActivity());
        }
    }
    

}

package com.app.haptikchat.chatmain;

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
import com.app.haptikchat.adapeter.RecylerViewHomeAdapter;
import com.app.haptikchat.networking.Connectivity;
import com.app.haptikchat.model.MessageModel;

import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class ChatHistoryFragment extends Fragment implements ChatHistoryInterface {

     RecyclerView mRecyclerView;
    RecylerViewHomeAdapter mRecylerViewHomeAdapter;
    ChatHistoryPresenter chatHistoryPresenter;
    private LinearLayoutManager mLayoutManager;
    OnFragmentInteractionListener onItemClickListener;

    public static ChatHistoryFragment newInstance(String tab_id) {
        ChatHistoryFragment fragment = new ChatHistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_id", tab_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_tab_chat_recycler, container, false);
        chatHistoryPresenter = new ChatHistoryPresenter(getActivity(),this);
        checkInternetConnectivityAndProceed();
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.chatRecyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return rootview;
    }
    private void checkInternetConnectivityAndProceed() {
        if(Connectivity.isConnected(getContext())) {
            chatHistoryPresenter.getChatResponse(getActivity());
        }
        else {
            // mRelativeLayoutError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgressDialog(String message, boolean indeterminate, boolean isCancelable) {

    }

    @Override
    public void hideProgressDialog() {

    }

    public interface OnFragmentInteractionListener {
        void onHomeItemClick(String name, int position, boolean b);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onItemClickListener = (OnFragmentInteractionListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "interfaceNavigation");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setChatdata(Context context, List<MessageModel> messages, Integer count) {
     //   Toast.makeText(getActivity(),""+messages.toString(),Toast.LENGTH_SHORT).show();

        mRecylerViewHomeAdapter=new RecylerViewHomeAdapter(getActivity(),messages,count,onItemClickListener);
        mRecyclerView.setAdapter(mRecylerViewHomeAdapter);
    }



}

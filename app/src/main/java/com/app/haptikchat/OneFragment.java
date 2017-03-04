package com.app.haptikchat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class OneFragment extends Fragment implements HomeFragmentInterface {

     RecyclerView mRecyclerView;
    RecylerViewHomeAdapter mRecylerViewHomeAdapter;
    HomeFragmentPresenter homeFragmentPresenter;
    private LinearLayoutManager mLayoutManager;

    public static OneFragment newInstance(String tab_id) {
        OneFragment fragment = new OneFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_id", tab_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_tab_chat_recycler, container, false);
        homeFragmentPresenter = new HomeFragmentPresenter(getActivity(),this);
        checkInternetConnectivityAndProceed();
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerviewDynamicone);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return rootview;
    }
    private void checkInternetConnectivityAndProceed() {
        if(Connectivity.isConnected(getContext())) {
            homeFragmentPresenter.getChatResponse(getActivity());
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

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);


    }

    @Override
    public void setChatdata(Context context, List<Message> messages, Integer count) {
     //   Toast.makeText(getActivity(),""+messages.toString(),Toast.LENGTH_SHORT).show();

        mRecylerViewHomeAdapter=new RecylerViewHomeAdapter(getActivity(),messages,count);
        mRecyclerView.setAdapter(mRecylerViewHomeAdapter);
    }
}

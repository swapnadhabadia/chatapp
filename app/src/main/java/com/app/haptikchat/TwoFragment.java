package com.app.haptikchat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by swapna on 4/3/17.
 */
public class TwoFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    public static TwoFragment newInstance(String tab_id) {
        TwoFragment fragment = new TwoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab_id", tab_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_tab_chat_count_recycler, container, false);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.recyclerviewDynamic);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        return rootview;
    }
}

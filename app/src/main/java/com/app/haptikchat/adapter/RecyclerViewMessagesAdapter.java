package com.app.haptikchat.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.haptikchat.BaseApplication;
import com.app.haptikchat.model.ChatSummeryDbDetails;
import com.app.haptikchat.R;
import com.app.haptikchat.model.FavTotalModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by swapna on 5/3/17.
 */
public class RecyclerViewMessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private  List<FavTotalModel> favTotalModelsList;
    ImageLoader imageLoader;
    List<ChatSummeryDbDetails> notificationModelArrayList;
    Context context;

    public RecyclerViewMessagesAdapter(Context context, List<ChatSummeryDbDetails> notificationModelArrayList, List<FavTotalModel> favTotalModelsList) {
        this.context=context;
        this.favTotalModelsList=favTotalModelsList;
        this.notificationModelArrayList=notificationModelArrayList;
        imageLoader = BaseApplication.getImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater
                .inflate(R.layout.summary_fragment, viewGroup, false);

        return new RecyclerViewMessagesAdapter.ViewHolderTotal(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ChatSummeryDbDetails chatSummeryDbDetails=notificationModelArrayList.get(position);


        RecyclerViewMessagesAdapter.ViewHolderTotal viewHolderTotal = (RecyclerViewMessagesAdapter.ViewHolderTotal) holder;
        viewHolderTotal.txtName.setText(chatSummeryDbDetails.getUserName());
        viewHolderTotal.totalMessageTxt.setText(String.valueOf(chatSummeryDbDetails.getTotalMessages()));


            for (int i = 0; i < favTotalModelsList.size(); i++) {
                String favName = favTotalModelsList.get(i).getUserName();
                if(favName.equals(chatSummeryDbDetails.getUserName()))
                {
                    viewHolderTotal.favTxt.setText(String.valueOf(favTotalModelsList.get(i).getFavTotal()));
                }
            }


        imageLoader.displayImage(chatSummeryDbDetails.getImages(), viewHolderTotal.displayProfile);
        //viewHolderTotal.imageView.setImageResource();
    }

    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }


    public class ViewHolderTotal extends RecyclerView.ViewHolder {
         private AppCompatImageView displayProfile;
        private AppCompatTextView txtName;
        private AppCompatTextView totalMessageTxt;
        private AppCompatTextView favTxt;

        public ViewHolderTotal(View itemView) {
            super(itemView);
            displayProfile = (AppCompatImageView) itemView.findViewById(R.id.displayProfile);
            txtName = (AppCompatTextView) itemView.findViewById(R.id.txtName);
            totalMessageTxt = (AppCompatTextView) itemView.findViewById(R.id.totalMessagetxt);
            favTxt = (AppCompatTextView) itemView.findViewById(R.id.favTxt);

           // time = (AppCompatTextView) itemView.findViewById(R.id.time1);

        }
    }
}

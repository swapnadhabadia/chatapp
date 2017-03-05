package com.app.haptikchat.adapeter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.haptikchat.BaseApplication;
import com.app.haptikchat.R;
import com.app.haptikchat.chatSummeryDbDetails;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by swapna on 5/3/17.
 */
public class RecylerViewTotalMessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ImageLoader imageLoader;
    List<chatSummeryDbDetails> notificationModelArrayList;
    Context context;

    public RecylerViewTotalMessagesAdapter(Context context, List<chatSummeryDbDetails> notificationModelArrayList) {
        this.context=context;
        this.notificationModelArrayList=notificationModelArrayList;
        imageLoader = BaseApplication.getImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater
                .inflate(R.layout.chat_detail, viewGroup, false);

        return new RecylerViewTotalMessagesAdapter.ViewHolderTotal(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecylerViewTotalMessagesAdapter.ViewHolderTotal viewHolderTotal = (RecylerViewTotalMessagesAdapter.ViewHolderTotal) holder;
        viewHolderTotal.txtName.setText(notificationModelArrayList.get(position).getUserName());
        viewHolderTotal.totalMessagetxt.setText(String.valueOf(notificationModelArrayList.get(position).getTotalMessages()));
        imageLoader.displayImage(notificationModelArrayList.get(position).getImages(), viewHolderTotal.displayProfile);
        //viewHolderTotal.imageView.setImageResource();
    }

    @Override
    public int getItemCount() {
        return notificationModelArrayList.size();
    }


    public class ViewHolderTotal extends RecyclerView.ViewHolder {
         private AppCompatImageView displayProfile;
        private AppCompatTextView txtName;
        private AppCompatTextView totalMessagetxt;
        private AppCompatTextView time6;

        public ViewHolderTotal(View itemView) {
            super(itemView);
            displayProfile = (AppCompatImageView) itemView.findViewById(R.id.displayProfile);
            txtName = (AppCompatTextView) itemView.findViewById(R.id.txtName);
            totalMessagetxt = (AppCompatTextView) itemView.findViewById(R.id.totalMessagetxt);
            time6 = (AppCompatTextView) itemView.findViewById(R.id.favTxt);

           // time = (AppCompatTextView) itemView.findViewById(R.id.time1);

        }
    }
}

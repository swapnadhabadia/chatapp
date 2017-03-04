package com.app.haptikchat;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class RecylerViewHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


      Context context;
      List<Message> messages;
      Integer count;
      ImageLoader imageLoader;

    public RecylerViewHomeAdapter(Context context, List<Message> messages, Integer count) {
        this.context = context;
        this.messages = messages;
        this.count = count;
        imageLoader = BaseApplication.getImageLoader();
        Toast.makeText(context,""+messages.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case Constants.HomeAdapterRowViewType.USER:
                view = inflater
                        .inflate(R.layout.chat_me_background, viewGroup, false);

                return new ViewHolderMeUser(view);

            case Constants.HomeAdapterRowViewType.OTHERS:

                view = inflater
                        .inflate(R.layout.chat_background, viewGroup, false);
                return new ViewHolderOtherUser(view);
            default:
                view = inflater
                        .inflate(R.layout.chat_me_background, viewGroup, false);

                return new ViewHolderMeUser(view);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case Constants.HomeAdapterRowViewType.USER:
                final ViewHolderMeUser viewHolderMeUser = (ViewHolderMeUser) holder;
                viewHolderMeUser.mTextViewProductInfoPopUp.setText(messages.get(position).getName());
                viewHolderMeUser.mTextViewProductInfoPop.setText(messages.get(position).getBody());

               String date=dateformate(messages.get(position).getMessageTime());
                viewHolderMeUser.time.setText(date.toString());
                break;
            case Constants.HomeAdapterRowViewType.OTHERS:
                 ViewHolderOtherUser viewHolderOtherUser = (ViewHolderOtherUser) holder;
                viewHolderOtherUser.mTextViewProductInfoPopUp.setText(messages.get(position).getName());
                viewHolderOtherUser.mTextViewProductInfoPop.setText(messages.get(position).getBody());
                date=dateformate(messages.get(position).getMessageTime());
               viewHolderOtherUser.time.setText(date.toString());

                break;
            default:
                 viewHolderOtherUser = (ViewHolderOtherUser) holder;
                viewHolderOtherUser.mTextViewProductInfoPopUp.setText(messages.get(position).getName());
                viewHolderOtherUser.mTextViewProductInfoPop.setText(messages.get(position).getBody());
                date=dateformate(messages.get(position).getMessageTime());
              viewHolderOtherUser.time.setText(date.toString());
        }
    }

    private String dateformate(String messageTime) {

        Date futureDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = null;
        try {
            futureDate = dateFormat.parse(messageTime);
             formattedTime = output.format(futureDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedTime;
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).userme;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderMeUser extends RecyclerView.ViewHolder {
       // private AppCompatImageView mImageViewCategoryItem;
        private AppCompatTextView mTextViewProductInfoPopUp;
        private AppCompatTextView mTextViewProductInfoPop;
        private AppCompatTextView time;

        public ViewHolderMeUser(View itemView) {
            super(itemView);
          //  mImageViewCategoryItem = (AppCompatImageView) itemView.findViewById(R.id.mImageViewCategoryItem);
            mTextViewProductInfoPopUp = (AppCompatTextView) itemView.findViewById(R.id.mTextViewProductInfoPopUp1);
            mTextViewProductInfoPop = (AppCompatTextView) itemView.findViewById(R.id.mTextViewProductInfoPop1);
            time = (AppCompatTextView) itemView.findViewById(R.id.time1);

        }
    }

    public class ViewHolderOtherUser extends RecyclerView.ViewHolder {
        // private AppCompatImageView mImageViewCategoryItem;
        private AppCompatTextView mTextViewProductInfoPopUp;
        private AppCompatTextView mTextViewProductInfoPop;
        private AppCompatTextView time;

        public ViewHolderOtherUser(View itemView) {
            super(itemView);
            mTextViewProductInfoPopUp = (AppCompatTextView) itemView.findViewById(R.id.mTextViewProductInfoPopUp);
            mTextViewProductInfoPop = (AppCompatTextView) itemView.findViewById(R.id.mTextViewProductInfoPop);
            time = (AppCompatTextView) itemView.findViewById(R.id.time);
        }
    }


}

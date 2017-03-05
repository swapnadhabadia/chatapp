package com.app.haptikchat.adapeter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.haptikchat.BaseApplication;
import com.app.haptikchat.chatmain.ChatHistoryFragment;
import com.app.haptikchat.others.Constants;
import com.app.haptikchat.model.MessageModel;
import com.app.haptikchat.R;
import com.app.haptikchat.database.MessagesStore;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by swapna on 4/3/17.
 */
public class RecylerViewHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    ChatHistoryFragment.OnFragmentInteractionListener onItemClickListener;
    private MessagesStore messagesStore;
    Context context;
      List<MessageModel> messages;
      Integer count;
      ImageLoader imageLoader;

    public RecylerViewHomeAdapter(Context context, List<MessageModel> messages, Integer count, ChatHistoryFragment.OnFragmentInteractionListener onItemClickListener) {
        this.context = context;
        this.messages = messages;
        this.count = count;
        imageLoader = BaseApplication.getImageLoader();
        this.onItemClickListener=onItemClickListener;
        Toast.makeText(context,""+messages.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case Constants.HomeAdapterRowViewType.USER:
                view = inflater
                        .inflate(R.layout.my_chat_item_view, viewGroup, false);

                return new ViewHolderMeUser(view);

            case Constants.HomeAdapterRowViewType.OTHERS:

                view = inflater
                        .inflate(R.layout.user_chat_item_view, viewGroup, false);
                return new ViewHolderOtherUser(view);
            default:
                view = inflater
                        .inflate(R.layout.my_chat_item_view, viewGroup, false);

                return new ViewHolderMeUser(view);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        messagesStore=new MessagesStore(context);
        messagesStore.setAllNotificationData(messages.get(position).getUsername(),messages.get(position).getBody(), messages.get(position).getImageUrl(),messages.get(position).getName(),"false");

        switch (holder.getItemViewType()) {
            case Constants.HomeAdapterRowViewType.USER:
                final ViewHolderMeUser viewHolderMeUser = (ViewHolderMeUser) holder;
                viewHolderMeUser.mTxtMyName.setText(messages.get(position).getName());
                viewHolderMeUser.mTxtChatMsg.setText(messages.get(position).getBody());

               String date=dateformate(messages.get(position).getMessageTime());
                viewHolderMeUser.myTimeStamp.setText(date.toString());

                viewHolderMeUser.addToFavouriteImg.setTag(false);

                final ViewHolderMeUser finalViewHolderMeUser = viewHolderMeUser;
                viewHolderMeUser.addToFavouriteImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean tagOfImageView = (Boolean) finalViewHolderMeUser.addToFavouriteImg.getTag();

                        if(tagOfImageView)
                        {
                            finalViewHolderMeUser.addToFavouriteImg.setTag(false);
                            finalViewHolderMeUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_empty));
                            onItemClickListener.onHomeItemClick(messages.get(position).getUsername(),position,false);
                           // messagesStore.setAllNotificationData(messages.get(position).getUsername(),messages.get(position).getBody(), messages.get(position).getImageUrl(),messages.get(position).getName(),"0");

                        }
                        else
                        {
                            finalViewHolderMeUser.addToFavouriteImg.setTag(true);
                            finalViewHolderMeUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_fill));
                            onItemClickListener.onHomeItemClick(messages.get(position).getUsername(),position,true);
                           // messagesStore.setAllNotificationData(messages.get(position).getUsername(),messages.get(position).getBody(), messages.get(position).getImageUrl(),messages.get(position).getName(),"1");

                        }


                    }
                });

                break;
            case Constants.HomeAdapterRowViewType.OTHERS:
                 final ViewHolderOtherUser viewHolderOtherUser = (ViewHolderOtherUser) holder;
                viewHolderOtherUser.mTxtUserName.setText(messages.get(position).getName());
                viewHolderOtherUser.mTxtUserChatMsg.setText(messages.get(position).getBody());
                date=dateformate(messages.get(position).getMessageTime());
               viewHolderOtherUser.userTimeStamp.setText(date.toString());

                viewHolderOtherUser.addToFavouriteImg.setTag(false);

                viewHolderOtherUser.addToFavouriteImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean tagOfImageView = (Boolean)  viewHolderOtherUser.addToFavouriteImg.getTag();

                        if(tagOfImageView)
                        {
                            viewHolderOtherUser.addToFavouriteImg.setTag(false);
                            viewHolderOtherUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_empty));
                            onItemClickListener.onHomeItemClick(messages.get(position).getUsername(),position,false);
                       //     messagesStore.setAllNotificationData(messages.get(position).getUsername(),messages.get(position).getBody(), messages.get(position).getImageUrl(),messages.get(position).getName(),"0");

                        }
                        else
                        {
                            viewHolderOtherUser.addToFavouriteImg.setTag(true);
                            viewHolderOtherUser.addToFavouriteImg.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_fill));
                            onItemClickListener.onHomeItemClick(messages.get(position).getUsername(),position, true);
                         //   messagesStore.setAllNotificationData(messages.get(position).getUsername(),messages.get(position).getBody(), messages.get(position).getImageUrl(),messages.get(position).getName(),"1");

                        }
                    }
                });

                break;
            default:
                /*viewHolderMeUser = (ViewHolderMeUser) holder;
                viewHolderMeUser.mTextViewProductInfoPopUp.setText(messages.get(position).getName());
                viewHolderMeUser.mTextViewProductInfoPop.setText(messages.get(position).getBody());

                date=dateformate(messages.get(position).getMessageTime());
                viewHolderMeUser.time.setText(date.toString());

                viewHolderMeUser.mImageViewAddToWishlist.setTag(false);

                viewHolderMeUser.mImageViewAddToWishlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolderMeUser.mImageViewAddToWishlist.setTag(true);
                    }
                });*/
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
        private AppCompatImageView addToFavouriteImg;
        private AppCompatTextView mTxtMyName;
        private AppCompatTextView mTxtChatMsg;
        private AppCompatTextView myTimeStamp;

        public ViewHolderMeUser(View itemView) {
            super(itemView);
            addToFavouriteImg = (AppCompatImageView) itemView.findViewById(R.id.addToFavouriteImg);
            mTxtMyName = (AppCompatTextView) itemView.findViewById(R.id.mTxtMyName);
            mTxtChatMsg = (AppCompatTextView) itemView.findViewById(R.id.mTxtChatMsg);
            myTimeStamp = (AppCompatTextView) itemView.findViewById(R.id.myTimeStamp);

        }
    }

    public class ViewHolderOtherUser extends RecyclerView.ViewHolder {
        // private AppCompatImageView mImageViewCategoryItem;
        private AppCompatTextView mTxtUserName;
        private AppCompatTextView mTxtUserChatMsg;
        private AppCompatTextView userTimeStamp;
        private AppCompatImageView addToFavouriteImg;
        public ViewHolderOtherUser(View itemView) {
            super(itemView);
            addToFavouriteImg = (AppCompatImageView) itemView.findViewById(R.id.addToFavouriteImg);
            mTxtUserName = (AppCompatTextView) itemView.findViewById(R.id.mTxtUserName);
            mTxtUserChatMsg = (AppCompatTextView) itemView.findViewById(R.id.mTxtUserChatMsg);
            userTimeStamp = (AppCompatTextView) itemView.findViewById(R.id.name);
        }
    }


}

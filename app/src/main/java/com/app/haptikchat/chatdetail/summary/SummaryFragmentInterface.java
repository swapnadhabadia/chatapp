package com.app.haptikchat.chatdetail.summary;

import android.content.Context;

import com.app.haptikchat.model.ChatSummeryDbDetails;
import com.app.haptikchat.model.FavTotalModel;

import java.util.List;

/**
 * Created by swapna on 6/3/17.
 */
public interface SummaryFragmentInterface {
    void setChatSummeryData(Context context, List<ChatSummeryDbDetails> chatSummeryDbDetailsList, List<FavTotalModel> favTotalModelsList);
}

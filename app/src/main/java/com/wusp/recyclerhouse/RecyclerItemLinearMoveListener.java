package com.wusp.recyclerhouse;

import android.support.v7.widget.RecyclerView;

/**
 * Created by wusp on 16/5/24.
 */
public interface RecyclerItemLinearMoveListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction);
    boolean onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target);
}
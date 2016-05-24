package com.wusp.recyclerhouse;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by wusp on 16/5/24.
 */
public class ItemLinearMoveCallback extends ItemTouchHelper.Callback {
    private RecyclerItemLinearMoveListener listener;
    private int layoutDirection;

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlags, dragFlags;
        if (layoutDirection == LinearLayoutManager.HORIZONTAL) {
            swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            dragFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }else{
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (listener != null){
            return listener.onMoved(recyclerView, viewHolder, target);
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener != null){
            listener.onSwiped(viewHolder, direction);
        }
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    public void setOnMoveListener(RecyclerItemLinearMoveListener listener){
        if (listener != null){
            this.listener = listener;
        }
    }

    public void setLayoutDirection(int direction){
        layoutDirection = direction;
    }
}

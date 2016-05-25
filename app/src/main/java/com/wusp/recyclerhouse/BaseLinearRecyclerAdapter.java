package com.wusp.recyclerhouse;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by wusp on 16/5/24.
 */
public class BaseLinearRecyclerAdapter extends RecyclerView.Adapter<BaseLinearRecyclerAdapter.CardViewHolder> implements RecyclerItemLinearMoveListener {
    private static final int VIEW_TYPE_ONE = 1;
    private static final int VIEW_TYPE_TWO = 2;

    private List<String> mDataList;
    private boolean canSwipeOut = false;
    private boolean canSwaped = false;
    private ItemLinearMoveCallback itemSwipeCallback;

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView;
        switch(viewType){
            case 1:
                contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_one, parent, false);
                break;
            default:
                contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_one, parent, false);
                break;
        }
        return new CardViewHolder(contentView);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.setName(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List getmDataList() {
        return mDataList;
    }

    public void setmDataList(List mDataList) {
        this.mDataList = mDataList;
        notifyDataSetChanged();
    }

    /**
     * To know whether this recycler can be swiped out.
     * @return true if can be swiped out.
     */
    public boolean canSwipeOut(){
        return canSwipeOut;
    }

    /**
     * To set whether this recycler can be swiped out.
     * @param canSwipeOut true if can be swiped out.
     * @param direction direction that the item view is layout.
     */
    public void enableSwipeOut(boolean canSwipeOut, int direction){
        this.canSwipeOut = canSwipeOut;
        if (this.canSwipeOut && itemSwipeCallback == null) {
            itemSwipeCallback = new ItemLinearMoveCallback();
            itemSwipeCallback.setLayoutDirection(direction);
            itemSwipeCallback.setOnMoveListener(this);
        }
        itemSwipeCallback.setCanSwiped(canSwipeOut);
    }

    /**
     * To know whether this recycler data can be swaped.
     * @return
     */
    public boolean canDataSwaped(){
        return canSwaped;
    }

    /**
     * To set whether this recycler data can be swaped.
     * @param canSwaped
     */
    public void enableDataSwap(boolean canSwaped){
        this.canSwaped = canSwaped;
        if (this.canSwaped && itemSwipeCallback == null){
            itemSwipeCallback = new ItemLinearMoveCallback();
            itemSwipeCallback.setOnMoveListener(this);
        }
        itemSwipeCallback.setCanMoved(canSwaped);
    }

    /**
     * ItemTouchHelper needs a callback to work.
     * @return ItemMoveCallback.
     */
    public ItemLinearMoveCallback getItemMoveCallback(){
        if (itemSwipeCallback == null) {
            itemSwipeCallback = new ItemLinearMoveCallback();
            itemSwipeCallback.setOnMoveListener(this);
        }

        return itemSwipeCallback;
    }

    /**
     * Only when this adapter has set to listen to the movement would invoke this.
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //Only when the swipe out is enabled.
        if (canSwipeOut) {
            int pos = viewHolder.getAdapterPosition();
            mDataList.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    /**
     * Only when this adapter has set to listen to the movement would invoke this.
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return true if has been moved.
     */
    @Override
    public boolean onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //Only when the move is enabled.
        if (canSwaped){
            int fromPos = viewHolder.getAdapterPosition();
            int toPos = target.getAdapterPosition();
            if (fromPos < toPos){
                for (int i = fromPos; i < toPos; i++){
                    Collections.swap(mDataList, i, i + 1);
                }
            }else{
                for (int i = fromPos; i > toPos; i--){
                    Collections.swap(mDataList, i, i - 1);
                }
            }
            notifyItemMoved(fromPos, toPos);
            return true;
        }
        return false;
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public CardView card;

        public CardViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            card = (CardView) itemView.findViewById(R.id.card);
        }

        public void setName(String name) {
            if (tvName != null) {
                tvName.setText(name);
            }
        }
    }
}

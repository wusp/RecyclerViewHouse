# RecyclerViewHouse
This project shows some RecyclerView usages. <bf />
Only contains linear effect practice now.

## BaseLinearRecyclerAdapter
1. Use swipe-out function to remove item
```
baseRecyclerAdapter.enableSwipeOut(true, LinearLayoutManager.VERTICAL);
```
2. Use move-swap function to swap item
```
baseRecyclerAdapter.enableDataSwap(true);
```
3. Set a RecyclerView OnclickListener
```
itemClickListener = new RecyclerItemClickListener(this);
itemClickListener.setOnItemClickListener(RecyclerItemClickListener.OnItemClickListener);
recyclerView.addOnItemTouchListener(itemClickListener);
```

![](https://github.com/wusp/RecyclerViewHouse/blob/master/app/screenshots/linearadapter.gif)




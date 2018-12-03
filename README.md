# GridDividerMoreTypeItemDecoration
this is for there are more than two type of Item  gridLayoutManager decoration

这个Decoration区别与本人仓库中的GridDividerItemDecoration, 那个是主要用于只有一种ItemType Image或者其他view的情况

而这个GridDividerMoreTypeItemDecoartion
先上个图片

![图一](https://github.com/haozi5460/GridDividerMoreTypeItemDecoration/blob/master/WX20181203-174630.png)
![图二](https://github.com/haozi5460/GridDividerMoreTypeItemDecoration/blob/master/WX20181203-174644.png)


如果一般第一眼看到这个功能我们首先想到的是RecyclerView嵌套recyclerView了。 这种和符合大众思维，也好做。

而这里我们有了GridDivideMoreTypeItemDecoration, 则只要一个Recyclerview就可以解决问题，我们不需要再嵌套recyclerView了。

主要适用于每个子项既有HeaderView ，有有其他类似Image等的子Item情形。

使用
1. 设置GridLayoutManager的setSpanSizeLookup
```
final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
    @Override
    public int getSpanSize(int position) {
        int itemViewType = subAdapter.getItemViewType(position); //Adapter中你需要区分ViewType
        if(itemViewType == Constants.PENDING_UPLOAD_SUB_VIEW_TYPE_HEADER)
            return gridLayoutManager.getSpanCount();
        else
            return 1;
    }
});
```
2. 给RecyclerView设置Decoration
```
recyclerView.addItemDecoration(new GridDividerMoreTypeItemDecoration(this,Utils.dp2px(this,20)
                ,Utils.dp2px(this,15),true));
```

package com.android.haozi.wanandroid.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.starlight.mobile.android.smsone.adapter.PendingUploadSubWaitAdapter;
import com.starlight.mobile.android.smsone.common.BaseActivity;
import com.starlight.mobile.android.smsone.common.Constants;
import com.starlight.mobile.android.smsone.common.GridDividerMoreTypeItemDecoration;
import com.starlight.mobile.android.smsone.greendao.entity.AttachmentUploadEntity;
import com.starlight.mobile.android.smsone.greendao.entity.WOStoreEntity;
import com.starlight.mobile.android.smsone.util.OffLineUtils;
import com.starlight.mobile.android.smsone.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PendingUploadItemWaitActivity extends BaseActivity {
    @BindView(R.id.pending_upload_sub_layout_title)
    TextView pendingUploadSubLayoutTitle;
    @BindView(R.id.pending_upload_sub_layout_toolbar)
    Toolbar pendingUploadSubLayoutToolbar;
    @BindView(R.id.pending_upload_sub_upload_btn)
    Button pendingUploadSubUploadBtn;
    @BindView(R.id.pending_upload_sub_uploading)
    TextView pendingUploadSubUploading;
    @BindView(R.id.pending_upload_sub_bottom_layout)
    FrameLayout pendingUploadSubBottomLayout;
    @BindView(R.id.pending_upload_sub_layout_recyclerview)
    RecyclerView pendingUploadSubRecyclerView;

    private WOStoreEntity mWOStoreEntity = null;
    private PendingUploadSubWaitAdapter subAdapter = null;
    private List<AttachmentUploadEntity> attachmentUploadEntityList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.pending_upload_sub_layout;
    }

    @Override
    public void initData() {
        if(getIntent() != null && getIntent().hasExtra(Constants.CURRENT_OFFLINE_WOSTORE_ENTITY)){
            mWOStoreEntity = (WOStoreEntity) getIntent().getSerializableExtra(Constants.CURRENT_OFFLINE_WOSTORE_ENTITY);
        }

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = subAdapter.getItemViewType(position);
                if(itemViewType == Constants.PENDING_UPLOAD_SUB_VIEW_TYPE_HEADER)
                    return gridLayoutManager.getSpanCount();
                else
                    return 1;
            }
        });
        pendingUploadSubRecyclerView.setLayoutManager(gridLayoutManager);
        subAdapter = new PendingUploadSubWaitAdapter(attachmentUploadEntityList,this);
        pendingUploadSubRecyclerView.addItemDecoration(new GridDividerMoreTypeItemDecoration(this,Utils.dp2px(this,20)
                ,Utils.dp2px(this,15),true));
        pendingUploadSubRecyclerView.setAdapter(subAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapterData();
    }

    private void setAdapterData() {
        if(subAdapter != null && mWOStoreEntity != null){
            attachmentUploadEntityList = OffLineUtils.getSubAdapterDataList(mWOStoreEntity);
            subAdapter.setListData(attachmentUploadEntityList);
        }
    }

    @OnClick(R.id.pending_upload_sub_upload_btn)
    public void onViewClicked() {
    }
}


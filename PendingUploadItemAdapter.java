package com.android.haozi.wanandroid.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.starlight.mobile.android.smsone.R;
import com.starlight.mobile.android.smsone.common.BaseAdapter;
import com.starlight.mobile.android.smsone.common.BaseViewHolder;
import com.starlight.mobile.android.smsone.common.Constants;
import com.starlight.mobile.android.smsone.greendao.entity.AttachmentUploadEntity;
import com.starlight.mobile.android.smsone.util.OffLineUtils;

import java.util.List;

import butterknife.BindView;

public class PendingUploadSubWaitAdapter extends BaseAdapter<AttachmentUploadEntity> {

    public PendingUploadSubWaitAdapter(List<AttachmentUploadEntity> listData, Context context) {
        super(listData, context);
    }

    @Override
    public BaseViewHolder<AttachmentUploadEntity> getViewHolder(View v) {
        return new PendingUploadSubViewHolder(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.pending_upload_sub_item_wait_layout;
    }

    @Override
    public int getItemViewType(int position) {
        if(listData != null)
            return listData.get(position).getViewType();
        return super.getItemViewType(position);
    }

    public class PendingUploadSubViewHolder extends BaseViewHolder<AttachmentUploadEntity> {
        @BindView(R.id.pending_upload_sub_item_attachment_type)
        TextView pendingUploadSubitemAttachmentType;
        @BindView(R.id.pending_upload_sub_item_attachment_time)
        TextView pendingUploadSubitemAttachmentTime;
        @BindView(R.id.pending_upload_sub_item_img)
        SimpleDraweeView pendingUploadSubitemImg;
        @BindView(R.id.pending_upload_sub_item_text_layout)
        RelativeLayout pendingTextLayout;
        @BindView(R.id.pending_upload_sub_item_bottom_line)
        View topLine;

        public PendingUploadSubViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setItemBindData(AttachmentUploadEntity data) {
            switch (data.getViewType()){
                case Constants.PENDING_UPLOAD_SUB_VIEW_TYPE_HEADER:
                    pendingTextLayout.setVisibility(View.VISIBLE);
                    topLine.setVisibility(this.getAdapterPosition() == 0 ? View.GONE:View.VISIBLE);
                    pendingUploadSubitemImg.setVisibility(View.GONE);
                    pendingUploadSubitemAttachmentType.setText(data.getTypeHeadNameId());
                    break;
                case Constants.PENDING_UPLOAD_SUB_VIEW_TYPE_IMG:
                    pendingTextLayout.setVisibility(View.GONE);
                    pendingUploadSubitemImg.setVisibility(View.VISIBLE);
                    pendingUploadSubitemImg.setImageURI(OffLineUtils.getFileUri(data.getPhotoLocalPath()));
                    break;
            }

        }
    }
}


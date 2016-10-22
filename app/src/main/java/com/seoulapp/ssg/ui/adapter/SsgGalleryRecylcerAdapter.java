package com.seoulapp.ssg.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seoulapp.ssg.R;
import com.seoulapp.ssg.model.Ssg;
import com.seoulapp.ssg.ui.adapter.basic.BasicRecyclerAdapter;
import com.seoulapp.ssg.ui.adapter.viewholder.BasicViewHolder;

/**
 * Created by Dongyoon on 2016. 10. 19..
 */

public class SsgGalleryRecylcerAdapter extends BasicRecyclerAdapter {

    public SsgGalleryRecylcerAdapter(Context context) {
        super(context);
    }

    @Override
    public BasicViewHolder getViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = getLayoutInflater();

        return new GalleryViewHolder(inflater.inflate(R.layout.item_ssg_gallery, parent, false));
    }

    private class GalleryViewHolder extends BasicViewHolder<Ssg> {
        ImageView ivSsgPicture;
        TextView tvSpotDetail, tvSsgDate, tvSsgComment;
        Button btnRemoveSsg;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            ivSsgPicture = (ImageView) itemView.findViewById(R.id.iv_picture);
            tvSpotDetail = (TextView) itemView.findViewById(R.id.tv_spot_detail);
            tvSsgDate = (TextView) itemView.findViewById(R.id.tv_ssg_date);
            tvSsgComment = (TextView) itemView.findViewById(R.id.tv_ssg_comment);
            btnRemoveSsg = (Button) itemView.findViewById(R.id.btn_remove_ssg);
        }

        @Override
        public void onBindView(Ssg ssg) {
            tvSpotDetail.setText(ssg.spotDetail);
            tvSsgDate.setText(ssg.date);
            tvSsgComment.setText(ssg.comment);
            btnRemoveSsg.setText("지워주세요\n" + ssg.declareCount);
            Glide
                    .with(getContext())
                    .load(ssg.picture)
                    .fitCenter()
                    .into(ivSsgPicture);
        }
    }
}

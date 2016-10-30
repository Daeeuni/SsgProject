package com.seoulapp.ssg.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.seoulapp.ssg.R;

import java.util.List;

import com.seoulapp.ssg.model.Ssg;
import com.seoulapp.ssg.ui.activity.SsgGalleryActivity;
import com.seoulapp.ssg.ui.adapter.basic.BasicRecyclerAdapter;
import com.seoulapp.ssg.ui.adapter.viewholder.BasicViewHolder;
import com.seoulapp.ssg.widget.SquareImageView;


public class SsgHistoryRecyclerAdapter extends BasicRecyclerAdapter {

    public SsgHistoryRecyclerAdapter(Context context) {
        super(context);
    }

    public SsgHistoryRecyclerAdapter(Context context, List items) {
        super(context, items);
    }

    @Override
    public BasicViewHolder getViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = getLayoutInflater();
        return new ViewHolder(inflater.inflate(R.layout.item_ssg_history, parent, false));
    }

    public class ViewHolder extends BasicViewHolder<Ssg> implements View.OnClickListener {
        SquareImageView cv_ssghistory_image;


        public ViewHolder(View itemView) {
            super(itemView);
            cv_ssghistory_image = (SquareImageView) itemView.findViewById(R.id.cv_ssghistory_image);
            cv_ssghistory_image.setOnClickListener(this);
        }

        @Override
        public void onBindView(Ssg ssg) {
            Glide.with(getContext())
                    .load(ssg.picture)
                    .centerCrop()
                    .into(cv_ssghistory_image);
        }

        @Override
        public void onClick(View v) {
            Log.d("SsgHistoryAdapter", "onClick: " + getAdapterPosition() );


            if (v.getId() == R.id.cv_ssghistory_image) {
                Intent intent = new Intent(getContext(), SsgGalleryActivity.class);
                Ssg ssg = (Ssg) getItem(getAdapterPosition());
                intent.putExtra("ssg", ssg);
                getContext().startActivity(intent);
            }

        }
    }
}

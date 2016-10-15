package com.seoulapp.ssg.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import com.bumptech.glide.Glide;
import com.seoulapp.ssg.R;
import com.seoulapp.ssg.model.Volunteer;
import com.seoulapp.ssg.ui.adapter.basic.BasicRecyclerAdapter;
import com.seoulapp.ssg.ui.adapter.viewholder.BasicViewHolder;


import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Dongyoon on 2016. 10. 3..
 */

public class VolunteerRecyclerAdapter extends BasicRecyclerAdapter<Volunteer> {

    public VolunteerRecyclerAdapter(Context context) {
        super(context);
    }

    public VolunteerRecyclerAdapter(Context context, List<Volunteer> items) {
        super(context, items);
    }

    @Override
    public BasicViewHolder getViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = getLayoutInflater();

        return new VolunteerViewHolder(inflater.inflate(R.layout.item_volunteer_main, parent, false));
    }

    private class VolunteerViewHolder extends BasicViewHolder<Volunteer> {
        TextView tvVolunteerName;
        ImageView ivVolunteerSpot;

        public VolunteerViewHolder(View itemView) {
            super(itemView);
            tvVolunteerName = (TextView) itemView.findViewById(R.id.tv_volunteer_name);
            ivVolunteerSpot = (ImageView) itemView.findViewById(R.id.iv_volunteer_spot);
        }

        @Override
        public void onBindView(Volunteer volunteer) {
            tvVolunteerName.setText(volunteer.getSpot());
            Glide
                    .with(getContext())
                    .load(volunteer.getThumbnail())
                    .centerCrop()
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .into(ivVolunteerSpot);
        }
    }
}

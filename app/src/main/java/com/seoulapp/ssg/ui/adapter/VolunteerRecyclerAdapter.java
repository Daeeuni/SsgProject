package com.seoulapp.ssg.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seoulapp.ssg.R;
import com.seoulapp.ssg.model.Volunteer;
import com.seoulapp.ssg.ui.adapter.basic.BasicRecyclerAdapter;
import com.seoulapp.ssg.ui.adapter.viewholder.BasicViewHolder;

import java.util.List;

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

        return new VollunteerViewHolder(inflater.inflate(R.layout.item_vollunteer_main, parent, false));
    }

    private class VollunteerViewHolder extends BasicViewHolder<Volunteer> {
        TextView tvVollunterName;

        public VollunteerViewHolder(View itemView) {
            super(itemView);
            tvVollunterName = (TextView) itemView.findViewById(R.id.tv_vollunteer_name);
        }

        @Override
        public void onBindView(Volunteer vollunteer) {
            tvVollunterName.setText(vollunteer.title);

        }
    }
}

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

/**
 * Created by Dongyoon on 2016. 10. 27..
 */
public class SsacHistoryRecyclerAdapter extends BasicRecyclerAdapter {
    public SsacHistoryRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public BasicViewHolder getViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = getLayoutInflater();

        return new SsacHistoryViewHolder(inflater.inflate(R.layout.item_ssac_list, parent, false));
    }

    private class SsacHistoryViewHolder extends BasicViewHolder<Volunteer> {
        private TextView tvDate, tvLocation, tvTime;

        public SsacHistoryViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);

        }

        @Override
        public void onBindView(Volunteer ssac) {
            tvLocation.setText(ssac.getMeeting_location());
            tvDate.setText(ssac.getSchedule());
            tvTime.setText(ssac.getTime());

        }
    }

}

package com.seoulapp.ssg.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seoulapp.ssg.R;
import com.seoulapp.ssg.api.SsgApiService;
import com.seoulapp.ssg.model.Ssg;
import com.seoulapp.ssg.network.ServiceGenerator;
import com.seoulapp.ssg.ui.adapter.basic.BasicRecyclerAdapter;
import com.seoulapp.ssg.ui.adapter.viewholder.BasicViewHolder;
import com.seoulapp.ssg.ui.dialog.SsgReportDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dongyoon on 2016. 10. 19..
 */

public class SsgGalleryRecyclerAdapter extends BasicRecyclerAdapter {

    public interface OnSsgItemClick {

        void onEraseClick(int position);
    }

    private OnSsgItemClick mCallback;

    public SsgGalleryRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public BasicViewHolder getViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = getLayoutInflater();

        return new GalleryViewHolder(inflater.inflate(R.layout.item_ssg_gallery, parent, false));
    }

    private class GalleryViewHolder extends BasicViewHolder<Ssg> implements View.OnClickListener, SsgReportDialog.OnOkButtonClick {
        ImageView ivSsgPicture;
        TextView tvSpotDetail, tvSsgDate, tvSsgComment, btnReportSsg;
        Button btnEraseSsg;
        boolean isReported;

        public GalleryViewHolder(View itemView) {
            super(itemView);
            ivSsgPicture = (ImageView) itemView.findViewById(R.id.iv_picture);
            tvSpotDetail = (TextView) itemView.findViewById(R.id.tv_spot_detail);
            tvSsgDate = (TextView) itemView.findViewById(R.id.tv_ssg_date);
            tvSsgComment = (TextView) itemView.findViewById(R.id.tv_ssg_comment);
            btnEraseSsg = (Button) itemView.findViewById(R.id.btn_remove_ssg);
            btnReportSsg = (TextView) itemView.findViewById(R.id.btn_report_ssg);
            btnEraseSsg.setOnClickListener(this);
            btnReportSsg.setOnClickListener(this);

        }

        @Override
        public void onBindView(Ssg ssg) {
            tvSpotDetail.setText(ssg.spotDetail);
            tvSsgDate.setText(ssg.date);
            tvSsgComment.setText(ssg.comment);
            btnEraseSsg.setText("지워주세요!\n" + ssg.likeCount);

            Glide
                    .with(getContext())
                    .load(ssg.picture)
                    .fitCenter()
                    .into(ivSsgPicture);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();
            switch (id) {
                case R.id.btn_report_ssg:
                    if (id == R.id.btn_remove_ssg) {
                        if (mCallback != null) {
                            mCallback.onEraseClick(position);
                        }
                    } else if (id == R.id.btn_report_ssg) {
                        showReportDialog(isReported);
                    }
                    break;
                case R.id.btn_remove_ssg:
                    requestRemoveSsg((Ssg)getItem(position));
                    break;

            }
        }

        private void requestRemoveSsg(Ssg item) {
            SsgApiService service = ServiceGenerator.getInstance().createService(SsgApiService.class);
            Call<Ssg> call = service.requestSsgRemove(item.ssgId, 1);
            call.enqueue(new Callback<Ssg>() {
                @Override
                public void onResponse(Call<Ssg> call, Response<Ssg> response) {
                    if(response.isSuccessful()){
                        setBtnRemove(response.body().wantRemove());
                    }
                }

                @Override
                public void onFailure(Call<Ssg> call, Throwable t) {

                }
            });
        }

        private void setBtnRemove(boolean b) {
            if(b){
                btnEraseSsg.setBackgroundColor(getContext().getResources().getColor(R.color.ssg_erase_btn_clicked));
            } else {
                btnEraseSsg.setBackgroundColor(getContext().getResources().getColor(R.color.ssg_erase_btn_non_clicked));
            }
        }

        private void showReportDialog(boolean isReported) {
            SsgReportDialog dialog = new SsgReportDialog();
            dialog.setOnOkButtonClickListener(this);
            Bundle bundle = new Bundle();
            bundle.putBoolean("isReported", isReported);
            dialog.setArguments(bundle);
            if (getContext() instanceof FragmentActivity) {
                dialog.show(((FragmentActivity) getContext()).getSupportFragmentManager(), null);
            }
        }

        @Override
        public void onOkClick() {
            if (!isReported) {
                isReported = true;
                btnReportSsg.setTextColor(getContext().getResources().getColor(R.color.btnChanged));
                btnReportSsg.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_church_bell_red), null, null, null);
            } else {
                isReported = false;
                btnReportSsg.setTextColor(getContext().getResources().getColor(android.R.color.black));
                btnReportSsg.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.ic_church_bell_black), null, null, null);

            }
        }
    }

}

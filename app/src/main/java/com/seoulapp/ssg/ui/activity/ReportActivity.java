package com.seoulapp.ssg.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.seoulapp.ssg.R;
import com.seoulapp.ssg.ui.dialog.UploadPictureDialog;

import java.io.File;

public class ReportActivity extends AppCompatActivity implements UploadPictureDialog.OnChoiceClickListener {
    private UploadPictureDialog mDialog;
    private static final int REQUEST_CODE_GALLERY = 0;
    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_CROP = 2;

    private Uri mImageCaptureUri;

    private ImageView ivUploadPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ivUploadPicture = (ImageView) findViewById(R.id.iv_upload_picture);
        ivUploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = new UploadPictureDialog();
                mDialog.setOnButtonClickListener(ReportActivity.this);
                mDialog.show(getSupportFragmentManager(), null);

            }
        });
    }

    @Override
    public void onCameraClick() {

    }

    @Override
    public void onGalleryClick() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
//        intent.putExtra("outputFormat",
//                Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", true);

        startActivityForResult(intent, REQUEST_CODE_GALLERY);

    }

    protected Uri getTempUri() {
        // 임시로 사용할 파일의 경로 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis() + ".jpeg");
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
        return mImageCaptureUri;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {
            case REQUEST_CODE_GALLERY: {

                final Bundle extras = data.getExtras();

                if (extras != null) {
                    String imagePath = mImageCaptureUri.getPath();

                    Glide.with(this)
                            .load(imagePath)
                            .into(ivUploadPicture);

                }

                break;
            }
            case REQUEST_CODE_CAMERA: {
//                String imagePath = mSavedFile.getAbsolutePath();
//                try {
//                    String url = MediaStore.Images.Media.insertImage(getActivity()
//                                    .getContentResolver(), imagePath, "camera image",
//                            "original image");
//                    Uri photouri = Uri.parse(url);
//                    ContentValues values = new ContentValues();
//                    values.put(MediaStore.Images.Media.ORIENTATION, 90);
//                    getActivity().getContentResolver().update(photouri, values,
//                            null, null);
//                    cropImage(photouri);
//                } catch (FileNotFoundException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
                break;
            }
            case REQUEST_CODE_CROP: {

//                String filePath = mSavedFile.getAbsolutePath();

//                modifyProfileImage(filePath);

                break;
            }

        }

    }
}

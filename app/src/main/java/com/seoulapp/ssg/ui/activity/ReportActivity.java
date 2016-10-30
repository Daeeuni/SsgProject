package com.seoulapp.ssg.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.seoulapp.ssg.R;
import com.seoulapp.ssg.ui.dialog.UploadPictureDialog;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.io.File;

public class ReportActivity extends BaseActivity implements UploadPictureDialog.OnChoiceClickListener,
        View.OnClickListener, MapView.MapViewEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {
    private static final String TAG = ReportActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    private UploadPictureDialog mDialog;
    private static final int REQUEST_CODE_GALLERY = 0;
    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_CROP = 2;

    private LocationManager lm;

    private Uri mImageCaptureUri;

    private ImageView ivUploadPicture;
    private MapView mMapView;
    private MapPOIItem mMapPOIItem;
    private TextView tvMapDescription;
    private ScrollView mRootScrollView;
    private EditText editLocationDetail, editComment;
    private MapReverseGeoCoder mReverseGeoCoder = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            setTitle("");
        }

        ivUploadPicture = (ImageView) findViewById(R.id.iv_upload_picture);
        ivUploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog = new UploadPictureDialog();
                mDialog.setOnChoiceClickListener(ReportActivity.this);
                mDialog.show(getSupportFragmentManager(), null);

            }
        });

        mMapView = new MapView(this);
        mMapView.setDaumMapApiKey(getResources().getString(R.string.daum_map_api_key));
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mRootScrollView = (ScrollView) findViewById(R.id.scroll_view);
        tvMapDescription = (TextView) findViewById(R.id.tv_map_description);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mMapView);
        mMapView.setMapViewEventListener(this);
        mMapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mRootScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        Button btnSubmit = (Button) findViewById(R.id.btn_submit);
        Button btnCancel = (Button) findViewById(R.id.btn_cancel);

        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                mReverseGeoCoder = new MapReverseGeoCoder(getString(R.string.daum_map_api_key), mMapView.getMapCenterPoint(), ReportActivity.this, ReportActivity.this);
                mReverseGeoCoder.startFindingAddress();
                break;

            case R.id.btn_cancel:
                finish();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.
            double longitude = location.getLongitude(); //경도
            double latitude = location.getLatitude();   //위도
            double altitude = location.getAltitude();   //고도
            float accuracy = location.getAccuracy();    //정확도
            String provider = location.getProvider();   //위치제공자
            //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
            //Network 위치제공자에 의한 위치변화
            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
            tvMapDescription.setText("위치정보 : " + provider + "\n위도 : " + longitude + "\n경도 : " + latitude
                    + "\n고도 : " + altitude + "\n정확도 : " + accuracy);
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude);
            mMapView.setMapCenterPoint(mapPoint, true);
            mMapPOIItem = new MapPOIItem();
            mMapPOIItem.setItemName("낙서 요기");
            mMapPOIItem.setTag(0);
            mMapPOIItem.setMapPoint(mapPoint);
            mMapPOIItem.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
            mMapPOIItem.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

            mMapView.addPOIItem(mMapPOIItem);

            if (ActivityCompat.checkSelfPermission(ReportActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                lm.removeUpdates(mLocationListener);
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        double latitude = mapPoint.getMapPointGeoCoord().latitude;
        double longitude = mapPoint.getMapPointGeoCoord().longitude;

        tvMapDescription.setText("위도 : " + longitude + "\n경도 : " + latitude);
        if (mMapPOIItem != null)
            mMapPOIItem.setMapPoint(mapPoint);
    }

    @Override
    public void onMapViewInitialized(MapView mapView) { // map을 사용할 준비가 되었다.

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                    100, // 통지사이의 최소 시간간격 (miliSecond)
                    1, // 통지사이의 최소 변경거리 (m)
                    mLocationListener);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                    100, // 통지사이의 최소 시간간격 (miliSecond)
                    1, // 통지사이의 최소 변경거리 (m)
                    mLocationListener);
        }
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {

    }

    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        onFinishReverseGeoCoding(s);
    }

    private void onFinishReverseGeoCoding(String result) {
        Toast.makeText(ReportActivity.this, "Reverse Geo-coding : " + result, Toast.LENGTH_SHORT).show();
    }
}

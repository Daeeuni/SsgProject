//package com.seoulapp.ssg.ui.activity;
//
//import android.app.Activity;
//import android.net.Uri;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//
//import com.seoulapp.ssg.R;
//
///**
// * Created by win7-64 on 2016-09-11.
// */
//public class KakaoActivity extends Activity {
//
//    CheckBox option1;
//    CheckBox option2;
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    @Override
//    protected void onCreate(Bundle savedInstancState) {
//        super.onCreate(savedInstancState);//액티비티 생성
//        setContentView(R.layout.activity_kakao);
//
//        //버튼 선언
//        final Button nextButton = (Button) findViewById(R.id.nextbutton);
//        nextButton.setEnabled(false);//버튼 비활성화
//        option1 = (CheckBox) findViewById(R.id.option1);
//        option2 = (CheckBox) findViewById(R.id.option2);
//
//        option1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked)
//                    nextButton.setEnabled(true);
//                else
//                    nextButton.setEnabled(false);
//
//            }
//        });
//
//      /*  nextButton.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//
//                Intent resultIntent = new Intent();
//
//
//                setResult(RESULT_OK, resultIntent);
//                Intent intent = new Intent(getApplicationContext(), EmailActivity.class);
//                startActivity(intent);//이메일인증으로 넘어감
//            }
//        });
//    }*/
//    }
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Kakao Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.example.win7_64.ssg/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Kakao Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://com.example.win7_64.ssg/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }
//}

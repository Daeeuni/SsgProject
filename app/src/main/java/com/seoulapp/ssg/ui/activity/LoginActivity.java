package com.seoulapp.ssg.ui.activity;

/**
 * Created by win7-64 on 2016-10-09.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.seoulapp.ssg.R;
import com.seoulapp.ssg.ui.dialog.LoginDialog;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private CallbackManager mCallbackManager;
    private SessionCallback callback;

    private LoginDialog loginDialog;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Button btnFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext()); // SDK 초기화 (setContentView 보다 먼저 실행되어야합니다. 안그럼 에러납니다.)
        mCallbackManager = CallbackManager.Factory.create();  //로그인 응답을 처리할 콜백 관리자

        LoginManager.getInstance().registerCallback(mCallbackManager, mCallback);
        setContentView(R.layout.activity_login);

        TextView tvLinkify = (TextView) findViewById(R.id.tvLinkify);

        String text = "회원가입을 함으로써 쓱싹의 이용약관 및 개인정보 취급방침에 동의합니다.";
        tvLinkify.setText(text);

        Linkify.TransformFilter mTransform = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };
        Pattern pattern1 = Pattern.compile("이용약관 및 개인정보 취급방침");

        Linkify.addLinks(tvLinkify, pattern1, "http://naver.com", null, mTransform);
        btnFacebook = (Button) findViewById(R.id.facebook_login);
        btnFacebook.setOnClickListener(this);

        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                //로그아웃 성공 후 하고싶은 내용 코딩 ~
            }
        });

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    private void redirectMainActivity() {
        startActivity(new Intent(this, LoginDialog.class));
        finish();
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {

            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;
                    Log.e(TAG, "onFailure: " + message);

                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) {
                        finish();
                    } else {
                        redirectMainActivity();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Log.e(TAG, "onSessionClosed: " + errorResult.getErrorMessage());
                }

                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    Log.e(TAG, "onSuccess: 여기까지 들어옴");
                    Log.e(TAG, "UserProfle : " + userProfile);
                    Bundle bundle = new Bundle();
                    Log.e(TAG, "nickname: " + userProfile.getNickname());
                    bundle.putString("name", userProfile.getNickname());
                    bundle.putString("profile", userProfile.getThumbnailImagePath());

                    LoginDialog loginDialog = new LoginDialog();
                    loginDialog.setArguments(bundle);
                    loginDialog.show(getSupportFragmentManager(), null);
                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            // 세션 연결이 실패했을때
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.facebook_login:
                openFacebookSession();
                break;
        }
    }

    private void openFacebookSession() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }


    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.e(TAG, "onSuccess: accessToken = " + loginResult.getAccessToken().getToken());
            final String profile = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?type=normal";

            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    if (response.getError() != null) {
                        //handle error
                        Log.e(TAG, "onCompleted: geError " + response.getError().toString());
                    } else {
                        String email = object.optString("email");
                        String id = object.optString("id");
                        String name = object.optString("name");
                        if (TextUtils.isEmpty(email)) {
                            email = "";
                        }

                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("email", email);
                        bundle.putString("profile", profile);
                        loginDialog = new LoginDialog();
                        loginDialog.setArguments(bundle);
                        loginDialog.show(getSupportFragmentManager(), "login");
                    }
                }
            });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "name, email");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //간편로그인시 호출 ,없으면 간편로그인시 로그인 성공화면으로 넘어가지 않음
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
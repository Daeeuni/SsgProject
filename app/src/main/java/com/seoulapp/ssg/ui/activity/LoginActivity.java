package com.seoulapp.ssg.ui.activity;

/**
 * Created by win7-64 on 2016-10-09.
 */

import android.content.Intent;
import android.os.Bundle;
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
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.seoulapp.ssg.R;
import com.seoulapp.ssg.ui.dialog.LoginDialog;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private CallbackManager mCallbackManager;
    private SessionCallback kakaoCallback;

    private LoginDialog loginDialog;
    private Button btnFacebook, btnCustomKakaoLogin;
    private LoginButton btnKakaoLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext()); // SDK 초기화 (setContentView 보다 먼저 실행되어야합니다. 안그럼 에러납니다.)
        mCallbackManager = CallbackManager.Factory.create();  // 페이스북 로그인 응답을 처리할 콜백 관리자

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
        btnFacebook = (Button) findViewById(R.id.btn_facebook_login);
        btnKakaoLogin = (LoginButton) findViewById(R.id.com_kakao_login);
        btnFacebook.setOnClickListener(this);
        btnCustomKakaoLogin = (Button) findViewById(R.id.btn_kakao_login);
        btnCustomKakaoLogin.setOnClickListener(this);

        kakaoCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(kakaoCallback);
        Session.getCurrentSession().checkAndImplicitOpen();

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
                    Log.e(TAG, "onNotSignedUp: ");
                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    Log.e(TAG, "onSuccess: " + userProfile.toString());
                    Bundle bundle = new Bundle();
                    String mName = userProfile.getNickname();
                    String mProfile = userProfile.getThumbnailImagePath();
                    bundle.putString("name", mName);
                    bundle.putString("profile", mProfile);
                    LoginDialog loginDialog = new LoginDialog();
                    loginDialog.setArguments(bundle);
                    loginDialog.show(getSupportFragmentManager(), null);
                }
            });

        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_facebook_login:
                openFacebookSession();
                break;
            case R.id.btn_kakao_login:
                btnKakaoLogin.callOnClick();
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
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(kakaoCallback);

    }
}
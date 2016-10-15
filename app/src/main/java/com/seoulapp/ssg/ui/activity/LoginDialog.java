package com.seoulapp.ssg.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seoulapp.ssg.R;

/**
 * Created by win7-64 on 2016-10-15.
 */

public class LoginDialog extends Dialog {

    private TextView mTitleView;
    private Button mButton;
    private EditText mEmail;
    private EditText mName;
    private String mTitle;
    private String mContent;


    private View.OnClickListener mClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     /*   // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
*/
        setContentView(R.layout.dialog_login);

        mTitleView = (TextView) findViewById(R.id.edit_title);
        mEmail = (EditText) findViewById(R.id.edit_email);
        mName = (EditText) findViewById(R.id.edit_name);
        mButton = (Button) findViewById(R.id.edit_login);

        // 제목과 내용을 생성자에서 셋팅한다.
        mTitleView.setText(mTitle);


        // 클릭 이벤트 셋팅
        if (mClickListener != null) {
            mButton.setOnClickListener(mClickListener);
            }
        }


    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public LoginDialog(Context context, String title,
                       View.OnClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mClickListener = singleListener;
    }
}
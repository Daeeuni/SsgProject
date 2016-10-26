package com.seoulapp.ssg.ui.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.seoulapp.ssg.R;
import com.seoulapp.ssg.ui.activity.MainActivity;
import com.seoulapp.ssg.util.Utils;

/**
 * Created by win7-64 on 2016-10-15.
 */

public class LoginDialog extends DialogFragment {
    private static final String TAG = LoginDialog.class.getSimpleName();

    String mName, mProfile, mEmail;
    EditText editName, editEmail;
    Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mName = bundle.getString("name");
            mProfile = bundle.getString("profile");
            mEmail = bundle.getString("email");
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;

        int dialogWidth = Utils.getScreenWidth(getActivity()) - (int) getResources().getDimension(R.dimen.login_dialog_horizontal_margin);
        int dialogHeight = Utils.getScreenHeight(getActivity()) - (int) getResources().getDimension(R.dimen.login_dialog_vertical_margin);
        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_login, container, false);

        Button SignupButton = (Button) v.findViewById(R.id.edit_login);
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginDialog.this.dismiss();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        ImageView ivProfile;

        btnLogin = (Button) v.findViewById(R.id.edit_login);
        editName = (EditText) v.findViewById(R.id.edit_name);
        editName.setText(mName);
        editEmail = (EditText) v.findViewById(R.id.edit_email);
        editEmail.setText(mEmail);
        ivProfile = (ImageView) v.findViewById(R.id.profile_picture);

        Glide.with(this).load(mProfile).into(ivProfile);

        return v;
    }

}
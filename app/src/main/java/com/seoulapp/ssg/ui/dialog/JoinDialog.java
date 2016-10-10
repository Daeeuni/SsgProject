package com.seoulapp.ssg.ui.dialog;
import com.seoulapp.ssg.model.Volunteer;
import com.seoulapp.ssg.ui.activity.*;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seoulapp.ssg.R;

/**
 * Created by Boram Moon on 2016-10-06.
 */
public class JoinDialog extends Dialog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     * @see android.R.styleable#Theme_dialogTheme
     */
    public JoinDialog(Context context) {
        super(context);
    }
}

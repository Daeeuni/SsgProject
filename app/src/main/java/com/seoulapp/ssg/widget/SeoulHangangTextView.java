package com.seoulapp.ssg.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Dongyoon on 2016. 10. 22..
 */

public class SeoulHangangTextView extends TextView {

    private static final String SEOUL = "SeoulHangangM.otf";
    private static Typeface TYPE_FACE_SEOUL;

    public SeoulHangangTextView(Context context) {
        super(context);
    }

    public SeoulHangangTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SeoulHangangTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf) {
        if(TYPE_FACE_SEOUL == null){
            TYPE_FACE_SEOUL = Typeface.createFromAsset(getContext().getAssets(), SEOUL);
        }
        super.setTypeface(TYPE_FACE_SEOUL);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        if(TYPE_FACE_SEOUL == null){
            TYPE_FACE_SEOUL = Typeface.createFromAsset(getContext().getAssets(), SEOUL);
        }
        super.setTypeface(TYPE_FACE_SEOUL, style);
    }
}
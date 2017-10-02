package com.hololo.library.otpview;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

class OTPEditText extends AppCompatEditText implements TextWatcher, View.OnFocusChangeListener, View.OnClickListener, View.OnTouchListener {
    private int order;

    public OTPEditText(Context context, int order) {
        super(context);
        this.order = order;
        init(context);
    }

    public OTPEditText(Context context, AttributeSet attrs, int order) {
        super(context, attrs);
        this.order = order;
        init(context);
    }

    public OTPEditText(Context context, AttributeSet attrs, int defStyleAttr, int order) {
        super(context, attrs, defStyleAttr);
        this.order = order;
        init(context);
    }

    public void init(Context context) {
        setHint("*");
        setCursorVisible(false);
        changeBackground(true);
        addTextChangedListener(this);
        setOnTouchListener(this);
        setClickable(true);
        setOnClickListener(this);
        setOnFocusChangeListener(this);
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_DEL) {
                    if (getParent() instanceof OTPView) {
                        OTPEditText.this.setText("");
                        ((OTPView) getParent()).onBackPressed(OTPEditText.this);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        changeBackground(editable.toString().isEmpty());
    }

    private void changeBackground(boolean isEmpty) {
        setBackgroundColor(Color.TRANSPARENT);
        if (!isEmpty) {
            ((OTPView) getParent()).onKeyPressed(this);
        }
    }

    public int getOrder() {
        return order;
    }

    public void setMargin(int pixel) {
        setPadding(pixel, pixel, pixel, pixel);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            ((OTPView) getParent()).focusChange(view);
        }
    }

    @Override
    public void onClick(View view) {
        ((OTPView) getParent()).setFocus();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ((OTPView) getParent()).setFocus();
        return false;
    }
}
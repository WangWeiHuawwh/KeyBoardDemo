package com.example.administrator.keyboarddemo;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2015/7/30.
 */
public class KeyboardViewNumber extends KeyboardView {

    public KeyboardViewNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public KeyboardViewNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean onLongPress(Keyboard.Key popupKey) {
        // TODO Auto-generated method stub
        if (popupKey.codes[0] == Keyboard.KEYCODE_DELETE) {
            //DefineKeyboard.mDefineKeyboardUtil.clearEditTextContent();
            //可使用OnKeyboardActionListener中的各种方法实现该功能
            getOnKeyboardActionListener().onKey(4896, null);
        }

        return super.onLongPress(popupKey);
    }


}


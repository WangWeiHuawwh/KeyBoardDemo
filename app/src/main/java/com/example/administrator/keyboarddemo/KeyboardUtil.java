package com.example.administrator.keyboarddemo;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class KeyboardUtil {
    private KeyboardView keyboardView;
    private Keyboard k;// 数字键盘
    private EditText ed;
    Animation animationShow;
    Animation animationHide;
    private Context context;
    private onShowCloseListener showListener;

    public interface onShowCloseListener {
        public void show();

        public void onPush();

        public void close();
    }

    public KeyboardUtil(View view, Context ctx, EditText edit) {
        this.ed = edit;
        this.context = ctx;
        k = new Keyboard(ctx, R.xml.symbols);
        keyboardView = (KeyboardViewNumber) view.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(k);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setVisibility(View.GONE);
        keyboardView.setOnKeyboardActionListener(listener);
        animationShow = AnimationUtils.loadAnimation(ctx, R.anim.popup_fade_in);
        animationHide = AnimationUtils.loadAnimation(ctx, R.anim.popup_fade_out);
    }

    public KeyboardUtil(Activity activity, Context ctx, EditText edit) {
        this.ed = edit;
        this.context = ctx;
        k = new Keyboard(ctx, R.xml.symbols);
        keyboardView = (KeyboardViewNumber) activity.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(k);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setVisibility(View.GONE);
        keyboardView.setOnKeyboardActionListener(listener);
        animationShow = AnimationUtils.loadAnimation(ctx, R.anim.popup_fade_in);
        animationHide = AnimationUtils.loadAnimation(ctx, R.anim.popup_fade_out);

    }

    public void setShowListener(onShowCloseListener listener) {
        showListener = listener;
    }

    private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        //一些特殊操作按键的codes是固定的比如完成、回退等
        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = ed.getText();
            int start = ed.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
                if (showListener != null) {
                    showListener.onPush();
                }
            } else if (primaryCode == 4896) {
                editable.clear();
                if (showListener != null) {
                    showListener.onPush();
                }
            } else if (primaryCode == -10) {
                hideKeyboard();
            } else { //将要输入的数字现在编辑框中
                editable.insert(start, Character.toString((char) primaryCode));
                if (showListener != null) {
                    showListener.onPush();
                }
            }

        }
    };

    public boolean isShow() {
        return keyboardView.getVisibility() == View.VISIBLE;
    }

    public boolean showKeyboard() {
        if (keyboardView.getVisibility() == View.GONE || keyboardView.getVisibility() == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
            if (showListener != null) {
                showListener.show();
            }
            return true;
        } else {
            return false;
            //Toast.makeText(context, "数字键盘已开启", Toast.LENGTH_SHORT).show();
        }

    }

    public void startShow() {
        keyboardView.startAnimation(animationShow);
    }

    public void hideKeyboard() {
        if (keyboardView.getVisibility() == View.VISIBLE) {
            keyboardView.startAnimation(animationHide);
            keyboardView.setVisibility(View.GONE);
            if (showListener != null) {
                showListener.close();
            }
        }
    }
}
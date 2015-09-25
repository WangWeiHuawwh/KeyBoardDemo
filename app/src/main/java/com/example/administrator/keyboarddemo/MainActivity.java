package com.example.administrator.keyboarddemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Method;


public class MainActivity extends ActionBarActivity {
    EditText mInputAmountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInputAmountView = (EditText) findViewById(R.id.edit_text);
        disableShowSoftInput(mInputAmountView);
        mInputAmountView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //这样是在触摸到控件时，软键盘才会显示出来
                showKeyboard();
                return false;
            }
        });

    }

    private KeyboardUtil ku;

    private void showKeyboard() {
        if (ku == null) {
            ku = new KeyboardUtil(this, this, mInputAmountView);
            ku.setShowListener(new KeyboardUtil.onShowCloseListener() {
                @Override
                public void show() {

                }

                @Override
                public void onPush() {

                }

                @Override
                public void close() {

                }
            });
        }
        if (ku.showKeyboard()) {
            ku.startShow();
        }
    }

    /**
     * 禁止Edittext弹出软件盘，光标依然正常显示。
     */
    public void disableShowSoftInput(EditText editText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                // TODO: handle exception
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

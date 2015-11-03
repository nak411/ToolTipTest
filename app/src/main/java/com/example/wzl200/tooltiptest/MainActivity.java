package com.example.wzl200.tooltiptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;


public class MainActivity extends AppCompatActivity {

    private Button btnShow;
    private Tooltip tooltip;

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = (Button) findViewById(R.id.btn_show);
        showToolTip();
    }


    private void showToolTip() {
//        tooltip = new Tooltip(this, btnShow);
//        tooltip.setToolTipContentView(getLayoutInflater().inflate(R.layout.popcontent, null))
//            .setBackgroundColor(android.R.color.holo_red_dark)
//            .show();
        final PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        view = getLayoutInflater().inflate(R.layout.popcontent, null);
        popupWindow.setContentView(view);
        btnShow.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAsDropDown(btnShow);
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                AccessibilityNodeProvider provider = view.getAccessibilityNodeProvider();
                view.setAccessibilityDelegate(new View.AccessibilityDelegate());
            }
        });
    }
}

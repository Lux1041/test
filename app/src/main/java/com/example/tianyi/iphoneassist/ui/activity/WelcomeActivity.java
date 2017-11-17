package com.example.tianyi.iphoneassist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;
import com.example.tianyi.iphoneassist.R;

/**
 * Created by Tianyi on 2017/11/14.
 */

public class WelcomeActivity extends AppCompatActivity {
    private PathView pathView, pathview2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        pathView = (PathView) findViewById(R.id.pathView);
        pathview2 = (PathView) findViewById(R.id.pathView2);
        pathView.getPathAnimator().delay(0)
                .duration(3000)
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
//                        jumpToMain();
                    }
                })
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();
        pathview2.getPathAnimator().delay(1000).duration(3000).listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
            @Override
            public void onAnimationEnd() {
                jumpToMain();
            }
        }).start();
    }

    private void jumpToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

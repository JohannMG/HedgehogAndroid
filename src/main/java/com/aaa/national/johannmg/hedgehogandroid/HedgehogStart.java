package com.aaa.national.johannmg.hedgehogandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class HedgehogStart extends AppCompatActivity {

    private ScaleGestureDetector scaleDetector;
    private GestureDetector swipeDetector;
    private float fact2Scaling = 0.1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hedgehog_start);

        //move first hedgehog off screen on loads
        ImageView hedgehog = (ImageView) findViewById(R.id.hedgehog_click_img);
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        hedgehog.animate().translationX((float) screenSize.y);

        //add listener for pinch/zoom
        scaleDetector = new ScaleGestureDetector(this, new ScaleListener());
        ImageView zoomImg = (ImageView) findViewById(R.id.zoomImg);
        zoomImg.setScaleY( fact2Scaling );
        zoomImg.setScaleX( fact2Scaling );

        //fling listener
        swipeDetector = new GestureDetector(this, new SwipeListener());

        //hide hedgehog 3
        ImageView hogimg3 = (ImageView) findViewById(R.id.hedgehog3Img);
        hogimg3.setAlpha(0.0f);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleDetector.onTouchEvent(event);
        swipeDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hedgehog_start, menu);
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

    public void hedgehogFactsNextAction(View view) {

        Intent toListMenuIntent = new Intent(this, HedgehogListMenu.class);
        startActivity(toListMenuIntent);

    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        private final float SCALING_FACTOR = 0.5f;
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            ImageView zoomImg = (ImageView) findViewById(R.id.zoomImg);
            fact2Scaling = fact2Scaling * detector.getScaleFactor();
            Math.min(fact2Scaling, 1.3);
            Math.max(fact2Scaling, .1);
            zoomImg.setScaleX( fact2Scaling );
            zoomImg.setScaleY( fact2Scaling );
            return true; //if scaled, no other interface in for that ges
        }
    }

    private class SwipeListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("fling: ","DOWN ( " + e1.getX() + ", " + e1.getY() + ")");
            Log.i("fling: ","UP ( " + e2.getX() + ", " + e2.getY() + ")");

            animateSlideIn(300.0f, 0.0f);

            return true;
        }

        public void animateSlideIn(float offsetX , float offsetY){
            ImageView hogimg3 = (ImageView) findViewById(R.id.hedgehog3Img);
            hogimg3.setTranslationX(offsetX);
            hogimg3.setTranslationX(offsetY);
            hogimg3.setAlpha(1.0f);

            TranslateAnimation tx = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT,  offsetX,
                    Animation.RELATIVE_TO_PARENT, 0f,
                    Animation.RELATIVE_TO_PARENT, offsetY,
                    Animation.RELATIVE_TO_PARENT, 0f);
            AlphaAnimation aa = new AlphaAnimation(0,1);

            AnimationSet aSet = new AnimationSet(false);
            aSet.setFillBefore(true);
            aSet.setFillEnabled(true);
            aSet.setInterpolator(new DecelerateInterpolator());
            aSet.addAnimation(tx);
            aSet.addAnimation(aa);
            aSet.setDuration(300);
            aSet.setStartTime(0);

            hogimg3.startAnimation(aSet);


        }
    }

}

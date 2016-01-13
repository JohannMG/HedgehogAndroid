package com.aaa.national.johannmg.hedgehogandroid;

import android.content.Context;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class HedgehogStart extends AppCompatActivity {

    private ScaleGestureDetector scaleDetector;
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



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleDetector.onTouchEvent(event);
        return true;
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

        ImageView hedgehog = (ImageView) findViewById(R.id.hedgehog_click_img);
        hedgehog.animate().translationX(0.0f);


    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            ImageView zoomImg = (ImageView) findViewById(R.id.zoomImg);
            fact2Scaling = fact2Scaling * detector.getScaleFactor();
            zoomImg.setScaleX( fact2Scaling );
            zoomImg.setScaleY( fact2Scaling );
            return true;
        }
    }

}

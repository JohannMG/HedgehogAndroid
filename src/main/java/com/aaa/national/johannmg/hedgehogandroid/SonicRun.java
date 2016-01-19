package com.aaa.national.johannmg.hedgehogandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class SonicRun extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonic_run);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sonic_rub, menu);
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

    public void runSonic(View view) {

        ImageView sonicImg = (ImageView) findViewById(R.id.sonicRunImg);
        View mainView = findViewById(R.id.sonicLayoutView);

        BoundKeep boundK = new BoundKeep(
                0, mainView.getWidth() - sonicImg.getWidth(), //min, max x including image size
                0, mainView.getHeight() - sonicImg.getHeight() //min, max y including image size
        );

        sonicImg.setX(0);
        sonicImg.setY(0);

        final long animationDuration = 500; //ms

        AnimationSet animSet = new AnimationSet(false);

        //0: top left to top right
        TranslateAnimation trans1 = new TranslateAnimation(
                0, boundK.maxX,
                0, 0
        );
        trans1.setDuration(animationDuration);

        //1: top right to bottom right
        TranslateAnimation trans2 = new TranslateAnimation(
                0, 0,
                0, boundK.maxY
        );
        trans2.setStartOffset(animationDuration);
        trans2.setDuration(animationDuration);

        //2: bottom right to bottom left
        TranslateAnimation trans3 = new TranslateAnimation(
                0, -boundK.maxX,
                0, 0
        );
        trans3.setDuration(animationDuration);
        trans3.setStartOffset(animationDuration * 2);

        //3: final bottom left to top left
        TranslateAnimation trans4 = new TranslateAnimation(
                0, 0,
                0, -boundK.maxY
        );
        trans4.setDuration(animationDuration);
        trans4.setStartOffset(animationDuration * 3);


        animSet.setFillAfter(true);
        animSet.addAnimation(trans1);
        animSet.addAnimation(trans2);
        animSet.addAnimation(trans3);
        animSet.addAnimation(trans4);
        animSet.setStartTime(0);

        final View lastViewState = view;
        animSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //show pop-up
                getShowEndingAlert(lastViewState);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        sonicImg.startAnimation(animSet);





    }

    private void getShowEndingAlert(View view) {
        final View innerView = view;
        final Context contextToRestart = this;
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("SO FAST!")
                .setMessage("Would you like to run again or start over?")
                .setPositiveButton("Run Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        runSonic(innerView);
                    }
                })
                .setNegativeButton("Beginning", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent startScreen = new Intent(contextToRestart, HedgehogStart.class);
                        startActivity(startScreen);
                    }
                })
                .show();
    }

    //helper to track bounds
    private static class BoundKeep{
        int minX;
        int maxX;
        int minY;
        int maxY;

        public BoundKeep(int minX, int maxX, int minY, int maxY){
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY= maxY;
        }

    }
}

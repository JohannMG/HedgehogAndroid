package com.aaa.national.johannmg.hedgehogandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class DayNight extends AppCompatActivity {
    public final static String LOG_ID = "DayNight_Class";
    public final static String OPTIONS_MSG = "com.aaa.national.johannmg.DAYNIGHT";
    public final static String SEND_OPTION = "SENDDAYTIME";
    public final static int OPTIONS_INTENT_CODE = 1;

    private boolean isDaytime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_night);
        isDaytime = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_day_night, menu);
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


    public void getOptionsActivity(View view) {

        Intent settings = new Intent( this, DayNightOptions.class );
        settings.putExtra(SEND_OPTION, isDaytime);
        startActivityForResult(settings, OPTIONS_INTENT_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == OPTIONS_INTENT_CODE && resultCode == RESULT_OK){
            //get the data
            Boolean returnedDay = data.getBooleanExtra(OPTIONS_MSG, false);
            if (isDaytime != returnedDay) {
                isDaytime = returnedDay;
                updateDayNight();
            }

        }
    }

    private void updateDayNight() {

        ImageView img = (ImageView) findViewById(R.id.dayNightImage);
        View mainView = findViewById(R.id.mainDayNightView);

        if (isDaytime){
            img.setImageResource(R.mipmap.sleeping_hedgehod);
            mainView.setBackgroundColor( Color.parseColor("#ffffff") );




        } else{
            img.setImageResource(R.mipmap.moon);
            mainView.setBackgroundResource(R.color.darkBG);
        }

    }
}

package com.aaa.national.johannmg.hedgehogandroid;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aaa.national.johannmg.hedgehogandroid.Services.WeatherService;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CritterWeather extends AppCompatActivity {

    WeatherService ws = WeatherService.getInstance();
    TextView degreesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critter_weather);

        degreesText = (TextView) findViewById(R.id.weatherDegreesLabel);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected() && netInfo.isAvailable()){
            new GetWeatherData().execute();
        }
        else {
            //no data?
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_critter_weather, menu);
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


    //AsyncTask<Params, Progress, Result>
    private class GetWeatherData extends AsyncTask<Void, Void, Void>{

        public float weatherDegree;
        DecimalFormat df = new DecimalFormat("#.0");


        @Override
        protected Void doInBackground(Void... params) {
            df.setRoundingMode(RoundingMode.HALF_UP);
            weatherDegree = ws.getTemperatureInFahrenheit();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            degreesText.setText( df.format(weatherDegree) + "F"  );


        }
    }
}

package com.aaa.national.johannmg.hedgehogandroid.Services;

import android.app.DownloadManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

/**
 * A sigleton class to get the weather
 * Created by jgarces on 1/19/16.
 */
public class WeatherService {
    private static WeatherService ourInstance = new WeatherService();
    private String apiUrl = "http://api.openweathermap.org/data/2.5/weather?zip=32801,us&APPID=a02ff5ada233c6ad32d5479712c0ecc0";
    public static final String LOG_ID = "WeatherService_Class";

    public static WeatherService getInstance() {
        return ourInstance;
    }

    private float tempKelvin = 0.0f;

    private WeatherService() {
        //constructor
    }

    /** Should not have to be called publicly b/c it is in singleton init but available. Gets new weather data.  */
    public void loadWeather(){

        InputStream iStream = null;

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?zip=32801,us&APPID=a02ff5ada233c6ad32d5479712c0ecc0");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            connection.setDoInput(true);

            //opens stream
            connection.connect();
            int rescode = connection.getResponseCode();
            iStream = connection.getInputStream();

            //non-200 OK response code
            if (rescode < 200 || rescode >= 300) {
                Log.d(LOG_ID, "loadWeather contacted API with unsupported response code: "+ rescode );
                return;
            }

            //Parses response into a string
            byte [] readBuffer = new byte[2048];
            iStream.read(readBuffer);
            String resString = new String(readBuffer, "UTF-8");

            //parse it as JSON
            JSONObject weatherJson = new JSONObject( resString );
            tempKelvin = (float) weatherJson.getJSONObject("main").getDouble("temp");
        }

        catch (MalformedURLException e){
            Log.d(LOG_ID, "error with parsing url " + apiUrl);
            e.printStackTrace();
        }

        catch (IOException e){
            Log.d(LOG_ID, "loadWeather encountered an IO exception" );
            e.printStackTrace();
        }
        
        catch (JSONException e){
            Log.d(LOG_ID, "loadWeather, error parsing Json object" );
            e.printStackTrace();
        }

        finally {
            try { if (iStream != null)  iStream.close(); }
            catch (IOException e) {  /* then it's already null */  }
        }


    }


    /** Best call this in an async module  */

    public float getTemperatureInFahrenheit(){

        if (tempKelvin < 1.0 ) { loadWeather(); }
        return (float)  ((9.0/5.0) * (tempKelvin - 273)) + 32;
    }
}

package com.aaa.national.johannmg.hedgehogandroid;

import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 // Created by jgarces on 1/14/16.
 */


public class MenuNavItem {

    public String navText;
    public int navImage;
    private static final String DEBUG_NAME = "NavMenuItem_Class";

    public MenuNavItem(String text){
        navText = text;
        navImage = R.mipmap.hedgehog1;
    }

    public static ArrayList<MenuNavItem> getNavItemsFromJsonFile(InputStream fileStream){

        ArrayList<MenuNavItem> navigationItems = new ArrayList<MenuNavItem>();
        if (fileStream == null){
            return  navigationItems;
        }

        String fileInput = null;
        try {
            int size = fileStream.available();
            byte[] buffer = new byte[size];
            fileStream.read(buffer);
            fileStream.close();
            fileInput = new String(buffer, "UTF-8");

        }

        catch (IOException e){
            fileStream = null;
            Log.d(DEBUG_NAME, "Problem opening file");
            Log.d(DEBUG_NAME, e.getMessage());
            return navigationItems;
        }
        finally {

        }

        //json

        try{
            JSONArray jsonArray = new JSONArray(fileInput);
            if (jsonArray!= null){
                for (int i = 0; i < jsonArray.length(); i ++){
                    navigationItems.add(  new MenuNavItem( jsonArray.get(i).toString() )  );
                }
            }
        }

        catch ( JSONException e){
            Log.d(DEBUG_NAME, "Problem Parsing JSON string to array");
            Log.d(DEBUG_NAME, e.getMessage());
            return navigationItems;
        }
        finally {

        }


        return navigationItems;
    }


}

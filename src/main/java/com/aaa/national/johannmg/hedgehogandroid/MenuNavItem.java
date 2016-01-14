package com.aaa.national.johannmg.hedgehogandroid;

import android.widget.ImageView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 // Created by jgarces on 1/14/16.
 */


public class MenuNavItem {

    public String navText;
    public int navImage;

    public MenuNavItem(String text){
        navText = text;
        navImage = R.mipmap.hedgehog1;
    }

    public static ArrayList<MenuNavItem> getNavItemsFromJsonFile(String filename){
        ArrayList<MenuNavItem> navigationItems = new ArrayList<MenuNavItem>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(  );
        }

        catch (Exception e){

        }
        finally {

        }


        return navigationItems;
    }


}

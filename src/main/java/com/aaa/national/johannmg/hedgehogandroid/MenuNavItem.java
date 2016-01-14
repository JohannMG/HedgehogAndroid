package com.aaa.national.johannmg.hedgehogandroid;

import android.widget.ImageView;

import org.json.JSONArray;

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

    public static ArrayList<MenuNavItem> getNavItemsFromJsonFile(JSONArray items){
        ArrayList<MenuNavItem> navigationItems = new ArrayList<MenuNavItem>();



        return navigationItems;
    }


}

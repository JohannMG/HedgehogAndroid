package com.aaa.national.johannmg.hedgehogandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;

public class HedgehogListMenu extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    public static final String DEBUG_TAG = "HedgehogListMenu_Class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hedgehog_list_menu);


        ArrayList<MenuNavItem> arrayOfItems = null;


        try{
            InputStream fileStream= getAssets().open("menuData.json");
            arrayOfItems = MenuNavItem.getNavItemsFromJsonFile(fileStream);

        }
        catch (Exception e){
            arrayOfItems = new ArrayList<MenuNavItem>();
            arrayOfItems.add( new MenuNavItem("Menu Item not Found" ));
        }

        NavItemsAdapter navAdapter = new NavItemsAdapter(this, arrayOfItems);
        ListView listView = (ListView) findViewById(R.id.menuListView);
        listView.setAdapter(navAdapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hedgehog_list_menu, menu);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            //day night hedgehog application
            case 0:
                Intent daynight = new Intent(this, DayNight.class);
                startActivity(daynight);
                break;

            //weather application
            case 1:
                Intent weather = new Intent(this, CritterWeather.class);
                startActivity(weather);
                break;

            //Hedgehog run
            case 2:
                break;

            //surprise?
            default:
                break;
        }
    }
}

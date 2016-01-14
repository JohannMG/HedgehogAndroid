package com.aaa.national.johannmg.hedgehogandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class HedgehogListMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hedgehog_list_menu);


        ArrayList<MenuNavItem> arrayOfItems = new ArrayList<MenuNavItem>();
        arrayOfItems.add( new MenuNavItem("First Item" ));
        arrayOfItems.add(new MenuNavItem("Second Item"));
        NavItemsAdapter navAdapter = new NavItemsAdapter(this, arrayOfItems);
        ListView listView = (ListView) findViewById(R.id.menuListView);
        listView.setAdapter(navAdapter);


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


}

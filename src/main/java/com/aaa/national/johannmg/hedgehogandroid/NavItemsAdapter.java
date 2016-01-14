package com.aaa.national.johannmg.hedgehogandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by jgarces on 1/14/16.
 */
public class NavItemsAdapter extends ArrayAdapter<MenuNavItem> {

    public NavItemsAdapter(Context context, ArrayList<MenuNavItem> users  ){

        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        //not efficient for now..

        //inherited getItem() gets from the ArrayList
        MenuNavItem item = getItem(position);

        //if being sent a new view, we only will have to update the fields,
        //but here we'll make one otherwise
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_row, parent, false);
        }

        TextView itemText = (TextView) convertView.findViewById(R.id.menuItemText);
        ImageView itemImage = (ImageView) convertView.findViewById(R.id.menuItemImg);

        itemText.setText(item.navText);
        itemImage.setImageResource(item.navImage);

        return convertView;

    }
}

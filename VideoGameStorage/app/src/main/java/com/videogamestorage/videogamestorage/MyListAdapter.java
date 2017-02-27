package com.videogamestorage.videogamestorage;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by ZFleezy on 2/25/2017.
 */


public class MyListAdapter extends SimpleCursorAdapter {

    public MyListAdapter(Context context, int layout, Cursor cursor, String[] from, int[] to) {
        super(context, layout , cursor, from, to);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Create the idno textview with background image
//        TextView id = (TextView) view.findViewById(R.id.idNum);
//        id.setText(cursor.getString(1));
        TextView name = (TextView) view.findViewById(R.id.gameName);
        name.setText(cursor.getString(2));
        TextView ry = (TextView) view.findViewById(R.id.releaseYear);
        ry.setText(cursor.getString(3));
        // create the material textview
        TextView materials = (TextView) view.findViewById(R.id.releaseYear);
        materials.setText(cursor.getString(3));
        cursor.moveToNext();
    }
}
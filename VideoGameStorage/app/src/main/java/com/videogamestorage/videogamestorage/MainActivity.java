package com.videogamestorage.videogamestorage;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button addTo, edit, delete, confirm, cancelBttn, displayBttnm;
    private TextView vgtext, rytext;
    private EditText vgEdit, ryEdit;
    private DBHandler db;
    private Bundle save;
    private ListView list;
    //    private DatabaseA dbHelper;
//    private SimpleCursorAdapter dataAdapter;
public DBHandler getDb(){
    return db;
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        save = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTo = (Button) findViewById(R.id.addToDatabaseButton);
        edit = (Button) findViewById(R.id.editButton);
        delete = (Button) findViewById(R.id.deleteItem);
        vgtext = (TextView) findViewById(R.id.vgText);
        vgEdit = (EditText) findViewById(R.id.vgEditText);
        rytext = (TextView) findViewById(R.id.ryText);
        ryEdit = (EditText) findViewById(R.id.ryEditText);
        confirm = (Button) findViewById(R.id.Confirm);
        cancelBttn = (Button) findViewById(R.id.Cancel);
        displayBttnm = (Button) findViewById(R.id.display);
        cancelBttn.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.INVISIBLE);
        vgtext.setVisibility(View.INVISIBLE);
        vgEdit.setVisibility(View.INVISIBLE);
        rytext.setVisibility(View.INVISIBLE);
        ryEdit.setVisibility(View.INVISIBLE);
        list = (ListView) findViewById(R.id.dblist);
        list.setVisibility(View.INVISIBLE);
        db = new DBHandler(this);
//        for(int i = 1; i <=10; i++) db.deletegame(db.getgame(i));

    }

    //    deleteItem, editItem, addToDatabase
    public void addToDatabase(View v) {
        edit.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.INVISIBLE);
        displayBttnm.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.VISIBLE);
        vgtext.setVisibility(View.VISIBLE);
        vgEdit.setVisibility(View.VISIBLE);
        rytext.setVisibility(View.VISIBLE);
        ryEdit.setVisibility(View.VISIBLE);
        cancelBttn.setVisibility(View.VISIBLE);
    }

    public void editItem(View v) {

    }

    public void deleteItem(View v) {

    }

    public void confirmEntry(View v) {
        if (!vgEdit.getText().toString().equalsIgnoreCase("") && !ryEdit.getText().toString().equalsIgnoreCase("")) {
//            Log.d("Insert: ", "Inserting ..");
            System.out.println("Inserting " + vgEdit.getText().toString());
            db.addGame(new Game(vgEdit.getText().toString(), ryEdit.getText().toString()));
//            Log.d("Reading: ", "Reading all shops after adding..");
            System.out.println("Reading " + vgEdit.getText().toString());
            List<Game> games = db.getAllGames();
            for (Game game : games) {
                String log = "Id: " + game.getId() + " ,Name: " + game.getName() + " ,Release Year: " + game.getReleaseYear();
                // Writing shops to log
//                Log.d("Game: ", log);
                System.out.println("Game: " + log);
            }

        }
    }

    public void cancel(View v) {
        vgEdit.setText("");
        ryEdit.setText("");
        cancelBttn.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.INVISIBLE);
        vgtext.setVisibility(View.INVISIBLE);
        vgEdit.setVisibility(View.INVISIBLE);
        rytext.setVisibility(View.INVISIBLE);
        ryEdit.setVisibility(View.INVISIBLE);
        edit.setVisibility(View.VISIBLE);
        delete.setVisibility(View.VISIBLE);
        displayBttnm.setVisibility(View.VISIBLE);
    }

    public void displayDatabase(View v) {
//    MaterialsListFragment list = new MaterialsListFragment();
//    list.onCreateView(getLayoutInflater(),(ViewGroup) v, save);
//    list.onCreate(save);
      Cursor cursor = db.getAllGamesCursor();
        startManagingCursor(cursor);
        String[] from = new String[]{DBHandler.getKeyName(), DBHandler.getKeyGRy()};
       int[] to = new int[db.getAllGames().size()];
        for (int x = 0; x<db.getAllGames().size();x++){
            to[x]=(x+1);
        }
//               {R.id.gameName, R.id.releaseYear};
//    String itemline = ;
    SimpleCursorAdapter ca = new SimpleCursorAdapter(this, R.layout.activity_main, cursor, from, to);
//        MyListAdapter materials = new MyListAdapter(this, R.layout.databaselayout, cursor, from, to);
//        CursorA = ;
        list.setAdapter(ca);
        list.setVisibility(View.VISIBLE);
    }
}



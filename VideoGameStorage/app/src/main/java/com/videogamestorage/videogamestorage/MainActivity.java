package com.videogamestorage.videogamestorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button addTo, edit, delete, confirm, cancelBttn;
    private TextView vgtext, rytext;
    private EditText vgEdit, ryEdit;
    private DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        cancelBttn.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.INVISIBLE);
        vgtext.setVisibility(View.INVISIBLE);
        vgEdit.setVisibility(View.INVISIBLE);
        rytext.setVisibility(View.INVISIBLE);
        ryEdit.setVisibility(View.INVISIBLE);
        db = new DBHandler(this);
    }
//    deleteItem, editItem, addToDatabase
    public void addToDatabase(View v){
        edit.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.VISIBLE);
        vgtext.setVisibility(View.VISIBLE);
        vgEdit.setVisibility(View.VISIBLE);
        rytext.setVisibility(View.VISIBLE);
        ryEdit.setVisibility(View.VISIBLE);
        cancelBttn.setVisibility(View.VISIBLE);
    }
    public void editItem(View v){

    }
    public void deleteItem(View v){

    }

    public void confirmEntry(View v){
        if(!vgEdit.getText().toString().equalsIgnoreCase("")&& !ryEdit.getText().toString().equalsIgnoreCase("")){
            Log.d("Insert: ", "Inserting ..");
            db.addgame(new Game(vgEdit.getText().toString(),ryEdit.getText().toString()));
            Log.d("Reading: ", "Reading all shops after adding..");
            List<Game> games = db.getAllgames();
            for (Game game : games) {
                String log = "Id: " + game.getId() + " ,Name: " + game.getName() + " ,Release Year: " + game.getReleaseYear();
                // Writing shops to log
                Log.d("Game: ", log);
            }
        }
    }

    public void cancel(View v){
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
    }
}

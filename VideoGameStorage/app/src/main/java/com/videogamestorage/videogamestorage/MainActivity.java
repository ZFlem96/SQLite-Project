package com.videogamestorage.videogamestorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.videogamestorage.videogamestorage.dbobjects.Game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonAddGame;
    ListView listViewGames;
    List<Game> gameList = new ArrayList<Game>();
    ArrayAdapter<Game> gameArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
//        Game.deleteAll(Game.class);
            buttonAddGame = (Button) findViewById(R.id.buttonAddGame);
            listViewGames = (ListView) findViewById(R.id.listViewGames);
            gameArrayAdapter = new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, gameList);
            listViewGames.setAdapter(gameArrayAdapter);
            Iterator<Game> iterator = Game.findAll(Game.class);
            while (iterator.hasNext()) {
                Game game = iterator.next();
                gameArrayAdapter.add(game);
            }
            final MainActivity self = this;
            listViewGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(self, EditGameActivity.class);
                    intent.putExtra("gameId", ((Game)listViewGames.getItemAtPosition(i)).getId());
                    startActivity(intent);
                }
            });
        }catch(Exception e){

        }


    }


    public void addGameButtonClick(View v) {
        Intent intent = new Intent(this, AddGameActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        try{
            super.onResume();
            gameArrayAdapter.clear();
            Iterator<Game> iterator = Game.findAll(Game.class);
            while (iterator.hasNext()) {
                Game game = iterator.next();
                gameArrayAdapter.add(game);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

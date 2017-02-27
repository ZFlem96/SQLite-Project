package com.videogamestorage.videogamestorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.videogamestorage.videogamestorage.dbobjects.Game;

public class EditGameActivity extends AppCompatActivity {

    Game game;
    EditText editTextVideoGame;
    CheckBox checkBoxCompletion;
    EditText editTextProgress;
    EditText editTextHours;
    EditText editTextReleaseYear;
    Button buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);

        Bundle extras = getIntent().getExtras();
        Long id = extras.getLong("gameId");
        game = Game.findById(Game.class, id);

        editTextVideoGame = (EditText) findViewById(R.id.editTextVideoGame);
        checkBoxCompletion = (CheckBox) findViewById(R.id.checkBoxCompletion);
        editTextProgress = (EditText) findViewById(R.id.editTextProgress);
        editTextHours = (EditText) findViewById(R.id.editTextHours);
        editTextReleaseYear = (EditText) findViewById(R.id.editTextReleaseYear);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        editTextVideoGame.setText(game.getVideoGame());
        checkBoxCompletion.setChecked(game.isComplete());
        editTextProgress.setText(Double.toString(game.getProgress()));
        editTextHours.setText(Double.toString(game.getTimeSpentPlaying()));
        editTextReleaseYear.setText(game.getReleaseYear());
    }

    public void buttonDeleteClick(View v) {
        game.delete();
        finish();
    }

    public void buttonUpdateClick(View v) {
        game.setVideoGame(editTextVideoGame.getText().toString());
        game.setComplete(checkBoxCompletion.isChecked());
        game.setProgress(Double.parseDouble(editTextProgress.getText().toString()));
        game.setTimeSpentPlaying(Double.parseDouble(editTextHours.getText().toString()));
        game.setReleaseYear(editTextReleaseYear.getText().toString());
        game.save();
        finish();
    }

}

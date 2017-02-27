package com.videogamestorage.videogamestorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.videogamestorage.videogamestorage.dbobjects.Game;

public class AddGameActivity extends AppCompatActivity {

    EditText editTextVideoGame;
    CheckBox checkBoxCompletion;
    EditText editTextProgress;
    EditText editTextHours;
    EditText editTextReleaseYear;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        editTextVideoGame = (EditText) findViewById(R.id.editTextVideoGame);
        checkBoxCompletion = (CheckBox) findViewById(R.id.checkBoxCompletion);
        editTextProgress = (EditText) findViewById(R.id.editTextProgress);
        editTextHours = (EditText) findViewById(R.id.editTextHours);
        editTextReleaseYear = (EditText) findViewById(R.id.editTextReleaseYear);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

    }

    public void submitButtonClick(View v) {
        Game newGame = new Game();
        newGame.setVideoGame(editTextVideoGame.getText().toString());
        newGame.setComplete(checkBoxCompletion.isChecked());
        newGame.setProgress(Double.parseDouble(editTextProgress.getText().toString()));
        newGame.setTimeSpentPlaying(Double.parseDouble(editTextHours.getText().toString()));
        newGame.setReleaseYear(editTextReleaseYear.getText().toString());
        newGame.save();
        finish();
    }

}

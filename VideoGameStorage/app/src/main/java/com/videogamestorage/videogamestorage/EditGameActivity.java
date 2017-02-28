package com.videogamestorage.videogamestorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.videogamestorage.videogamestorage.dbobjects.Game;

import java.util.Timer;
import java.util.TimerTask;

public class EditGameActivity extends AppCompatActivity {

    Game game;
    EditText editTextVideoGame;
    CheckBox checkBoxCompletion;
    EditText editTextProgress;
    EditText editTextHours;
    EditText editTextReleaseYear;
    Button buttonUpdate;
    TextView time;
    int count=0;
    boolean stopTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);
        time = (TextView) findViewById(R.id.timer);
        Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if(stopTime) {
                            time.setText(String.valueOf(count+(" (s)")));
                            count++;
                        }
                    }
                });
            }
        }, 1000, 1000);
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
        stopTime = false;
        game.delete();
        finish();
    }

    public void buttonUpdateClick(View v) {
        stopTime = false;
        game.setVideoGame(editTextVideoGame.getText().toString());
        game.setComplete(checkBoxCompletion.isChecked());
        game.setProgress(Double.parseDouble(editTextProgress.getText().toString()));
        game.setTimeSpentPlaying(Double.parseDouble(editTextHours.getText().toString()));
        game.setReleaseYear(editTextReleaseYear.getText().toString());
        game.save();
        finish();
    }

}

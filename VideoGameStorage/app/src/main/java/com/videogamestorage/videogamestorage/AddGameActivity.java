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

public class AddGameActivity extends AppCompatActivity {

    EditText editTextVideoGame;
    CheckBox checkBoxCompletion;
    EditText editTextProgress;
    EditText editTextHours;
    EditText editTextReleaseYear;
    TextView time;
    int count=0;
    boolean stopTime = true;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        time = (TextView) findViewById(R.id.timer);
        editTextVideoGame = (EditText) findViewById(R.id.editTextVideoGame);
        checkBoxCompletion = (CheckBox) findViewById(R.id.checkBoxCompletion);
        editTextProgress = (EditText) findViewById(R.id.editTextProgress);
        editTextHours = (EditText) findViewById(R.id.editTextHours);
        editTextReleaseYear = (EditText) findViewById(R.id.editTextReleaseYear);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
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


    }

    public void submitButtonClick(View v) {
        stopTime = false;
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

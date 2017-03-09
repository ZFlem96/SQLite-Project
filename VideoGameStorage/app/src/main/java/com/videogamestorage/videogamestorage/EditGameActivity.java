package com.videogamestorage.videogamestorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.videogamestorage.videogamestorage.dbobjects.Game;
import com.videogamestorage.videogamestorage.orm.Time;

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
//    Time timer;
//    int secs=0, mins=0, hours=0;
    boolean stopTime = true;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       try{
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_edit_game);
           time = (TextView) findViewById(R.id.timer);
           Bundle extras = getIntent().getExtras();
           id = extras.getLong("gameId");
           game = Game.findById(Game.class, id);
           editTextVideoGame = (EditText) findViewById(R.id.editTextVideoGame);
           checkBoxCompletion = (CheckBox) findViewById(R.id.checkBoxCompletion);
           editTextProgress = (EditText) findViewById(R.id.editTextProgress);
           editTextHours = (EditText) findViewById(R.id.editTextHours);
           editTextReleaseYear = (EditText) findViewById(R.id.editTextReleaseYear);
           buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
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
//                            hours+"."+mins+"."secs;
//                            time.setText(String.valueOf(count+(" (s)")));
//                            int secs=0, mins=0, hours=0;
//                            AddGameActivity.getTimeFromList(id).getSecMinsHours(secs,mins,hours);
//                               time.setText(AddGameActivity.getTimeFromList(id).getHours()+"."+AddGameActivity.getTimeFromList(id).getMins()+"."+AddGameActivity.getTimeFromList(id).getSecs());
//                               AddGameActivity.getTimeFromList(id).increase();
//                               time.setText(game.getTime().getHours()+"."+game.getTime().getMins()+"."+game.getTime().getSecs());
//                               game.getTime().increase();
//                            secs++;
//                            if(secs==60){
//                                secs = 0 ;
//                                mins++;
//                            }
//                            if(mins==60){
//                                mins = 0 ;
//                                hours++;
//                            }
//                            AddGameActivity.getTimeFromList(id).setTime(secs,mins,hours);
//                            count++;
                           }
                       }
                   });
               }
           }, 1000, 1000);
           editTextVideoGame.setText(game.getVideoGame());
           checkBoxCompletion.setChecked(game.isComplete());
           editTextProgress.setText(Double.toString(game.getProgress()));
           editTextHours.setText(Double.toString(game.getTimeSpentPlaying()));
           editTextReleaseYear.setText(game.getReleaseYear());
       }catch (Exception e){
           e.printStackTrace();
       }


//        timer = game.getSecMinsHours(secs,mins,hours, timer);
//        game.getSecMinsHours(secs,mins,hours);

    }

    public void buttonDeleteClick(View v) {
        stopTime = false;
        game.delete();
        finish();
    }

    public void buttonUpdateClick(View v) {
        try{
            stopTime = false;
//        timer.setTime(secs,mins,hours);
//        game.setTime(secs,mins,hours,timer);
//        game.setTime(secs,mins,hours);
            game.setVideoGame(editTextVideoGame.getText().toString());
            game.setComplete(checkBoxCompletion.isChecked());
            game.setProgress(Double.parseDouble(editTextProgress.getText().toString()));
            game.setTimeSpentPlaying(Double.parseDouble(editTextHours.getText().toString()));
            game.setReleaseYear(editTextReleaseYear.getText().toString());
//            game.setTime(game.getTime().getSecs(),game.getTime().getMins(),game.getTime().getHours());
            game.save();
//            int secs=0, mins=0, hours=0;
//            AddGameActivity.getTimeFromList(id).getSecMinsHours(secs,mins,hours);
//            time.setText(hours+"."+mins+"."+secs);
            finish();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}

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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AddGameActivity extends AppCompatActivity {

    EditText editTextVideoGame;
    CheckBox checkBoxCompletion;
    EditText editTextProgress;
    EditText editTextHours;
    EditText editTextReleaseYear;
    TextView time;
    Time timer = new Time();
//    int count=0;
//    int secs=0, mins=0, hours=0;
    boolean stopTime = true;
    Button buttonSubmit;
    private static ArrayList<Time> timeList = new ArrayList<Time>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
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
//        int secs = 0,mins = 0, hours = 0;
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
                                time.setText(timer.getHours()+"."+timer.getMins()+"."+timer.getSecs());
//                            secs++;
//                            if(secs==60){
//                                secs = 0 ;
//                                mins++;
//                            }
//                            if(mins==60){
//                                mins = 0 ;
//                                hours++;
//                            }
//                            count++;
                                timer.increase();
                                timer.setTime(timer.getSecs(),timer.getMins(),timer.getHours());
                            }
                        }
                    });
                }
            }, 1000, 1000);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void submitButtonClick(View v) {
        try{
            stopTime = false;
            Game newGame = new Game();
//        timer = new Time();
//        timer.setTime(secs,mins,hours);
//        time.setText(hours+"."+mins+"."+secs);
//        newGame.setTime(secs,mins,hours);
//        time.setText(timer.getTime());
//        time.setText(newGame.getTime().getTime());
            newGame.setVideoGame(editTextVideoGame.getText().toString());
            newGame.setComplete(checkBoxCompletion.isChecked());
            newGame.setProgress(Double.parseDouble(editTextProgress.getText().toString()));
//            newGame.setTimeSpentPlaying(Double.parseDouble(editTextHours.getText().toString()));
            newGame.setReleaseYear(editTextReleaseYear.getText().toString());
            int sec=0,min=0,hours=0;
            timer.getSecMinsHours(sec,min,hours);
//            newGame.setTime(sec,min,hours);
            newGame.save();
//        int sec=0,min=0,hours=0;
//        timer.getSecMinsHours(sec,min,hours);
//        timer.setTime(sec,min,hours);
//        time.setText(hours+"."+min+"."+sec);
            timeList.add(timer);
            finish();
        }catch (Exception e){
e.printStackTrace();
        }

    }

    public static Time getTimeFromList(Long id){
        int num = id.intValue();
        return timeList.get(num);
    }

}

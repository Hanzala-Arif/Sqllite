package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity3 extends AppCompatActivity {
    TextView t21;
    int count;
    Timer timer;
    Handler handle= new Handler();
    String cou;
public class data extends AsyncTask<String,Void,String>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        try{
           Thread.sleep(9000);
           count++;
            cou=String.valueOf(count);
        }catch(Exception e){
            return "Failed";
        }
      return cou;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        t21.setText(s);
    }
}
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        data dt=new data();
        dt.execute();
        t21=(TextView) findViewById(R.id.t21);
    }
    protected void onStop(){
    super.onStop();
    }
}
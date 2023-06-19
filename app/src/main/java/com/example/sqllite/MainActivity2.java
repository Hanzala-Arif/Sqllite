package com.example.sqllite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private int count = 0;
    String cou;
    private TextView countTextView;

  //  private static final String STATE_COUNT = "state_count";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        countTextView = (TextView) findViewById(R.id.tt1);
        Button b1=(Button)findViewById(R.id.button);
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("saved");
        }
        countTextView.setText(String.valueOf(count));
//        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//        String name = sharedPreferences.getString("Name", "null")
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("saved",count);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cou= savedInstanceState.getString("saved");
        countTextView.setText(cou);
    }



    public void plus(View view) {
        count++;
        countTextView.setText(String.valueOf(count));
    }


}
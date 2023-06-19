package com.example.sqllite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
EditText t1,t2,t3;
Button b1,b2,b4;
RadioButton r1,r2;
 SharedPreferences sharedPreferences;
    //public static String PREFS_NAME="MyPrefsFile";
DatabaseReference ref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://appdata-a5c42-default-rtdb.firebaseio.com/");
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText) findViewById(R.id.t1);
        t2=(EditText) findViewById(R.id.t2);
r1=(RadioButton) findViewById(R.id.r1);
r2=(RadioButton) findViewById(R.id.r2);

b1=(Button)findViewById(R.id.b1);
b2=(Button)findViewById(R.id.b2);
        b4=(Button)findViewById(R.id.b4);
        b4.setEnabled(false);
    }


    public void insert(View view) {
       String T1=t1.getText().toString();
       String T2=t2.getText().toString();
       String R1=r1.getText().toString();
String R2=r2.getText().toString();

        ref.child("Books").child(T2).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    if (snapshot.getKey().equals(T2)) {
                        Toast.makeText(MainActivity.this, "Already exist", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                    ref.child("Books").child(T2).child("Book_Name").setValue(T1);
                    ref.child("Books").child(T2).child("Book_Number").setValue(T2);
                    ref.child("Books").child(T2).child("Name").setValue(R1);
                sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("Name",T1);
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity2.class));

//               else if(r2.isChecked()) {
//                    ref.child("Books").child(T2).child("Book_Name").setValue(T1);
//                    ref.child("Books").child(T2).child("Book_Number").setValue(T2);
//                    ref.child("Books").child(T2).child("Name").setValue(R2);
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putString("Name",T1);
//                    editor.apply();
//                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void delete(View view) {

        String T2=t2.getText().toString();

        ref.child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChild(T2)){
                    ref.child("Books").child(T2).removeValue();
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Search(View view) {
        String T2=t2.getText().toString();

        ref.child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChild(T2)){

                  String name=snapshot.child(T2).child("Book_Name").getValue(String.class);
                    // String author=snapshot.child(T2).child("Name").getValue(String.class);
                    String number=snapshot.child(T2).child("Book_Number").getValue(String.class);

                    t1.setText(name);
                    t2.setText(number);
//                    r1.setChecked(true);
                    b1.setEnabled(false);
                    b2.setEnabled(false);
                    b4.setEnabled(true);
                    Toast.makeText(MainActivity.this,"Searched successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void update(View view) {
        String name=t1.getText().toString();
        String number=t2.getText().toString();
        String author=t3.getText().toString();
        ref.child("Books").child(number).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             
                ref.child("Books").child(number).child("Book_Name").setValue(name);
                ref.child("Books").child(number).child("Book_Number").setValue(number);
                ref.child("Books").child(number).child("Author").setValue(author);
                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
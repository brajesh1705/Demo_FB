package com.example.brajeshkumar.demo_fb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FB extends AppCompatActivity {
    static int num=0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== Activity.RESULT_OK){
            Toast.makeText(this,"data Inserted",Toast.LENGTH_SHORT).show();
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb);

    }


    public void addfriend(View view) {
        Intent intent = new Intent(this, AddFriend.class);
        startActivityForResult(intent, 1);
    }
    public void displayfriend(View view) {
        Intent intent = new Intent(this, DisplayFriend.class);
        try{
            startActivity(intent);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

}

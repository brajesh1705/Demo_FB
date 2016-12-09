package com.example.brajeshkumar.demo_fb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class DisplayFriend extends AppCompatActivity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    Database database;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    List<FriendDetails> res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        database = new Database(getApplicationContext());
        sqLiteDatabase = database.getReadableDatabase();

        res = database. getAllData();
        listView = (ListView) findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(this,res);
        listView.setAdapter(listDataAdapter);
    }

}

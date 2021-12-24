package com.example.fpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        SQLiteOpenHelper helper = new SQLiteOpenHelper(this, "db.sqlite3", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            }
        };

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM history", null);

        EditText search = findViewById(R.id.textToSearch);

        ListView listView = findViewById(R.id.listView1);
        ArrayList<String> arrayList = new ArrayList<>();
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            arrayList.add(cur.getString(cur.getColumnIndexOrThrow("sentence")));
            cur.moveToNext();
        }
        cur.close();
//        for (int i = 0; i < 26; i++) {
//            arrayList.add((char)(i+65) + "");
//        }
//        arrayList.add("SPACE");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dictionary_row, R.id.textView, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(HistoryActivity.this, SentenceActivity.class);
                intent.putExtra("text", arrayList.get(position));
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.arrowBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
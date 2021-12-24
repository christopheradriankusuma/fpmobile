package com.example.fpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        EditText search = findViewById(R.id.textToSearch);

        ListView listView = findViewById(R.id.listView1);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            arrayList.add((char)(i+65) + "");
        }
        arrayList.add("SPACE");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dictionary_row, R.id.textView, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                System.out.println("Clicking " + position);
                int resId = getResources().getIdentifier(arrayList.get(position).toLowerCase(Locale.ROOT), "drawable", getPackageName());
                Intent intent = new Intent(ListActivity.this, WordActivity.class);
                intent.putExtra("title", "Dictionary");
                intent.putExtra("letter", arrayList.get(position));
                intent.putExtra("image", resId);
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
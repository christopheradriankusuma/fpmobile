package com.example.fpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SentenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Hello 1");
        arrayList.add("Hello 2");
        arrayList.add("Hello 3");
        arrayList.add("Hello 4");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, arrayList);
        ListView listView = findViewById(R.id.vertical_list);
        listView.setAdapter(arrayAdapter);

        Button backButton = findViewById(R.id.arrowBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
package com.example.fpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SentenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        ListView listView = findViewById(R.id.vertical_list);

        ArrayList<Item> arrayList = new ArrayList<>();
        for (int i = 65; i < 65 + 26; i++) {
            arrayList.add(new Item((char)i+""));
        }
        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), arrayList);
        listView.setAdapter(itemAdapter);

        Button backButton = findViewById(R.id.arrowBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
package com.example.fpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SentenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        Intent intent = getIntent();
        String text = intent.getStringExtra("text");

        ListView listView = findViewById(R.id.vertical_list);

        ArrayList<Item> arrayList = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            String drawable = (char)text.charAt(i) + "";
            drawable = drawable.toLowerCase(Locale.ROOT);
            if (drawable.equals(" ")) {
                drawable = "space";
            }
            int id = getResources().getIdentifier(drawable, "drawable", getPackageName());
            arrayList.add(new Item(drawable, id));
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
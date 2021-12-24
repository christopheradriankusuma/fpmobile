package com.example.fpmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, List<Item> itemList) {
        super(context, 0, itemList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        LinearLayout layout = convertView.findViewById(R.id.row);
        ImageView image = convertView.findViewById(R.id.image);
        TextView textView = convertView.findViewById(R.id.textView);

        String letter = item.getLetter();
        String imageName = item.getImageName();

        textView.setText(letter);

        layout.addView(convertView);

        return convertView;
    }
}

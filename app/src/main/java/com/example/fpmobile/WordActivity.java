package com.example.fpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class WordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        TextView titleView = findViewById(R.id.title);
        TextView letterView = findViewById(R.id.letter);
        ImageView imageView = findViewById(R.id.asl_picture);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String letter = intent.getStringExtra("letter");

        titleView.setText(title);
        letterView.setText(letter);
        Object param = intent.getExtras().get("image");
        if (param instanceof File) {
            File f = (File)param;
            try {
                Bitmap img = getBitmapFromUri(Uri.fromFile(f));
                ExifInterface exifInterface = new ExifInterface(f.getAbsolutePath());
                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        img = rotateImage(img, 90);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        img = rotateImage(img, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        img = rotateImage(img, 270);
                        break;

                    case ExifInterface.ORIENTATION_NORMAL:
                    default:
                        img = img;
                }
                imageView.setImageBitmap(img);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            int id = (int)param;
            imageView.setImageResource(id);
        }

        Button backButton = findViewById(R.id.arrowBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}
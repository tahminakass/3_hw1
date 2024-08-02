package com.example.a3_hw1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    ImageView image;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button = findViewById(R.id.btn_first);
        image = findViewById(R.id.img_ph);
        //openButton = findViewById(R.id.btn_second);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openGallery();
            }
        });

       // image.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {

            //    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            //    startActivity(intent);
            }
       // });

   // }
    private void openGallery() {
      Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
      photoPickerIntent.setType("image/*");
      startActivityForResult(photoPickerIntent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK && data != null && reqCode == REQUEST_CODE) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                image.setImageBitmap(selectedImage);
            } catch (Exception e) {
                System.out.println("Error");
            }

        }
    }


}
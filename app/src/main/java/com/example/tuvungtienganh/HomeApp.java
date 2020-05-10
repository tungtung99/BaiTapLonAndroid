package com.example.tuvungtienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeApp extends AppCompatActivity {
    ImageView imgwords,imgexercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);
        init();
        controlhome();
    }

    private void controlhome() {
        imgwords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeApp.this,Chude_tuvung.class);
                startActivity(intent);
            }
        });
        imgexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeApp.this,Tu_Vung.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        imgwords=findViewById(R.id.imgwords);
        imgexercise=findViewById(R.id.imgexercise);
    }
}

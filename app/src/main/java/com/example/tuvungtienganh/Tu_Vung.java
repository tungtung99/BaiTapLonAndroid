package com.example.tuvungtienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuvungtienganh.DataBase.CusTomAdapterTuVung;
import com.example.tuvungtienganh.Models.TuVung;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Tu_Vung extends AppCompatActivity {
    ListView lst;
    ImageView imgBack;
    DBHelper db;
//
//    public ArrayList<String> names = new ArrayList<>();
//    int[] images={R.drawable.tao,R.drawable.chuoi,R.drawable.nho};

    @Override
    protected void onStart() {
        super.onStart();
        db.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu__vung);

        lst = findViewById(R.id.lsttuvung);
        imgBack = findViewById(R.id.imgBackHome);

        db = new DBHelper(Tu_Vung.this);


//        Intent intentTuVung = getIntent();
//        Bundle chuDe = intentTuVung.getBundleExtra("data");
//        String tuVung = chuDe.getString("tuvung");
//        if(tuVung.equals("Dish"))
//        {
//             names.add("apple");
//            names.add("banana");
//            names.add("orange");
//        }
//        else
//        {
//            if(tuVung.equals("Number-Time"))
//            {
//                names.add("one");
//                names.add("two");
//                names.add("three");
//            }
//        }
        Cursor cursor = db.getAllRecord();
        CusTomAdapterTuVung adapter=new CusTomAdapterTuVung(Tu_Vung.this,getContent1(cursor));
        lst.setAdapter(adapter);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tu_Vung.this, HomeApp.class);
                startActivity(intent);
            }
        });
        
    }

    public ArrayList<TuVung> getContent1(Cursor cursor)
    {
        ArrayList<TuVung> contents = new ArrayList<TuVung>();
        TuVung p;
        while (cursor.moveToNext())
        {
            p=new TuVung(Integer.parseInt(String.valueOf(cursor.getColumnIndex("id"))),String.valueOf(cursor.getColumnIndex("tu")),String.valueOf(cursor.getColumnIndex("nghia")),String.valueOf(cursor.getColumnIndex("vidu")),String.valueOf(cursor.getColumnIndex("anh")));
            contents.add(p);
        }
        return contents;
    }

}

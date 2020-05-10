package com.example.tuvungtienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Chude_tuvung extends AppCompatActivity {
    ListView listViewTuVung;

    String[] names={"Animals","Dish","Number-Time"};
    int[] images={R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chude_tuvung);

        listViewTuVung = (ListView) findViewById(R.id.listviewtuvung);
        final CustomAdapter adapter=new CustomAdapter(Chude_tuvung.this,getWords());
        listViewTuVung.setAdapter(adapter);
    }
    public ArrayList<Words> getWords()
    {
        ArrayList<Words> words = new ArrayList<Words>();
        Words p;

        for(int i=0;i< names.length;i++)
        {
            p=new Words(names[i],images[i]);
            words.add(p);
        }
        return words;
    }
}

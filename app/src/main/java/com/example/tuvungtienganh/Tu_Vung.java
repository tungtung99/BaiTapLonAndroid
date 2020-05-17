package com.example.tuvungtienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Tu_Vung extends AppCompatActivity {
    ListView listViewTuVung;

    String[] names={"Apple","Banana","grapes"};
    int[] images={R.drawable.tao,R.drawable.chuoi,R.drawable.nho};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chude_tuvung);

        listViewTuVung = (ListView) findViewById(R.id.listviewchude);
        final CustomAdapter adapter=new CustomAdapter(Tu_Vung.this,getContent());
        listViewTuVung.setAdapter(adapter);
        
    }

    public ArrayList<Content> getContent()
    {
        ArrayList<Content> contents = new ArrayList<Content>();
        Content p;

        for(int i=0;i< names.length;i++)
        {
            p=new Content(names[i],images[i]);
            contents.add(p);
        }
        return contents;
    }

}

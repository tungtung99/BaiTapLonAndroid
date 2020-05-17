package com.example.tuvungtienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Chude_tuvung extends AppCompatActivity {
    ListView listViewChuDe;

    String[] names={"Animals","Number-Time","Dish"};
    int[] images={R.drawable.b,R.drawable.a,R.drawable.c};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chude_tuvung);

        listViewChuDe = (ListView) findViewById(R.id.listviewchude);
        final CustomAdapter adapter=new CustomAdapter(Chude_tuvung.this,getContent());
        listViewChuDe.setAdapter(adapter);
        controlcontent();
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
    public void controlcontent(){
        listViewChuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Chude_tuvung.this,Tu_Vung.class);
                startActivity(intent);
            }
        });
    }
}

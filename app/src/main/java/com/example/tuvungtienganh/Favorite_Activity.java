package com.example.tuvungtienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tuvungtienganh.DataBase.CusTomAdapterTuVung;
import com.example.tuvungtienganh.Models.TuVung;

import java.util.ArrayList;

public class Favorite_Activity extends AppCompatActivity {
    ImageView imgback;
    ListView lstuathich;
    DBHelper dbTu;
    public ArrayList<TuVung> lstTu;
    @Override
    protected void onStart() {
        super.onStart();
        dbTu.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbTu.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_);
        imgback=findViewById(R.id.imgBackHome2);
        lstuathich=findViewById(R.id.lstuathich);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Favorite_Activity.this, HomeApp.class);
                startActivity(intent);
            }
        });
        dbTu = new DBHelper(Favorite_Activity.this);
        Cursor cursor123 = dbTu.getReadableDatabase().rawQuery("SELECT * FROM TuVung1 where idlove = 1",null);
        lstTu = getContent1(cursor123);
        CusTomAdapterTuVung adapter1=new CusTomAdapterTuVung(Favorite_Activity.this,lstTu);
        lstuathich.setAdapter(adapter1);
        controlbtn();
    }

    private void controlbtn() {
        lstuathich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Favorite_Activity.this,word.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",lstTu.get(position).getId());
                bundle.putString("tu",lstTu.get(position).getTuVung());
                bundle.putString("nghia",lstTu.get(position).getNghia());
                bundle.putString("vidu",lstTu.get(position).getViDu());
                bundle.putInt("machuyentrang",2);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });
    }

    public ArrayList<TuVung> getContent1(Cursor cursor)
    {
        ArrayList<TuVung> contents = new ArrayList<TuVung>();
        while (cursor.moveToNext())
        {
            contents.add(new TuVung(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getBlob(5),cursor.getInt(6)));
        }
        return contents;
    }
}

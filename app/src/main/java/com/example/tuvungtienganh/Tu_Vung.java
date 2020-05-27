package com.example.tuvungtienganh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuvungtienganh.DataBase.CusTomAdapterTuVung;
import com.example.tuvungtienganh.Golobal.Golobal;
import com.example.tuvungtienganh.Models.TuVung;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Tu_Vung extends AppCompatActivity {
    ListView lst;
    ImageView imgBack;
    DBHelper db;
    Bundle bundleTuVung;
    DBHelperChuDe dbHelperChuDe;
    ArrayList<TuVung> lstTuVung;
    @Override
    protected void onStart() {
        super.onStart();
        db.openDB();
        dbHelperChuDe.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.closeDB();
        dbHelperChuDe.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu__vung);

        lst = findViewById(R.id.lsttuvung);
        imgBack = findViewById(R.id.imgBackHome);

        db = new DBHelper(Tu_Vung.this);
        dbHelperChuDe = new DBHelperChuDe(this);
        Intent intentTuvung = getIntent();
        bundleTuVung = intentTuvung.getBundleExtra("data");


//        String query = "SELECT tuvung1.* FROM TuVung1,Chude WHERE TuVung1.idChuDe = Chude._idChuDe and Chude.name = ' " + chude + "'";
//
//        Cursor cursor123 = db.getWritableDatabase().rawQuery(query,null);
        lstTuVung = getContent1();
        CusTomAdapterTuVung adapter=new CusTomAdapterTuVung(Tu_Vung.this,lstTuVung);
        lst.setAdapter(adapter);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tu_Vung.this, HomeApp.class);
                startActivity(intent);
            }
        });
        controlcontent();
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tu_Vung.this,insertTuvung.class);
                startActivity(intent);
            }
        });
    }

    private void controlcontent() {
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(Tu_Vung.this,word.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",lstTuVung.get(position).getId());
                bundle.putString("tu",lstTuVung.get(position).getTuVung());
                bundle.putString("nghia",lstTuVung.get(position).getNghia());
                bundle.putString("vidu",lstTuVung.get(position).getViDu());
               // bundle.putByteArray("anh",lstTuVung.get(position).getAnh());
                bundle.putInt("machuyentrang",1);
                intent1.putExtra("data",bundle);
                Golobal.setChude(bundleTuVung.getString("chude"));
                startActivity(intent1);
            }
        });
    }

    public ArrayList<TuVung> getContent1()
    {

        ArrayList<TuVung> contents = new ArrayList<TuVung>();
        int idChude = 0;
        String selectIdChude = "select _idchude from ChuDe where name  = '" + bundleTuVung.getString("chude") + "'";
        Cursor cursor123 = dbHelperChuDe.getWritableDatabase().rawQuery(selectIdChude,null);
        while (cursor123.moveToNext())
        {
            idChude = cursor123.getInt(0);
        }
        String selectTuVung = "select * from tuvung1 where idchude = " + idChude;
        Cursor cursor = db.getWritableDatabase().rawQuery(selectTuVung,null);
        while (cursor.moveToNext())
        {
            contents.add(new TuVung(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getBlob(5),cursor.getInt(6)));
        }
        return contents;
    }

}

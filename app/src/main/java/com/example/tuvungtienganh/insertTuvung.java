package com.example.tuvungtienganh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tuvungtienganh.Golobal.Golobal;
import com.example.tuvungtienganh.Models.ChuDe;
import com.example.tuvungtienganh.Models.TuVung;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class insertTuvung extends AppCompatActivity {
    EditText editTextTu,editTextNghia,editTextVidu;
    Button btnInsert,btnHuy;
    Spinner spTenChuDe;
    DBHelperChuDe dbchude;
    DBHelper dbTu;

    ImageView imageViewinsertanh;
    int idchude;
    public ArrayList<ChuDe> lstChude;
    public ArrayList<TuVung> lstTu;
    public InputStream inputStream;
    public Bitmap bitmap;
    Uri imageUri;
    private static final int PICK_IMAGE = 222;

    @Override
    protected void onStart() {
        super.onStart();
        dbchude.openDB();
        dbTu.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbchude.closeDB();
        dbTu.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_tuvung);

        dbchude = new DBHelperChuDe(insertTuvung.this);
        Cursor cursor12 = dbchude.getReadableDatabase().rawQuery("select * from ChuDe",null);
        lstChude = getContent(cursor12);
        spTenChuDe = findViewById(R.id.spinnertenChude);
        List<String> list = new ArrayList<String>();
        for (int i = 0 ; i< lstChude.size();i++)
        {
            list.add(lstChude.get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTenChuDe.setAdapter(dataAdapter);

        editTextTu=findViewById(R.id.editTextTu);
        imageViewinsertanh = findViewById(R.id.imageViewinsertanh);
        editTextNghia=findViewById(R.id.editTextNghia);
        editTextVidu=findViewById(R.id.editTextVidu);
        btnInsert=findViewById(R.id.btnInsertTuvung);
        btnHuy=findViewById(R.id.btnCancleTuvung);
        dbTu = new DBHelper(insertTuvung.this);
        Cursor cursor123 = dbchude.getReadableDatabase().rawQuery("SELECT * FROM TuVung1",null);
        lstTu = getContent1(cursor123);

        imageViewinsertanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPicture();
            }
        });

        controlbutton();
    }



    private void controlbutton() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbTu.getWritableDatabase().rawQuery("select max(_id) from TuVung1",null);
                int maxid = 0;
                while (cursor.moveToNext())
                {
                    maxid = cursor.getInt(0);
                }
                for (int i =0;i<lstChude.size();i++)
                {
                    if (lstChude.get(i).getName()==String.valueOf(spTenChuDe.getSelectedItem()))
                    {
                        idchude=lstChude.get(i).getIdChuDe();
                    }
                }
                String tu=editTextTu.getText().toString();
                String nghia=editTextNghia.getText().toString();
                String vidu=editTextVidu.getText().toString();
                long resultAdd= dbTu.Insert(maxid +1,idchude,tu,nghia,vidu,imageViewAnhtoByte(imageViewinsertanh),0);
                if (resultAdd==-1){
                    Toast.makeText(insertTuvung.this,"Error",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(insertTuvung.this,"Successfully insertted",Toast.LENGTH_SHORT).show();
                }
                Bundle bundle = new Bundle();
                bundle.putString("chude", Golobal.getChude());
                Intent intent = new Intent(insertTuvung.this,Tu_Vung.class);
                intent.putExtra("data",bundle);
                startActivity(intent);
                Golobal.setChude("");
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("chude", Golobal.getChude());
                Intent intent = new Intent(insertTuvung.this,Tu_Vung.class);
                intent.putExtra("data",bundle);
                startActivity(intent);
                Golobal.setChude("");
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
    public ArrayList<ChuDe> getContent(Cursor cursor)
    {
        ArrayList<ChuDe> contents = new ArrayList<ChuDe>();
        while (cursor.moveToNext())
        {
            contents.add(new ChuDe(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getBlob(2)));
        }
        return contents;
    }

    public void openPicture()
    {
        Intent openPicture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(openPicture,PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data.getData();

            try {
                inputStream = getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewinsertanh.setImageBitmap(bitmap);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }


    private byte[] imageViewAnhtoByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}

package com.example.tuvungtienganh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tuvungtienganh.Golobal.Golobal;
import com.example.tuvungtienganh.Models.ChuDe;
import com.example.tuvungtienganh.Models.TuVung;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class word extends AppCompatActivity {
    int[] img = {R.drawable.ic_favorite_black_24dp,R.drawable.ic_favorite1_black_24dp};
    public Bundle bundlewords;
    TextView textViewtu,textViewnghia,textViewvidu;
    Button btnXoa,btnSua,btnUpdate,btnCancle;
    ImageView imgback,imguathich,imgtuvung,imgupdateanh;
    EditText edTu,edNghia,edVidu;
    Spinner spTenChuDe;
    String tu,nghia,vidu,dk;
    DBHelperChuDe dbchude1;
    DBHelper dbTu1;
    public ArrayList<ChuDe> lstChude;
    public ArrayList<TuVung> lstTu;
    int id1,id,machuyentrang,idchude;
    byte[] anh;
    Bitmap bmp;
    public InputStream inputStream;
    public Bitmap bitmap;
    Uri imageUri;
    private static final int PICK_IMAGE = 222;
    @Override
    protected void onStart() {
        super.onStart();
        dbchude1.openDB();
        dbTu1.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbchude1.closeDB();
        dbTu1.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        textViewtu=findViewById(R.id.textviewTu);
        textViewnghia=findViewById(R.id.textviewNghia);
        textViewvidu=findViewById(R.id.textviewvidu);
        imgtuvung = findViewById(R.id.imgtuvung);

        btnSua=findViewById(R.id.btnSua);
        btnXoa=findViewById(R.id.btnXoa);
        imgback=findViewById(R.id.imgBackHome1);
        imguathich=findViewById(R.id.imageViewuathich);
        dbchude1 = new DBHelperChuDe(this);
        dbTu1 = new DBHelper(this);

        Intent intentwords = getIntent();
        bundlewords = intentwords.getBundleExtra("data");
        id=bundlewords.getInt("id");
        tu = bundlewords.getString("tu");
        nghia = bundlewords.getString("nghia");
        vidu = bundlewords.getString("vidu");
        //anh = bundlewords.getByteArray("anh");
        machuyentrang=bundlewords.getInt("machuyentrang");

        textViewtu.setText(tu);
        textViewnghia.setText(nghia);
        textViewvidu.setText(vidu);
//        Bitmap bmp1 = BitmapFactory.decodeByteArray(bundlewords.getByteArray("anh"), 0, bundlewords.getByteArray("anh").length);
//        imgtuvung.setImageBitmap(bmp1);
        String query = "select idlove from tuvung1 where _id = " + id;
        Cursor cursor = dbTu1.getWritableDatabase().rawQuery(query,null);
        int checkIdLove = 0;
        while (cursor.moveToNext()){
            checkIdLove = cursor.getInt(0);
        }

        if(checkIdLove == 0)
        {
            imguathich.setImageResource(img[0]);

        }
        else
        {
            if(checkIdLove == 1)
            {
                imguathich.setImageResource(img[1]);
            }
        }

        controlbutton();
    }

    private void controlbutton() {
        imguathich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "select idlove from tuvung1 where _id = " + id;
                Cursor cursor = dbTu1.getWritableDatabase().rawQuery(query,null);
                int checkIdLove = 0;
                while (cursor.moveToNext()){
                    checkIdLove = cursor.getInt(0);
                }
                if(checkIdLove == 0)
                {
                    imguathich.setImageResource(img[1]);
                    long resultUpdate= dbTu1.UpdateIdlove(id,1);
                    if (resultUpdate==0){
                        Toast.makeText(word.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                    else if (resultUpdate==1){
                        Toast.makeText(word.this,"Love",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(word.this,"Error,multiple records updates",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    if(checkIdLove == 1)
                    {
                        imguathich.setImageResource(img[0]);
                        long resultUpdate= dbTu1.UpdateIdlove(id,0);
                        if (resultUpdate==0){
                            Toast.makeText(word.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                        else if (resultUpdate==1){
                            Toast.makeText(word.this,"UnLove",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(word.this,"Error,multiple records updates",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (machuyentrang==1){
                    Bundle bundle = new Bundle();
                    bundle.putString("chude", Golobal.getChude());
                    Intent intent = new Intent(word.this,Tu_Vung.class);
                    intent.putExtra("data",bundle);
                    startActivity(intent);
                    Golobal.setChude("");
//                }
//                else{
//                    startActivity(new Intent(word.this,Favorite_Activity.class));
//                }


            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(word.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.layoutupdatetuvung);
                dialog.setTitle("Sửa Từ Vựng");
                edTu=dialog.findViewById(R.id.editTextTu2);
                edNghia=dialog.findViewById(R.id.editTextNghia2);
                edVidu=dialog.findViewById(R.id.editTextVidu2);
                imgupdateanh=dialog.findViewById(R.id.imageViewupdateanh);
                imgupdateanh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPicture();
                    }
                });
                edTu.setText(tu);
                edNghia.setText(nghia);
                edVidu.setText(vidu);
                dk=edTu.getText().toString();

                spTenChuDe=dialog.findViewById(R.id.spinnertenChude2);
                btnUpdate=dialog.findViewById(R.id.btnUpdateTuvung2);
                btnCancle=dialog.findViewById(R.id.btnCancleTuvung2);


                Cursor cursor12 = dbchude1.getReadableDatabase().rawQuery("select * from ChuDe",null);
                lstChude = getContent(cursor12);
                List<String> list = new ArrayList<String>();
                for (int i = 0 ; i< lstChude.size();i++)
                {
                    list.add(lstChude.get(i).getName());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(word.this, android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTenChuDe.setAdapter(dataAdapter);

                Cursor cursor123 = dbTu1.getReadableDatabase().rawQuery("SELECT * FROM TuVung1",null);
                lstTu = getContent1(cursor123);
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i =0;i<lstChude.size();i++)
                        {
                            if (lstChude.get(i).getName()==String.valueOf(spTenChuDe.getSelectedItem()))
                            {
                                idchude=lstChude.get(i).getIdChuDe();
                            }
                        }
                        for (int i =0 ;i<lstTu.size();i++){
                            if(lstTu.get(i).getTuVung().equals(dk)){
                                id1=lstTu.get(i).getId();
                            }
                        }
                        String tu1,nghia1,vidu1;
                        tu1=edTu.getText().toString();
                        nghia1=edNghia.getText().toString();
                        vidu1=edVidu.getText().toString();
                        long resultUpdate= dbTu1.Update(id1,idchude,tu1,nghia1,vidu1,imageViewAnhtoByte(imgupdateanh),0);
                        if (resultUpdate==0){
                            Toast.makeText(word.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                        else if (resultUpdate==1){
                            Toast.makeText(word.this,"Successfully update",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(word.this,"Error,multiple records updates",Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();
                    }
                });
                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long resultDelete=dbTu1.Delete(id);
                if (resultDelete==0){
                    Toast.makeText(word.this,"Error",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(word.this,"Data is successfully deleted",Toast.LENGTH_SHORT).show();
                }
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
                imgupdateanh.setImageBitmap(bitmap);
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

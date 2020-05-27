package com.example.tuvungtienganh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tuvungtienganh.DataBase.CusTomAdapterTuVung;
import com.example.tuvungtienganh.Models.ChuDe;
import com.example.tuvungtienganh.Models.TuVung;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class HomeApp extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    ListView listViewChuDe;
    DBHelperChuDe db;
    String id,name;
    public ArrayList<ChuDe> lstChude;
    public InputStream inputStream;
    public Bitmap bitmap;
    ImageView imginsertchude;
    public Uri imageUri;
    private static final int PICK_IMAGE = 222;
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
    //String[] names={"animals","number","plaints"};
    //int[] images={R.drawable.b,R.drawable.a,R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        db = new DBHelperChuDe(HomeApp.this);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeApp.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                UserMenuSelected(menuItem);
                return false;
            }
        });
        listViewChuDe = (ListView) findViewById(R.id.listviewchude);

        controlcontent();

        Cursor cursor12 = db.getReadableDatabase().rawQuery("select * from ChuDe",null);
        lstChude = getContent(cursor12);
        CustomAdapter adapter1=new CustomAdapter(HomeApp.this,lstChude);
        listViewChuDe.setAdapter(adapter1);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.getWritableDatabase().rawQuery("select max(_idchude) from ChuDe",null);
                int maxid = 0;
                while (cursor.moveToNext())
                {
                    maxid = cursor.getInt(0);
                }
                final Dialog dialog = new Dialog(HomeApp.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.layoutinsertchude);
                dialog.setTitle("Thêm Chủ Đề");

                final EditText edName=dialog.findViewById(R.id.editTextName);
                imginsertchude=dialog.findViewById(R.id.imageViewinsertchude);

                imginsertchude.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPicture();
                    }
                });

                Button btnHuy=dialog.findViewById(R.id.btnCancle);
                Button btnInsert=dialog.findViewById(R.id.btnInsert);
                final int finalMaxid = maxid;
                btnInsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = edName.getText().toString().trim();
                        long resultAdd= db.Insert(finalMaxid +1,name,imageViewAnhtoByte(imginsertchude));
                        if (resultAdd==-1){
                            Toast.makeText(HomeApp.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(HomeApp.this,"Successfully insertted",Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void UserMenuSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(this,HomeApp.class));
                break;
            case R.id.nav_quiz:

                break;
            case R.id.nav_favorites:
                startActivity(new Intent(this,Favorite_Activity.class));
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_logout:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    public void controlcontent(){
        listViewChuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeApp.this,Tu_Vung.class);
                Bundle bundle = new Bundle();
                bundle.putString("chude",lstChude.get(i).getName());
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });

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
                imginsertchude.setImageBitmap(bitmap);
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

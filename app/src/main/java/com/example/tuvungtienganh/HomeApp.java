package com.example.tuvungtienganh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeApp extends AppCompatActivity {
    ImageView imgwords,imgexercise;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    ListView listViewChuDe;
    Button btn;

    String[] names={"Animals","Number-Time","Dish"};
    int[] images={R.drawable.b,R.drawable.a,R.drawable.c};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeApp.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = findViewById(R.id.navigation_view);
        //View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                UserMenuSelected(menuItem);
                return false;
            }
        });
        listViewChuDe = (ListView) findViewById(R.id.listviewchude);
        final CustomAdapter adapter=new CustomAdapter(HomeApp.this,getContent());
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
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_about:
                break;
        }
    }

    public void controlcontent(){
        listViewChuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeApp.this,Tu_Vung.class);
                Bundle bundle = new Bundle();
                bundle.putString("tuvung",names[i]);
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });
    }
}

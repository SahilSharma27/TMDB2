package com.example.android.tmdb2;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView textView;
    FavoritesDAO favoritesDAO;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Intent intent=getIntent();
        bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

       // textView=findViewById(R.id.id);
//        FavoritesDatabase database = Room.databaseBuilder(this,FavoritesDatabase.class,"favorites_db").allowMainThreadQueries().build();
//        favoritesDAO = database.getFavoriteDao();
//
//        List<Favorites> fav=favoritesDAO.getFavorites();
//        if(fav.size()!=0) {
//            long s = fav.get(0).getId();
//          //  textView.setText(s + "");
//        }
//        else {
//          //  textView.setText("No favorites added");
//        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        int id=item.getItemId();
        if(id==R.id.MoviesMenu){
            MoviesFAVFragment moviesFAVFragment=new MoviesFAVFragment();
            transaction.replace(R.id.container1,moviesFAVFragment);
        }
        else if(id==R.id.TVMenu){
            TVFAVFragment tvfavFragment=new TVFAVFragment();
            transaction.replace(R.id.container1,tvfavFragment);

        }
        transaction.commit();
        return true;
    }


}

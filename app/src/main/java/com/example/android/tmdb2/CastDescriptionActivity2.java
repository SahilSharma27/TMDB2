package com.example.android.tmdb2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastDescriptionActivity2 extends AppCompatActivity {
    long personId;
    Person obj;
    ImageView imageView;
    TextView textView;
    String baseurl="http://image.tmdb.org/t/p/w780";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_description2);
        imageView=findViewById(R.id.personImage);
        textView=findViewById(R.id.biography);
        Intent intent=getIntent();
        personId=intent.getLongExtra("castId",0);
        //   Toast.makeText(this,a+" ",Toast.LENGTH_LONG).show();
        fetchdata();


    }
    void fetchdata(){
        Call<Person> call = ApiClient.getMovieTVServices().getPersonDetails(personId);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.d("Url generated",response.toString());
                obj = response.body();
                Picasso.get().load(baseurl+obj.getProfilePath()).into(imageView);
                textView.setText(obj.getBiography());

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });
    }
}

package com.example.aakash.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);

        Interface interface2 = ServiceGenerator.createService(Interface.class);
        Call<Example> call = interface2.requestResponse("London,uk", "b1b15e88fa797225412429c1c50c122a1");
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.code() == 200) {
                    Example pojo = response.body();
                    Log.e("Coord_Lat", String.valueOf(pojo.getCoord().getLat()));
                    textView.setText(String.valueOf(pojo.getCoord().getLat()));
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
            }
        });
    }

}

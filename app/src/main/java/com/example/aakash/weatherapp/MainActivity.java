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

        final TextView temp = (TextView) findViewById(R.id.temp);
        final TextView city = (TextView) findViewById(R.id.city);
        final TextView vis = (TextView) findViewById(R.id.vis);
        final TextView hum = (TextView) findViewById(R.id.hum);
        final TextView wind = (TextView) findViewById(R.id.wind);
        final TextView pres = (TextView) findViewById(R.id.pres);

        Interface interface2 = ServiceGenerator.createService(Interface.class);
        Call<Example> call = interface2.requestResponse("London,uk", "b1b15e88fa797225412429c1c50c122a1");
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.code() == 200) {
                    Example pojo = response.body();
                    Log.e("weather", String.valueOf(pojo.getVisibility()));
                    Log.e("wind", String.valueOf(pojo.getWind().getSpeed()));
                    Log.e("Coord_Lat", String.valueOf(pojo.getCoord().getLat()));
                    city.setText(String.valueOf(pojo.getName()));
                    temp.setText(String.valueOf(pojo.getMain().getTemp()));

                    vis.setText("Visibility: " + String.valueOf(pojo.getVisibility()));
                    hum.setText("Humidity: " + String.valueOf(pojo.getMain().getHumidity()));

                    wind.setText("Wind Speed: " + String.valueOf(pojo.getWind().getSpeed()));
                    pres.setText("Pressure: " + String.valueOf(pojo.getMain().getPressure()));
                    Log.e("wind", String.valueOf(pojo.getSys().getSunrise()));

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
            }
        });
    }

}

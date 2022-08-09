package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TextView[] textView = new TextView[1];
        Retrofit retrofit;
        RetrofitAPI retrofitApi;
        Call<List<UserInfo>> call;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/").addConverterFactory(GsonConverterFactory.create()).build();

        retrofitApi = retrofit.create(RetrofitAPI.class);
        call = retrofitApi.getMember();
        call.enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                if(response.isSuccessful()){
                    textView[0] = (TextView) findViewById(R.id.txt_json);

                    textView[0].setText(response.body().toString());
                }else{
                    Log.e("MainActivity","response but fail");
                }
            }

            @Override
            public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                Log.e("MainActivity!!!", "fail");
                t.printStackTrace();
            }
        });

    }
}
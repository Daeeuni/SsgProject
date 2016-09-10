package com.example.win7_64.ssg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity  {

    public DefaultRestClient<API_Service> restClient;
    public API_Service apiService;
    public Model model;
    public String ssg_cnt;
    public TextView ssg_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ssg_show = (TextView) findViewById(R.id.ssg_num);


        restClient = new DefaultRestClient<>();
        apiService = restClient.getClient(API_Service.class);

        Call<Model> call = apiService.getSsg_count();

        call.enqueue(new Callback<Model>(){
            @Override
            public void onResponse(Call<Model> call, Response <Model> response)
            {
                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"연결!!!!!", Toast.LENGTH_SHORT).show();//호출성공
                    model = response.body();
                    ssg_cnt=response.body().getSsg_count();
                    ssg_show.setText("쓱~~~"+ ssg_cnt);
                }
                else{
                    Toast.makeText(getApplicationContext(),"연결실패1", Toast.LENGTH_SHORT).show(); //호출실패
                }
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t){
                Toast.makeText(getApplicationContext(),"연결실패2", Toast.LENGTH_SHORT).show();
            }

        });


    }






}

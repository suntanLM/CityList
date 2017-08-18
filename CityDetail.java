package com.example.tantan.city;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

public class CityDetail extends AppCompatActivity {
    private ImageView ivimg;
    private TextView tvname,tvsum;
    private String strID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        initview();
        getdatafromintent();
        getJSONdata();
    }

    private void getJSONdata() {
        Ion.with(this)
                .load("https://vinhnq.000webhostapp.com/student_manage/getDetailCity.php?cityID="+strID)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(result!= null && !result.equals("")){
                            JSONArray jsonArray = null;
                            JSONObject jsonObject = null;
                            try {
                                jsonArray = new JSONArray(result);
                                if(jsonArray!= null && jsonArray.length()>0){
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        jsonObject = jsonArray.getJSONObject(i);
                                        String sumary = jsonObject.getString("sumary");
                                        String name = jsonObject.getString("name");
                                        String image = jsonObject.getString("image");
                                        tvname.setText(name);
                                        tvsum.setText(sumary);
                                        Ion.with(ivimg)
                                                .placeholder(R.mipmap.ic_launcher)
                                                .error(R.mipmap.ic_launcher)
                                                .load(image);
                                    }
                                }
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }


    private void getdatafromintent() {
        strID=getIntent().getStringExtra("ID");
    }

    private void initview() {
        ivimg=(ImageView)findViewById(R.id.iv_detail);
        tvname=(TextView)findViewById(R.id.tv_detailname);
        tvsum=(TextView)findViewById(R.id.tv_detailsum);
    }

    
}

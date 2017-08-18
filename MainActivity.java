package com.example.tantan.city;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<CityInfo> CTlist = new ArrayList<CityInfo>();
    private ListView lvmain;
    private CTAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getJSONdata();
        initview();
    }

    private void getJSONdata() {
        Ion.with(this)
                .load("https://vinhnq.000webhostapp.com/student_manage/getListCity.php")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        String strJson = result;
                        Log.d("TAG","Result = "+result);
                        parseJSONarr(strJson);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void parseJSONarr(String strJson) {
        JSONArray array = null;
        try{
            array = new JSONArray(strJson);
            for(int i =0;i<array.length();i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String img = jsonObject.getString("image");
                CityInfo ct = new CityInfo(id, name, img, "");
                CTlist.add(ct);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initview() {
        lvmain = (ListView) findViewById(R.id.lv_main);
        adapter = new CTAdapter(CTlist,this);
        lvmain.setAdapter(adapter);
        lvmain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityInfo cityInfo = CTlist.get(position);
                Intent intent = new Intent(MainActivity.this,CityDetail.class);
                String cityID = cityInfo.getId();
                intent.putExtra("ID",cityID);
                startActivity(intent);
            }
        });
    }


}

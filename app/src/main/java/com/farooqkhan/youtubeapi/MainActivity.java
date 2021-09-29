package com.farooqkhan.youtubeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.farooqkhan.youtubeapi.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//   RecyclerView recyclerView;
    VideoAdapter adapter;
    ActivityMainBinding binding;
    ArrayList<YoutubeModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        recyclerView = findViewById(R.id.recyclerview);
        adapter = new VideoAdapter(MainActivity.this,list);

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(adapter);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//       recyclerView.setLayoutManager(layoutManager);
//       recyclerView.setAdapter(adapter);
       fetchdata();
    }

    private void fetchdata(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCJihyK0A38SZ6SdJirEdIOw&maxResults=30&key=AIzaSyCacnxQgpd8j08hJ2pNB8jJH7NwP3gvZMc",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("items");
//                            Log.i("msg","len "+jsonArray.length());
//                            list.clear();
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                JSONObject jsonvideoid = jsonObject1.getJSONObject("id");
//                                Log.i("msg","id "+jsonArray.getJSONObject(i));
                                JSONObject jsonsnippet = jsonObject1.getJSONObject("snippet");
                                JSONObject jsonthumbnail = jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");

                                YoutubeModel model = new YoutubeModel();
                               if(i!=1 && i!=2){
                                   model.setVideoId(jsonvideoid.getString("videoId"));
//                                   Log.i("msg","Id "+jsonvideoid.getString("videoId"));
                                   model.setTitle(jsonsnippet.getString("title"));
                                   model.setUrl(jsonthumbnail.getString("url"));
                                   list.add(model);
                               }


                            }
                            if(list.size()>0){
                                adapter.notifyDataSetChanged();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error!!",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);

    }

}
package com.example.ct2vallay;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(postAdapter);

        // Установка слушателя для нажатий на элементы списка
        postAdapter.setOnItemClickListener(post -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("title", post.getTitle());
            intent.putExtra("body", post.getBody());
            startActivity(intent);
        });

        fetchData();
    }

    private void fetchData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                response -> {
                    postList.clear();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject postJson = response.getJSONObject(i);
                            Post post = new Post(
                                    postJson.getInt("id"),
                                    postJson.getString("title"),
                                    postJson.getString("body")
                            );
                            postList.add(post);
                        }
                        postAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> error.printStackTrace()
        );

        requestQueue.add(jsonArrayRequest);
    }
}


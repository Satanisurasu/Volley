package com.example.ct2vallay;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ct2vallay.R;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList = new ArrayList<>();
    private Button button;
    private static final String TAG = "MainActivity";
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(postAdapter);

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> fetchData());
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
                        Log.e(TAG, "Error parsing JSON", e);
                    }
                },
                error -> Log.e(TAG, "Volley error", error)
        );

        requestQueue.add(jsonArrayRequest);
    }
}

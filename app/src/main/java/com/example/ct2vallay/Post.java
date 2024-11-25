package com.example.ct2vallay;
import androidx.annotation.NonNull;


public class Post {
    private int id;
    private String title;
    private String body;

    public Post(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @NonNull
    @Override
    public String toString() {
        return "Post " + id + ":\nTitle: " + title + "\nBody: " + body;
    }
}

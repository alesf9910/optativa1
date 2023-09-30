package com.example.myapplication.services;

import com.example.myapplication.models.Comment;
import com.example.myapplication.models.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface JphApi {
    @GET("posts/")
    Call<ArrayList<Post>> GetPosts();
    @GET("comments/")
    Call<ArrayList<Comment>> GetComments(@Query("postId") int idPost);
}

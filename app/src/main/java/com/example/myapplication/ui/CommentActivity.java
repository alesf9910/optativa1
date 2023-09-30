package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.DependencyLocator;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.models.Comment;
import com.example.myapplication.models.CommentAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {
    RecyclerView listComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        listComment = (RecyclerView) findViewById(R.id.listComment);
        listComment.setLayoutManager(new LinearLayoutManager(this));
        int idPost = getIntent().getIntExtra("idPost", -1);
        if(idPost != -1)GetComments(idPost);
    }

    private void GetComments(int idPost){
        Call<ArrayList<Comment>> comments = DependencyLocator.GetJphApi().GetComments(idPost);
        comments.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                ArrayList<Comment> com = response.body();
                CommentAdapter adapter = new CommentAdapter(com);
                listComment.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                
            }
        });
    }
}
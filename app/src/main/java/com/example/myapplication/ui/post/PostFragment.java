package com.example.myapplication.ui.post;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.DependencyLocator;
import com.example.myapplication.R;
import com.example.myapplication.models.Post;
import com.example.myapplication.models.PostAdapter;
import com.example.myapplication.ui.CommentActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {

    private RecyclerView listPost;
    private ProgressBar progressBar;
    private FloatingActionButton btn;
    public static PostFragment newInstance() {
        return new PostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        listPost = (RecyclerView) view.findViewById(R.id.listPost);
        progressBar = (ProgressBar) view.findViewById(R.id.progress1);
        btn = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetPosts();
            }
        });
        listPost.setLayoutManager(new LinearLayoutManager(getContext()));
        GetPosts();
        return view;
    }

    private void GetPosts(){
        Call<ArrayList<Post>> posts = DependencyLocator.GetJphApi().GetPosts();
        ProgressVisibility(true);
        posts.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                ArrayList<Post> res = response.body();
                PostAdapter adapter = new PostAdapter(res, new PostAdapter.OnClickListener() {
                    @Override
                    public void onItemClick(int postId) {
                        Intent intent = new Intent(getContext(), CommentActivity.class);
                        intent.putExtra("idPost", postId);
                        startActivity(intent);
                    }
                });
                listPost.setAdapter(adapter);
                ProgressVisibility(false);

            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(getContext(), "Connection error", Toast.LENGTH_LONG).show();
                ProgressVisibility(false);
            }
        });
    }

    public void ProgressVisibility(boolean visible){
        if(visible){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
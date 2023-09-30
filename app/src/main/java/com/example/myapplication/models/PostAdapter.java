package com.example.myapplication.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<Post> posts;
    private OnClickListener onClickListener;
    public PostAdapter(ArrayList<Post> posts, OnClickListener onClickListener){
        this.posts = posts;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(posts.get(position).getTitle());
        holder.body.setText(posts.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView body;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) (TextView)itemView.findViewById(R.id.title);
            body = (TextView) (TextView)itemView.findViewById(R.id.body);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onItemClick(posts.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    public interface OnClickListener{
        void onItemClick(int postId);
    }
}

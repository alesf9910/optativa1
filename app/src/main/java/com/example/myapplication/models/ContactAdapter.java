package com.example.myapplication.models;


import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<Contact> contacts;
    private OnClickListener onClickListener;
    public ContactAdapter(ArrayList<Contact> contacts, OnClickListener onClickListener){
        this.contacts = contacts;
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
        holder.title.setText(contacts.get(position).getName());
        holder.body.setText(contacts.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView title;
        public TextView body;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) (TextView)itemView.findViewById(R.id.title);
            body = (TextView) (TextView)itemView.findViewById(R.id.body);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onClickListener.onItemClick(contacts.get(getAdapterPosition()));
                    return false;
                }
            });
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(Menu.NONE, R.id.deleteMenu, Menu.NONE, "Delete");
            contextMenu.add(Menu.NONE, R.id.updateMenu, Menu.NONE, "Edit");
        }
    }

    public interface OnClickListener{
        void onItemClick(Contact contact);
    }
}

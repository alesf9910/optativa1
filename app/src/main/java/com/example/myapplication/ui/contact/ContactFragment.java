package com.example.myapplication.ui.contact;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.DependencyLocator;
import com.example.myapplication.R;
import com.example.myapplication.models.Contact;
import com.example.myapplication.models.ContactAdapter;
import com.example.myapplication.services.ContactService;
import com.example.myapplication.ui.ContactEditActivity;

import java.util.ArrayList;

public class ContactFragment extends Fragment {
    Contact contactSelected;
    private RecyclerView recyclerView;
    private ContactService service;
    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        registerForContextMenu(recyclerView);
        service = DependencyLocator.GetContactService(getContext());
        showContacts();
        return view;
    }

    private void showContacts(){
        ArrayList<Contact> contacts = service.getContacts();
        ContactAdapter adapter = new ContactAdapter(contacts, new ContactAdapter.OnClickListener() {
            @Override
            public void onItemClick(Contact contact) {
                contactSelected = contact;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.deleteMenu){
            service.deleteContact(contactSelected.getId());
            showContacts();
        } else if (item.getItemId() == R.id.updateMenu) {
            Intent intent = new Intent(getContext(), ContactEditActivity.class);
            intent.putExtra("name", contactSelected.getName());
            intent.putExtra("phone", contactSelected.getPhone());
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        showContacts();
    }
}
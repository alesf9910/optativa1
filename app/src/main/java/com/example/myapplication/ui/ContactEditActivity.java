package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.DependencyLocator;
import com.example.myapplication.R;
import com.example.myapplication.models.Contact;
import com.example.myapplication.services.ContactService;

public class ContactEditActivity extends AppCompatActivity {
    EditText contactName;
    EditText contactPhone;
    Button bt;
    ContactService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");
        service = DependencyLocator.GetContactService(this);
        contactName = (EditText) findViewById(R.id.editTextText);
        contactName.setText(name);
        contactPhone = (EditText) findViewById(R.id.editTextPhone);
        contactPhone.setText(phone);
        bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.updateContact(new Contact(id, name, phone), contactName.getText().toString(), contactPhone.getText().toString());
                onBackPressed();
            }
        });
    }
}
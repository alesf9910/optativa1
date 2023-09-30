package com.example.myapplication.services;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;

import com.example.myapplication.models.Contact;

import java.util.ArrayList;

public class ContactService {
    private ContentResolver contentResolver;
    private ArrayList<ContentProviderOperation> contentProviderOperations;
    public ContactService(Context ctx){
        contentResolver = ctx.getContentResolver();
        contentProviderOperations = new ArrayList<>();
    }
    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();
        Cursor cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, new String[]{
                ContactsContract.Data.CONTACT_ID,
                ContactsContract.Data.DISPLAY_NAME,
                Phone.NUMBER,
                Phone.TYPE
        },null, null, ContactsContract.Data.DISPLAY_NAME);
        while (cursor.moveToNext()){
            int type = cursor.getInt(3);
            if(type == Phone.TYPE_MOBILE)
                contacts.add(new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
        }
        return contacts;
    }
    public void updateContact(Contact c, String newName, String newNumber){
        contentProviderOperations.clear();
        contentProviderOperations.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).
                withSelection(ContactsContract.Data.CONTACT_ID+" = ?", new String[]{c.getId()+""}).
                withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, newName).
                withValue(Phone.NUMBER, newNumber).build());
        try {
            contentResolver.applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
        } catch (OperationApplicationException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteContact(int id){
        contentProviderOperations.clear();
        contentProviderOperations.add(ContentProviderOperation.newDelete(
                ContactsContract.RawContacts.CONTENT_URI
        ).withSelection(ContactsContract.RawContacts.CONTACT_ID + "=?",
                new String[]{id +""}).build());
        try {
            contentResolver.applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
        }catch(Exception e){

        }
    }
}

package com.example.myapplication;

import android.content.Context;

import com.example.myapplication.models.Comment;
import com.example.myapplication.models.Post;
import com.example.myapplication.services.ContactService;
import com.example.myapplication.services.JphApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DependencyLocator {
    private static Retrofit retrofit = null;
    private static JphApi jphApi = null;
    private static ContactService contactService = null;
    public static Retrofit GetRetrofit(){
        if(retrofit == null)retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static JphApi GetJphApi(){
        if(jphApi == null)jphApi = GetRetrofit().create(JphApi.class);
        return jphApi;
    }
    public static ContactService GetContactService(Context ctx){
        if(contactService == null) contactService = new ContactService(ctx);
        return contactService;
    }
}

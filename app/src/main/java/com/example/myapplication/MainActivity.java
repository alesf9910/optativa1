package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RequestPermissionsContact();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_post)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void RequestPermissionsContact(){
        String [] perm = new String[]{
                android.Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
        };
        int requestCode = 100;
        if(ContextCompat.checkSelfPermission(this, perm[0]) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, perm[0]) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, perm, requestCode);
        }
    }

}
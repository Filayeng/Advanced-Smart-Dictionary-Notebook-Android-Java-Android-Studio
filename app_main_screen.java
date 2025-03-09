package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class app_main_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main_screen);
        BottomNavigationView botNavMain = findViewById(R.id.bottomNavMain);
        NavHostFragment botHostMain = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainHostFrag);
        NavigationUI.setupWithNavController(botNavMain,botHostMain.getNavController());
    }
}
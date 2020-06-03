package com.example.gmaomobile.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.gmaomobile.ui.CurvedBottomNavigationView;
import com.example.gmaomobile.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
    private CurvedBottomNavigationView bottom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAdd = (FloatingActionButton) findViewById(R.id.btnadd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateInterventionActivity.class));
            }
        });


        bottom = (CurvedBottomNavigationView) findViewById(R.id.curvedBottomNavigationView);
        final HomeFragment homeFragment = new HomeFragment();
        final ProfileFragment profileFragment = new ProfileFragment();

        setFragment(homeFragment);

        bottom.setOnNavigationItemSelectedListener(new CurvedBottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home) {
                    setFragment(new HomeFragment());
                    return true;
                } else if (id == R.id.profile) {
                    setFragment(new ProfileFragment());
                    return true;
                }
                return false;
            }
        });
    }

    private void setFragment(Fragment fragment) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
    }




}


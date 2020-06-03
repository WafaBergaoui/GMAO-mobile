package com.example.gmaomobile.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gmaomobile.R;


public class DetailInterventionActivity extends AppCompatActivity {

    private TextView TitleView, PriorityView, EquipmentView, DescriptionView;
    private String title;
    private String priority;
    private String equipment;
    private String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initView();

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        priority = intent.getStringExtra("priority");
        equipment = intent.getStringExtra("equipment");
        description = intent.getStringExtra("description");

        TitleView.setText(title);
        PriorityView.setText(priority);
        EquipmentView.setText(equipment);
        DescriptionView.setText(description);

    }

    private void initView() {
        TitleView = (TextView) findViewById(R.id.titleView);
        PriorityView = (TextView) findViewById(R.id.priorityView);
        EquipmentView = (TextView) findViewById(R.id.equipmentView);
        DescriptionView = (TextView) findViewById(R.id.descriptionView);
    }
}

package com.example.gmaomobile.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gmaomobile.Model.Database;
import com.example.gmaomobile.R;

import java.util.Calendar;


public class CreateInterventionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText title;
    private EditText priority;
    private TextView date;
    private EditText equipment;
    private EditText description;
    private Button btnSave;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        title = (EditText) findViewById(R.id.title);
        priority = (EditText) findViewById(R.id.priority);
        date = (TextView) findViewById(R.id.date);
        equipment = (EditText) findViewById(R.id.equipment);
        description = (EditText) findViewById(R.id.description);
        btnSave = (Button) findViewById(R.id.btnsave);


        db = new Database(CreateInterventionActivity.this);
        btnSave.setOnClickListener(addFields());


        //to show date
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private View.OnClickListener addFields() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Title = title.getText().toString();
                String Priority = priority.getText().toString();
                String Date = date.getText().toString();
                String Equipment = equipment.getText().toString();
                String Description = description.getText().toString();

                if (TextUtils.isEmpty(Title) || TextUtils.isEmpty(Priority) || TextUtils.isEmpty(Date) || TextUtils.isEmpty(Equipment) || TextUtils.isEmpty(Description)) {
                    Toast.makeText(getApplicationContext(), "Remplissez les cases vides ", Toast.LENGTH_SHORT).show();
                } else {
                    db.addIntervention(title.getText().toString(), priority.getText().toString(), date.getText().toString(), equipment.getText().toString(), description.getText().toString());
                    Intent i = new Intent(CreateInterventionActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        };
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dat = "month/day/year: " + month + "/" + dayOfMonth + "/" + year;
        date.setText(dat);
    }
}


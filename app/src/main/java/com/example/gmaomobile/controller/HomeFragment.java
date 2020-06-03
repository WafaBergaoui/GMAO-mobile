package com.example.gmaomobile.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gmaomobile.Model.InterventionListener;
import com.example.gmaomobile.Model.Interventions;
import com.example.gmaomobile.Adapter.InterventionAdapter;
import com.example.gmaomobile.Model.Database;
import com.example.gmaomobile.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements InterventionListener {

    private RecyclerView rv;



    private ImageButton btnDelete;
    InterventionAdapter adapter ;
    Database db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rv = (RecyclerView) view.findViewById(R.id.rev);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);

        db = new Database(getActivity());
        ArrayList<Interventions> list = db.getAllIntervention();

        adapter = new InterventionAdapter(getActivity(), list, this);
        rv.setAdapter(adapter);

        btnDelete = (ImageButton) view.findViewById(R.id.btndelete);

        return view;

    }


    @Override
    public void onItemClick(Interventions interventions) {

        Intent intent = new Intent(getActivity(), DetailInterventionActivity.class);
        intent.putExtra("title", interventions.getTitle());
        intent.putExtra("priority", interventions.getPriority());
        intent.putExtra("equipment", interventions.getEquipment());
        intent.putExtra("description", interventions.getDescription());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Interventions interventions) {
        db.deleteInterventions(interventions);
        adapter.removeItem(interventions);
        Toast.makeText(getActivity(), "deleted", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onEditClick(Interventions interventions) {
        Intent i = new Intent(getActivity(), UpdateInterventionActivity.class);
        startActivity(i);
    }


}








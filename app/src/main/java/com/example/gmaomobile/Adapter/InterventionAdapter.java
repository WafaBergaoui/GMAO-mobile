package com.example.gmaomobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmaomobile.Model.InterventionListener;
import com.example.gmaomobile.Model.Interventions;
import com.example.gmaomobile.R;
import java.util.ArrayList;

public class InterventionAdapter extends RecyclerView.Adapter<InterventionHolder>{

    private Context context;
    private ArrayList<Interventions> list = new ArrayList<>();
    private InterventionListener listener;


    public InterventionAdapter(Context context, ArrayList<Interventions> list, InterventionListener listener){
        this.context = context;
        this.list=list;
        this.listener = listener;
    }

    @NonNull
    // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
    @Override
    public InterventionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item,parent,false);
        return new InterventionHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull InterventionHolder holder, final int position ) {
       holder.bind(list.get(position),listener);
    }


    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount()
    {
        return list.size();
    }


    public void removeItem(Interventions interventions) {
        list.remove(interventions);
        notifyDataSetChanged();
    }
}

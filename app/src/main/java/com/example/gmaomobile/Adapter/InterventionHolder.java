package com.example.gmaomobile.Adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmaomobile.Model.InterventionListener;
import com.example.gmaomobile.Model.Interventions;
import com.example.gmaomobile.R;



public class InterventionHolder extends RecyclerView.ViewHolder {

    TextView title, description;
    ImageButton btnDelete,btnEdit;
    CardView cardView;


    public InterventionHolder(@NonNull View itemView) {

        super(itemView);
        title=itemView.findViewById(R.id.titre);
        description=itemView.findViewById(R.id.descripttion);
        btnDelete = itemView.findViewById(R.id.btndelete);
        btnEdit = itemView.findViewById(R.id.btnedit);
        cardView=itemView.findViewById(R.id.cardView);

    }


    public void bind(final Interventions interventions, final InterventionListener listener) {

        title.setText(interventions.getTitle());
        description.setText(interventions.getDescription());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(interventions);
                    }
                }
            }
        });


      btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(interventions);

                    }
                }
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onEditClick(interventions);
                    }
                }
            }
        });



    }
}


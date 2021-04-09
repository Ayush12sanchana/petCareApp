package com.example.petapp002.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petapp002.Model.PetDetails;
import com.example.petapp002.R;

import java.util.List;

public class PetDetailsAdapter extends RecyclerView.Adapter<PetDetailsAdapter.PetDetailsAdapterHoler>  {


    List<PetDetails> petDetailsList;

    public PetDetailsAdapter(List<PetDetails> petDetailsList)
    {
        this.petDetailsList =petDetailsList;
    }

    @NonNull
    @Override
    public PetDetailsAdapter.PetDetailsAdapterHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_details_card,parent,false);

        return new PetDetailsAdapterHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetDetailsAdapter.PetDetailsAdapterHoler holder, int position) {

        //String aa= petDetailsList.get(position).getPetName();
        holder.name.setText(petDetailsList.get(position).getPetName());
        holder.breed.setText(petDetailsList.get(position).getPetBreed());
        String fk=petDetailsList.get(position).getImgURL();

        Glide.with(holder.itemView.getContext())
                .load(fk)
                .into(holder.iii);
    }

    @Override
    public int getItemCount() {
        return petDetailsList.size();
    }

    public class PetDetailsAdapterHoler extends RecyclerView.ViewHolder {

        View mView;
        public TextView name,breed;
        ImageView iii;

        public PetDetailsAdapterHoler(@NonNull View itemView) {
            super(itemView);

            mView=itemView;
            name = (TextView) itemView.findViewById(R.id.card_pet_name);
            breed = (TextView) itemView.findViewById(R.id.card_pet_breed);
            iii =(ImageView)itemView.findViewById(R.id.card_pet_image);
        }
    }
}

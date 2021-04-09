package com.example.petapp002;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.petapp002.Adapters.PetDetailsAdapter;
import com.example.petapp002.Model.PetDetails;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyPetActivity extends AppCompatActivity {

    private String TAG="Activity_Equipment";
    RecyclerView recyclerView;
    public List<PetDetails> petDetailsList;
    PetDetailsAdapter petDetailsAdapter;
    private FirebaseFirestore db;
    public FirebaseAuth mFirebaseAuth;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pet);

        petDetailsList=new ArrayList<>();
        petDetailsAdapter = new PetDetailsAdapter(petDetailsList);
        mFirebaseAuth = FirebaseAuth.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.pet_detail_recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setAdapter(petDetailsAdapter);

        db=FirebaseFirestore.getInstance();
        userId = mFirebaseAuth.getCurrentUser().getUid();


        db.collection("Pets").document(userId).collection("dogs").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException error) {
                if(error !=null){
                    Log.d(TAG, "onEvent: ");
                }
                for(DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if(doc.getType()== DocumentChange.Type.ADDED){
                        PetDetails petDetails=doc.getDocument().toObject(PetDetails.class);
                        petDetailsList.add(petDetails);
                        petDetailsAdapter.notifyDataSetChanged();

                    }
                }

            }
        });

    }
}

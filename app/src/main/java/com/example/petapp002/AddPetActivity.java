package com.example.petapp002;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class AddPetActivity extends AppCompatActivity {

    public EditText petDOB, petName, petAge, petBread, petWeight;
    public Spinner dogBreed;
    public ImageView dogImage;
    public Button petSaveBtn;
    public Calendar myCalendar;
    public FirebaseAuth mFirebaseAuth;
    public FirebaseFirestore mfirebaseFirestore;
    public String userId;
    public Uri imageuri;
    private Bitmap bitmap;
    private Uri pickedImgUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        mfirebaseFirestore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        myCalendar = Calendar.getInstance();
        petDOB = (EditText) findViewById(R.id.pet_birth_date_txt);
        petName = findViewById(R.id.pet_name_txt);
        petAge = findViewById(R.id.pet_age_txt);
        petBread = findViewById(R.id.pet_weight_txt);
        petWeight = findViewById(R.id.pet_weight_txt);
        dogBreed = findViewById(R.id.pet_breed_spinner);
        petSaveBtn = findViewById(R.id.pet_save_btn);
        dogImage = findViewById(R.id.dog_image);


        petSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(petName.getText().toString())){
                    petName.setError("name required");
                    petName.requestFocus();
                }
                else if(TextUtils.isEmpty(petAge.getText().toString())){
                    petAge.setError("age required");
                    petAge.requestFocus();
                }
                else if(TextUtils.isEmpty(petDOB.getText().toString())){
                    petDOB.setError("Date of Birth required");
                    petDOB.requestFocus();
                }
                else if(TextUtils.isEmpty(petWeight.getText().toString())){
                    petWeight.setError("weight required");
                    petWeight.requestFocus();
                }
                else if(TextUtils.isEmpty(dogBreed.getSelectedItem().toString())){
                    dogBreed.requestFocus();
                }


                else if(!(petName.getText().toString().isEmpty() && petAge.getText().toString().isEmpty() && petDOB.getText().toString().isEmpty())) {


                    userId = mFirebaseAuth.getCurrentUser().getUid();



                    StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
                    final StorageReference imageFilePath = mStorage.child(imageuri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // image uploaded succesfully
                            // now we can get our image url

                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    // uri contain user image url

                                    String url = uri.toString();

                                    DocumentReference documentReference = mfirebaseFirestore.collection("Pets").document(userId).collection("dogs").document(petName.getText().toString());
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Uid", userId);
                                    user.put("petName", petName.getText().toString());
                                    user.put("petAge", petAge.getText().toString());
                                    user.put("petDOB", petDOB.getText().toString());
                                    user.put("petWeight", petWeight.getText().toString());
                                    user.put("petBreed", dogBreed.getSelectedItem().toString());
                                    user.put("imgURL", url);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            Toast.makeText(AddPetActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                            //Bundle bundle = new Bundle();
                                            //bundle.putString("Uid", userId);

                                            //Intent intent = new Intent(registrationtwo.this, login01.class);
                                            //intent.putExtras(bundle);
                                            //startActivity(intent);

                                            //Intent intent = new Intent(RegisterPetOwnerActivity.this, LoginActivity.class);
                                            //startActivity(intent);

                                        }
                                    });

                                }
                                });
                        }
                    });





                }

            }
        });

        dogImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),12);
            }
        });


        //Date Picker Functions
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        petDOB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddPetActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //End Date Picker Functions



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        petDOB.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==12 && resultCode==RESULT_OK && data!=null) {
            imageuri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                dogImage.setImageBitmap(bitmap);
                pickedImgUri=data.getData();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

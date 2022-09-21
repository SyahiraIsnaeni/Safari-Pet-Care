package belajar.android.safaripetcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddGuestActivity extends AppCompatActivity {

    Button addPet;
    EditText namePet, petSpecies, petGender, petOwner, contactOwner, petTreatment;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guest);

        addPet = findViewById(R.id.addpet);
        namePet = findViewById(R.id.namepet);
        petSpecies = findViewById(R.id.pet);
        petGender = findViewById(R.id.genderpet);
        petOwner = findViewById(R.id.owner);
        contactOwner = findViewById(R.id.contact);
        petTreatment = findViewById(R.id.treatment);

        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String petName = namePet.getText().toString().trim();
                String species = petSpecies.getText().toString().trim();
                String gender = petGender.getText().toString().trim();
                String owner = petOwner.getText().toString().trim();
                String contact = contactOwner.getText().toString().trim();
                String treatment = petTreatment.getText().toString().trim();

                if(petName.isEmpty()){
                    namePet.setError("Pet Name is required");
                    namePet.requestFocus();
                    return;
                }else if(species.isEmpty()){
                    petSpecies.setError("Pet species is required");
                    petSpecies.requestFocus();
                    return;
                }else if(gender.isEmpty()){
                    petGender.setError("Pet gender is required");
                    petGender.requestFocus();
                    return;
                }else if(owner.isEmpty()){
                    petOwner.setError("Pet owner is required");
                    petOwner.requestFocus();
                    return;
                }else if(contact.isEmpty()){
                    contactOwner.setError("Contact owner is required");
                    contactOwner.requestFocus();
                    return;
                }else if(treatment.isEmpty()){
                    petTreatment.setError("Pet treatment is required");
                    petTreatment.requestFocus();
                    return;
                }else{
                    database.child("Guest").push().setValue(new GuestDetail(petName, species, gender, owner, contact, treatment)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AddGuestActivity.this, "Data has been saved successfully!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddGuestActivity.this, "Failed to save data! please try again!", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });

    }
}
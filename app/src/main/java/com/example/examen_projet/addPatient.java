package com.example.examen_projet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addPatient extends AppCompatActivity {
    private EditText editTextName, editTextAge,description;
    private Spinner spinnerGender;
    private Button btnSubmit;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_patient);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        description = findViewById(R.id.description);

        btnSubmit = findViewById(R.id.btnSubmit);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("/");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }
    private void submitForm() {
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String desc =description.getText().toString().trim();


        saveToDatabase(name, age,desc);

        String message = "Name: " + name + "\nAge: " + age + "\nGender: " + desc;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void saveToDatabase(String name, String age ,String desc ) {
        // Create a unique key for each entry
        String patientId = databaseReference.push().getKey();



        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("name", name.toString());
        stringStringMap.put("age", age.toString());
        stringStringMap.put("desc", desc.toString());

        if (patientId != null) {
            databaseReference.child(patientId).setValue(stringStringMap);
        }
    }
}
package com.example.examen_projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dashboard extends AppCompatActivity {
    private Button btnSubmit,btnSubmi,logout,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        btnSubmit = findViewById(R.id.add_patient);
        btnSubmi = findViewById(R.id.veiw_patient);
        logout = findViewById(R.id.logout);
        delete = findViewById(R.id.delete_patient);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,addPatient.class);
                startActivity(i);
            }
        });
        btnSubmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,viewPatient.class);
                startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dashboard.this,deleteAccount.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dashboard.this, login.class);
                startActivity(intent);
// Finish the current activity to remove it from the back stack
                finish();
            }
        });
    }
}
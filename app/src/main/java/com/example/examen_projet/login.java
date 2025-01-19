package com.example.examen_projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText editText_email ,editText_password ;

    Button login ;
    FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance() ;
        editText_email=findViewById(R.id.Mail_id) ;
        editText_password=findViewById(R.id.password_id) ;
        login=(Button) findViewById(R.id.signeup_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password ;
                email=String.valueOf(editText_email.getText()) ;
                password=String.valueOf(editText_password.getText()) ;



                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(login.this, "Enter your mail ", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(login.this, "Enter your password ", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent i = new Intent(login.this,dashboard.class);
                        startActivity(i);
                        Toast.makeText(login.this, "login seccseful",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this, "fail",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
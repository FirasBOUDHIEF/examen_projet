package com.example.examen_projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
EditText editText_username ,editText_email,editText_password,editText_repassword ;
Button button_signup ;
FirebaseAuth  mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth=FirebaseAuth.getInstance() ;
        editText_email=findViewById(R.id.Mail_id) ;
        editText_username=findViewById(R.id.username_id) ;
        editText_password=findViewById(R.id.password_id) ;
        editText_repassword=findViewById(R.id.Repassword_id) ;
        button_signup=(Button) findViewById(R.id.signup_btn);
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password ,username,repassword ;
                email=String.valueOf(editText_email.getText()) ;
                password=String.valueOf(editText_password.getText()) ;
                username=String.valueOf(editText_username.getText()) ;
                repassword=String.valueOf(editText_repassword.getText()) ;


                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(signup.this, "Enter your mail ", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(signup.this, "Enter your password ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(username)) {
                    Toast.makeText(signup.this, "Enter your username ", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(TextUtils.isEmpty(repassword)) {
                    Toast.makeText(signup.this, "Enter your repassword", Toast.LENGTH_SHORT).show();
                    return ;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {


                                    Toast.makeText(signup.this, "acount created.",
                                            Toast.LENGTH_SHORT).show();


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

    }
}
package com.iaproject.miage.intelligentagenda.SignInUp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.iaproject.miage.intelligentagenda.Profil.Profile;
import com.iaproject.miage.intelligentagenda.R;

public class LoginActivity extends AppCompatActivity {


    public FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        final Button buttonSignin = (Button) findViewById(R.id.buttonSignin);
        final EditText editTextMail = (EditText) findViewById(R.id.editTextEmail);
        final EditText editTextPasword = (EditText) findViewById(R.id.editTextPassword);
        final TextView textViewSignup = (TextView) findViewById(R.id.textViewSignup);
        final TextView textViewmdpOublie=(TextView) findViewById(R.id.textViewmdp);
        firebaseAuth = FirebaseAuth.getInstance();


        /*if(firebaseAuth.getCurrentUser()==null){
            //dfgbh
        }*/
        textViewmdpOublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),ResetPasswordActivity.class));

            }
        });


        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == buttonSignin) {
                    UserLogin();
                }


            }

            private void UserLogin() {

                String email = editTextMail.getText().toString().trim();
                String password = editTextPasword.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    // email vide
                    Toast.makeText(getApplicationContext(), "Veuillez rentrer votre email SVP", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {

                    //password vide
                    Toast.makeText(getApplicationContext(), "Veuillez rentrer votre mot de passe SVP", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Please wait ... ");
                progressDialog.show();

                progressDialog.setCancelable(true);

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Bravo", Toast.LENGTH_SHORT).show();
                                    //finish();
                                    startActivity(new Intent(getApplicationContext(),Profile.class));

                                } else {
                                    Toast.makeText(getApplicationContext(), "Connection failed ", Toast.LENGTH_SHORT).show();


                                }
                            }
                        });
            }
        });


        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }
}





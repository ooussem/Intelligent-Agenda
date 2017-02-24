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


public class MainActivity extends AppCompatActivity {
    public FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        final Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        final EditText editTextMail = (EditText) findViewById(R.id.editTextEmail);
        final EditText editTextPasword = (EditText) findViewById(R.id.editTextPassword);
        final TextView textViewSignin = (TextView) findViewById(R.id.textViewSignin);



        firebaseAuth = FirebaseAuth.getInstance();



       /* if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Profile.class));
            //dfgbh

        }*/






        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == buttonRegister) {
                    registerUser();
                }
            }
            private void registerUser() {
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

                progressDialog.setMessage("Registring user ... ");
                progressDialog.show();
                progressDialog.setCancelable(true);

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(), Profile.class));
                                    Toast.makeText(MainActivity.this, "Registred Succesfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Could not registred ... Please Try again ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,LoginActivity.class));



            }

        });





    }

}
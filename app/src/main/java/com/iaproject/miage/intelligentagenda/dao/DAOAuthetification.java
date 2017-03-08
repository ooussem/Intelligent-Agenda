package com.iaproject.miage.intelligentagenda.dao;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.iaproject.miage.intelligentagenda.profil.Profile;

/**
 * Created by kp on 08/03/2017.
 */

public class DAOAuthetification {

    public Activity MyActivity ;

    public DAOAuthetification(Activity myActivity) {
        MyActivity = myActivity;
    }

    public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    public void userLogin(String email,String password) {

        if (TextUtils.isEmpty(email)) {
            // email vide
           Toast.makeText(MyActivity.getApplicationContext(), "Veuillez rentrer votre email SVP", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password vide
           Toast.makeText(MyActivity.getApplicationContext(), "Veuillez rentrer votre mot de passe SVP", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MyActivity,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      //  progressDialog.dismiss();
                        if (task.isSuccessful()) {
                           Toast.makeText(MyActivity.getApplicationContext(), "Bravo", Toast.LENGTH_SHORT).show();
                            //finish();
                            MyActivity.startActivity(new Intent(MyActivity.getApplicationContext(), Profile.class));
                        } else {
                           Toast.makeText(MyActivity.getApplicationContext(), "Connection failed ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    public void registerUser(String mail, String pass) {

        if (TextUtils.isEmpty(mail)) {
            // email vide
            Toast.makeText(MyActivity.getApplicationContext(), "Veuillez rentrer votre email SVP", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            //password vide
            Toast.makeText(MyActivity.getApplicationContext(), "Veuillez rentrer votre mot de passe SVP", Toast.LENGTH_SHORT).show();
            return;

        }


        firebaseAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            MyActivity.startActivity(new Intent(MyActivity.getApplicationContext(), Profile.class));
                            Toast.makeText(MyActivity.getApplicationContext(), "Registred successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MyActivity.getApplicationContext(), "Could not registred ... Please Try again ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



    public  void resetPassword(String email){

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(MyActivity.getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MyActivity.getApplicationContext(), "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MyActivity.getApplicationContext(), "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }



}

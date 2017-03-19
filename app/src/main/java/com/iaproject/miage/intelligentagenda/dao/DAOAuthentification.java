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
import com.iaproject.miage.intelligentagenda.activity.home.HomeActivity;

/**
 * Created by kp on 08/03/2017.
 */

public final class DAOAuthentification {
    public Activity activity;
    public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public DAOAuthentification(Activity activity) {
        this.activity = activity;
    }


    public void userLogin(String email,String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
	                if (task.isSuccessful()) {
	                    Toast.makeText(activity.getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                        activity.startActivity(new Intent(activity.getApplicationContext(),HomeActivity.class));
	                } else {
	                    Toast.makeText(activity.getApplicationContext(), "Connection failed ", Toast.LENGTH_SHORT).show();
	                }
                }
            }
        );
    }


    public void registerUser(String mail, String pass) {
        // If email empty
        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(activity.getApplicationContext(), "Veuillez rentrer votre email SVP", Toast.LENGTH_SHORT).show();
            return;
        }
	    // If password empty
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(activity.getApplicationContext(), "Veuillez rentrer votre mot de passe SVP", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(mail, pass)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //activity.startActivity(new Intent(activity.getApplicationContext(), ProfileActivity.class));
                        Toast.makeText(activity.getApplicationContext(), "Registred successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity.getApplicationContext(), "Could not registred ... Please Try again ", Toast.LENGTH_SHORT).show();
                    }
                }
            });


    }



    public  void resetPassword(String email) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(activity.getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.sendPasswordResetEmail(email)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
                Toast.makeText(activity.getApplicationContext(), "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity.getApplicationContext(), "Failed to send reset email!", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

}

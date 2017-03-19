package com.iaproject.miage.intelligentagenda.activity.authentification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.dao.DAOAuthentification;
import com.iaproject.miage.intelligentagenda.dao.DAODatabase;
import com.iaproject.miage.intelligentagenda.service.ServiceDatabase;

public class SignUpActivity extends AppCompatActivity {
	Activity SignUpActitvity = this;
	Button buttonRegister;
    DAOAuthentification daoAuthentification;
	DAODatabase daoDatabase;
	ServiceDatabase service;
	String locationBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        final EditText editTextMail = (EditText) findViewById(R.id.editTextEmail);
        final EditText editTextPasword = (EditText) findViewById(R.id.editTextPassword);
        final TextView textViewSignin = (TextView) findViewById(R.id.textViewSignin);
	    //TODO: EDIT TEXT pour le lieu de location

	    daoAuthentification = new DAOAuthentification(SignUpActitvity);
	    daoDatabase = DAODatabase.getInstance();


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextMail.getText().toString().trim();
                String password = editTextPasword.getText().toString().trim();
                daoAuthentification.registerUser(email,password);
//	            service.addAgenda(locationBase);
            }
        });


        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });

    }

}
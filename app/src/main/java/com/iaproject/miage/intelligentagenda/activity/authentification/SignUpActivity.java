package com.iaproject.miage.intelligentagenda.activity.authentification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.dao.DAOAuthetification;

public class SignUpActivity extends AppCompatActivity {
    Button buttonRegister;
    DAOAuthetification daoAuthetification = new DAOAuthetification(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        final EditText editTextMail = (EditText) findViewById(R.id.editTextEmail);
        final EditText editTextPasword = (EditText) findViewById(R.id.editTextPassword);
        final TextView textViewSignin = (TextView) findViewById(R.id.textViewSignin);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextMail.getText().toString().trim();
                String password = editTextPasword.getText().toString().trim();
                daoAuthetification.registerUser(email,password);
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
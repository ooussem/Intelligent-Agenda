package com.iaproject.miage.intelligentagenda.activity.authentification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.dao.DAOAuthentification;


public class ResetPasswordActivity extends AppCompatActivity {

    DAOAuthentification daoAuthentification = new DAOAuthentification(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        final EditText inputEmail = (EditText) findViewById(R.id.editTextEmailoublie);
        final Button btnReset = (Button) findViewById(R.id.btnReset);
        final TextView textViewBack = (TextView) findViewById(R.id.textViewBack);



        textViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                daoAuthentification.resetPassword(email);
            }
        });




    }
}

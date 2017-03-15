package com.iaproject.miage.intelligentagenda.activity.authentification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.activity.dayevent.ActivityDay;
import com.iaproject.miage.intelligentagenda.dao.DAOAuthetification;



public class LoginActivity extends AppCompatActivity {
    public Context context = this;
    public Intent intent = null;
    public static final String KEY_PARAM1 = "KEY_PARAM1";
    DAOAuthetification daoAuthetification = new DAOAuthetification(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final Button buttonSignin = (Button) findViewById(R.id.buttonSignin);
        final EditText editTextMail = (EditText) findViewById(R.id.editTextEmail);
        final EditText editTextPasword = (EditText) findViewById(R.id.editTextPassword);
        final TextView textViewSignup = (TextView) findViewById(R.id.textViewSignup);
        final TextView textViewmdpOublie=(TextView) findViewById(R.id.textViewmdp);

        textViewmdpOublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ResetPasswordActivity.class));
            }

        });

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextMail.getText().toString().trim();
                String password = editTextPasword.getText().toString().trim();
                String idUser = null;
                daoAuthetification.userLogin(email,password);
                startActivity(new Intent(getApplicationContext(),ActivityDay.class));
            }
        });


        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }

        });
    }

}


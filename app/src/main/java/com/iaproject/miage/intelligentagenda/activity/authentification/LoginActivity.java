package com.iaproject.miage.intelligentagenda.activity.authentification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iaproject.miage.intelligentagenda.R;

import com.iaproject.miage.intelligentagenda.service.ServiceDatabase;


public class LoginActivity extends AppCompatActivity {
    public Context context = this;
	public Activity activity = this;
    public Intent intent = null;
    public static final String KEY_PARAM1 = "KEY_PARAM1";



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
	            boolean isComplete = true;
	            if (TextUtils.isEmpty(password) && TextUtils.isEmpty(email)) {
		            Toast.makeText(getApplicationContext(), "Veuillez rentrer votre mail et votre mot de passe SVP", Toast.LENGTH_SHORT).show();
		            isComplete = false;
		            return;
	            }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Veuillez rentrer votre email SVP", Toast.LENGTH_SHORT).show();
	                isComplete = false;
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Veuillez rentrer votre mot de passe SVP", Toast.LENGTH_SHORT).show();
	                isComplete = false;
                    return;
                }
	            if(isComplete){
		            ServiceDatabase.login(activity, email,password);
	            }

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


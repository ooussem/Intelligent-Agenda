package com.iaproject.miage.intelligentagenda.Profil;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.SignInUp.LoginActivity;

public class Profile extends AppCompatActivity {


    public FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        firebaseAuth = FirebaseAuth.getInstance();
       /* if(firebaseAuth.getCurrentUser()==null){

            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            //dfgbh
        }*/

        FirebaseUser user=firebaseAuth.getCurrentUser();


        TextView TextViewEmail=(TextView) findViewById(R.id.textViewProfile);
        Button buttonLogout=(Button) findViewById(R.id.buttonLogout);

        TextViewEmail.setText("Bonjour "+user.getEmail());

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            }
        });

    }
}

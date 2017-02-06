package com.iaproject.miage.intelligentagenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

        FirebaseUser user = firebaseAuth.getCurrentUser();


        TextView TextViewEmail = (TextView) findViewById(R.id.textViewProfile);
        Button buttonLogout = (Button) findViewById(R.id.buttonLogout);

        TextViewEmail.setText("Bonjour " + user.getEmail());
    }
}

package com.iaproject.miage.intelligentagenda.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iaproject.miage.intelligentagenda.feature.event.model.Agenda;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

/**
 * Created by kp on 07/03/2017.
 */

public class daoDatabase {

    final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference databaseReference = database.getReference();


    public final void addEvent(Event event, Agenda agenda){


        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child("users").child(user.getUid()).setValue(event);
        agenda.addEvent(event);




    }


}

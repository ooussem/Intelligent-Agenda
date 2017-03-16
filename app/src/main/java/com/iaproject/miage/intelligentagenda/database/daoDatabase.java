package com.iaproject.miage.intelligentagenda.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.iaproject.miage.intelligentagenda.feature.event.model.Agenda;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

/**
 * Created by kp on 07/03/2017.
 */

public class daoDatabase {

    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference databaseReference = database.getReference();


    public final void addEvent(Event event, Agenda agenda) {


        FirebaseUser user=firebaseAuth.getCurrentUser();
        String key = databaseReference.child("event").push().getKey();
        databaseReference.child("users").child(user.getUid()).child(agenda.titleAgenda).child("event").child(key).setValue(event);
        agenda.addEvent(event);


    }


    public final void deleteEvent(Agenda agenda , Event ev) {


        //agenda.deleteEvent(ev);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("users").child(user.getUid()).child("My agenda").child("event");

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}

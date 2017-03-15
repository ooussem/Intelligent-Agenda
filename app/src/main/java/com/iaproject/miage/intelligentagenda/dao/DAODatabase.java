package com.iaproject.miage.intelligentagenda.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iaproject.miage.intelligentagenda.feature.event.model.Agenda;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

/**
 * Created by kp on 07/03/2017.
 */

public class DAODatabase {

	public FirebaseDatabase database;
	public FirebaseAuth firebaseAuth;
    public DatabaseReference databaseReference;
	public FirebaseUser user ;

    public DAODatabase() {
	    this.database = FirebaseDatabase.getInstance();
	    this.databaseReference = database.getReference();
	    this.firebaseAuth = FirebaseAuth.getInstance();
	    this.user = firebaseAuth.getCurrentUser();
    }

	public void addUser(){
		String uid = this.user.getUid();
		databaseReference.child("users").setValue(uid);
	}

    public final void addEvent(Agenda agenda, Event event) {
        String key = databaseReference.child(this.user.getUid()).child(agenda.titleAgenda).child("events").push().getKey();
//        databaseReference.child(user.getUid()).child(agenda.titleAgenda).child("event").child(key).setValue(event);

	    databaseReference.child("users").child(this.user.getUid()).child(agenda.titleAgenda).child("events")
			    .child(key).setValue(event);
        agenda.addEvent(event);
    }

    public final void deleteEvent() {
        String key = databaseReference.child("event").push().getKey();
        // FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child(key).removeValue();
    }

}

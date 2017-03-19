package com.iaproject.miage.intelligentagenda.dao;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.iaproject.miage.intelligentagenda.feature.event.model.Course;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kp on 07/03/2017.
 */

public final class DAODatabase {
	public static DAODatabase instance;
    private final FirebaseAuth firebaseAuth;
	private final FirebaseDatabase database;
	private final DatabaseReference databaseReference;
	private final FirebaseUser user;
	Course course = new Course();


	private DAODatabase() {
		firebaseAuth = FirebaseAuth.getInstance();
		user = firebaseAuth.getCurrentUser();
		database = FirebaseDatabase.getInstance();
		databaseReference = database.getReference();
	}

	public static DAODatabase getInstance() {
		if(instance == null) {
			instance = new DAODatabase();
		}
		return instance;
	}



	public boolean addEvent(String keyDate, Event event) {
		getEvents(keyDate);
//		*********
		for(Event e : course.listEvents){
			Log.d("Event = " , e.title);
		}

		Log.d("Size course list", String.valueOf(course.listEvents.size()));
//		*********

		if(course.addEvent(event)) {

			String key = databaseReference.child("users").child(user.getUid()).child("Agenda").child("events")
					.child(keyDate).push().getKey();
			databaseReference.child("users").child(user.getUid()).child("Agenda")
					.child("events")
					.child(keyDate)
					.child(key)
					.setValue(event);
			return true;
		}
		else{
			return false;
		}

	}


	public void getEvents(String keyDate){
		Query querryGetEvents = databaseReference.child("users").child(user.getUid()).child("Agenda").child("events")
				.child(keyDate);
		querryGetEvents.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()) {
					for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
						Event event = snapshot.getValue(Event.class);
						Log.d("Event", event.title);
						course.addEvent(event);
					}
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

	}


	List<String> listKey;
	public List<String> getKeyDate(){
		listKey = new ArrayList<>();
		Query querryGetKey = databaseReference.child("users").child(user.getUid()).child("Agenda").child("events");
		querryGetKey.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()) {
					for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
						String key = snapshot.getValue(String.class);
						listKey.add(key);
					}
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
		return listKey;
	}




	public void deleteEvent(String keyDate) {
		Query querryGet= databaseReference.child("users").child(user.getUid()).child("Agenda").child("events");
		querryGet.addListenerForSingleValueEvent(new ValueEventListener() {
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

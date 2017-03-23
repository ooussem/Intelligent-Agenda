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
    public final FirebaseAuth firebaseAuth;
	public final FirebaseDatabase database;
	public final DatabaseReference databaseReference;
	public final FirebaseUser user;
	public Course course = new Course();


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






	public Integer finishedToGetEventsList = 0;
	public int addEvent(final String keyDate, final Event event) {
//		getEvents(keyDate);
		Log.d("finishedToGetEventsList", String.valueOf(finishedToGetEventsList));

		databaseReference.child("users").child(user.getUid()).child("Agenda").child("events")
				.child(keyDate).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Iterable<DataSnapshot> children = dataSnapshot.getChildren();
				for (DataSnapshot snapshot : children) {
					finishedToGetEventsList=0;
					Log.d("finishedToGetEventsList", String.valueOf(finishedToGetEventsList));
					Event e = snapshot.getValue(Event.class);
					Log.d("Event", e.title);
					course.addEvent(e);
					finishedToGetEventsList=2;
					Log.d("finishedToGetEventsList", String.valueOf(finishedToGetEventsList));
				}
				if (finishedToGetEventsList==2) {
					if (course.addEvent(event)) {
						String key = databaseReference.child("users").child(user.getUid()).child("Agenda").child("events")
								.child(keyDate).push().getKey();
						databaseReference.child("users").child(user.getUid()).child("Agenda")
								.child("events")
								.child(keyDate)
								.child(key)
								.setValue(event);
						finishedToGetEventsList = 1;
						Log.d("finishedToGetEventsList", String.valueOf(finishedToGetEventsList));
					} else {
						finishedToGetEventsList = -1;
						Log.d("finishedToGetEventsList", String.valueOf(finishedToGetEventsList));
					}
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

		Log.d("finishedToGetEventsList", String.valueOf(finishedToGetEventsList));

		return finishedToGetEventsList;
	}





//
//	public synchronized void getEvents(String keyDate){
//		synchronized (finishedToGetEventsList){
//			Query querryGetEvents = databaseReference.child("users").child(user.getUid()).child("Agenda").child("events")
//					.child(keyDate);
//			querryGetEvents.addListenerForSingleValueEvent(new ValueEventListener() {
//				@Override
//				public void onDataChange(DataSnapshot dataSnapshot) {
//					if (dataSnapshot.exists()) {
//						for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//							Event event = snapshot.getValue(Event.class);
//							Log.d("Event", event.title);
//							course.addEvent(event);
//						}
//					}
//
//
//				}
//
//				@Override
//				public void onCancelled(DatabaseError databaseError) {
//
//				}
//			});
//			finishedToGetEventsList = true;
//			notifyAll();
//		}
//
//	}


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

//
//
//
//	public void deleteEvent(String keyDate) {
//		Query querryGet= databaseReference.child("users").child(user.getUid()).child("Agenda").child("events");
//		querryGet.addListenerForSingleValueEvent(new ValueEventListener() {
//			@Override
//			public void onDataChange(DataSnapshot dataSnapshot) {
//				for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
//					appleSnapshot.getRef().removeValue();
//				}
//			}
//
//			@Override
//			public void onCancelled(DatabaseError databaseError) {
//
//			}
//		});
//	}

}

package com.iaproject.miage.intelligentagenda.activity.dayevent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

import java.util.ArrayList;
import java.util.List;

public class DayActivity extends AppCompatActivity {
	DatabaseReference databaseReference;
	FirebaseUser user;
	ListView listView;
	ArrayList<Event> events;
	EventListAdapter adapter;
	String keyDate;
	TextView dateView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day2);
		dateView = (TextView) findViewById(R.id.activity_day2_date);

		databaseReference = FirebaseDatabase.getInstance().getReference();
		user = FirebaseAuth.getInstance().getCurrentUser();

		Intent intent = getIntent();
		keyDate = intent.getStringExtra("DATE");

		listView  = (ListView) findViewById(R.id.activity_day2_list_view);
		events = new ArrayList<>();
		adapter = new EventListAdapter(this,events);
		listView.setAdapter(adapter);

		dateView.setText(keyDate);

		databaseReference.child("users").child(user.getUid()).child("Agenda").child("events").child(keyDate)
			.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					Iterable<DataSnapshot> children = dataSnapshot.getChildren();
					events.clear();
					for (DataSnapshot snapshot : children)  {
						Event event = snapshot.getValue(Event.class);
						events.add(event);
						Log.d("event ", event.title);
						adapter.notifyDataSetChanged();
					}
					for (Event event : events) {
						Log.d("event ", event.title);
					}
				}

				@Override
				public void onCancelled(DatabaseError databaseError) {

				}

			});
	}

	public class EventListAdapter extends ArrayAdapter<Event>{
		private Activity context;
		private List<Event> eventsList;

		public EventListAdapter(Activity context, List<Event> eventsList) {
			super(context, R.layout.event_information,eventsList);
			this.context = context;
			this.eventsList = eventsList;
		}

		@NonNull
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View listViewEvent = inflater.inflate(R.layout.event_information,null,true);
			TextView textViewTitle = (TextView) listViewEvent.findViewById(R.id.event_information_title);
			TextView textViewPlace = (TextView) listViewEvent.findViewById(R.id.event_information_place);
			TextView textViewStart = (TextView) listViewEvent.findViewById(R.id.event_information_start);
			TextView textViewEnd = (TextView) listViewEvent.findViewById(R.id.event_information_end);
			TextView textViewDescription = (TextView) listViewEvent.findViewById(R.id.event_information_description);
			TextView textViewTransport = (TextView) listViewEvent.findViewById(R.id.event_information_transport);
			Event event = eventsList.get(position);

			textViewTitle.setText(event.title);
			textViewPlace.setText(event.place);
			textViewDescription.setText(event.description);
			textViewStart.setText(event.startDate);
			textViewEnd.setText(event.endDate);
			textViewDescription.setText(event.description);
			textViewTransport.setText(event.transportMode);

			return listViewEvent;
		}
	}



}





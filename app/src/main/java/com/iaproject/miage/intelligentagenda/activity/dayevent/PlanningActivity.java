package com.iaproject.miage.intelligentagenda.activity.dayevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iaproject.miage.intelligentagenda.R;

import java.util.ArrayList;

public class PlanningActivity extends AppCompatActivity {
	DatabaseReference databaseReference;
	FirebaseUser user;
	ListView listView;
	ArrayList<String> keys;
	ArrayAdapter<String> keyAdapter;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planning);
		databaseReference = FirebaseDatabase.getInstance().getReference();
		user = FirebaseAuth.getInstance().getCurrentUser();

		listView  = (ListView) findViewById(R.id.activity_planning_list_key_events);
		keys = new ArrayList<>();
		keyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, keys);
		listView.setAdapter(keyAdapter);

		databaseReference.child("users").child(user.getUid()).child("Agenda").child("events")
				.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Iterable<DataSnapshot> children = dataSnapshot.getChildren();
				for (DataSnapshot snapshot : children)  {
					String key = snapshot.getKey().toString();
					keys.add(key);
					Log.d("key ", key);
					keyAdapter.notifyDataSetChanged();
				}
				for (String key : keys) {
					Log.d("key ", key);
				}

			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});




		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), DayActivity.class);

				intent.putExtra("DATE", keys.get(position));

				startActivity(intent);
			}
		});


	}

}

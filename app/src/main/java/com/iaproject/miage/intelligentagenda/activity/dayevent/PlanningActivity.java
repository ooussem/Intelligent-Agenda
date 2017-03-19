package com.iaproject.miage.intelligentagenda.activity.dayevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.iaproject.miage.intelligentagenda.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlanningActivity extends AppCompatActivity {
	Intent intentFromPlanningActivity;
	List<HashMap<String,Object>> listKey = null;
	HashMap<String,Object> mapKey = null;
	ListView listView = null;
	SimpleAdapter sa = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planning);
		listView = (ListView) findViewById(R.id.activity_planning_list_key_events);
		final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();;
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		final DatabaseReference databaseReference = database.getReference();
		final FirebaseUser user = firebaseAuth.getCurrentUser();


		listKey = new ArrayList<>();

		String[] from = new String[]{"key"};
		int[] to = new int[]{R.id.activity_planning_list_key_events};
		sa = new SimpleAdapter(this, listKey, R.layout.list_event, from, to);

		listView.setAdapter(sa);
		sa.notifyDataSetChanged();

		Query querryGetKey = databaseReference.child("users").child(user.getUid()).child("Agenda").child("events");
		querryGetKey.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()) {
					for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
						HashMap key = snapshot.getValue(HashMap.class);
						mapKey.put("key", key);
						listKey.add(mapKey);

						listView.setAdapter(sa);
						sa.notifyDataSetChanged();
					}
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
				HashMap<String, Object> date = listKey.get(position);
				intent.putExtra("KEY_DATE", date.get("key").toString());
				startActivity(intent);
			}
		});

	}

}

package com.iaproject.miage.intelligentagenda.activity.dayevent;

import android.app.Activity;
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
import java.util.List;

public class PlanningActivity extends AppCompatActivity {
//	List<HashMap<String,Object>> listKey = null;
//	HashMap<String,Object> mapKey = null;
//	SimpleAdapter sa = null;
	Activity activity = this;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planning);

		final ListView listView = (ListView) findViewById(R.id.activity_planning_list_key_events);

		final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
		final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

		final List<String> keys = new ArrayList<>();



//		listKey = new ArrayList<>();
//
//		String[] from = new String[]{"key"};
//		int[] to = new int[]{R.id.activity_planning_list_key_events};
//		sa = new SimpleAdapter(this, listKey, R.layout.list_event, from, to);
//
//		listView.setAdapter(sa);
//		sa.notifyDataSetChanged();





		databaseReference.child("users").child(user.getUid()).child("Agenda").child("events")
				.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				Iterable<DataSnapshot> children = dataSnapshot.getChildren();
				for (DataSnapshot snapshot : children)  {
					String key = snapshot.getKey();
					keys.add(key);
					Log.d("key ", key);

//					mapKey.put("key", key);
//					listKey.add(mapKey);
//
//					listView.setAdapter(sa);
//					sa.notifyDataSetChanged();
				}
				for (String key : keys) {
					Log.d("key ", key);
				}

				ArrayAdapter<String> keyAdapter = new ArrayAdapter<String>(activity,
						android.R.layout.activity_list_item, keys);

				listView.setAdapter(keyAdapter);
				keyAdapter.notifyDataSetChanged();


			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});




		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), DayActivity.class);

//				HashMap<String, Object> date = listKey.get(position);
//				intent.putExtra("KEY_DATE", date.get("key").toString());

				startActivity(intent);
			}
		});


	}

}

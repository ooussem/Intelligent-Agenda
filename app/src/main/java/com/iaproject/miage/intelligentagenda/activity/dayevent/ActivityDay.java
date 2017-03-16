package com.iaproject.miage.intelligentagenda.activity.dayevent;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.exception.AddEventException;
import com.iaproject.miage.intelligentagenda.feature.event.model.Agenda;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;
import com.iaproject.miage.intelligentagenda.dao.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.iaproject.miage.intelligentagenda.R.layout.dialog;


public class ActivityDay extends AppCompatActivity {
	DAODatabase daoDatabase;
	DatabaseReference dref;
	Event event = null;
	public Agenda agenda ;

	ImageButton buttonAdd;
	SimpleAdapter sa = null;
	List<HashMap<String,Object>> list = null;
	HashMap<String,Object> map = null;
	ListView listView = null;
	static int i = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day);
		daoDatabase = new DAODatabase();
		agenda = Agenda.getInstance();

		list = new ArrayList<>();

		String[] from = new String[]{"titre", "place","start","end","despcription"};
		int[] to = new int[]{R.id.activity_title_event, R.id.activity_place,R.id.activity_start,R.id.activity_end,R.id.activity_descrption};
		sa = new SimpleAdapter(this, list, R.layout.list_event, from, to);
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(sa);
		sa.notifyDataSetChanged();


	/*	dref= FirebaseDatabase.getInstance().getReference();
		dref.child("users").addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				Iterable<DataSnapshot> chiledren = dataSnapshot.getChildren();
				for(DataSnapshot child:chiledren){

					Event ev=child.getValue(Event.class);
					map = new HashMap<String, Object>();
					map.put("titre", ev.title);
					map.put("place", ev.place);
					map.put("start",ev.dateStart);
					map.put("end",ev.dateEnd);
					map.put("description",ev.description);

					list.add(map);
					sa.notifyDataSetChanged();
					lv.setAdapter(sa);
				}
			}
			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s) {
			}
			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {
			}
			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s) {
			}
			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
		*/


		buttonAdd = (ImageButton) findViewById(R.id.activity_day_button_add);
		buttonAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final View view = LayoutInflater.from(ActivityDay.this).inflate(dialog, null);
				AlertDialog.Builder Builder = new AlertDialog.Builder(ActivityDay.this);
				Builder.setMessage("Creer votre evenement")
				.setView(view)
						.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int wich) {
								// operation à effectuées

								EditText descrip = (EditText) view.findViewById(R.id.editTextDescription);
								EditText tit = (EditText) view.findViewById(R.id.editTextTitle);
								EditText pla = (EditText) view.findViewById(R.id.editTextPlace);
								EditText start = (EditText) view.findViewById(R.id.editTextStart);
								EditText end = (EditText) view.findViewById(R.id.editTextEnd);
								CheckBox dForte = (CheckBox) view.findViewById(R.id.checkBoxStart);
								CheckBox fForte = (CheckBox) view.findViewById(R.id.checkBoxeEnd);
								boolean isDateStartStrongness = true;
								boolean isDateEndStrongness = true;


								if (TextUtils.isEmpty(tit.getText().toString())||TextUtils.isEmpty(pla.getText().toString())
										||TextUtils.isEmpty(start.getText().toString())||TextUtils.isEmpty(end.getText().toString())
										||TextUtils.isEmpty(descrip.getText().toString()) ) {
									Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs S.V.P", Toast.LENGTH_SHORT).show();
									return;

								}
								if (dForte.isChecked()) {
									isDateStartStrongness = false;
								}
								if (fForte.isChecked()) {
									isDateEndStrongness = false;
								}

								try {
									event = new Event(tit.getText().toString(), pla.getText().toString(), start.getText().toString(), end.getText().toString(), descrip.getText().toString(), isDateStartStrongness, isDateEndStrongness);
									daoDatabase = new DAODatabase();
									daoDatabase.addEvent(event,agenda);map = new HashMap<String, Object>();
									map.put("titre", tit.getText().toString());
									map.put("place", pla.getText().toString());
									i++;
									list.add(map);
									sa.notifyDataSetChanged();
									listView.setAdapter(sa);

								} catch (AddEventException e) {
									e.printStackTrace();
									Toast.makeText(getApplicationContext(), "catch 1", Toast.LENGTH_SHORT).show();
								} catch (ParseException e) {
									e.printStackTrace();
									Toast.makeText(getApplicationContext(), "catch 2", Toast.LENGTH_SHORT).show();
								}

								Toast.makeText(getApplicationContext(), tit.getText().toString(), Toast.LENGTH_SHORT).show();
								//	Toast.makeText(getApplicationContext(),tit.getText().toString(), Toast.LENGTH_SHORT).show();


							}




//								Notification.Builder builder = new Notification.Builder(getApplicationContext());
//								Notification notification = builder
//										.setSmallIcon(R.mipmap.ic_launcher)
//										.setContentTitle("Intelligent agenda")
//										.setContentText("Un nouvel evenement à été ajouté " )
//										.build();
//								NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//								notificationManager.notify(0,notification);

						}
						)

						.setNegativeButton("quitter", null)
						.setCancelable(false);

				AlertDialog dialog = Builder.create();
				dialog.show();

			}

		});


		final ImageButton sms = (ImageButton) findViewById(R.id.sms);
		sms.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent smsIntent = new Intent(Intent.ACTION_VIEW);
				smsIntent.setData(Uri.parse("smsto:"));
				smsIntent.setType("vnd.android-dir/mms-sms");
				smsIntent.putExtra("address", new String("01234"));
				smsIntent.putExtra("sms_body", "Test ");
				try {
					startActivity(smsIntent);
					finish();
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(ActivityDay.this, "SMS failded please try again later.", Toast.LENGTH_SHORT).show();
				}

			}

		});

		final ImageButton phone = (ImageButton) findViewById(R.id.phone);
		phone.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent callIntent = new Intent(Intent.ACTION_VIEW);
				String tel = "0101010101";// Votre numéro de téléphone
				callIntent.setData(Uri.parse("tel:" + tel));
				startActivity(callIntent);

				try {
					startActivity(callIntent);
					finish();
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(ActivityDay.this, "call faild, please try again later.", Toast.LENGTH_SHORT).show();
				}

			}

		});


		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
				list.remove(position);
				sa.notifyDataSetChanged();
				daoDatabase.deleteEvent(agenda, event);
				return true;
			}
		});

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				final View view2 = LayoutInflater.from(ActivityDay.this).inflate(R.layout.event_information, null);
				AlertDialog.Builder Builder1 = new AlertDialog.Builder(ActivityDay.this);



				TextView des=(TextView)view2.findViewById(R.id.TextViewDescription);
				TextView ti=(TextView)view2.findViewById(R.id.TextViewTitre);
				TextView dd=(TextView)view2.findViewById(R.id.TextViewDateDebut);
				TextView df=(TextView)view2.findViewById(R.id.TextViewDateFin);
				TextView pl=(TextView)view2.findViewById(R.id.TextViewPlace);

				ti.setText(event.title);
				des.setText(event.description);
				pl.setText(event.place);
				dd.setText(event.startDate);
				df.setText(event.endDate);

				Builder1.setMessage("Votre evenement ")
						.setView(view2)
						.setNegativeButton("OK", null)
						.setCancelable(false);
				AlertDialog dialog = Builder1.create();
				dialog.show();





			}

		});

	}

	@Override
	protected void onStart() {
		super.onStart();
	}
}
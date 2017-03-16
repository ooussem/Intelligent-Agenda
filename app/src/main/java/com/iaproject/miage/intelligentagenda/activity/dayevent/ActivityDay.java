package com.iaproject.miage.intelligentagenda.activity.dayevent;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
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

import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.dao.DAODatabase;
import com.iaproject.miage.intelligentagenda.exception.AddEventException;
import com.iaproject.miage.intelligentagenda.feature.event.model.Agenda;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.iaproject.miage.intelligentagenda.R.layout.dialog;
import static com.iaproject.miage.intelligentagenda.R.layout.event_information;


public class ActivityDay extends AppCompatActivity {
<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java
	DAODatabase daoDatabase;
	Agenda agenda;

=======


	daoDatabase daoDatabase;
	DatabaseReference dref;
	Event event = null;
	public Agenda agenda ;
>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java
	ImageButton buttonAdd;
	SimpleAdapter sa = null;
	List<HashMap<String,Object>> list = null;
	HashMap<String,Object> map = null;
	ListView listView = null;
	static int i = 0;

<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java
=======
	public ActivityDay() throws AddEventException {
	}

>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day);
<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java
		daoDatabase = new DAODatabase();

=======
		agenda = Agenda.getInstance();
>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java

		this.agenda = new Agenda("programme","Nanterre");
		list = new ArrayList<>();
<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java

		String[] from = new String[]{"titre", "description"};
		int[] to = new int[]{R.id.activity_title_event, R.id.activity_title_descip};

		sa = new SimpleAdapter(this, list, R.layout.list_event, from, to);
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(sa);
=======
		String[] from = new String[]{"titre", "place","start","end","despcription"};
		int[] to = new int[]{R.id.activity_title_event, R.id.activity_place,R.id.activity_start,R.id.activity_end,R.id.activity_descrption};
		sa = new SimpleAdapter(this, list, R.layout.list_event, from, to);
		lv = (ListView) findViewById(R.id.listView);
		lv.setAdapter(sa);
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
>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java

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
<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java
								EditText descrip = (EditText)view.findViewById(R.id.editTextDescription);
								EditText tit = (EditText)view.findViewById(R.id.editTextTitle);
								EditText pla = (EditText)view.findViewById(R.id.editTextPlace);
								EditText start = (EditText)view.findViewById(R.id.editTextStart);
								EditText end = (EditText)view.findViewById(R.id.editTextEnd);
								CheckBox dForte=(CheckBox) view.findViewById(R.id.checkBoxStart);
								CheckBox fForte=(CheckBox) view.findViewById(R.id.checkBoxeEnd);
								boolean isDateStartStrongness=true;
								boolean isDateEndStrongness=true;

								if(dForte.isChecked()){
									isDateStartStrongness=false;
=======
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
									Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs S.V.P ...", Toast.LENGTH_SHORT).show();
									return;
>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java
								}
								if (dForte.isChecked()) {
									isDateStartStrongness = false;
								}
								if (fForte.isChecked()) {
									isDateEndStrongness = false;
								}
<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java

//								SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy hh:mm");
//								Date date1 ;
//								Date date2 ;
//								GregorianCalendar cal = new GregorianCalendar();
//								GregorianCalendar cal2 = new GregorianCalendar();
//								date1 = sdf.parse(start.getText().toString(),new ParsePosition(0));
//								date2 = sdf.parse(end.getText().toString(),new ParsePosition(0));
//								cal.setTime(date1);
//								cal2.setTime(date2);


								try {
									event = new Event(tit.getText().toString(), pla.getText().toString(),
											start.getText().toString(),end.getText().toString(),
											descrip.getText().toString(),isDateStartStrongness,isDateEndStrongness);
=======


								try {
									event = new Event(tit.getText().toString(), pla.getText().toString(), start.getText().toString(), end.getText().toString(), descrip.getText().toString(), isDateStartStrongness, isDateEndStrongness);
									daoDatabase = new daoDatabase();
									daoDatabase.addEvent(event, agenda);
>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java
								} catch (AddEventException e) {
									e.printStackTrace();
									Toast.makeText(getApplicationContext(), "catch 1", Toast.LENGTH_SHORT).show();
								} catch (ParseException e) {
									e.printStackTrace();
									Toast.makeText(getApplicationContext(), "catch 2", Toast.LENGTH_SHORT).show();
								}
<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java

								agenda.addEvent(event);
								daoDatabase.addEvent(agenda,event);


								Toast.makeText(getApplicationContext(),tit.getText().toString(), Toast.LENGTH_SHORT).show();

=======
								Toast.makeText(getApplicationContext(), tit.getText().toString(), Toast.LENGTH_SHORT).show();
								//	Toast.makeText(getApplicationContext(),tit.getText().toString(), Toast.LENGTH_SHORT).show();
>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java
								map = new HashMap<String, Object>();
								map.put("titre", tit.getText().toString());
								map.put("place", pla.getText().toString());
								i++;
								list.add(map);
								sa.notifyDataSetChanged();
<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java
								listView.setAdapter(sa);
							}
						})
=======
								lv.setAdapter(sa);

								Notification.Builder builder = new Notification.Builder(getApplicationContext());
								Notification notification = builder
										.setSmallIcon(R.mipmap.ic_launcher)
										.setContentTitle("Intelligent agenda")
										.setContentText("Un nouvel evenement à été ajouté " )
										.build();
								NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
								notificationManager.notify(0,notification);
							}
						}
						)
>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java
						.setNegativeButton("quitter", null)
						.setCancelable(false);
				AlertDialog dialog = Builder.create();
				dialog.show();

			}

		});

<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
=======
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


		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
				list.remove(position);
				sa.notifyDataSetChanged();
				daoDatabase.deleteEvent(agenda,event);
				//agenda.deleteEvent(event);
				return true;
			}
		});
<<<<<<< HEAD:app/src/main/java/com/iaproject/miage/intelligentagenda/activity/dayevent/ActivityDay.java


		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
			// sans code
=======
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				final View view2 = LayoutInflater.from(ActivityDay.this).inflate(event_information, null);
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



>>>>>>> ed9000f2436311df8441a89a9c920210eb8eb213:app/src/main/java/com/iaproject/miage/intelligentagenda/activityday/ActivityDay.java

			}

		});

	}

	@Override
	protected void onStart() {
		super.onStart();
	}
}
package com.iaproject.miage.intelligentagenda.activityday;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.database.daoDatabase;
import com.iaproject.miage.intelligentagenda.exception.AddEventException;
import com.iaproject.miage.intelligentagenda.feature.event.model.Agenda;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.iaproject.miage.intelligentagenda.R.layout.dialog;



public class ActivityDay extends AppCompatActivity {
	daoDatabase daoDatabase;
	DatabaseReference dref;
	private Agenda agenda=new Agenda("programme");

	ImageButton buttonAdd;
	Event event;
	SimpleAdapter sa = null;
	List<HashMap<String, Object>> list = null;
	HashMap<String, Object> map = null;
	ListView lv = null;
	static int i = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day);

		list = new ArrayList<>();
		String[] from = new String[]{"titre", "description"};
		int[] to = new int[]{R.id.activity_title_event, R.id.activity_title_descip};
		sa = new SimpleAdapter(this, list, R.layout.list_event, from, to);
		lv = (ListView) findViewById(R.id.listView);
		lv.setAdapter(sa);
		dref= FirebaseDatabase.getInstance().getReference();
		dref.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				String value=dataSnapshot.getValue(String.class);

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

								EditText descrip = (EditText)view.findViewById(R.id.editTextDescription);
								EditText tit = (EditText)view.findViewById(R.id.editTextTitle);
								EditText pla = (EditText)view.findViewById(R.id.editTextPlace);
								EditText start = (EditText)view.findViewById(R.id.editTextStart);
								EditText  end = (EditText)view.findViewById(R.id.editTextEnd);
								CheckBox dForte=(CheckBox) view.findViewById(R.id.checkBoxStart);
								CheckBox fForte=(CheckBox) view.findViewById(R.id.checkBoxeEnd);
								boolean isDateStartStrongness=true;
								boolean isDateEndStrongness=true;



								if(dForte.isChecked()){
									isDateStartStrongness=false;
								}
								if(fForte.isChecked()){
									isDateEndStrongness=false;
								}

							/*	SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy hh:mm");
								Date date1 ;
								Date date2 ;
								GregorianCalendar cal = new GregorianCalendar();
								GregorianCalendar cal2 = new GregorianCalendar();
								date1 = sdf.parse(start.getText().toString(),new ParsePosition(0));
								date2 = sdf.parse(end.getText().toString(),new ParsePosition(0));
								cal.setTime(date1);
								cal2.setTime(date2);*/



								try {
									event = new Event(tit.getText().toString(), pla.getText().toString(),start.getText().toString(),end.getText().toString(), descrip.getText().toString(),isDateStartStrongness,isDateEndStrongness);
									agenda.addEvent(event);
									daoDatabase=new daoDatabase();
									daoDatabase.addEvent(event,agenda);

								} catch (AddEventException e) {
									e.printStackTrace();
								} catch (ParseException e) {
									e.printStackTrace();
								}

								Toast.makeText(getApplicationContext(),tit.getText().toString(), Toast.LENGTH_SHORT).show();

								map = new HashMap<String, Object>();
								map.put("titre", tit.getText().toString());
								i++;
								list.add(map);
								sa.notifyDataSetChanged();
								lv.setAdapter(sa);


							}

						})

						.setNegativeButton("quitter", null)

						.setCancelable(false);

				AlertDialog dialog = Builder.create();

				dialog.show();

			}

		});

		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
				list.remove(position);
				sa.notifyDataSetChanged();
				daoDatabase.deleteEvent();
				agenda.deleteEvent(event);
				return true;
			}

		});
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override

			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
// sans code

			}

		});

	}

}
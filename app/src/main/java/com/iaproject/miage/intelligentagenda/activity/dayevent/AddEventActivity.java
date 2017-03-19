package com.iaproject.miage.intelligentagenda.activity.dayevent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.activity.home.HomeActivity;
import com.iaproject.miage.intelligentagenda.exception.AddEventException;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;
import com.iaproject.miage.intelligentagenda.service.ServiceDatabase;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;



public class AddEventActivity extends AppCompatActivity {
	EditText description;
	EditText title;
	EditText place;
	EditText transportMode;
	CheckBox dateStartWeak;
	CheckBox dateEndWeak;
	TextView dateStart;
	TextView dateEnd;
	TextView timeStart;
	TextView timeEnd;
	Calendar currentDate = Calendar.getInstance();
	Button validate;
	ServiceDatabase serviceDatabase;
	private boolean isAllFinished = false;
	public Event event;
	String titleAgenda = null;
	Intent intentFromHomeActivity;
	StringBuilder start;
	StringBuilder end;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);
		serviceDatabase = new ServiceDatabase();

		this.intentFromHomeActivity = getIntent();
//		this.titleAgenda = intentFromHomeActivity.getStringExtra(KEY_INTENT_ACTIVITY_ADD);

		this.description = (EditText)findViewById(R.id.activity_add_event_edit_description);
		this.title = (EditText)findViewById(R.id.activity_add_event_edit_title);
		this.place = (EditText)findViewById(R.id.activity_add_event_edit_place);
		this.transportMode = (EditText)findViewById(R.id.activity_add_event_transport_mode);
		this.dateStartWeak = (CheckBox) findViewById(R.id.activity_add_event_checkbox_start);
		this.dateEndWeak = (CheckBox) findViewById(R.id.activity_add_event_checkbox_end);
		this.dateStart = (TextView) findViewById(R.id.activity_add_event_date_start);
		this.dateEnd = (TextView) findViewById(R.id.activity_add_event_date_end);
		this.timeStart = (TextView) findViewById(R.id.activity_add_event_time_start);
		this.timeEnd = (TextView) findViewById(R.id.activity_add_event_time_end);
		this.validate = (Button)findViewById(R.id.activity_add_event_ok);

		dateStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateDateStart();
			}
		});

		dateEnd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateDateEnd();
			}
		});

		timeStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateTimeStart();
			}
		});

		timeEnd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateTimeEnd();
			}
		});

		final Intent intentToHome = new Intent(getApplicationContext(), HomeActivity.class);
		validate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (TextUtils.isEmpty(title.getText().toString()) || TextUtils.isEmpty(place.getText().toString())
						|| TextUtils.isEmpty(dateStart.getText().toString()) || TextUtils.isEmpty(dateEnd.getText().toString())
						|| TextUtils.isEmpty(description.getText().toString()) ||  TextUtils.isEmpty(timeStart.getText().toString())
						|| TextUtils.isEmpty(timeEnd.getText().toString())) {
					Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
					isAllFinished = false;
					return;
				}else isAllFinished = true;

				try {
					boolean isDateStartStrongness = true ;
					boolean isDateEndStrongness = true;
					if (dateStartWeak.isChecked()) {
						isDateStartStrongness = false;
					}
					if (dateEndWeak.isChecked()) {
						isDateEndStrongness = false;
					}

					if(isAllFinished) {
						start = new StringBuilder();
						end = new StringBuilder();
						start.append(dateStart.getText().toString()+ " ");
						start.append(timeStart.getText().toString());
						end.append(dateEnd.getText().toString()+ " ");
						end.append(timeEnd.getText().toString());

						Log.d("start", start.toString().substring(0,10));
						Log.d("start complete", start.toString());


						event = new Event(title.getText().toString(), place.getText().toString(),
								start.toString(), end.toString(), description.getText().toString(), isDateStartStrongness, isDateEndStrongness,
								transportMode.getText().toString());
						Log.d("EVENT", start.toString());

						if(serviceDatabase.addEvent(start.toString().replace("/", " ").substring(0,10), event))
							startActivity(intentToHome);
						else
							Toast.makeText(getApplicationContext(), "Cette activité se chevauche avec une autre", Toast.LENGTH_SHORT);

//						Toast.makeText(getApplicationContext(), "Problème d'accès à la base de donnée", Toast.LENGTH_SHORT);

//						map = new HashMap<String, Object>();
//						map.put("titre", tit.getText().toString());
//						map.put("place", pla.getText().toString());
//						i++;
//						list.add(map);
//						sa.notifyDataSetChanged();
//						listView.setAdapter(sa);
					}

				} catch (AddEventException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "La date de début doit être avant la date de fin", Toast.LENGTH_SHORT).show();
				} catch (ParseException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Mauvais parsage de la date", Toast.LENGTH_SHORT).show();
					isAllFinished = false;
				}finally {

				}
			}
		});

	}




	@Override
	protected void onStart() {
		super.onStart();
	}



	private void updateDateStart() {
		new DatePickerDialog(this, dateStartSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),
				currentDate.get(Calendar.DAY_OF_MONTH)).show();
	}

	private void updateDateEnd() {
		new DatePickerDialog(this, dateEndSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),
				currentDate.get(Calendar.DAY_OF_MONTH)).show();
	}

	private void updateTimeStart() {
		new TimePickerDialog(this, timeStartSetListener, currentDate.get(Calendar.HOUR_OF_DAY),
				currentDate.get(Calendar.MINUTE), true)
				.show();
	}

	private void updateTimeEnd() {
		new TimePickerDialog(this, timeEndSetListener, currentDate.get(Calendar.HOUR_OF_DAY),
				currentDate.get(Calendar.MINUTE), true)
				.show();
	}


	DatePickerDialog.OnDateSetListener dateStartSetListener = new DatePickerDialog.OnDateSetListener(){
		@Override
		public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
			dateStart.setText(String.format(Locale.FRANCE,"%02d/%02d/%d", dayOfMonth, month+1, year).toUpperCase());
		}
	};
	DatePickerDialog.OnDateSetListener dateEndSetListener = new DatePickerDialog.OnDateSetListener(){
		@Override
		public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
			dateEnd.setText(String.format(Locale.FRANCE,"%2d/%02d/%d", dayOfMonth, month+1, year).toUpperCase());
		}
	};

	TimePickerDialog.OnTimeSetListener timeStartSetListener = new TimePickerDialog.OnTimeSetListener(){
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			timeStart.setText(String.format(Locale.FRANCE,"%02d:%02d",hourOfDay,minute));
		}
	};
	TimePickerDialog.OnTimeSetListener timeEndSetListener = new TimePickerDialog.OnTimeSetListener(){
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			timeEnd.setText(String.format(Locale.FRANCE,"%02d:%02d",hourOfDay,minute));
		}
	};


}

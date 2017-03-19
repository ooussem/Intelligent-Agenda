package com.iaproject.miage.intelligentagenda.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iaproject.miage.intelligentagenda.R;
import com.iaproject.miage.intelligentagenda.activity.dayevent.AddEventActivity;
import com.iaproject.miage.intelligentagenda.activity.dayevent.PlanningActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
	MaterialCalendarView materialCalendarView;
	Button buttonAddEvent;
	Button buttonSeeEvent;
	Intent intentCalendarView;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);


		/**
		 * Display MaterialCalendar view
		 */
		materialCalendarView = (MaterialCalendarView)this.findViewById(R.id.activity_home_calendar_view);
		materialCalendarView.state().edit()
				.setFirstDayOfWeek(Calendar.MONDAY)
				.setMinimumDate(CalendarDay.from(1900, 1, 1))
				.setMaximumDate(CalendarDay.from(2100, 12, 31))
				.setCalendarDisplayMode(CalendarMode.MONTHS)
				.commit();

		materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
			@Override
			public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
				Log.d("CalendarDay date", "date :" +date);
			}
		});

		buttonAddEvent = (Button)findViewById(R.id.activity_home_button_add);
		buttonAddEvent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				intentCalendarView = new Intent(getApplicationContext(), AddEventActivity.class);
				startActivity(intentCalendarView);
			}
		});

		buttonSeeEvent = (Button)findViewById(R.id.activity_home_button_see_events);
		buttonSeeEvent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				intentCalendarView = new Intent(getApplicationContext(), PlanningActivity.class);
				startActivity(intentCalendarView);
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
	}


}

package com.iaproject.miage.intelligentagenda.feature.event.model;


import com.google.firebase.database.Exclude;
import com.iaproject.miage.intelligentagenda.exception.AddEventException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Event implements Comparable<Event>{

	public String title;
	public String description;
	public String place;
	public boolean isDateStartStrongness = true;
	public boolean isDateEndStrongness = true;
	public String startDate;
	public String endDate;
	public String transportMode;
	@Exclude
	public GregorianCalendar dateStart;
	@Exclude
	public GregorianCalendar dateEnd;
	@Exclude
	public SimpleDateFormat sdf;

	public Event() {
	}

	public Event(String title, String place, String startDate, String endDate, String description,
	             Boolean isDateStartStrongness, Boolean isDateEndStrongness, String transportMode)
			throws AddEventException, ParseException {

		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);

		this.title = title;
		this.place = place;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isDateEndStrongness = isDateEndStrongness;
		this.isDateStartStrongness = isDateStartStrongness;
		this.transportMode = transportMode;
		Date dd = sdf.parse(startDate);
		Date de = sdf.parse(endDate);
		this.dateStart = new GregorianCalendar();
		this.dateEnd = new GregorianCalendar();
		this.dateStart.setTime(dd);
		this.dateEnd.setTime(de);
		if(!dateStart.before(dateEnd)) {
			throw new AddEventException();
		}
	}

	public Event(String title, String place, String startDate, String endDate, String description,
	             Boolean isDateStartStrongness,Boolean isDateEndStrongness)
			throws AddEventException, ParseException {
		this(title, place,startDate, endDate, description,isDateStartStrongness, isDateEndStrongness,"");
	}

	public Event(Event e)throws AddEventException, ParseException {
		this(e.title, e.place,e.startDate, e.endDate, e.description,e.isDateStartStrongness, e.isDateEndStrongness,e.description);
	}



	@Override
	public int compareTo(Event event) {
		return this.dateStart.compareTo(event.dateStart);
	}


	public boolean equals(Object obj) {
		Event event = (Event) obj;
		if(event == this) return true;
		if(this.dateStart.compareTo(event.dateStart)==0 &&
				this.dateEnd.compareTo(event.dateEnd)==0) return true;
		else
			return false;

	}

}



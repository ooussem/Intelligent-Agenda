package com.iaproject.miage.intelligentagenda.feature.event.model;


import com.google.firebase.database.Exclude;
import com.iaproject.miage.intelligentagenda.exception.AddEventException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Event implements Comparable<Event>{

	public String title;
	public String description;
	@Exclude
	public GregorianCalendar dateStart;
	@Exclude
	public GregorianCalendar dateEnd;
	public String place;
	public boolean isDateStartStrongness=true;
	public boolean isDateEndStrongness=true;
	public String startDate;
	public String endDate;
	@Exclude
	public SimpleDateFormat sdf;
	public Interlocutor interlocutor;



//	public Event(String title, String place, Calendar dateStart, Calendar dateEnd, String description,
//	             boolean isDateStartStrongness, boolean isDateEndStrongness, String transport, Interlocutor interlocutor)
//		throws AddEventException {
//		this.title = title;
//		this.place = place;
//		this.description = description;
//		this.transport = transport;
//		this.interlocutor = interlocutor;
//		if(dateStart.before(dateEnd)){
//			this.dateStart = dateStart;
//			this.dateEnd = dateEnd;
//			this.isDateEndStrongness = true;
//			this.isDateStartStrongness = true;
//			sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE);
//			startDate= sdf.format(dateStart.getTime());
//			endDate= sdf.format(dateEnd.getTime());
//		}
//		else
//			throw new AddEventException();
//	}

	public Event(String title, String place, String startDate, String endDate, String description,
	             Boolean isDateStartStrongness,Boolean isDateEndStrongness )
			throws AddEventException, ParseException {

		this.title = title;
		this.place = place;
		this.description = description;
		sdf = new SimpleDateFormat("dd MM yyyy HH:mm");
		this.startDate = startDate;
		this.endDate = endDate;
		this.isDateEndStrongness = isDateEndStrongness;
		this.isDateStartStrongness = isDateStartStrongness;
		Date dd = sdf.parse(startDate);
		Date de = sdf.parse(endDate);
		this.dateStart=new GregorianCalendar();
		this.dateEnd=new GregorianCalendar();
		this.dateStart.setTime(dd);
		this.dateEnd.setTime(de);
		this.isDateEndStrongness = true;
		this.isDateStartStrongness = true;
		if(dateStart.before(dateEnd)) {
			this.isDateEndStrongness = true;
			this.isDateStartStrongness = true;

		}
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



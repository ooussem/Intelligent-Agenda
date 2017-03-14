package com.iaproject.miage.intelligentagenda.feature.event.model;

import com.iaproject.miage.intelligentagenda.exception.AddEventException;

import java.util.Calendar;

/**
 * Created by OOussema on 25/02/2017.
 */

public class Event implements Comparable<Event> {
	public String title;
	public String description;

	public Calendar dateStart;
	public Calendar dateEnd;
	public String place;
	public String transport;
	public boolean isDateStartStrongness;
	public boolean isDateEndStrongness;
	public Interlocutor interlocutor;



	public Event(String title, String place, Calendar dateStart, Calendar dateEnd, String description,
	             boolean isDateStartStrongness, boolean isDateEndStrongness, String transport, Interlocutor interlocutor)
			throws AddEventException {
		this.title = title;
		this.place = place;
		this.description = description;
		this.transport = transport;
		this.interlocutor = interlocutor;
		if(dateStart.before(dateEnd)){
			this.dateStart = dateStart;
			this.dateEnd = dateEnd;
			this.isDateEndStrongness = isDateStartStrongness;
			this.isDateStartStrongness = isDateEndStrongness;
		}
		else
			throw new AddEventException();
	}


	@Override
	public int compareTo(Event event) {
		return this.dateStart.compareTo(event.dateStart);
	}


	@Override
	public boolean equals(Object obj) {
		Event event = (Event) obj;

		if(event == this) return true;

		if(this.dateStart.compareTo(event.dateStart)==0 &&
				this.dateEnd.compareTo(event.dateEnd)==0) return true;

		else
			return false;
	}


}

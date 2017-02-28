package com.iaproject.miage.intelligentagenda.feature.event.model;

import java.util.Date;

/**
 * Created by OOussema on 25/02/2017.
 */

public class Event {
	public String title;
	public Date start;
	public Date end;
	public String description;


	public Event(String title, Date start, Date end, String description) {
		this.title = title;
		this.description = description;
		if(start.before(end)){
			this.start = start;
			this.end = end;
		}
	}


}

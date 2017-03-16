package com.iaproject.miage.intelligentagenda.test;

import com.iaproject.miage.intelligentagenda.feature.event.model.Agenda;
import com.iaproject.miage.intelligentagenda.feature.event.model.Course;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by OOussema on 25/02/2017.
 */

public class AgendaTest {
	Agenda agenda;
	Calendar e1Start;
	Calendar e1End;
	Calendar e2Start;
	Calendar e2End;
	Calendar e3Start;
	Calendar e3End;
	Calendar e4Start;
	Calendar e4End;
	Calendar e5Start;
	Calendar e5End;
	Calendar e6Start;
	Calendar e6End;
	Calendar e7Start;
	Calendar e7End;
	Calendar e8Start;
	Calendar e8End;
	Event e1;
	Event e2;
	Event e3;
	Event e4;
	Event e5;
	Event e6;
	Event e7;
	Event e8;
	List<Event> eventList;

	@Before
	public void setUp() throws Exception {
		agenda = Agenda.getInstance("My agenda","Nanterre");

//		e1Start = new GregorianCalendar(2017, 2, 13, 14, 00);
//		e1End = new GregorianCalendar(2017, 2, 13, 15, 00);
//
//		e2Start = new GregorianCalendar(2017, 2, 14, 9, 00);
//		e2End = new GregorianCalendar(2017, 2, 14, 12, 00);
//
//		e3Start = new GregorianCalendar(2017, 4, 14, 13, 00);
//		e3End = new GregorianCalendar(2017, 4, 14, 14, 00);
//
//		e4Start = new GregorianCalendar(2017, 2, 15, 9, 00);
//		e4End = new GregorianCalendar(2017, 2, 15, 12, 00);
//
//		e5Start = new GregorianCalendar(2017, 5, 15, 9, 00);
//		e5End = new GregorianCalendar(2017, 5, 15, 12, 00);
//
//		e6Start = new GregorianCalendar(2017, 5, 15, 14, 30);
//		e6End = new GregorianCalendar(2017, 5, 15, 15, 00);
//
//		e7Start = new GregorianCalendar(2017, 2, 13, 12, 30);
//		e7End = new GregorianCalendar(2017, 2, 13, 14, 15);
//
//		e8Start = new GregorianCalendar(2017, 5, 15, 13, 30);
//		e8End = new GregorianCalendar(2017, 5, 15, 14, 45);

		e1 = new Event("Evenement 1","Nanterre","1302201714:00","1302201715:00", "Rendez vous pour un entretien 1",true,true);
		e2 = new Event("Evenement 2","Nanterre","140220179:00", "1402201712:00", "Rendez vous pour un entretien 2",true,true);
		e3 = new Event("Evenement 3","Nanterre","1402201713:00","1402201714:00", "Rendez vous pour un entretien 3",true,true);
		e4 = new Event("Evenement 4","Nanterre","150220179:00","1502201712:00", "Rendez vous pour un entretien 4",true,true);
		e5 = new Event("Evenement 5","Nanterre","150520179:00","1505201712:00", "Rendez vous pour un entretien 5",true,true);
		e6 = new Event("Evenement 6","Nanterre","1505201714:30","1505201715:00", "Rendez vous pour un entretien 6",true,true);
		e7 = new Event("Evenement 7","Nanterre","1302201712:30","1302201714:15", "Rendez vous pour un entretien 7",true,true);
		e8 = new Event("Evenement 8","Nanterre","1505201713:30","1505201714:45", "Rendez vous pour un entretien 8",true,true);
		e1.isDateStartStrongness = false;
		e5.isDateEndStrongness=false;
		e6.isDateStartStrongness = false;
	}


	@After
	public void tearDown() throws Exception {
		agenda = null;
		e1 = null;
		e2 = null;
		e3 = null;
		e1Start = null;
		e1End = null;
		e2Start = null;
		e2End = null;
		e3Start = null;
		e3End = null;
		eventList = null;
	}




	@Test
	public void addEvent() throws Exception {
		Assert.assertEquals(1,e3.compareTo(e1));
		Assert.assertEquals(1,e3.compareTo(e2));

		Assert.assertEquals(true,agenda.addEvent(e1,e1.startDate));
		Assert.assertEquals(true,agenda.addEvent(e2,e2.startDate));
		Assert.assertEquals(false,agenda.addEvent(e1,e1.startDate));
		Assert.assertEquals(false,agenda.addEvent(e1,e1.startDate));
		Assert.assertEquals(false,agenda.addEvent(e2,e2.startDate));
		Assert.assertEquals(true,agenda.addEvent(e3,e3.startDate));
		Assert.assertEquals(true,agenda.addEvent(e4,e4.startDate));
		Assert.assertEquals(false,agenda.addEvent(e3,e3.startDate));
		Assert.assertEquals(true,agenda.addEvent(e5,e5.startDate));
		Assert.assertEquals(true,agenda.addEvent(e6,e6.startDate));
		Assert.assertEquals(true,agenda.addEvent(e7,e7.startDate));
		Assert.assertEquals(true,agenda.addEvent(e8,e8.startDate));


		System.out.println("Size = " +agenda.courses.size());

		for(Map.Entry<String,Course> course: agenda.courses.entrySet()){
			System.out.println("\n" +course.getKey()+ " : " +course.getValue().date);
			for(Event event : course.getValue().listEvents){
				System.out.println("Event = " +event.title);
			}
		}
	}


}
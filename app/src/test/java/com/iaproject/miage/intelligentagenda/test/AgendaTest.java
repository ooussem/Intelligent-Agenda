package com.iaproject.miage.intelligentagenda.test;

import com.iaproject.miage.intelligentagenda.feature.event.model.Agenda;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by OOussema on 25/02/2017.
 */

public class AgendaTest {
	Agenda agenda;
	GregorianCalendar e1Start;
	GregorianCalendar e1End;
	GregorianCalendar e2Start;
	GregorianCalendar e2End;
	GregorianCalendar e3Start;
	GregorianCalendar e3End;
	GregorianCalendar e4Start;
	GregorianCalendar e4End;
	GregorianCalendar e5Start;
	GregorianCalendar e5End;
	GregorianCalendar e6Start;
	GregorianCalendar e6End;
	GregorianCalendar e7Start;
	GregorianCalendar e7End;
	GregorianCalendar e8Start;
	GregorianCalendar e8End;
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
		agenda = new Agenda("My agenda");

		e1Start = new GregorianCalendar(2017, 2, 13, 14, 00);
		e1End = new GregorianCalendar(2017, 2, 13, 15, 00);

		e2Start = new GregorianCalendar(2017, 2, 14, 9, 00);
		e2End = new GregorianCalendar(2017, 2, 14, 12, 00);

		e3Start = new GregorianCalendar(2017, 4, 14, 13, 00);
		e3End = new GregorianCalendar(2017, 4, 14, 14, 00);

		e4Start = new GregorianCalendar(2017, 2, 15, 9, 00);
		e4End = new GregorianCalendar(2017, 2, 15, 12, 00);

		e5Start = new GregorianCalendar(2017, 5, 15, 9, 00);
		e5End = new GregorianCalendar(2017, 5, 15, 12, 00);

		e6Start = new GregorianCalendar(2017, 5, 15, 14, 30);
		e6End = new GregorianCalendar(2017, 5, 15, 15, 00);

		e7Start = new GregorianCalendar(2017, 2, 13, 12, 30);
		e7End = new GregorianCalendar(2017, 2, 13, 14, 15);

		e8Start = new GregorianCalendar(2017, 5, 15, 13, 30);
		e8End = new GregorianCalendar(2017, 5, 15, 14, 45);

		e1 = new Event("Evenement 1","Nanterre",e1Start,e1End, "Rendez vous pour un entretien 1",true,true,"Nanterre");
		e2 = new Event("Evenement 2","Nanterre",e2Start,e2End, "Rendez vous pour un entretien 2",true,true,"Nanterre");
		e3 = new Event("Evenement 3","Nanterre",e3Start,e3End, "Rendez vous pour un entretien 3",true,true,"Nanterre");
		e4 = new Event("Evenement 4","Nanterre",e4Start,e4End, "Rendez vous pour un entretien 4",true,true,"Nanterre");
		e5 = new Event("Evenement 5","Nanterre",e5Start,e5End, "Rendez vous pour un entretien 5",true,true,"Nanterre");
		e6 = new Event("Evenement 6","Nanterre",e6Start,e6End, "Rendez vous pour un entretien 6",true,true,"Nanterre");
		e7 = new Event("Evenement 7","Nanterre",e7Start,e7End, "Rendez vous pour un entretien 7",true,true,"Nanterre");
		e8 = new Event("Evenement 8","Nanterre",e8Start,e8End, "Rendez vous pour un entretien 8",true,true,"Nanterre");
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

		Assert.assertEquals(true,agenda.addEvent(e1));
		Assert.assertEquals(true,agenda.addEvent(e2));
		Assert.assertEquals(false,agenda.addEvent(e1));
		Assert.assertEquals(false,agenda.addEvent(e1));
		Assert.assertEquals(false,agenda.addEvent(e2));
		Assert.assertEquals(true,agenda.addEvent(e3));
		Assert.assertEquals(true,agenda.addEvent(e4));
		Assert.assertEquals(false,agenda.addEvent(e3));
		Assert.assertEquals(true,agenda.addEvent(e5));
		Assert.assertEquals(true,agenda.addEvent(e6));
		Assert.assertEquals(true,agenda.addEvent(e7));
		Assert.assertEquals(true,agenda.addEvent(e8));


		System.out.println("Size = " +agenda.listEvent.size());

		for(Event event: agenda.listEvent.subList(0,agenda.listEvent.size())){
			System.out.println(event.title+ ", hd = " +event.dateStart.getTime()+ ", he = " +event.dateEnd.getTime());
		}
	}


//	@Test
//	public void deleteEvent() throws Exception {
//		agenda.deleteEvent(e1);
//		boolean testDel = agenda.listEvent.contains(e1);
//		Assert.assertEquals(false, testDel);
//	}



	@Test
	public void checkOverlapEvent() throws Exception {


	}

}
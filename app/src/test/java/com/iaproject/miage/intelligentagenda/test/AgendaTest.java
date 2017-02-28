package com.iaproject.miage.intelligentagenda.test;

import com.iaproject.miage.intelligentagenda.feature.event.model.Agenda;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by OOussema on 25/02/2017.
 */

public class AgendaTest {
	Agenda agenda;
	Date e1Dstart;
	Date e1Dend;
	Date e2Dstart;
	Date e2Dend;
	Event e1;
	Event e2;


	@Before
	public void setUp() throws Exception {
		agenda = new Agenda("My agenda");
		e1Dstart = new Date(2017, 2, 13, 14, 20);
		e1Dend = new Date(2017, 2, 13, 15, 00);
		e1 = new Event("Rendez vous pro",e1Dstart,e1Dend, "Rendez vous pour un entretien");
		e2Dstart = new Date(2017, 2, 14, 9, 00);
		e2Dend = new Date(2017, 2, 14, 12, 00);
		e2 = new Event("Rendez vous RH",e2Dstart,e2Dend, "Rendez vous pour un candidat");
	}

	@After
	public void tearDown() throws Exception {
		agenda = null;
		e1Dstart = null;
		e1Dend = null;
	}

	@Test
	public void addEvent() throws Exception {
		boolean testAdd = agenda.addEvent(e1);
		Assert.assertSame(true,testAdd);
	}

	@Test
	public void deleteEvent() throws Exception {
		agenda.deleteEvent(e1);
		boolean testDel = agenda.listEvent.contains(e1);
		Assert.assertEquals(false, testDel);
	}

	@Test
	public void checkOverlapEvent() throws Exception {

	}

}
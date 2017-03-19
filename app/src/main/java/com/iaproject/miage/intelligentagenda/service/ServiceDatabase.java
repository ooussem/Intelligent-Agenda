package com.iaproject.miage.intelligentagenda.service;

import android.app.Activity;

import com.iaproject.miage.intelligentagenda.dao.DAOAuthentification;
import com.iaproject.miage.intelligentagenda.dao.DAODatabase;
import com.iaproject.miage.intelligentagenda.feature.event.model.Event;

/**
 * Created by OOussema on 17/03/2017.
 */

public class ServiceDatabase {

	public static void login(Activity activity, String email, String password){
		DAOAuthentification daoAuthentification = new DAOAuthentification(activity);
		daoAuthentification.userLogin(email,password);
	}




	/**
	 * Call the method addEvent in DAODatabase class to add an event in Firebase
	 * @param event : Event.
	 * @param keyDate : String. It's the key to add an event for a particular day.
	 */
	public boolean addEvent(String keyDate,Event event){
		DAODatabase daoDatabase = DAODatabase.getInstance();
		return daoDatabase.addEvent(keyDate,event);
	}


//	public static List<Event> getEvents(String keyDate){
//		DAODatabase daoDatabase = DAODatabase.getInstance();
//		return daoDatabase.getEvents(keyDate);
//	}


}

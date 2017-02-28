package com.iaproject.miage.intelligentagenda.feature.event.model;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by OOussema on 25/02/2017.
 */

public class Agenda {
	public String titleAgenda;
	public List<Event> listEvent;

	public Agenda(String titleAgenda) {
		this.titleAgenda = titleAgenda;
		this.listEvent = new ArrayList<>();
	}

	/***
	 * Permet d'ajouter un évenement dans la liste
	 * @param e : c'est l'évenement qui sera ajouté
	 * @return
	 */
	public boolean addEvent(Event e){
		boolean result = false;
		try{
			if(!checkOverlapEvent(e)) {
				result = this.listEvent.add(e);
			}
		}catch (Exception exp){

		}
		return result;
	}

	/***
	 * Permet de supprimer un évenement de la liste
	 * @param e : c'est l'évenement selectionné
	 * @return
	 */
	public boolean deleteEvent(Event e){
		boolean result = false;
		try{
			result = this.listEvent.remove(e);
		}catch (Exception exp){

		}
		return result;
	}

	/***
	 * Vérifie si deux évenements ne sont pas parallèles
	 */
	public boolean checkOverlapEvent(Event e){
		boolean result = false;
		for(Event event : listEvent){
			if(e.end.before(event.start)) return false;
		}
		return result;
	}


}

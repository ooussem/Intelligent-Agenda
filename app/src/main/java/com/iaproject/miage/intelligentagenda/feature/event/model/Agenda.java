package com.iaproject.miage.intelligentagenda.feature.event.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by OOussema on 25/02/2017.
 */

public class Agenda {
	public  Agenda agenda ;
	public String titleAgenda;
	public String basePlace;
	public List<Event> listEvent;
	public Map<Calendar, List<Traject>> courses;
	public static Agenda instance = null;


	private Agenda(String titleAgenda,String basePlace){
		this.titleAgenda = titleAgenda;
		this.listEvent = new ArrayList<>();
		this.basePlace = basePlace;
		courses = new HashMap<>();
	}

	/** Point d'accès pour l'instance unique du singleton */
	public static Agenda getInstance(String titleAgenda,String basePlace) {
		if(instance == null){
			return instance = new Agenda(titleAgenda,basePlace);
		}
		else return instance;
	}







	/**
	 * Permet d'ajouter un évenement dans la liste
	 * @param event : c'est l'évenement qui sera ajouté
	 * @return
	 */
	public boolean addEvent(Event event){
		boolean result = false;
		try {
			if(this.listEvent.size() == 0) return this.listEvent.add(event);
			else if (!checkOverlapEvent(event)) {
				result = this.listEvent.add(event);
				Collections.sort(listEvent);

				/*  Quand j'ajoute un nouvel évenemnt il faut que j'ajoutte un nouveau trajet
					Comment savoir si j'ai un seul trajet ou un parcours
					On se base sur le fait que une journée = un parours
					Un parcours possède 1 ou plusieurs trajets
					Un trajet est caractérisé par 2 lieux, une distance, un mode de transport et un temps
					On identifie un parcours par le début de sa date yyyyMMdd.

					Je cherche le jours yyyyMMdd
					S'il y a déjà un évenement à cette date
							alors j'ajoute un trajet au parcours
				        sinon
				            J'instancie un nouveau parcours avec comme clé la date
				            J'ajoute le trajet à ce parcours
				 */
			}
		}
		catch (Exception exp){

		}
		return result;
	}

	/**
	 * Permet de supprimer un évenement de la liste
	 * @param event : c'est l'évenement selectionné
	 * @return
	 */
	public boolean deleteEvent(Event event){
		boolean result = false;
		try{
			result = this.listEvent.remove(event);
		}catch (Exception exp){

		}
		return result;
	}

	/**
	 * Vérifie si deux évenements ne sont pas parallèles
	 */
	public boolean checkOverlapEvent(Event otherEvent){
		boolean result = true;
		Event previousEvent = this.listEvent.get(0);
		if(otherEvent.dateEnd.before(previousEvent.dateStart) || (this.listEvent.size()==1
				&& otherEvent.dateStart.getTimeInMillis()>=(previousEvent.dateEnd.getTimeInMillis()+previousEvent.dateStart.getTimeInMillis())/2))

			if(otherEvent.isDateStartStrongness && !previousEvent.isDateEndStrongness) return false;
			else if(otherEvent.dateStart.after(previousEvent.dateEnd)) return false;
			else return true;

		else if (otherEvent.equals(previousEvent)) return true;

		else if(otherEvent.isDateEndStrongness && !previousEvent.isDateStartStrongness
				&& otherEvent.dateEnd.getTimeInMillis()<=(previousEvent.dateEnd.getTimeInMillis()+previousEvent.dateStart.getTimeInMillis())/2)
				return false;

		for(Event nextEvent : this.listEvent.subList(1,this.listEvent.size())){
			if(nextEvent.equals(otherEvent)) return true;
			else if((otherEvent.dateStart.after(previousEvent.dateEnd) && (otherEvent.dateEnd.before(nextEvent.dateStart))))
				return false;
			else if(otherEvent.dateStart.after(nextEvent.dateEnd)){
				result = false;
				continue;
			}

			else if(previousEvent.isDateEndStrongness && nextEvent.isDateStartStrongness
					&& nextEvent.isDateEndStrongness) {
				result = true;
				continue;
			}

			else if(otherEvent.dateStart.getTimeInMillis()>=(previousEvent.dateEnd.getTimeInMillis()
															+previousEvent.dateStart.getTimeInMillis())/2 &&
					otherEvent.dateEnd.getTimeInMillis()<=(nextEvent.dateEnd.getTimeInMillis()
							+nextEvent.dateStart.getTimeInMillis())/2) {

					if(otherEvent.isDateStartStrongness && !previousEvent.isDateEndStrongness){
						result = false;
					}
					if(otherEvent.isDateEndStrongness && !nextEvent.isDateStartStrongness) result = false;

					if(otherEvent.isDateStartStrongness && otherEvent.isDateEndStrongness &&
							!nextEvent.isDateStartStrongness && !previousEvent.isDateEndStrongness)
						result = false;
				}
			previousEvent = nextEvent;
		}

		return result;
	}




}

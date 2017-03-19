package com.iaproject.miage.intelligentagenda.feature.event.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by OOussema on 16/03/2017.
 */

public class Course {
	public String date;
	public List<Event> listEvents;

	public Course() {
		listEvents = new ArrayList<>();
	}

	public Course(String date) {
		this.date = date;
		listEvents = new ArrayList<>();
	}
	public Course(String date, List<Event> list) {
		this.date = date;
		listEvents = list;
	}

	/**
	 * Permet d'ajouter un évenement dans la liste
	 * @param event de type Evenement. C'est l'évenement passé en paramètre qui sera ajouté
	 * @return
	 */
	public boolean addEvent(Event event) {
		boolean result = false;
		try {
			if(this.listEvents.size() == 0) return this.listEvents.add(event);
			else if (!checkOverlapEvent(event)) {
				result = this.listEvents.add(event);
				Collections.sort(listEvents);
			}
		}
		catch (Exception exp){

		}
		return result;
	}

	/**
	 * Permet de supprimer un évenement de la liste
	 * @param event de type Evenement. C'est l'évenement passé en paramètre qui sera supprimé
	 * @return si l'évenement s'est bien supprimé
	 */
	public boolean deleteEvent(Event event){
		boolean result = false;
		try{
			result = this.listEvents.remove(event);
		}catch (Exception exp){

		}
		return result;
	}



	/**
	 * Vérifie si deux évenements ne sont pas parallèles
	 */
	public boolean checkOverlapEvent(Event otherEvent){
		boolean result = true;
		Event previousEvent = this.listEvents.get(0);
		if(otherEvent.dateEnd.before(previousEvent.dateStart) || (this.listEvents.size()==1
				&& otherEvent.dateStart.getTimeInMillis()>=(previousEvent.dateEnd.getTimeInMillis()+previousEvent.dateStart.getTimeInMillis())/2))

			if(otherEvent.isDateStartStrongness && !previousEvent.isDateEndStrongness) return false;
			else if(otherEvent.dateStart.after(previousEvent.dateEnd)) return false;
			else return true;

		else if (otherEvent.equals(previousEvent)) return true;

		else if(otherEvent.isDateEndStrongness && !previousEvent.isDateStartStrongness
				&& otherEvent.dateEnd.getTimeInMillis()<=(previousEvent.dateEnd.getTimeInMillis()+previousEvent.dateStart.getTimeInMillis())/2)
			return false;

		for(Event nextEvent : this.listEvents.subList(1,this.listEvents.size())){
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


	public List<Event> getListEvents() {
		return listEvents;
	}

	public void setListEvents(List<Event> listEvents) {
		this.listEvents = listEvents;
	}
}

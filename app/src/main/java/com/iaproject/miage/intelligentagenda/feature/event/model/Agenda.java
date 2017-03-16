package com.iaproject.miage.intelligentagenda.feature.event.model;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by OOussema on 25/02/2017.
 */

public class Agenda {
	public String titleAgenda;
	public String basePlace;
	public Map<String,Course> courses;
	public static Agenda instance = null;


	private Agenda(String titleAgenda,String basePlace){
		this.titleAgenda = titleAgenda;
		this.basePlace = basePlace;
		this.courses = new HashMap<>();
	}

	/** Point d'accès pour l'instance unique du singleton */
	public static Agenda getInstance(String titleAgenda,String basePlace) {
		if(instance == null){
			return instance = new Agenda(titleAgenda,basePlace);
		}
		else return instance;
	}

	/**
	 * Quand j'ajoute un nouvel évenemnt il faut que j'ajoutte un nouveau trajet
	 * Comment savoir si j'ai un seul trajet ou un parcours
	 * On se base sur le fait que une journée = un parours
	 * Un parcours possède 1 ou plusieurs trajets
	 * Un trajet est caractérisé par 2 lieux, une distance, un mode de transport et un temps
	 * On identifie un parcours par le début de sa date yyyyMMdd.
	 * Je cherche le jours yyyyMMdd
	 * S'il y a déjà un évenement à cette date
	 *  alors j'ajoute un trajet au parcours
	 *      sinon
	 *          J'instancie un nouveau parcours avec comme clé la date
	 *          J'ajoute le trajet à ce parcours
	 */
	public boolean addEvent(Event event, String keyDate) {
		if(courses.containsKey(keyDate)){
			return courses.get(keyDate).addEvent(event);
		}
		else{
			Course course = addCourse(keyDate);
			return course.addEvent(event);
		}
	}

	/**
	 * Ajoute un Parcours
	 * @param keyDate de type string qui est une date sous format ddMMyyyy
	 * @return vrai si l'ajout s'est bien eéffectué
	 */
	public Course addCourse(String keyDate){
		Course course = new Course(keyDate);
		this.courses.put(keyDate, course);
		return course;
	}

}

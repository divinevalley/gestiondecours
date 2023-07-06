package ift1025Tp1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/*
 * 
attributs : heureDébut, heureFin

(eg. 08:30, 10:30) utiliser Calendar localtime

*/

public class HoraireHeure {

// attributs: 
	private LocalTime heureDebut;
	private LocalTime heureFin;
	
	
	public HoraireHeure(String heureDebutString, String heureFinString) {
		
		
		this.heureDebut = parseHeure(heureDebutString);
		this.heureFin = parseHeure(heureFinString);
	}
	
	public LocalTime parseHeure(String heureAParser) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(heureAParser, formatter);
	}

	public LocalTime getHeureDebut() {
		return heureDebut;
	}


	public void setHeureDebut(String heureDebut) {
		this.heureDebut = parseHeure(heureDebut);
	}


	public LocalTime getHeureFin() {
		return heureFin;
	}


	public void setHeureFin(String heureFin) {
		this.heureFin = parseHeure(heureFin);
	}

}

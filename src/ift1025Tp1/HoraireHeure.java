package ift1025Tp1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * 
attributs : heureDébut, heureFin
(eg. 08:30, 10:30) utiliser Calendar localtime
*/

public class HoraireHeure {

// attributs: 
	private LocalTime heureDebut;
	private LocalTime heureFin;
	

	
	// constructeur
	public HoraireHeure(String heureDebutString, String heureFinString) throws DateTimeParseException {
		this.heureDebut = parseHeure(heureDebutString);
		this.heureFin = parseHeure(heureFinString);
		// TODO verifier heure fin apres debut 
	}
	
	public HoraireHeure(String heureDebutString) throws DateTimeParseException {
		this.heureDebut = parseHeure(heureDebutString);
	}
	
	// pour initialiser
	public HoraireHeure() {
	}
	
	public LocalTime parseHeure(String heureAParser) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(heureAParser, formatter);
	}

	public LocalTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut) throws DateTimeParseException {
		this.heureDebut = parseHeure(heureDebut);
	}

	public LocalTime getHeureFin() {
		return heureFin;
	}


	public void setHeureFin(String heureFin) throws DateTimeParseException {
		this.heureFin = parseHeure(heureFin);
	}

	public boolean conflit(HoraireHeure autre) {
		return (this.heureFin.isAfter(autre.heureDebut) && this.heureDebut.isBefore(autre.heureFin));
		
//		if (this.heureFin.isAfter(autre.heureDebut) && this.heureDebut.isBefore(autre.heureFin)) {
//			System.out.println("thisheurefin" + this.heureFin + " is after autreheuredebut:" + autre.heureDebut);
//			System.out.println("ou " + this.heureDebut + " is before autreheurefin: " + autre.heureFin);
//			return true;
//		} 
//		return false;
	}
	
	@Override
	public String toString() {
		return heureDebut + "-" + heureFin + ".";
	}

}

package ift1025Tp1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

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
	
	/**
	 * @param String heureAParser eg. 08:00
	 * @return objet LocalTime pour préparer à l'affectation aux attributs 
	 * @throws DateTimeParseException
	 */
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


	/**
	 * setter de HeureFin qui inclut une vérficiation que l'heure de Fin n'est pas avant l'heure de Début (incohérent) 
	 * @param heureFin
	 * @throws DateTimeParseException
	 * @throws InputMismatchException
	 */
	public void setHeureFin(String heureFin) throws DateTimeParseException, InputMismatchException {
		LocalTime heureFinTime = parseHeure(heureFin);
		// verifier si coherent (on n'a pas de chose du genre 09:00-08:00) 
		if (heureFinTime.isBefore(this.heureDebut)) {
			throw new InputMismatchException();
		} else {
			this.heureFin = heureFinTime;			
		}

	}

	/**
	 * évalue si conflit existe entre deux HoraireHeures
	 * @param autre
	 * @return
	 */
	public boolean conflit(HoraireHeure autre) {
		return (this.heureFin.isAfter(autre.heureDebut) && this.heureDebut.isBefore(autre.heureFin));
	}
	
	@Override
	public String toString() {
		return heureDebut + "-" + heureFin + ".";
	}

}

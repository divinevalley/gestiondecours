package ift1025Tp1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.InputMismatchException;
/**
 * La classe HoraireSession est utilisée pour représenter un certain horaire TH, TP, ou examen. 
 * Elle comporte une date de début et une date de fin (eg. 2023-01-01 au 2023-05-05)
 * ainsi qu'un horaire hebdomadaire (HoraireSemaine) (eg.  lundis, 08:00-10:00 ; jeudis 09:00-10:00) 
 * 
 * Un exemple d'HoraireSession TH est comme suit :
 *  
 * cours TH: Dates : 2023-01-01 - 2023-05-05	[vendredi, 20:00-21:00.]
 * 
 * Notez que si un HoraireSession a lieu sur un meme jour plutôt que sur plusieurs semaines, 
 * (dans le cas d'un examen), la date de début va être égale à la date de fin. Mais elle va toujours 
 * avoir un horaire hebdomadaire (horaireSemaine) car toujours besoin d'une heure de début/fin 
 * pour l'examen.  
 */
public class HoraireSession {

	//attributs:
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private HashSet<HoraireSemaine> horaireSemaine; // eg. lundis, 08:00-10:00 ; jeudis 09:00-10:00 
	// HashSet pour ne pas accepter de doublons
		
	//constructeur
	public HoraireSession(String dateDebut, String dateFin) {
		this.dateDebut = parseDate(dateDebut);
		this.dateFin = parseDate(dateFin);
		this.horaireSemaine = new HashSet<>(); //initialiser mais vide pour l'instant
	}
	
	//constructeur pour initialiser
	public HoraireSession() {
	}
	
	public LocalDate parseDate(String aaaaMMjj) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(aaaaMMjj, formatter);
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = parseDate(dateDebut);
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) throws InputMismatchException {
		LocalDate dateFinParse = parseDate(dateFin);
		if (dateFinParse.isBefore(dateDebut)) {
			throw new InputMismatchException();
		} else {
			this.dateFin = dateFinParse;
		}
		
	}

	public HashSet<HoraireSemaine> getHoraireSemaine() {
		return horaireSemaine;
	}

	public void setHoraireSemaine(HashSet<HoraireSemaine> horaireSemaine) {
		this.horaireSemaine = horaireSemaine;
	}
	
	public void addHoraireSemaine(int jourSemaine, String heureDebut, String heureFin) {
		HoraireHeure newHoraireHeure = new HoraireHeure(heureDebut, heureFin); // eg. 08:00, 10:00 
		HoraireSemaine horaireSemaine = new HoraireSemaine(jourSemaine); // eg. 1 pour lun
		horaireSemaine.setHoraireHeure(newHoraireHeure);
		this.horaireSemaine.add(horaireSemaine);
	}
	
	/**
	 * évalue si conflit existe entre l'horaire session et un uatre horaire session 
	 * @param autre
	 * @return true si conflit existe
	 */
	public boolean conflit(HoraireSession autre) {
		//check null
		if(this==null || autre == null) {
			return false; // si un des deux horaires est complètement null, on va dire aucun conflit
		}
		
		// d'abord checker si overlap entre dates session (càd les deux sont de la meme session)
		if (autre.dateDebut.isBefore(this.dateFin) && this.dateFin.isAfter(autre.dateDebut)) {
			// checker si conflit jour (càd partage le meme jour de la semaine)
			for (HoraireSemaine thisHoraire : this.horaireSemaine) {

				for (HoraireSemaine autreHoraire: autre.horaireSemaine) {
					return thisHoraire.conflit(autreHoraire);					
				}
			}
		}
		return false;
	}

	
	public String toStringDatesSeulement() {
		return "Dates : " + dateDebut + " - " + dateFin;
	}
	@Override
	public String toString() {
		return "Dates : " + dateDebut + " - " + dateFin + "\t" + (horaireSemaine != null ? horaireSemaine: "");
	}

}

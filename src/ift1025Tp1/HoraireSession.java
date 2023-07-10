package ift1025Tp1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
public class HoraireSession {

	//attributs:
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private HashSet<HoraireSemaine> horaireSemaine;
	
	//	(eg. dateDebut=2023/01/05, dateFin=2023/04/30, [horaireSemaine lundi avec heureDéb/fin, 
	// mercredi avec heure Déb/Fin])
	
	
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

	public void setDateFin(String dateFin) {
		this.dateFin = parseDate(dateFin);
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
	
	public boolean conflit(HoraireSession autre) {
		// d'abord checker si overlap entre dates session (càd les deux sont de la meme session)
		if (autre.dateDebut.isBefore(this.dateFin) && this.dateFin.isAfter(autre.dateDebut)) {
			// checker si conflit jour (càd partage le meme jour de la semaine)
			for (HoraireSemaine thisHoraire : this.horaireSemaine) {
//				System.out.println("1. for : " + this.horaireSemaine);
				for (HoraireSemaine autreHoraire: autre.horaireSemaine) {
//					System.out.println("2. for: " + autre.horaireSemaine);
//					System.out.println("on compare this horaire semaine: " + thisHoraire + " avec autrehoraire: " + autre.horaireSemaine);
					
//					if (thisHoraire.conflit(autreHoraire)) {
//						System.out.println("TRUE! horaire conflit! this: " +  this.horaireSemaine + " avec " + autre.horaireSemaine);
//					}
					return thisHoraire.conflit(autreHoraire);					
				}
			}
		}
		return false;
	}
	
	// TODO calculer conflit si examen
	

	@Override
	public String toString() {
		return "Dates : " + dateDebut + " - " + dateFin + "\t" + horaireSemaine;
	}

	

}

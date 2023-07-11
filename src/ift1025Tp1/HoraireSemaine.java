package ift1025Tp1;

import java.util.InputMismatchException;

/**
 * La classe HoraireSemaine représente l'horaire régulier hebdomadaire, utilisé pour les cours TH et TP 
 * Un objet HoraireSemaine comporte un jour de la semaine (eg. tous les lundis), représenté par un int 
 * (1 pour lundi, 2 pour mardi, etc), et un objet HoraireHeure (qui comporte l'heure de Début / fin) 
 * pour ce jour. Donc un objet HoraireSemaine peut être le suivant : 
 * 
 *  [jeudi, 12:00-14:00; vendredi, 12:00-13:00]
 *
 */
public class HoraireSemaine {

	//attributs:
	private int jourDeLaSemaine; // 1 pour lundi, 2 pour mardi, etc 
	private HoraireHeure horaireHeure; // comporte heureDébut/Fin)

	// constructeur
	public HoraireSemaine(int jourDeLaSemaine) {
		if (jourDeLaSemaine>=1 && jourDeLaSemaine <=7) {
			this.jourDeLaSemaine = jourDeLaSemaine;
		} else {
			throw new InputMismatchException();
		}
	}

	//constructeur pour initialiser
	public HoraireSemaine() {
	}

	public int getJourDeLaSemaine() {
		return jourDeLaSemaine;
	}

	public void setJourDeLaSemaine(int jourDeLaSemaine) {
		this.jourDeLaSemaine = jourDeLaSemaine;
	}

	public HoraireHeure getHoraireHeure() {
		return horaireHeure;
	}

	public void setHoraireHeure(HoraireHeure horaireHeure) {
		this.horaireHeure = horaireHeure;
	}


	/**
	 * évalue si conflit existe entre cet horaireSemaine et autre HoraireSemaine
	 * @param autre HoraireSemaine
	 * @return boolean TRUE si conflit existe
	 */
	public boolean conflit(HoraireSemaine autre) {
		if (this.jourDeLaSemaine == autre.jourDeLaSemaine) { // seulement si meme jour	
//			System.out.println("this.jour " + this.jourDeLaSemaine + " == " + autre.jourDeLaSemaine); // TODO remove
			return (this.horaireHeure.conflit(autre.horaireHeure));
		}
		return false;
	}
	
	/**
	 * convertit les int (eg. "1") en nom du jour de la semaine string
	 * @return le nom du jour en format string
	 */
	public String jourSemaineEnString() {
		String jourSemaineString = "";

		switch (this.jourDeLaSemaine) {
		case 1:
			jourSemaineString = "lundi";
			break;
		case 2:
			jourSemaineString = "mardi";
			break;
		case 3:
			jourSemaineString = "mercredi";
			break;
		case 4:
			jourSemaineString = "jeudi";
			break;
		case 5:
			jourSemaineString = "vendredi";
			break;
		case 6:
			jourSemaineString = "samedi";
			break;
		case 7:
			jourSemaineString = "dimanche";
			break;
		default:
			break;
		}
		
		return jourSemaineString;
	}

	@Override
	public String toString() {
		return this.jourSemaineEnString() + ", " 
		+ (horaireHeure != null ? horaireHeure : "(horaire (heures) non renseigné)");
	}


}

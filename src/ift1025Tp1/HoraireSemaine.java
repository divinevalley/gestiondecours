package ift1025Tp1;

import java.util.InputMismatchException;

public class HoraireSemaine {

	//attributs:
	private int jourDeLaSemaine;
	private HoraireHeure horaireHeure;

	//(eg. jourDeLaSemaine = 1 pour lundi, horaireHeure = comporte heureDébut/Fin)


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


	public boolean conflit(HoraireSemaine autre) {
		if (this.jourDeLaSemaine == autre.jourDeLaSemaine) { // seulement si meme jour	
//			System.out.println("this.jour " + this.jourDeLaSemaine + " == " + autre.jourDeLaSemaine); // TODO remove
			return (this.horaireHeure.conflit(autre.horaireHeure));
		}
		return false;
	}
	
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

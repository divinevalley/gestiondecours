package ift1025Tp1;

import java.util.InputMismatchException;

public class HoraireSemaine {

	//attributs:
	private int jourDeLaSemaine;
	private HoraireHeure horaireHeure;

	//(eg. jourDeLaSemaine = lundi, horaireHeure = comporte heureDébut/Fin)
	// lun mar mer jeu ven (pas sam dim)


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
		if (this.jourDeLaSemaine == autre.jourDeLaSemaine) { // if meme jour	
			if (this.horaireHeure.conflit(autre.horaireHeure)){ // et if conflit
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {

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


		return jourSemaineString + ", " 
		+ (horaireHeure != null ? horaireHeure : "(horaire (heures) non saisis)");
	}


}

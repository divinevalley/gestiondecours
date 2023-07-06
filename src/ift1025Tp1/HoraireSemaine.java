package ift1025Tp1;

import java.util.InputMismatchException;

public class HoraireSemaine {

	//attributs:
	private String jourDeLaSemaine;
	private HoraireHeure horaireHeure;
	
	//(eg. jourDeLaSemaine = lundi, horaireHeure = comporte heureDébut/Fin)
	// lun mar mer jeu ven (pas sam dim)
	
	// constructeur
	public HoraireSemaine(String jourDeLaSemaine) {
		if (jourDeLaSemaine.equalsIgnoreCase("lun") || jourDeLaSemaine.equalsIgnoreCase("mar") || 
				jourDeLaSemaine.equalsIgnoreCase("mer") || jourDeLaSemaine.equalsIgnoreCase("jeu") || 
				jourDeLaSemaine.equalsIgnoreCase("ven")) {
			this.jourDeLaSemaine = jourDeLaSemaine;
		} else {
			throw new InputMismatchException();
		}
	}
	
	//constructeur pour initialiser
	public HoraireSemaine() {
	}

	public String getJourDeLaSemaine() {
		return jourDeLaSemaine;
	}

	public void setJourDeLaSemaine(String jourDeLaSemaine) {
		this.jourDeLaSemaine = jourDeLaSemaine;
	}

	public HoraireHeure getHoraireHeure() {
		return horaireHeure;
	}

	public void setHoraireHeure(HoraireHeure horaireHeure) {
		this.horaireHeure = horaireHeure;
	}

	@Override
	public String toString() {
		return "HoraireSemaine [jourDeLaSemaine=" + jourDeLaSemaine + ", horaireHeure=" + horaireHeure + "]";
	}

	 

	

}

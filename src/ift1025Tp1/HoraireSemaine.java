package ift1025Tp1;

public class HoraireSemaine {

	//attributs:
	private String jourDeLaSemaine;
	private HoraireHeure horaireHeure;
	
	//(eg. jourDeLaSemaine = lundi, horaireHeure = comporte heureDébut/Fin)
	// lun mar mer jeu ven (pas sam dim)
	
	public HoraireSemaine(String jourDeLaSemaine, HoraireHeure horaireHeure) {
		if (jourDeLaSemaine.equalsIgnoreCase("lun") || jourDeLaSemaine.equalsIgnoreCase("mar") || jourDeLaSemaine.equalsIgnoreCase("mer") || jourDeLaSemaine.equalsIgnoreCase("jeu") || jourDeLaSemaine.equalsIgnoreCase("ven")) {
			this.jourDeLaSemaine = jourDeLaSemaine;
			this.horaireHeure = horaireHeure;
		}
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

	 

	

}

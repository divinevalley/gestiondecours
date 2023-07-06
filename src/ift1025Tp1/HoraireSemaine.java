package ift1025Tp1;

public class HoraireSemaine {

	//attributs:
	private String jourDeLaSemaine;
	private HoraireHeure horaireHeure;
	
	//(eg. jourDeLaSemaine = lundi, horaireHeure = comporte heureDébut/Fin)
	
	public HoraireSemaine(String jourDeLaSemaine, HoraireHeure horaireHeure) {
		this.jourDeLaSemaine = jourDeLaSemaine;
		this.horaireHeure = horaireHeure;
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

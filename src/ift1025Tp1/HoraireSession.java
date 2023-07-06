package ift1025Tp1;

import java.util.ArrayList;
import java.util.Date;
public class HoraireSession {

	//attributs:
	private Date dateDebut;
	private Date dateFin;
	private  ArrayList<HoraireSemaine> horaireSemaine;
	
	//	(eg. dateDebut=2023/01/05, dateFin=2023/04/30, [horaireSemaine lundi avec heureDéb/fin, 
	// mercredi avec heure Déb/Fin])
	
	
	public HoraireSession(Date dateDebut, Date dateFin, ArrayList<HoraireSemaine> horaireSemaine) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.horaireSemaine = horaireSemaine;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public ArrayList<HoraireSemaine> getHoraireSemaine() {
		return horaireSemaine;
	}

	public void setHoraireSemaine(ArrayList<HoraireSemaine> horaireSemaine) {
		this.horaireSemaine = horaireSemaine;
	}


}

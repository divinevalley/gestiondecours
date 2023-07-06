package ift1025Tp1;

import java.time.LocalDateTime;

/*
 * 
attributs : heureDébut, heureFin

(eg. 08:30, 10:30) utiliser Calendar localtime

*/

public class HoraireHeure {

// attributs: 
	private LocalDateTime heureDebut;
	private LocalDateTime heureFin;
	
	
	public HoraireHeure(LocalDateTime heureDebut, LocalDateTime heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}


	public LocalDateTime getHeureDebut() {
		return heureDebut;
	}


	public void setHeureDebut(LocalDateTime heureDebut) {
		this.heureDebut = heureDebut;
	}


	public LocalDateTime getHeureFin() {
		return heureFin;
	}


	public void setHeureFin(LocalDateTime heureFin) {
		this.heureFin = heureFin;
	}

}

package ift1025Tp1;

public class Cours {

	// attributs 
	private HoraireSession horaireTH;  
	private HoraireSession horaireTP; 
	private HoraireSession examI;  
	private HoraireSession examF;
	private String sigle;
	int nbCredits;


	/**
	 *  eg. horaire TH du type  “HoraireSession” (comporte une certaine date 
 		de début/fin et aussi les jours dans la semaine qui eux contiennent
		les heures début/fin), pareil avec horaireTP, horaire Exam I,
		horaire Exam F. Les horaire ExamI et ExamF vont juste avoir une 
		même date début/fin.
	 */

	//constructeur 
	public Cours(String sigle, int nbCredits) {
		this.sigle = sigle;		
		this.nbCredits = nbCredits;
		// idée ici : TGDE va créer en mettant juste le sigle (dans un premier temps) 
		// et ensuite on va lui proposer de mettre les autres attributs 
		// nb credits, horairesTH, horaireTP, examI, examF
	}
	
	//constructeur pour initialiser
	public Cours() {
	}
	
   //getter et setters 
	public int getNbCrédits() {
		return nbCredits;
	}


	public void setNbCrédits(int nbCrédits) {
		this.nbCredits = nbCrédits;
	}


	public HoraireSession getHoraireTH() {
		return horaireTH;
	}


	public HoraireSession getHoraireTP() {
		return horaireTP;
	}


	public HoraireSession getExamI() {
		return examI;
	}


	public HoraireSession getExamF() {
		return examF;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	@Override
	public String toString() {
		return "Cours: \n1. horaireTH=" + horaireTH + ", \n2. horaireTP=" + horaireTP + ", \n3. examI=" + examI + ", \n4.examF=" + examF
				+ ", \n5.sigle=" + sigle + ", \n6.nbCredits=" + nbCredits;
	}
	
	


}

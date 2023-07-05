package ift1025Tp1;

/**
 * @author mimit
 *
 */

public class Cours {

	// attributs 

	private final HoraireSession horaireTH;
	private final HoraireSession horaireTP;
	private final HoraireSession examI;
	private final HoraireSession examF;
	private final Sigle sigle;
	int nbCrédits;


	/**
	 *  eg. horaire TH du type  “HoraireSession” (comporte une certaine date 
 		de début/fin et aussi les jours dans la semaine qui eux contiennent
		les heures début/fin), pareil avec horaireTP, horaire Exam I,
		horaire Exam F. Les horaire ExamI et ExamF vont juste avoir une 
		même date début/fin.
	 */

	//constructeur 
	public Cours(HoraireSession horaireTH, HoraireSession horaireTP) {
		this.horaireTH = new HoraireSession();
		this.horaireTP = new HoraireSession();
		this.examI = new HoraireSession();
		this.examF = new HoraireSession();
			this.sigle = new Sigle();
	}
   //getter et setters 


	public int getNbCrédits() {
		return nbCrédits;
	}


	public void setNbCrédits(int nbCrédits) {
		this.nbCrédits = nbCrédits;
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


	public Sigle getSigle() {
		return sigle;
	}
	

}

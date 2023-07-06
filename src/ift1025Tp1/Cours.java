package ift1025Tp1;



public class Cours {

	// attributs 
	private final HoraireSession horaireTH;  // XXX : jsp pourquoi final ? pour moi on veut avoir la possibilité de les modifier 
	private final HoraireSession horaireTP; // (donc on va mettre des setters. peut être eclipse te l'a proposé pcq tu n'as pas 
	private final HoraireSession examI;  // encore mis de setters ? ) 
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
	// XXX : ^ Ok j'essaie de te guider dans la logique : imagine au moment où tu va vouloir instancier un Cours 
	// (càd créer un cours), qu'est ce que tu va vouloir mettre par défaut ? (imagine d'être la TGDE)
	// probablement au moins au début le sigle 
	// et par la suite mettre les horaires (les autres attributs)
	// donc j'imaginerais plutôt mettre comme paramètre ici juste le String + int pour le sigle, et puis à l'intérieur
	// du constructuer, on peut instancier un objet Sigle avec cette information 
	
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

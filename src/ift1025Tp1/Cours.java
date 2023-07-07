package ift1025Tp1;

public class Cours implements Comparable<Cours> {

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

	// constructeur pour sigle seul
	public Cours (String sigle) {
		this.sigle = sigle.toUpperCase();
	}
	
	//constructeur 
	public Cours(String sigle, int nbCredits) {
		this.sigle = sigle.toUpperCase();		
		this.nbCredits = nbCredits;
		// idée ici : TGDE va créer en mettant juste le sigle (dans un premier temps) 
		// et ensuite on va lui proposer de mettre les autres attributs 
		// nb credits, horairesTH, horaireTP, examI, examF
	}
	
	//constructeur pour initialiser
	public Cours() {
	}
	
	
	// constructeur pour test
   public Cours(HoraireSession horaireTH, HoraireSession horaireTP, HoraireSession examI, HoraireSession examF,
			String sigle, int nbCredits) {
		this.horaireTH = horaireTH;
		this.horaireTP = horaireTP;
		this.examI = examI;
		this.examF = examF;
		this.sigle = sigle.toUpperCase();
		this.nbCredits = nbCredits;
	}

//getter et setters 
	public int getNbCredits() {
		return nbCredits;
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
	
	

	public void setHoraireTH(HoraireSession horaireTH) {
		this.horaireTH = horaireTH;
	}

	public void setHoraireTP(HoraireSession horaireTP) {
		this.horaireTP = horaireTP;
	}

	public void setExamI(HoraireSession examI) {
		this.examI = examI;
	}

	public void setExamF(HoraireSession examF) {
		this.examF = examF;
	}

	public void setNbCredits(int nbCredits) {
		this.nbCredits = nbCredits;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle.toUpperCase();
	}


	@Override
	public String toString() {
		return "Cours: " + sigle + (horaireTH != null ? "\n1. cours TH:" + horaireTH + ", " : "pas de TH")
				+ (horaireTP != null ? "\n2. cours TP:" + horaireTP + ", " : "pas de TP")
				+ (examI != null ? "\n3. Examen Intra: " + examI + ", " : "(pas d'examen Intra)") + 
				(examF != null ? "\n4. Examen Final: " + examF + ", " : "(pas d'examen final)")
				+ (sigle != null ? "\n5. Sigle:" + sigle + ", " : "") + "\n6. Nb credits=" + nbCredits;
	}
	

	@Override
	public int compareTo(Cours autre) { // pour trier dans Set : alphabetiser par sigle
		return this.sigle.compareTo(autre.sigle);
	}


}

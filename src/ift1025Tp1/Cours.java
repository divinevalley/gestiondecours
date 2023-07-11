package ift1025Tp1;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.TreeSet;

public class Cours implements Comparable<Cours> { // implements Comparable pour être trié dans Set (ordre alphabetique)

	// attributs 
	private HoraireSession horaireTH;  
	private HoraireSession horaireTP; 
	private HoraireSession examI;  
	private HoraireSession examF;
	private String sigle;
	private int nbCredits;



	public boolean conflit(Cours autre) {

		// conflits possibles: thisTH&autreTH; thisTH&autreTP; thisTP&autreTP; thisTP&autreTH  
		return (this.horaireTH.conflit(autre.horaireTH) || this.horaireTH.conflit(autre.horaireTP) 
				|| this.horaireTP.conflit(autre.horaireTP) || this.horaireTP.conflit(autre.horaireTH));

//		 pour debogguage : 
//				if (this.horaireTH.conflit(autre.horaireTH)) {
//					 System.out.println("1.conflit this.horaireTH autre horaireTH: " + this.horaireTH + " et " + autre.horaireTH);
//					 System.out.println("this: " + this.sigle + " autre: " + autre.sigle);
//					 return true;
//				} else if(this.horaireTH.conflit(autre.horaireTP)) {
//					System.out.println("2.conflit this.horaireTH autre horaireTP: " + this.horaireTH + " et " + autre.horaireTP);
//					System.out.println("this: " + this.sigle + " autre: " + autre.sigle);
//					return true;
//				} else if (this.horaireTP.conflit(autre.horaireTP)) {
//					System.out.println("3.conflit this.horaireTP autre horaireTP: " + this.horaireTP + " et " + autre.horaireTP);
//					System.out.println("this: " + this.sigle + " autre: " + autre.sigle);
//					return true;
//				} else if (this.horaireTP.conflit(autre.horaireTH)) {
//					System.out.println("4. conflit this.horaireTP autre horaireTH: " + this.horaireTP + " et " + autre.horaireTH);
//					System.out.println("this: " + this.sigle + " autre: " + autre.sigle);
//					return true;
//				}
//				
//		
//				return false;
	}


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

	public void setExamI(HoraireSession examI) throws InputMismatchException {
		// check null
		if (examI!=null) {
			if (examI.getDateDebut().isAfter(this.horaireTH.getDateFin()) || examI.getDateDebut().isBefore(this.horaireTH.getDateDebut())){
				throw new InputMismatchException(); // si jamais intra n'est pas dans le semestre
			} 
		}
		else {
			this.examI = examI;			
		}

	}

	public void setExamF(HoraireSession examF) throws InputMismatchException{
		// check null
		if (examF!=null) {
			if(examF.getDateDebut().isBefore(horaireTH.getDateDebut())) { 
				// on va supposer que c'est ok d'avoir un examen final après la fin
				// du semestre mais c'est sur que ça ne soit pas être avant le début ! 
				throw new InputMismatchException();
			}
		}
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


	public String toStringAvecNumero() {
		return "Cours: " + sigle + (horaireTH != null ? "\n1. cours TH: " + horaireTH + ", " : "pas de TH")
				+ (horaireTP != null ? "\n2. cours TP:" + horaireTP + ", " : "pas de TP")
				+ (examI != null ? "\n3. Examen Intra: " + examI + ", " : "(pas d'examen Intra)") + 
				(examF != null ? "\n4. Examen Final: " + examF + ", " : "(pas d'examen final)")
				+ (sigle != null ? "\n5. Sigle:" + sigle + ", " : "") + "\n6. Nb credits=" + nbCredits;
	}

	@Override
	public String toString() {
		return "Cours: " + sigle + (horaireTH != null ? "\n- cours TH: " + horaireTH + ", " : "\n- pas de TH")
				+ (horaireTP != null ? "\n- cours TP: " + horaireTP + ", " : "\n- pas de TP")
				+ (examI != null ? "\n- Examen Intra: " + examI + ", " : "\n- pas d'examen Intra") + 
				(examF != null ? "\n- Examen Final: " + examF + ", " : "\n- pas d'examen final")
				+ (sigle != null ? "\n- Sigle:" + sigle + ", " : "") + "\n- Nb credits=" + nbCredits + "\n\n";
	}


	@Override
	public int compareTo(Cours autre) { // pour trier dans Set : alphabetiser par sigle
		return this.sigle.compareTo(autre.sigle);
	}


}

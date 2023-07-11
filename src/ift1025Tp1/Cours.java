package ift1025Tp1;

import java.util.InputMismatchException;

/***
 * 
 * La classe Cours représente un cours avec un sigle, un nombre de crédits en entier,
 * tous les horaires de cours théorique (TH), cours pratiques (TP), 
 * ainsi que des horaires pour un examen intra et un examen final
 * Cette classe implémente l'interface comparable car on a besoin que les cours soit 
 * trié dans le repertoire de tous les cours (qu'on va mettre dans un TreeSet)  
 *
 */

public class Cours implements Comparable<Cours> { // implements Comparable pour être trié dans Set (ordre alphabetique)

	// attributs 
	private HoraireSession horaireTH;  // comporte dates de début/fin pour le cours théorique, mais aussi pour les horaire hebdomadaire 
	private HoraireSession horaireTP; 
	private HoraireSession examI;  
	private HoraireSession examF;
	private String sigle;
	private int nbCredits;

	// constructeur pour sigle seul
	public Cours (String sigle) {
		this.sigle = sigle.toUpperCase();
	}

	//constructeur 
	public Cours(String sigle, int nbCredits) {
		this.sigle = sigle.toUpperCase();		
		this.nbCredits = nbCredits;
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

	/**Setter inclut une étape de validation que la date d'examen intra soit dans la session (les dates de l'horaire TH)
	 * si aucun horaire TH n'a été saisi, on va dire que la date d'examen intra saisi peut être accepté 
	 * @param newExamI
	 * @throws InputMismatchException
	 */
	public void setExamI(HoraireSession newExamI) throws InputMismatchException {
		if (newExamI!=null && horaireTH!=null) { //verifier objet pas null avant de checker
			if (newExamI.getDateDebut().isAfter(this.horaireTH.getDateFin()) || newExamI.getDateDebut().isBefore(this.horaireTH.getDateDebut())){
				throw new InputMismatchException(); // si jamais intra n'est pas dans le semestre
			} 
		} 
		this.examI = newExamI;		
	}

	/**
	 * Setter inclut une étape de validation que la date d'examen final ne soit pas avant le début de la session (date Debut horaireTH) 
	 * si aucun horaireTH existe, on peut le permettre
	 * @param examF
	 * @throws InputMismatchException
	 */
	public void setExamF(HoraireSession examF) throws InputMismatchException{
		// check null
		if (examF!=null && horaireTH!=null) {
			if(examF.getDateDebut().isBefore(horaireTH.getDateDebut())) { 
				// on va supposer que c'est ok d'avoir un examen final après la fin
				// du semestre mais c'est sur que ça ne soit pas être avant le début ! 
				throw new InputMismatchException();
			}
		}
		this.examF = examF;
	}

	public void setNbCredits(int nbCredits) throws ValidationException{
		if (nbCredits <=0) {
			throw new ValidationException();
		}
		this.nbCredits = nbCredits;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle.toUpperCase();
	}
	

	/**
	 * évalue si un conflit existe entre le cours existant et le cours autre
	 * @param autre
	 * @return true si conflit existe
	 */
	public boolean conflit(Cours autre) {
		// parcourt tous les horaires du cours et compare à tous les horaires de l'autre cours 
		// conflits possibles: thisTH&autreTH; thisTH&autreTP; thisTP&autreTP; thisTP&autreTH  
		return (this.horaireTH.conflit(autre.horaireTH) || this.horaireTH.conflit(autre.horaireTP) 
				|| this.horaireTP.conflit(autre.horaireTP) || this.horaireTP.conflit(autre.horaireTH));
	}


	/**
	 * @return String lisible du cours avec attributs numerotés (pour permettre à l'utilisateur de choisir ce qu'il veut modifier)  
	 */
	public String toStringAvecNumero() {
		return "Cours: " + sigle + (horaireTH != null ? "\n1. cours TH: " + horaireTH + ", " : "pas de TH")
				+ (horaireTP != null ? "\n2. cours TP: " + horaireTP + ", " : "pas de TP")
				+ (examI != null ? "\n3. Examen Intra: " + examI + ", " : "(pas d'examen Intra)") + 
				(examF != null ? "\n4. Examen Final: " + examF + ", " : "(pas d'examen final)")
				+ (sigle != null ? "\n5. Sigle:" + sigle + ", " : "") + "\n6. Nb credits=" + nbCredits;
	}

	/**
	 *@return String lisible de chacun des attributs du cours sans numérotation 
	 */
	@Override
	public String toString() {
		return "" + sigle + (horaireTH != null ? "\n- cours TH: " + horaireTH + ", " : "\n- pas de TH")
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

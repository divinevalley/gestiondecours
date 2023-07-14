package ift1025Tp1;
import java.util.ArrayList;
import java.util.InputMismatchException;



public class EmploiDuTemps {
	//attributs : 
	private ArrayList<Cours> listeCours;
	private int nbCreditMax;
	private int nbCreditsTotal;
	private int maxConflits; 
	private int nbConflits;


	/**
	 * exception qui signale qu'un cours a déjà été ajouté
	 */
	public class CoursDejaAjouteException extends Exception {
		public CoursDejaAjouteException() {
			super();
		}
	}

	// constructeur : 
	public EmploiDuTemps(int nbCreditMax) {
		this.nbCreditMax = nbCreditMax;
		this.nbCreditsTotal=0;
		this.maxConflits=0;
		this.nbConflits=0;
		listeCours = new ArrayList<>();
	}

	//constructeur pour initialiser
	public EmploiDuTemps() {
		nbConflits=0;
		maxConflits=0;
	}

	public int getNbCreditsTotal() {
		return nbCreditsTotal;
	}

	public ArrayList<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(ArrayList<Cours> listeCours) {
		this.listeCours = listeCours;
	}

	public int getNbCreditMax() {
		return nbCreditMax;
	}

	/**
	 * setter inclut une étape de validation que le nombre n'est pas négatif ou 0
	 * @param nbCreditMax
	 * @throws ValidationException
	 */
	public void setNbCreditMax(int nbCreditMax) throws ValidationException {
		if (nbCreditMax<=0) {
			throw new ValidationException();
		}
		this.nbCreditMax = nbCreditMax;
	}

	public void setNbCreditsTotal(int nbCreditsTotal) {
		this.nbCreditsTotal = nbCreditsTotal;
	}
	

	public int getMaxConflits() {
		return maxConflits;
	}

	/**
	 * setter inclut une étape de validation que le nombre n'est pas négatif (0 est autorise)
	 * @param maxConflits
	 * @throws ValidationException
	 */
	public void setMaxConflits(int maxConflits) throws ValidationException {
		if (maxConflits <0) {
			throw new ValidationException();
		}
		this.maxConflits = maxConflits;
	}

	public int getNbConflits() {
		return nbConflits;
	}

	public void setNbConflits(int nbConflits) {
		this.nbConflits = nbConflits;
	}

	/**
	 * ajouter un cours à l'emploi du temps personnalisé. inclut des validations si jamais le cours a déjà été ajouté, si ça dépasse
	 * le nb de crédit max, ou s'il y a un conflit horaire
	 * @param cours
	 * @throws ValidationException
	 * @throws CoursDejaAjouteException
	 * @throws InputMismatchException
	 */
	public void ajouterCours (Cours cours) throws ValidationException, CoursDejaAjouteException, InputMismatchException {
		if (listeCours.contains(cours)) { // si deja ajoute
			throw new CoursDejaAjouteException();
		} else {
			if(depasseMaxCredits(cours)) { // si depasse max credits on s'arrete la 
				throw new InputMismatchException(); 
			} else if (calculerConflit(cours)) { // ensuite verifier si conflit existe
				
				if (nbConflits++ > maxConflits) { // si on depasse max conflits, stop! 
					throw new ValidationException();				
				} else {
					System.out.println("Conflit d'horaire autorisé.");
					listeCours.add(cours); // si nb conflits est autorise, on ajoute
					this.nbConflits++; //incrementer compteur nb conflits
				}
 
			} else { // sinon, (pas de conflits) ok pour ajouter
				listeCours.add(cours);
				nbCreditsTotal+= cours.getNbCredits();
			}
		} 
	}

	public void supprimerCours(Cours cours) {	
		listeCours.remove(cours);
		nbCreditsTotal -= cours.getNbCredits();
	}


	/**return TRUE si conflit avec un cours (pour évaluer si on peut l'ajouter ou pas)
	 * @param cours
	 * @return true si conflit existe
	 */
	public boolean calculerConflit (Cours cours){
		//parcourir tous les cours deja inscrit, identifier si problem
		if (this.listeCours.isEmpty()) {
			return false;
		}

		for (Cours coursDejaInscrit : this.listeCours) { // parcourir tous les cours deja dans emploi du temps
			if( coursDejaInscrit.conflit(cours)) {
				return true; // si j'amais c'est TRUE, on s'arrête là, on renvoie true. sinon, continue boucle
			}	
		}
		return false;
	}
 
	/**return TRUE si un cours si ajouté va depasser max crédits OU depasse max de 10 cours
	 * @param cours
	 * @return TRUE si dépasse 
	 */
	public boolean depasseMaxCredits(Cours cours) {
		int nbCreditsSiAjoute = nbCreditsTotal + cours.getNbCredits();
		return (nbCreditsSiAjoute > nbCreditMax || this.listeCours.size()==10); 
	}


	/**
	 * @return String pour affichage optimal de nombre de crédits en format eg. "Nombre de crédits : 12 (max: 18)" 
	 */
	public String afficherCredits() {
		return "\nNombre de credits: " + nbCreditsTotal + " (max: " + nbCreditMax + " credits)";
	}
	
	
	/**
	 * @return String pour affichage optimal de nombre de conflits en format eg. "Nombre de conflits : 1 (max: 3)" 
	 */
	public String afficherNbConflits() {
		return "\nNombre de conflits actuel: " + nbConflits + " (max: " + maxConflits + ")";
	}
	
	

	/**
	 *@return String pour afficher emploi du temps en format lisible 
	 */
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder("");
		if (listeCours.isEmpty()) { // nullpointerexception possible si pas encore créé
			toString.append("--Emploi du temps vide--"); 
		} else {
			toString.append("EmploiDuTemps:\n" + listeCours);
		}
		toString.append("\nNombre de credits: " + nbCreditsTotal + " (max: " + nbCreditMax + " credits)\n" 
		+ "Nombre de conflits actuel: " + nbConflits + " (max: " + maxConflits + ")" );
		
		return toString.toString();
	}



}

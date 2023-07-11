package ift1025Tp1;
import java.util.ArrayList;
import java.util.InputMismatchException;



public class EmploiDuTemps {
	//attributs : 
	private ArrayList<Cours> listeCours;
	private int nbCreditMax;
	private int nbCreditsTotal;


	/**
	 * exception qui signale qu'un cours a déjà été ajouté
	 *
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
		listeCours = new ArrayList<>();
	}

	//constructeur pour initialiser
	public EmploiDuTemps() {

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

	public void setNbCreditMax(int nbCreditMax) throws ValidationException {
		if (nbCreditMax<=0) {
			throw new ValidationException();
		}
		this.nbCreditMax = nbCreditMax;
	}

	public void setNbCreditsTotal(int nbCreditsTotal) {
		this.nbCreditsTotal = nbCreditsTotal;
	}

	//fonctions : 
	/**
	 * ajouter un cours à l'emploi du temps personnalisé. inclut des validations si jamais le cours a déjà été ajouté, si ça dépasse
	 * le nb de crédit max, ou s'il y a un conflit horaire
	 * 
	 * @param cours
	 * @throws ValidationException
	 * @throws CoursDejaAjouteException
	 * @throws InputMismatchException
	 */
	public void ajouterCours (Cours cours) throws ValidationException, CoursDejaAjouteException, InputMismatchException {
		if (listeCours.contains(cours)) { // si deja ajoute
			throw new CoursDejaAjouteException();
		} else {
			if(depasseMax(cours)) { // si depasse max on s'arrete la 
				throw new InputMismatchException(); 
			} else if (calculerConflit(cours)) { // ensuite verifier si conflit existe  
				throw new ValidationException(); 
			} else { // sinon, ok pour ajouter
				listeCours.add(cours);
				nbCreditsTotal+= cours.getNbCredits();
			}
		} 
	}

	public void supprimerCours(Cours cours) {	
		listeCours.remove(cours);
		nbCreditsTotal -= cours.getNbCredits();
	}


	// return TRUE si conflit
	/**
	 * @param cours
	 * @return
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

	// return TRUE si depasse max
	/**
	 * @param cours
	 * @return
	 */
	public boolean depasseMax(Cours cours) {
		int nbCreditsSiAjoute = nbCreditsTotal + cours.getNbCredits();
		return (nbCreditsSiAjoute > nbCreditMax); 
	}


	public String afficherCredits() {
		return "\nNombre de credits: " + nbCreditsTotal + " (max: " + nbCreditMax + " credits)";
	}

	//afficher horaire 
	@Override
	public String toString() {

		StringBuilder toString = new StringBuilder("");
		if (listeCours.isEmpty()) {
			toString.append("--Emploi du temps vide--"); 
		} else {
			toString.append("EmploiDuTemps:\n" + listeCours);
		}


		toString.append("\nNombre de credits: " + nbCreditsTotal + " (max: " + nbCreditMax + " credits)");

		return toString.toString();
	}



}

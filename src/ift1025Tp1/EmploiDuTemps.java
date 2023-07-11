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
 
	/**return TRUE si un cours si ajouté va depasser max crédits
	 * @param cours
	 * @return TRUE si dépasse 
	 */
	public boolean depasseMax(Cours cours) {
		int nbCreditsSiAjoute = nbCreditsTotal + cours.getNbCredits();
		return (nbCreditsSiAjoute > nbCreditMax); 
	}


	/**
	 * @return String pour affichage optimal de nombre de crédits en format eg. "Nombre de crédits : 12 (max: 18)" 
	 */
	public String afficherCredits() {
		return "\nNombre de credits: " + nbCreditsTotal + " (max: " + nbCreditMax + " credits)";
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
		toString.append("\nNombre de credits: " + nbCreditsTotal + " (max: " + nbCreditMax + " credits)");
		return toString.toString();
	}



}

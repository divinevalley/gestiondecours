package ift1025Tp1;
import java.util.ArrayList;

public class EmploiDuTemps {
	//attributs : 
	private ArrayList<Cours> listeCours;
	private int nbCreditMax;
	private int nbCreditsTotal;

	public class ValidationException extends Exception {
	    public ValidationException(String message) {
	        super(message);
	    }
	    
	    public ValidationException() {
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
		
	//fonctions : 
	public void ajouterCours (Cours cours) throws ValidationException {
		// TODO calculer depasse max ? 
//		depasseMax(cours);
		
		if (calculerConflit(cours) || depasseMax(cours)) { // si conflit existe 
			throw new ValidationException("conflit horaire"); 
		} else { // ok pour ajouter
			listeCours.add(cours);
			nbCreditsTotal+= cours.getNbCredits();
		}
	}
	
	public void supprimerCours(Cours cours) {	
		if (listeCours.contains(cours)) {
			listeCours.remove(cours);
			nbCreditsTotal -= cours.getNbCredits();
		} 
	}
	
			 
	//fonctions annexe : 
	// return TRUE si conflit
	public boolean calculerConflit (Cours cours){
		//parcourir tous les cours deja inscrit, identifier si problem
		if (this.listeCours.isEmpty()) {
			return false;
		}
		
		for (Cours coursDejaInscrit : this.listeCours) { // parcourir tous les cours deja dans emploi du temps
			if(coursDejaInscrit.conflit(cours)) { // TODO <- fonction pas encore implementee
				return true;
			}
		}
		return false;
	}
	
	// return TRUE si depasse max
	public boolean depasseMax(Cours cours) {
		int nbCreditsSiAjoute = nbCreditsTotal + cours.getNbCredits();
		if (nbCreditsSiAjoute > nbCreditMax) {
			return true;
		} else {
			return false;
		}
	}
	

	//afficher horaire 
	@Override
	public String toString() {
		return "EmploiDuTemps:\n" + listeCours + ", max credits:" + nbCreditMax + ", nb credits: " + nbCreditsTotal;
	}
	 
}

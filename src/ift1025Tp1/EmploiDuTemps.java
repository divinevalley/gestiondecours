package ift1025Tp1;
import java.util.ArrayList;


public class EmploiDuTemps {
	//attributs : 
	private ArrayList<Cours> listeCours;
	private int nbCreditMax;
	private int nbCreditsTotal;

	
	// constructeur : 
	public EmploiDuTemps(int nbCreditMax) {
		this.nbCreditMax = nbCreditMax;
		this.nbCreditsTotal=0;
	}
	
	//constructeur pour initialiser
	public EmploiDuTemps() {
		
	}
	
	public int getNbCreditsTotal() {
		return nbCreditsTotal;
	}
		
	//fonctions : 
	public void ajouterCours (Cours cours) {
		if (calculerConflit(cours) || depasseMax(cours)) { // si conflit existe 
			System.out.println("conflit"); //TODO, throw exception?
		} else { // ok pour ajouter
			listeCours.add(cours);
			nbCreditsTotal+= cours.getNbCredits();
		}
	}
	
	public void supprimerCours(Cours cours) {	
		if (listeCours.contains(cours)) {
			listeCours.remove(cours);
			nbCreditsTotal -= cours.getNbCredits();
		} // TODO gerer si jamais n'existe pas 
	}
	
			 
	//fonctions annexe : 
	// return TRUE si conflit
	public boolean calculerConflit (Cours cours){
		//parcourir tous les cours deja inscrit, identifier si problem
		for (Cours coursDejaInscrit : this.listeCours) {
			if(coursDejaInscrit.conflit(cours)) {
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

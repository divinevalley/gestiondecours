package ift1025Tp1;
import java.util.ArrayList;


public class EmploiDuTemps {
	//attributs : 
	private ArrayList<Cours> listeCours;
	private int nbCreditMax;

	
	// constructeur : 
	public EmploiDuTemps(int nbCreditMax) {
		this.nbCreditMax = nbCreditMax;
	}
	
	public int getNbCrédits() { // compter nb de credits
		int nbCredits = 0;
		for (Cours cours : listeCours) {
			nbCredits += cours.getNbCredits();
		}
		return nbCredits;
	}
		
	//fonctions : 
	public void ajouterCours (Cours cours) {
		if (calculerConflit(cours)) { // si conflit existe 
			System.out.println("conflit");
		} else {
			listeCours.add(cours);
		}
	}
	
	public void supprimerCours(Cours cours) {	
		if (listeCours.contains(cours)) {
			listeCours.remove(cours);
		} // TODO gerer si jamais n'existe pas 
	}
	
			 
	//fonctions annexe : 
	public boolean calculerConflit (Cours cours){
		
		// TODO: implementer
		// ...
		
		return false;
	}
	// soit trop proche ou superposé, si plus grand que le nombre de crédit max  (10 crédit max) 
	//faire afficher un message : doit //diminuer le nombre de crédit 
	// XXX : ^ c'est bien en terme de la signature et le type booléen
	

//afficher horaire 
	@Override
	public String toString() {
		return "EmploiDuTemps [listeCours=" + listeCours + ", nbCreditMax=" + nbCreditMax + "]";
	}
	// XXX : ^pas finalisé mais ceci peut être généré automatiquement aussi dans tes options ("sources" dans eclipse)  
	
	 
}

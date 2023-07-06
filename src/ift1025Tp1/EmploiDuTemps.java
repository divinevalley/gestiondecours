package ift1025Tp1;
import java.util.ArrayList;

public class EmploiDuTemps {
	//attributs : 
	private ArrayList<Cours> listeCours;
	private int nbCreditMax;
	
	public ArrayList<Cours> getNbCrédits() {   
		return null;
	}
	// ^ XXX commentaire: ici tu as écrit une fonction pour chercher nombre de crédits, que tu as marqué "attribut" 
	// alors ce n'est pas un attribut, il faudrait d'abord mettre ton attribut ("private int nbCredits"), 
	// puis je ne sais pas pourquoi il y a la liste (arraylist) de cours comme objet renvoyé, 
	// jsp ce que tu avais en tête ? si c'est pour getNbCredits, 
	// ça va retourner un int plutôt (genre ça va nous donner "12" par exemple). 
		
	//fonctions : 
	public void ajouterCours ( int numéro,  String matière, int nbCrédits) {
		// demande de saisir les infos à étudiant : numéro, matière, horaire, nbCrédits
		
		// ^ XXX : pour moi ce serait plus simple si ça prenant juste un objet "Cours" 
		// (sinon on serait obligé de l'instancier à l'intérieur de cette fonction, ce qui serait possible
		// mais si jamais il y a un problème dans l'instanciation je préfère que ça soit fait *avant* 
		// qu'on doive l'ajouter. Puisqu'on a l'objet "Cours" spécialement pour, autant l'utiliser à nôtre profit.  
	}
	
	public void supprimerCours(int numéro) {
		// demande du numero de cours et supprimer de la liste de cours 
		
		// ^XXX : pourquoi est ce que le numéro de cours (int) serait suffisant pour identifier quel cours supprimer ? 
		// (genre juste "1025" ? ) 
		// je pense qu'il faudrait plutôt mettre l'objet "Cours" entier, ou au minimum le sigle entier (genre "ift 1025").
		// (donc int ne suffirait pas)
	}
	
	public void modifierCours() {
		//demande du numero de l'ancien cours vs le nouveau numéro de cours 
		// ou donner accès aux parametre quil
		
		// XXX : ^ Hmmm... en fait, je sais pas si je me suis rendu compte avant, mais qu'est ce 
		// que ferait "modifierCours" dans l'emploidutemps personnalisé ? je pense que c'est 
		// plutôt pour le TGDE, (sur la liste de tous les cours disponible), alors 
		// cette fonction n'aurait pas de sens ici (pas sur un étudiant en particuler) 
		// ... on pourra mettre ça sur Utils 
		// sinon, si c'est pour un emploi du temps pour un étudiant, on a déjà supprimer/ajouter cours, 
		// donc je vois pas ce qu'on ferait dans son panier personnel en terme de "modifier cours" 
		// (il va pas modifier le sigle lol) 
	}
			 
	//fonctions annexe : 
	public boolean calculerConflit (){
		boolean n = false;
		return n;
	}
	// soit trop proche ou superposé, si plus grand que le nombre de crédit max  (10 crédit max) 
	//faire afficher un message : doit //diminuer le nombre de crédit 
	// XXX : ^ c'est bien en terme de la signature et le type booléen
	
	// constructeur : 
	public EmploiDuTemps(int nbCreditMax) {
		this.nbCreditMax = nbCreditMax;
	}
	
	
//afficher horaire 
	@Override
	public String toString() {
		return "EmploiDuTemps [listeCours=" + listeCours + ", nbCreditMax=" + nbCreditMax + "]";
	}
	// XXX : ^pas finalisé mais ceci peut être généré automatiquement aussi dans tes options ("sources" dans eclipse)  
	
	 
}

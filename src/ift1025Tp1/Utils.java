package ift1025Tp1;
import java.util.Scanner;
import java.util.TreeMap;

public class Utils {


	public static void ajouterCours(Cours cours, TreeMap<String, Cours> repertoireCours) {
		repertoireCours.put(cours.getSigle(), cours);        
	}

	public static void menuModifierCours(TreeMap<String, Cours> repertoireCours) {
		System.out.println("Voici tous les cours du repertoire : ");
		System.out.println(repertoireCours);

		Scanner scanner = new Scanner(System.in);
		
		String optionsMenuAAfficher = "Saisir le sigle du cours que vous souhaitez modifier (eg. \"IFT1025\")."
				+ " \nou faites le 0 pour quitter: ";
		System.out.println(optionsMenuAAfficher);

		boolean continuer = true;
		while (continuer) {
			String sigleSaisi = scanner.nextLine().trim();
			Cours coursAModifier = new Cours(); 
			if (repertoireCours.containsKey(sigleSaisi)) { // verifier cours existe
				coursAModifier = repertoireCours.get(sigleSaisi);
				sigleSaisi = "ok";
			}

			switch (sigleSaisi) {
			case "ok":
				modifierCours(coursAModifier, repertoireCours); // lancer les menus
				// de retour dans menu 
				System.out.println(optionsMenuAAfficher);
				break;
			case "0": // Quitter
				continuer = false;
				break;
			default:
				System.out.println("Option invalide ou sigle n'existe pas. Veuillez choisir une option valide.");
				break;
			}
		}
	}
	
	// une fois un certain Cours est choisi, proposer un menu d'options de modifications
	public static void modifierCours(Cours coursAModifier, TreeMap<String, Cours> repertoireCours) {
		System.out.println("vous avez sélectionné le cours suivant :");
		String menuAAfficher = coursAModifier + "7. supprimer ce cours du repertoire" + 
				"\nVeuillez choisir le numero correspondant au parametre que vous souhaitez modifier" + 
				"\n(ou faites le 0 pour quitter ce menu et retourner en arriere) : ";  
		
		// coursAModifier : 
		/* eg. "Cours 
		1. horaireTH=" + horaireTH + ", 
		2. horaireTP=" + horaireTP + ", 
		3. examI=" + examI + ", 
		4.examF=" + examF
		+ ", 
		5.sigle=" + sigle + ", 
		6.nbCredits=" + nbCredits */
		
		System.out.println(menuAAfficher);
		Scanner scanner = new Scanner(System.in);
		
		boolean continuer = true;
		while(continuer) {
			String option = scanner.nextLine().trim();
			
			switch (option) {
			
			case "0":
				// Quitter
				System.out.println("Option 0 : Quitter");
				continuer = false;
				break;
			case "1": //horaireTH
				// ... 
				break;
			case "2": //horaireTP
				// ... 
				break;
			case "3": //exam I
				// ... 
				break;
			case "4": //exam F
				// ... 
				break;
			case "5": //sigle
				// ... 
				break;
			case "6": //nbcredits
				// ... 
				break;
			case "7": //supprimer
				supprimerCours(coursAModifier.getSigle(), repertoireCours);
				
				// de retour dans menu
				System.out.println("de retour dans menu modification de cours : ");
				System.out.println(menuAAfficher);
				break;
				
			default:
				System.out.println("Option invalide. Veuillez choisir une option valide.");
				break;
			}
		}
	}

	public static void supprimerCours(String sigleCours, TreeMap<String, Cours> repertoireCours) {
		if (repertoireCours.containsKey(sigleCours)){
			repertoireCours.remove(sigleCours);
			System.out.println("Le cours a été supprimé avec succès.");
		} else {
			System.out.println("Le cours spécifié n'existe pas dans la liste.");
		}
	}

	public static void creerNvCours(TreeMap<String, Cours> repertoireCours) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Entrez le sigle du cours : ");
		String sigleNvCours = scanner.nextLine();

		System.out.print("Entrez le nombre de credits du cours (eg. 3) : ");
		int nbCredits = scanner.nextInt();

		Cours nvCours = new Cours(sigleNvCours, nbCredits);

		// Demander d'autres informations sur le cours (horaires, etc.) 
		// ... 
		// TODO
		// ...
		
//		String dateDebut =; 
//		String dateFin =;

		// Ajouter le cours à la liste
		repertoireCours.put(sigleNvCours, nvCours);
		ajouterCours(nvCours, repertoireCours);  

		System.out.println("Le cours a été créé avec succès.");
	}



}

package ift1025Tp1;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Utils {


	public static void ajouterCours(Cours cours, TreeMap<String, Cours> repertoireCours) {
		repertoireCours.put(cours.getSigle(), cours);        
	}


	public static void menuModifierCours(Cours coursAncien, Cours coursNouveau, TreeMap<String, Cours> repertoireCours) {
		System.out.println(repertoireCours);

		Scanner scanner = new Scanner(System.in);

		System.out.println("Saisir le sigle du cours que vous souhaitez modifier (eg. \"IFT1025\"): ");
		System.out.println("ou faites le 0 pour quitter");

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
				
				modifierCours(coursAModifier); // lancer les menus
				
				break;
			case "0":
				// Quitter
				System.out.println("Option 0 : Quitter");
				continuer = false;
				break;
			default:
				System.out.println("Option invalide. Veuillez choisir une option valide.");
				break;
			}
		}
	}
	
	public static void modifierCours(Cours coursAModifier) {
		System.out.println("vous avez sélectionné le cours suivant :");
		System.out.println(coursAModifier); 
		/* eg. "Cours [1. horaireTH=" + horaireTH + ", 
		2. horaireTP=" + horaireTP + ", 
		3. examI=" + examI + ", 
		4.examF=" + examF
		+ ", 
		5.sigle=" + sigle + ", 
		6.nbCredits=" + nbCredits + "]"; */
		
		System.out.println("Veuillez choisir le numero correspondant au parametre que vous souhaitez modifier");
		System.out.println("(ou faites le 0 pour quitter) : ");
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
			case "1":
				// ... 
				break;
			case "2":
				// ... 
				break;
			case "3":
				// ... 
				break;
			case "4":
				// ... 
				break;
			case "5":
				// ... 
				break;
			case "6":
				// ... 
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

		System.out.print("Entrez le nombre de credits du cours : ");
		int nbCredits = scanner.nextInt();

		Cours nvCours = new Cours(sigleNvCours, nbCredits);

		// Demander d'autres informations sur le cours (horaires, etc.) 
		// ... 
		// TODO
		// ...

		// Ajouter le cours à la liste
		repertoireCours.put(sigleNvCours, nvCours);
		ajouterCours(nvCours, repertoireCours);  

		System.out.println("Le cours a été créé avec succès.");
	}



}

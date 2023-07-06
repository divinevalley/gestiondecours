package ift1025Tp1;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class Utils {


	public static void ajouterCours(Cours cours, TreeMap<String, Cours> repertoireCours) {
		repertoireCours.put(cours.getSigle(), cours);        
	}

	public static void menuModifierCours(TreeMap<String, Cours> repertoireCours, Scanner scanner) {
		System.out.println("Voici tous les cours du repertoire : ");
		System.out.println(repertoireCours);


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

			scanner.close();
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

	public static void creerNvCours(TreeMap<String, Cours> repertoireCours, Scanner scanner) {
		System.out.print("Entrez le sigle du cours : ");
		String sigleNvCours = scanner.nextLine();

		System.out.print("Entrez le nombre de credits du cours (eg. 3) : ");
		int nbCredits = Integer.parseInt(scanner.nextLine());

		Cours nvCours = new Cours(sigleNvCours, nbCredits);

		// Demander d'autres informations sur le cours (horaires, etc.) 

		// attributs Cours
		//		private HoraireSession horaireTH;  1 
		//		private HoraireSession horaireTP;  2 
		//		private HoraireSession examI;   3 
		//		private HoraireSession examF; 4 
		//		private String sigle;
		//		int nbCredits;

		HoraireSession horaireSessionTH = inputHoraireSession("Veuillez saisir pour le cours THEORIQUE (TH) :", scanner, false);
		nvCours.setHoraireTH(horaireSessionTH);

		HoraireSession horaireSessionTP = inputHoraireSession("Veuillez saisir pour le cours PRATIQUE (TP) :", scanner, false);
		nvCours.setHoraireTP(horaireSessionTP);
		
		HoraireSession horaireSessionExamI = inputHoraireSession("pour l'examen INTRA : ", scanner, true); 
		nvCours.setExamI(horaireSessionExamI);
		
		HoraireSession horaireSessionExamF = inputHoraireSession("pour l'examen FINAL : ", scanner, true);
		nvCours.setExamF(horaireSessionExamF);

		// Ajouter le cours à la liste (repertoire)
		repertoireCours.put(sigleNvCours, nvCours);
		ajouterCours(nvCours, repertoireCours);  

		System.out.println("Le cours a été créé avec succès.");
	}

	public static HoraireSession inputHoraireSession(String typeDeCours, Scanner scanner, boolean estExam) {
		boolean continuer = true;
		HoraireSession horaireSession = new HoraireSession();
		
		String msg = estExam ? typeDeCours + " date de l'examen (format requis: aaaa-mm-jj) : " : typeDeCours + " la date de DEBUT (format requis: aaaa-mm-jj) : "; 
		
		while(continuer) {
			// tout d'abord, pour la date de DEBUT
			try {
				// prendre les Strings pour instancier 
				System.out.println(msg);
				String dateDebut = scanner.nextLine(); // eg. 2023-01-01

				// set attribut
				horaireSession.setDateDebut(dateDebut); 
				
				if (estExam) {
					horaireSession.setDateFin(dateDebut); // c'est exam, meme datefin que datedebut 
					continuer = false; // => vu que c'est qu'un examen (date simple), on va sortir de la boucle
				}
				
			} catch (DateTimeParseException e) {
				System.out.println("mauvais format, reessayer");
				scanner.nextLine(); // vider bad input
				// boucle continue 
			}
			
			// ensuite pour la date de FIN mais seulement si ce n'est pas un examen 
			if (!estExam) {
				try {
				// prendre les Strings pour instancier 
				System.out.println(typeDeCours + " la date de FIN");
				String dateFin = scanner.nextLine(); // eg. 2023-05-01

				// set attribut 
				horaireSession.setDateFin(dateFin);
				
				continuer = false; // => c'est bon. sortir boucle
				} catch (DateTimeParseException e) {
					System.out.println("mauvais format, reessayer");
					scanner.nextLine(); // vider bad input
					// boucle continue 
				} 
				
				// déclarer aussi emploi du temps par semaine, uniquement si ce n'est pas un examen
				HashSet<HoraireSemaine> horaireSemaine = inputHoraireSemaine(scanner); // les lundis de 08:00 - 10:00
				horaireSession.setHoraireSemaine(horaireSemaine);
				// TODO adapter ca pour examen 
			} 
		}

		
		
		return horaireSession;
	}

	public static HashSet<HoraireSemaine> inputHoraireSemaine(Scanner scanner) {
		HashSet<HoraireSemaine> setAvecHorairesSemaines = new HashSet<>();
		
		// add jours de la semaine avec horaires
		boolean continuer = true;
		System.out.println("Préciser l'horaire : \nSaisir jour de la semaine au format suivant : lun, mar, mer, jeu, ven : ");
		System.out.println("(Pour terminer, saisir 0) :");

		HoraireSemaine horaireSemaine = new HoraireSemaine();
		while (continuer) {
			System.out.println("jour: ");
			// prendre input pour le jour
			String inputJour = scanner.nextLine(); // eg. lun

			if (inputJour.equals("0")) { // pour terminer
				continuer = false;
			} else {
				try {
					// prendre input pour les horaires pour ce jour, ajouter au Set 
					horaireSemaine = new HoraireSemaine(inputJour);
					HoraireHeure horaireHeure = inputHoraireHeure(inputJour, scanner); // eg. 08:00 debut, 10:00 fin 
					horaireSemaine.setHoraireHeure(horaireHeure);					
					System.out.println("Horaire créé : " + horaireSemaine);
					
					// ajouter au Set 
					setAvecHorairesSemaines.add(horaireSemaine);
					
					System.out.println("horaire semaine : " + setAvecHorairesSemaines);
					System.out.println("Si le cours a lieu "
							+ "un autre jour de la semaine, veuillez saisir le jour de la semaine. "
							+ "Sinon, saisir 0 pour terminer");
				} catch (Exception e) {
					System.out.println("invalide. doit etre un des suivants : lun, mar, mer, jeu, ven : ");
					scanner.nextLine(); // vider input 
				}
			}
		}
		return setAvecHorairesSemaines;
	}

	public static HoraireHeure inputHoraireHeure(String msgAAfficher, Scanner scanner) {
		// prendre les Strings pour instancier 
		System.out.println("Veuillez saisir pour " + msgAAfficher + " l'heure de DEBUT. ");
		System.out.println("Format requis hh:mm par exemple 08:30 (non pas 8:30)");
		String heureDebut = scanner.nextLine();

		System.out.println("Veuillez saisir pour " + msgAAfficher + " l'heure de FIN");
		String heureFin = scanner.nextLine();

		// instancier
		HoraireHeure horaireHeure = new HoraireHeure(heureDebut, heureFin);
		return horaireHeure;
	}


}

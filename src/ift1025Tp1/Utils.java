package ift1025Tp1;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

public class Utils {

	// TODO 
	// verifier sigle existe deja
	// verifier horaire coherent (pas 09:00 - 08:00)
	// verifier si conflit horaire au sein d'un meme cours
	// proposer modification d'une seule partie plutôt que parcourir tout le menu
	// permettre de skip la partie TP, si pas de TP, pas d'examen intra, etc 
	// ne pas numeroter les infos dans cours tostring 

	public static void menuModifierCours(TreeSet<Cours> repertoireCours, Scanner scanner) {
		boolean continuer = true;
		while (continuer) {

			//afficher repertoire cours
			Cours[] arrayCours = repertoireCours.toArray(new Cours[repertoireCours.size()]);
			System.out.println(coursToStringAvecNumero(repertoireCours)); 

			String optionsMenuAAfficher = "\nSaisir le numero correspondant au cours que vous souhaitez modifier."
					+ " \n(ou faites le 0 pour quitter)"
					+ "\n\n cours a modifier=> ";
			
//			permettreSelectionCours(repertoireCours, 0);  // TODO <-- implementer
			System.out.println(optionsMenuAAfficher);

			// recevoir input
			String input = scanner.nextLine().trim();
			int inputInt = Integer.parseInt(input);
			int inputIndexCours = inputInt-1; // ajuster pour avoir index

			if (input.equals("0")) { // si veut quitter
				continuer = false;
			} else if (inputIndexCours < arrayCours.length) { // si option valide
				Cours coursAModifier = arrayCours[inputIndexCours]; // retrouver notre objet Cours a modifier 
				modifierCours(coursAModifier, repertoireCours, scanner); // lancer les menus modifier cours
				// de retour dans menu 
				System.out.println(optionsMenuAAfficher);

			} else { // invalide
				System.out.println("Option invalide. Veuillez choisir une option valide.");
			}
		}
	}

	// TODO faire une fonction afficher les cours en ARRAY numéroté, retourner Cours sélectionné
	private static Cours permettreSelectionCours(TreeSet<Cours> repertoireCours, int selection) {
		// ... 
		return null;
	}

	private static String coursToStringAvecNumero(TreeSet<Cours> repertoireCours) {
		int numero = 1; //commencer a 1
		StringBuilder toPrint = new StringBuilder("================================================================\nREPERTOIRE DES COURS\n");
		for (Cours cours : repertoireCours) {
			toPrint.append("---------------------------------------------------------------\n\n"
					+ ">>>("+ numero + ")<<<\n\n");
			toPrint.append(cours.toString() + "\n\n");
			numero ++;
		}
		return toPrint.toString();
	}

	// une fois un certain Cours est choisi, proposer un menu d'options de modifications
	public static void modifierCours(Cours coursAModifier, TreeSet<Cours> repertoireCours, Scanner scanner) {
		// coursAModifier : 
		/* eg. "Cours 
		1. horaireTH=" + horaireTH + ", 
		2. horaireTP=" + horaireTP + ", 
		3. examI=" + examI + ", 
		4. examF=" + examF
		5. nbCredits=" + nbCredits */

		boolean continuer = true;
		while(continuer) {
			System.out.println("Vous êtes maintenant sur le cours :");
			String menuAAfficher = coursAModifier + "\n7. supprimer ce cours du repertoire" + 
					"\nVeuillez choisir le numero correspondant au parametre que vous souhaitez modifier" + 
					"\n(ou faites le 0 pour quitter ce menu et retourner en arriere) : ";  
			System.out.println(menuAAfficher);
			String option = scanner.nextLine().trim();

			switch (option) {

			case "0":
				// Quitter
				System.out.println("Option 0 : Quitter");
				continuer = false;
				break;
			case "1": //horaireTH
				HoraireSession nvHoraireSessionTH = inputHoraireSession("Pour modifier le cours THEORIQUE (TH) : ",scanner, false);
				coursAModifier.setHoraireTH(nvHoraireSessionTH);
				break;
			case "2": //horaireTP
				HoraireSession nvHoraireSessionTP = inputHoraireSession("Pour modifier le cours PRATIQUE (TP) : ",scanner, false);
				coursAModifier.setHoraireTP(nvHoraireSessionTP);
				break;
			case "3": //exam I
				HoraireSession nvHoraireSessionExamI = inputHoraireSession("nouvel horaire pour examen INTRA", scanner, true);
				coursAModifier.setExamI(nvHoraireSessionExamI);
				break;
			case "4": //exam F
				HoraireSession nvHoraireSessionExamF = inputHoraireSession("nouvel horaire pour examen FINAL", scanner, true);
				coursAModifier.setExamF(nvHoraireSessionExamF);
				break;
			case "5": //sigle
				System.out.println("nouveau sigle: ");
				String sigleInput= scanner.nextLine();
				coursAModifier.setSigle(sigleInput);
				System.out.println("sigle modifié à : " + sigleInput);
				break;
			case "6": //nbcredits
				System.out.println("nouveau nombre de credits: ");
				int nvNbCredits = Integer.parseInt(scanner.nextLine());
				coursAModifier.setNbCredits(nvNbCredits);
				System.out.println("Nombre de crédits maintenant : " + nvNbCredits);
				break;
			case "7": //supprimer
				supprimerCours(coursAModifier, repertoireCours);
				// de retour dans menu
				System.out.println("de retour dans menu modification de cours : ");
				break;
			default:
				System.out.println("Option invalide. Veuillez choisir une option valide.");
				break;
			}
		}
	}

	public static void supprimerCours(Cours coursASupprimer, TreeSet<Cours> repertoireCours) {
		repertoireCours.remove(coursASupprimer);
		System.out.println("Le cours a été supprimé.");
	}

	public static void creerNvCours(TreeSet<Cours> repertoireCours, Scanner scanner) {
		// créer cours et vérifier sigle est unique 
		Cours nvCours = instancierCoursSigle(scanner, repertoireCours);

		// Demander d'autres informations sur le cours (horaires, etc.) 
		HoraireSession horaireSessionTH = inputHoraireSession("Veuillez saisir pour le cours THEORIQUE (TH) :", scanner, false);
		nvCours.setHoraireTH(horaireSessionTH);

		HoraireSession horaireSessionTP = inputHoraireSession("Veuillez saisir pour le cours PRATIQUE (TP) :", scanner, false);
		nvCours.setHoraireTP(horaireSessionTP);

		HoraireSession horaireSessionExamI = inputHoraireSession("pour l'examen INTRA : ", scanner, true); 
		nvCours.setExamI(horaireSessionExamI);

		HoraireSession horaireSessionExamF = inputHoraireSession("pour l'examen FINAL : ", scanner, true);
		nvCours.setExamF(horaireSessionExamF);

		// Ajouter le cours à la liste (repertoire)
		repertoireCours.add(nvCours);  

		System.out.println("Le cours a été créé avec succès : ");
		
		System.out.println(nvCours.toString());
	}

	private static Cours instancierCoursSigle(Scanner scanner, TreeSet<Cours> repertoire) {
		boolean continuer = true;
		Cours nvCours = new Cours();
		
		while (continuer) {
			// demander sigle
			System.out.print("Entrez le sigle du cours : ");
			String sigle = scanner.nextLine();

			if (sigleExisteDeja(sigle, repertoire)) {
				System.out.println("Sigle existe déjà ! Reessayer");
				continue; // recommencer
			} else {
				nvCours.setSigle(sigle);
				continuer = false; // => c'est bon, sortir
			}
		}
		
		continuer = true;
		while(continuer) {
			// demander nb de credits
			try {
				System.out.print("Entrez le nombre de credits (eg. 3) : ");
				int nbCredits = Integer.parseInt(scanner.nextLine());
				nvCours.setNbCredits(nbCredits);
				continuer = false; // => c'est bon, sortir boucle
			} catch (NumberFormatException e) {
				System.out.println("invalide! Reessayer");
			}
		}	
		return nvCours;
	}
	

	private static boolean sigleExisteDeja(String sigle, TreeSet<Cours> repertoire) {
		boolean existe = false;
		for (Cours cours : repertoire) {
			String coursSigleExistant = cours.getSigle();
			if (sigle.equalsIgnoreCase(coursSigleExistant)) {
				existe = true;
			}
		}
		return existe;
	}

	public static HoraireSession inputHoraireSession(String typeDeCours, Scanner scanner, boolean estExam) {
		boolean continuer = true;
		HoraireSession horaireSession = new HoraireSession();

		String msg = estExam ? typeDeCours + " date d'examen (format requis: aaaa-mm-jj) : " : typeDeCours + " la date de DEBUT (format requis: aaaa-mm-jj) : "; 

		while(continuer) {
			// tout d'abord, pour la date de DEBUT
			try {
				// prendre les Strings pour instancier 
				System.out.println(msg);
				String dateDebut = scanner.nextLine(); // eg. 2023-01-01

				// set attribut
				horaireSession.setDateDebut(dateDebut); 

				// c'est exam, meme datefin que datedebut
				if (estExam) {
					horaireSession.setDateFin(dateDebut);  
					continuer = false; // => vu que c'est qu'un examen (date simple), on va sortir de la boucle
				}

			} catch (DateTimeParseException e) {
				System.out.println("mauvais format pour date, reessayer:");

				continue;
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
					System.out.println("mauvais format, reessayer:");

					continue;
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

		//		 horaireSemaine = new HoraireSemaine();
		while (continuer) {
			System.out.println("jour: ");
			// prendre input pour le jour
			String inputJour = scanner.nextLine(); // eg. lun

			if (inputJour.equals("0")) { // pour terminer
				continuer = false;
			} else {
				try {
					// prendre input pour les horaires pour ce jour, ajouter au Set 
					System.out.println("partie try: ");

					HoraireSemaine horaireSemaine = new HoraireSemaine(inputJour); // eg. lun
					HoraireHeure horaireHeure = inputHoraireHeure(inputJour, scanner); // eg. 08:00 debut, 10:00 fin 
					horaireSemaine.setHoraireHeure(horaireHeure);					
					System.out.println("Horaire créé : " + horaireSemaine);

					// ajouter au Set 
					setAvecHorairesSemaines.add(horaireSemaine);

					System.out.println("horaire semaine : " + setAvecHorairesSemaines);
					System.out.println("Est ce que le cours a lieu un autre jour de la semaine ? "
							+ "Sinon, saisir 0 pour terminer cette partie. "
							+ "Si oui, saisir l'autre jour de la semaine : ");

				} catch (DateTimeParseException e) {
					System.out.println("invalide. format requis: hh:mm");
					//					scanner.nextLine(); // vider input 

				} catch (InputMismatchException e) {
					System.out.println("invalide. doit etre un des suivants : lun, mar, mer, jeu, ven : ");
					//					scanner.nextLine(); // vider input 

				} catch(Exception e) { // autre input invalide si jamais 
					System.out.println("invalide.");
					//					scanner.nextLine();
				}
			}
		}
		return setAvecHorairesSemaines;
	}

	public static HoraireHeure inputHoraireHeure(String msgPrecisionCours, Scanner scanner) {
		boolean continuer = true;
		HoraireHeure horaireHeure = new HoraireHeure();
		String heureDebut = "";
		String msgInvalide = "Invalide. Format requis hh:mm (24H), (eg. 08:30 et non pas 8:30).";

		while(continuer) {
			try {
				// prendre les Strings pour instancier 
				System.out.println("Veuillez saisir l'heure de DEBUT pour " + msgPrecisionCours);
				System.out.println("Format requis hh:mm (24H), (eg. 08:30): ");
				heureDebut = scanner.nextLine();
				// set heure debut
				horaireHeure.setHeureDebut(heureDebut);
				continuer = false; // sortir boucle
			} catch (DateTimeParseException e) {
				System.out.println(msgInvalide);
				continue;
			}
		}
		continuer = true;
		while(continuer) {
			try {
				System.out.println("Veuillez saisir l'heure de FIN pour " + msgPrecisionCours + ": " );
				String heureFin = scanner.nextLine();

				//set heurefin
				horaireHeure.setHeureFin(heureFin);
				continuer = false; // => c'est bon, sortir boucle
			} catch (DateTimeParseException e) {
				System.out.println(msgInvalide);
			}
		}
		return horaireHeure;
	}
	
	
	public static void creerEmploiDuTemps(Scanner scanner, TreeSet<Cours> repertoireCours) {
		
		EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
		boolean continuer = true;
		
		
		//instancier
		while(continuer) {
			// demander nb de credits
			try {
				System.out.print("Entrez le nombre de credits max (eg. 16) : ");
				int nbCredits = Integer.parseInt(scanner.nextLine());
				emploiDuTemps = new EmploiDuTemps(nbCredits);				
				continuer = false; // => c'est bon, sortir boucle
			} catch (NumberFormatException e) {
				System.out.println("invalide! Reessayer");
			}
		}
		
		continuer = true;
		while(continuer) {
			System.out.print("Choisissez une option (ou faites le 0 pour terminer) : ");
			System.out.println("faites r pour voir le repertoire de cours");
            String input = scanner.nextLine();

            if (input.equals("0")) {
            	break;
            } else if (input.equalsIgnoreCase("r")) {
            	System.out.println(coursToStringAvecNumero(repertoireCours));
            	
//            	permettreSelectionCours(repertoireCours, 0); // TODO <- implementer
            }
            
			
		}

	}
}

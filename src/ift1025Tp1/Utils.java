package ift1025Tp1;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Utils {

	public static void menuModifierCours(TreeSet<Cours> repertoireCours, Scanner scanner) {
		System.out.println("Voici tous les cours du repertoire : ");
		Cours[] arrayCours = repertoireCours.toArray(new Cours[repertoireCours.size()]);
		System.out.println(Arrays.toString(arrayCours)); // TODO créer toString pour afficher avec index 

		String optionsMenuAAfficher = "Saisir le numero correspondant au cours que vous souhaitez modifier."
				+ " \nou faites le 0 pour quitter: ";
		System.out.println(optionsMenuAAfficher);

		boolean continuer = true;
		while (continuer) {
			String input = scanner.nextLine().trim();
			int inputInt = Integer.parseInt(input);
			Cours coursAModifier = new Cours();
			
			if (inputInt < arrayCours.length) {
				coursAModifier = arrayCours[inputInt];
				input = "ok";
			}
			

			switch (input) {
			case "ok":
				modifierCours(coursAModifier, repertoireCours, scanner); // lancer les menus
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
	public static void modifierCours(Cours coursAModifier, TreeSet<Cours> repertoireCours, Scanner scanner) {
		System.out.println("vous avez sélectionné le cours suivant :");
		String menuAAfficher = coursAModifier + "\n6. supprimer ce cours du repertoire" + 
				"\nVeuillez choisir le numero correspondant au parametre que vous souhaitez modifier" + 
				"\n(ou faites le 0 pour quitter ce menu et retourner en arriere) : ";  

		// coursAModifier : 
		/* eg. "Cours 
		1. horaireTH=" + horaireTH + ", 
		2. horaireTP=" + horaireTP + ", 
		3. examI=" + examI + ", 
		4.examF=" + examF
		5.nbCredits=" + nbCredits */

		boolean continuer = true;
		while(continuer) {
			System.out.println(menuAAfficher); // TODO maj coursAModifier
			String option = scanner.nextLine().trim();

			switch (option) {

			case "0":
				// Quitter
				System.out.println("Option 0 : Quitter");
				continuer = false;
				break;
			case "1": //horaireTH
				HoraireSession nvHoraireSessionTP = inputHoraireSession("nouvel horaire pour cours PRATIQUE (TP) : ",scanner,false);
				coursAModifier.setHoraireTP(nvHoraireSessionTP);
				break;
			case "2": //horaireTP
				HoraireSession nvHoraireSessionTH = inputHoraireSession("nouvel horaire pour cours THEORIQUE : ",scanner,false);
				coursAModifier.setHoraireTH(nvHoraireSessionTH);
				break;
			case "3": //exam I
				HoraireSession nvHoraireSessionExamI = inputHoraireSession("nouvel horaire pour examen INTRA", scanner, true);
				coursAModifier.setExamI(nvHoraireSessionExamI);
				break;
			case "4": //exam F
				HoraireSession nvHoraireSessionExamF = inputHoraireSession("nouvel horaire pour examen FINAL", scanner, true);
				coursAModifier.setExamI(nvHoraireSessionExamF);
				break;
			case "5": //nbcredits
				System.out.println("nouveau nombre de credits: ");
				int nvNbCredits = Integer.parseInt(scanner.nextLine());
				coursAModifier.setNbCrédits(nvNbCredits);
				System.out.println("Nombre de crédits maintenant : " + nvNbCredits);
				break;
			case "6": //supprimer
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
		System.out.println("Le cours spécifié n'existe pas dans la liste.");
	}

	public static void creerNvCours(TreeSet<Cours> repertoireCours, Scanner scanner) {
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
		repertoireCours.add(nvCours);  

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

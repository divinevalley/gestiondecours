package ift1025Tp1;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

import ift1025Tp1.EmploiDuTemps.CoursDejaAjouteException;
import ift1025Tp1.EmploiDuTemps.ValidationException;

public class Utils {
	// pour github commands: C:\Users\Deanna\Documents\UdeM\IFT1025 programmation 2\devoirs notés TP\tp1\Tp1\src\ift1025Tp1>



	public static void menuModifierCours(TreeSet<Cours> repertoireCours, Scanner scanner) {
		boolean continuer = true;
		while (continuer) {

			//afficher repertoire cours
			System.out.println(coursToStringNumerote(repertoireCours));
			String optionsMenuAAfficher = "Saisir le numero correspondant au cours que vous souhaitez modifier."
					+ " \n(ou faites le 0 pour quitter)"
					+ "\n\nCours a modifier: ";
			System.out.println(optionsMenuAAfficher);

			// recevoir input
			String input = scanner.nextLine().trim();

			try {
				// retrouver notre objet Cours a modifier
				Cours coursAModifier = afficherEtSelectionnerCours(repertoireCours, input);

				if (coursAModifier == null) { // cours va etre null si input = 0 (l'utilisateur veut quitter)
					continuer = false;
					break;
				}

				// lancer les menus modifier cours
				modifierCours(coursAModifier, repertoireCours, scanner); 

				// de retour dans menu 
				//				System.out.println(optionsMenuAAfficher);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Option invalide. Ce cours n'existe pas. Veuillez choisir une option valide.");
			} catch (NumberFormatException e) {
				System.out.println("Invalide. Veuillez saisir un choix (numéro)");
			}

		}
	}

	// afficher les cours en ARRAY numéroté, retourner Cours sélectionné
	private static Cours afficherEtSelectionnerCours(TreeSet<Cours> repertoireCours, String selection) throws IndexOutOfBoundsException, NumberFormatException {

		int inputInt = Integer.parseInt(selection); // convertir en int

		//afficher repertoire cours
		Cours[] arrayCours = repertoireCours.toArray(new Cours[repertoireCours.size()]);
		System.out.println(coursToStringNumerote(repertoireCours)); 

		if (inputInt==0) { // utiliser met 0 si veut quitter, fonction va renvoyer null pour signaler qu'il veut quitter 
			return null;
		}

		if (inputInt > arrayCours.length || inputInt < 0) {
			throw new IndexOutOfBoundsException();
		}

		return arrayCours[inputInt-1];  // -1 pour avoir numerotation depuis  0 
	}

	// transformer repertoire en string pour pouvoir afficher avec numerotation  
	private static String coursToStringNumerote(TreeSet<Cours> repertoireCours) {
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

		//		Cours: IFT3090
		//		1. cours TH: Dates : 2023-01-01 - 2023-05-05	[vendredi, 20:00-21:00.], 
		//		2. cours TP:Dates : 2023-01-01 - 2023-05-05	[vendredi, 20:00-21:00.], 
		//		3. Examen Intra: Dates : 2023-03-15 - 2023-03-15	[], 
		//		4. Examen Final: Dates : 2023-05-15 - 2023-05-15	[mardi, 19:00-20:00.], 
		//		5. Sigle:IFT3090, 
		//		6. Nb credits=3
		//		7. supprimer ce cours du repertoire

		String menuAAfficher =  "\n------Vous êtes maintenant sur le cours : ------\n\n" + coursAModifier.toStringAvecNumero();  
		String menuAppend = "\n7. supprimer ce cours du repertoire" + 
				"\n\nVeuillez choisir le numero correspondant au parametre que vous souhaitez modifier" + 
				"\n(ou faites le 0 pour quitter ce menu et retourner en arriere) : ";
		menuAAfficher += menuAppend;

		boolean continuer = true;
		boolean coursSupprimer = false;
		while(continuer) {

			System.out.println(menuAAfficher);
			String option = coursSupprimer ? "0" :  scanner.nextLine().trim(); // si cours supprimé, on va dire "0" automatiquement pour signaler quitter 

			switch (option) {

			case "0":
				// Quitter
				continuer = false;
				break;
			case "1": //horaireTH
				HoraireSession nvHoraireSessionTH = inputHoraireSession("TH: Pour modifier le cours THEORIQUE (TH), ", scanner, false);
				coursAModifier.setHoraireTH(nvHoraireSessionTH);
				menuAAfficher = coursAModifier.toStringAvecNumero() + menuAppend;  // mettre a jour cours a afficher (pour afficher les bonnes info a jour)  
				break;
			case "2": //horaireTP
				HoraireSession nvHoraireSessionTP = inputHoraireSession("TP: Pour modifier le cours PRATIQUE (TP), ", scanner, false);
				coursAModifier.setHoraireTP(nvHoraireSessionTP);
				menuAAfficher = coursAModifier.toStringAvecNumero() + menuAppend;  // maj cours a afficher
				break;
			case "3": //exam I
				menuAAfficher = menuInputExamen(true, menuAppend, scanner, coursAModifier); // montre menu pour renseigner examen, maj cours a afficher
				break;
			case "4": //exam F
				menuAAfficher = menuInputExamen(false, menuAppend, scanner, coursAModifier);
				break;
			case "5": //sigle
				System.out.println("nouveau sigle: ");
				String sigleInput= scanner.nextLine();
				coursAModifier.setSigle(sigleInput);
				System.out.println("sigle modifié à : " + sigleInput);
				menuAAfficher = coursAModifier.toStringAvecNumero() + menuAppend;   // maj cours a afficher
				break;
			case "6": //nbcredits
				System.out.println("nouveau nombre de credits: ");
				int nvNbCredits = Integer.parseInt(scanner.nextLine());
				coursAModifier.setNbCredits(nvNbCredits);
				System.out.println("Nombre de crédits maintenant : " + nvNbCredits);
				menuAAfficher = coursAModifier.toStringAvecNumero() + menuAppend;   // maj cours a afficher
				break;
			case "7": //supprimer
				supprimerCours(coursAModifier, repertoireCours);
				// une fois supprimé on ne va pas retourner sur le cours, donc on va signaler de quitter
				coursSupprimer = true;
				menuAAfficher = "";
				break;
			default:
				System.out.println("Option invalide. Veuillez choisir une option valide.");
				break;
			}
		}
	}

	// String menuAppend seulement pour menu modif cours (donne instructions pour supprimer)
	// return tous le menu du cours 1.2.3.4... avec les options a modifier dans le menu modification cours
	private static String menuInputExamen(boolean estIntra, String menuAppend, Scanner scanner, Cours coursAModifier) {
		String msgTypeExam = estIntra ? "Pour examen INTRA," : "Pour examen FINAL,";

		while (true) {
			try {
				HoraireSession nvHoraireSessionExam = inputHoraireSession(msgTypeExam, scanner, true);

				if(estIntra) {
					coursAModifier.setExamI(nvHoraireSessionExam);					
				} else {
					coursAModifier.setExamF(nvHoraireSessionExam);
				}

				return coursAModifier.toStringAvecNumero() + menuAppend;  // maj cours a afficher
			} catch (InputMismatchException e) {
				System.out.println("\nErreur. examen intra doit etre pendant le semestre et l'examen final ne peut pas etre avant le debut. " 
						+ coursAModifier.getHoraireTH().toStringDatesSeulement() + ". SVP Reessayer.");
			} catch (NullPointerException e) {
				System.out.println("Vous devez saisir des dates pour les cours théoriques (TH) si vous souhaitez avoir une date d'examen");
				return coursAModifier.toStringAvecNumero() + menuAppend;  // quitter menu, maj cours a afficher, 
			}
		}
	}

	public static void supprimerCours(Cours coursASupprimer, TreeSet<Cours> repertoireCours) {
		repertoireCours.remove(coursASupprimer);
		System.out.println("Le cours " + coursASupprimer.getSigle() + " a été supprimé.");
	}

	public static void creerNvCours(TreeSet<Cours> repertoireCours, Scanner scanner) {
		// créer cours et vérifier sigle est unique 
		Cours nvCours = instancierCoursSigle(scanner, repertoireCours);

		// Demander d'autres informations sur le cours (horaires, etc.) 
		HoraireSession horaireSessionTH = inputHoraireSession("Veuillez saisir pour le cours THEORIQUE (TH),", scanner, false);
		nvCours.setHoraireTH(horaireSessionTH);

		HoraireSession horaireSessionTP = inputHoraireSession("Veuillez saisir pour le cours PRATIQUE (TP),", scanner, false);
		nvCours.setHoraireTP(horaireSessionTP);

		menuInputExamen(true, "", scanner, nvCours);
		//		HoraireSession horaireSessionExamI = inputHoraireSession("pour l'examen INTRA,", scanner, true); 
		//		nvCours.setExamI(horaireSessionExamI);

		menuInputExamen(false, "", scanner, nvCours);
		//		HoraireSession horaireSessionExamF = inputHoraireSession("pour l'examen FINAL,", scanner, true);
		//		nvCours.setExamF(horaireSessionExamF);

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
		String msgErreurFormatDate = "Mauvais format pour date (format requis : aaaa-mm-jj). Reessayer: ";
		String msgErreurDateIncoherente = "Date de fin doit être après la date de début. Ressayer: ";
		String msg = estExam ? typeDeCours + " la date d'examen (format requis: aaaa-mm-jj) : " : 
			typeDeCours + " la date de DEBUT (format requis: aaaa-mm-jj) : "
			+ "\n (OU faites la touche entrée pour ne rien renseigner (ie. pas de TP, pas d'examen...))"; 

		while(continuer) {
			// tout d'abord, pour la date de DEBUT
			try {
				// prendre les Strings pour instancier 
				System.out.println(msg);
				String dateDebut = scanner.nextLine(); // eg. 2023-01-01
				if (dateDebut.equals("")) { // si utilisateur n'a rien renseigner, on va sauter cette partie
					return null; // on va etre HoraireSession comme etant null
				}
				// set attribut
				horaireSession.setDateDebut(dateDebut);
				continuer = false; //c'est bon, sortir

				// c'est exam, meme datefin que datedebut
				if (estExam) {
					horaireSession.setDateFin(dateDebut);  
					continuer = false; // => vu que c'est qu'un examen (date simple), on va sortir de la boucle
				}

			} catch (DateTimeParseException e) {
				System.out.println(msgErreurFormatDate);
				continue; // reprend depuis debut boucle
			} catch (InputMismatchException e) {
				System.out.println(msgErreurDateIncoherente);
				continue; //reprend depuis debut
			}
		}

		// ensuite pour la date de FIN mais seulement si ce n'est pas un examen
		if (!estExam) {
			continuer = true;
			while (continuer){
				try {
					// prendre les Strings pour instancier 
					System.out.println(typeDeCours + " la date de FIN");
					String dateFin = scanner.nextLine(); // eg. 2023-05-01

					// set attribut 
					horaireSession.setDateFin(dateFin);
					continuer = false; // => c'est bon. sortir boucle
				} catch (DateTimeParseException e) {
					System.out.println(msgErreurFormatDate);
					continue;
				} catch (InputMismatchException e) {
					System.out.println(msgErreurDateIncoherente);
					continue;
				}

				// ensuite prendre input emploi du temps hebdomadaire
				HashSet<HoraireSemaine> horaireSemaine = inputHoraireSemaine(scanner, estExam, horaireSession.getDateFin()); // eg. les lundis, 08:00 - 10:00
				horaireSession.setHoraireSemaine(horaireSemaine);
			}

		}
		return horaireSession;
	}

	// prendre input, add jours de la semaine avec horaires
	public static HashSet<HoraireSemaine> inputHoraireSemaine(Scanner scanner, boolean estExam, LocalDate dateDebut) {
		HashSet<HoraireSemaine> setAvecHorairesSemaines = new HashSet<>();
		boolean continuer = true;

		while (continuer) {
			String inputJour = "";

			// demander jour seulement si ce n'est pas un examen
			if (!estExam) {
				System.out.println("Préciser l'horaire : \nSaisir le numero correspondant au jour de la semaine : \n"
						+ "1 - lundi, \n"
						+ "2 - mardi, \n"
						+ "3 - mercredi, \n"
						+ "4 - jeudi, \n"
						+ "5 - vendredi\n"
						+ "(Si vous avez terminé de saisir l'horaire hebdomadaire, saisir 0) :");

				// prendre input pour le jour
				inputJour = scanner.nextLine(); // eg. "1" pour lundi
			} else {
				// si c'est exam, on calcule quel jour c'est selon la date de facon automatique
				inputJour = dateDebut.getDayOfWeek().getValue()  + "";
			}

			if (inputJour.equals("0")) { // 0 pour terminer
				continuer = false;
			} else {
				try {
					// prendre input pour les horaires pour ce jour, ajouter au Set 
					int inputJourInt = Integer.parseInt(inputJour); // NumberFormatException possible
					HoraireSemaine horaireSemaine = new HoraireSemaine(inputJourInt); // eg. "1" pour lundi // InputMismatchException possible
					HoraireHeure horaireHeure = prendreInputHoraireHeure(horaireSemaine.jourSemaineEnString(), scanner); // eg. 08:00 debut, 10:00 fin  // DateTimeParse Exception possible
					horaireSemaine.setHoraireHeure(horaireHeure);					

					// ajouter au Set 
					setAvecHorairesSemaines.add(horaireSemaine);

					System.out.println("horaire semaine : " + setAvecHorairesSemaines);

					if (estExam) { // si exam, pas besoin de demander si ça prend lieu d'autres jours de la semaine. on s'arrete là.
						break;
					}
					System.out.println("Est-ce que le cours a lieu un autre jour de la semaine ? "
							+ "Sinon, saisir 0 pour terminer cette partie. "
							+ "Si oui, saisir l'autre jour de la semaine : ");

				} catch (DateTimeParseException e) {
					System.out.println("invalide. format requis: hh:mm");

				} catch (InputMismatchException e) {
					System.out.println("invalide. doit etre entre 1-7 : \n1- lun, 2- mar, 3- mer, 4- jeu, 5- ven, 6- sam, 7- dim: "); 

				} catch(NumberFormatException e) {  
					System.out.println("Invalide. Reessayer.");
					e.printStackTrace(); // TODO remove
				}
			}
		}
		return setAvecHorairesSemaines;
	}


	public static HoraireHeure prendreInputHoraireHeure(String msgPrecisionCours, Scanner scanner) {
		boolean continuer = true;
		HoraireHeure horaireHeure = new HoraireHeure();
		String heureDebut = "";
		String msgFormatInvalide = "Invalide. Format requis hh:mm (24H), (eg. 08:30 et non pas 8:30).";

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
				System.out.println(msgFormatInvalide);
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
				System.out.println(msgFormatInvalide);
			} catch (InputMismatchException e) {
				System.out.println("Erreur: Heure de fin est avant heure de debut.");
			}
		}
		return horaireHeure;
	}


	// afficher le repértoire des cours, instancier un objet EmploiDuTemps à partir du input de l'utilisateur
	public static EmploiDuTemps prendreInputCreerEmploiDuTemps(Scanner scanner, TreeSet<Cours> repertoireCours, EmploiDuTemps emploiDuTemps) {

		// tout d'abord, créer un emploi du temps vide
		System.out.println("\n----------Menu Création d'emploi du temps--------");

		boolean continuer = true;
		while(continuer) {
			// demander nb de credits
			try {
				System.out.print("Entrez le nombre de credits MAX pour cet emploi du temps (eg. 16) : ");
				int nbCredits = Integer.parseInt(scanner.nextLine());
				emploiDuTemps = new EmploiDuTemps(nbCredits);				
				continuer = false; // => c'est bon, sortir boucle
			} catch (NumberFormatException e) {
				System.out.println("Invalide! Reessayer");
			}
		}

		emploiDuTemps = ajouterEmploiDuTemps(repertoireCours, scanner, emploiDuTemps);

		return emploiDuTemps;
	}
	
	//lancer menu ajouter emploi du temps, renvoie nouvel objet emploi du temps modifié
	public static EmploiDuTemps ajouterEmploiDuTemps(TreeSet<Cours> repertoireCours, Scanner scanner, EmploiDuTemps emploiDuTemps) {
		boolean continuer = true;
		String msgErreur = "\n";

		//tout d'abord afficher repertoire pour premiere fois
		System.out.println(coursToStringNumerote(repertoireCours));
		while(continuer) {

			// afficher emploi du temps actuel
			System.out.println("\n=======Votre emploi du temps actuel:=======");
			System.out.println(emploiDuTemps.toString());

			System.out.println(msgErreur);
			System.out.println("\n\nVeuillez sélectionner le numéro du cours que vous souhaitez ajouter (eg. \"1\") \n(ou faites le 0 pour terminer):");

			Cours coursSelectionne  = new Cours();
			String inputNumeroCours = scanner.nextLine();

			if (inputNumeroCours.equals("0")) {
				continuer = false;
				System.out.println("Merci. Voici votre emploi du temps:\n");
				System.out.println(emploiDuTemps.toString());
				break;
			}

			try {
				coursSelectionne = afficherEtSelectionnerCours(repertoireCours, inputNumeroCours); // re-afficher rep cours, retrouver l'objet Cours choisi
				System.out.println("Vous avez choisi : " + coursSelectionne.getSigle());
			} catch (NumberFormatException e) {
				msgErreur = "Choix de cours invalide. Reessayer:";
				continue; // recommencer boucle
			} catch(IndexOutOfBoundsException e) {
				msgErreur = "Ce cours n'existe pas. Ressayer:";
//				e.printStackTrace();
				continue; // recommencer boucle 
			}

			// seulement si tout va bien, on ajoute le cours 
			try {
				emploiDuTemps.ajouterCours(coursSelectionne);
				msgErreur = ""; // vider msg erreur pour ne rien afficher la prochaine boucle 
			} catch (ValidationException e) {
				msgErreur =  "Conflit horaire! Le cours n'a pas été ajouté.";
			} catch(CoursDejaAjouteException e) {
				msgErreur = "Ce cours est deja dans votre emploi du temps. Le cours n'a pas été ajouté";
			} catch (InputMismatchException e) {
				msgErreur = "Vous ne pouvez pas dépasser le nombre de crédit maximum. Le cours n'a pas été ajouté.\n"
						+ "Vous pouvez ajuster le nombre de crédits maximum dans le menu modifier emploi du temps.";
			} catch(Exception e) {
				e.printStackTrace(); //TODO remove
			}

		}
		return emploiDuTemps;
	}
	

	//afficher emploi du temps avec numerotation pour suppression
	public static EmploiDuTemps modifierEmploiDuTemps(Scanner scanner, EmploiDuTemps emploiDuTemps) {
		try {
			

//			boolean continuer = true;

			while(true) {
				//afficher emploi du temps avec numerotation
				ArrayList<Cours> listCours = afficherEmploiDuTempsNumerote(emploiDuTemps);
				
				System.out.println("\nSélectionner le numéro du cours à supprimer "
						+ "\nFaites le \"c\" pour changer nombre de crédits"
						+ "\n(ou faites le 0 pour quitter/terminer) ");
				String input = scanner.nextLine();

				if (input.equals("0")) { //quitter
					
					break;
				}

				if (input.equalsIgnoreCase("c")) { // modifier max credits
					modifierMaxcredits(emploiDuTemps, scanner);			
					continue; // reprend depuis debut
				}

				try {
					int inputInt = Integer.parseInt(input);
					Cours coursSelectionner = listCours.get(inputInt-1); // -1 pour ajuster index
					emploiDuTemps.supprimerCours(coursSelectionner);
					listCours = afficherEmploiDuTempsNumerote(emploiDuTemps); // nouvel affichage
				} catch (NumberFormatException e) {
					System.out.println("Invalide. doit etre un numero. eg. 1\nReessayer:");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Cours n'existe pas! Reessayer:");
				}
			}
		} catch (IllegalStateException e) {
			System.out.println("Vous devez créer votre emploi avant de le modifier");
		}
		return emploiDuTemps;
	}

	private static void modifierMaxcredits(EmploiDuTemps emploiDuTemps, Scanner scanner) {
		while(true) {
			try {
				System.out.println("Nouveau max souhaité: ");
				int inputCredits = Integer.parseInt(scanner.nextLine());
				emploiDuTemps.setNbCreditMax(inputCredits);
				System.out.println("Credits max est maintenant : " + inputCredits);
				break;

			} catch (NumberFormatException e) {
				System.out.println("Nombre de credits doit etre un chiffre entier (eg. 12)");
			}
		}
	}

	private static ArrayList<Cours> afficherEmploiDuTempsNumerote(EmploiDuTemps emploiDuTemps) throws IllegalStateException {
		System.out.println("\n===========EMPLOI DU TEMPS============");
		ArrayList<Cours> listeCours = emploiDuTemps.getListeCours();
		if (listeCours==null) { //checker si null (pas cree)
			System.out.println("(emploi du temps n'a pas été créé)");
			throw new IllegalStateException();
		} else if(listeCours.isEmpty()) {
			System.out.println("(aucun cours)");
		} else {
			int i = 1; //commencer numerotation a 1
			// afficher tous les cours dans emploi du temps
			for(Cours cours : listeCours) {
				System.out.println(">>>(" + i + ")<<<");
				System.out.println(cours.toString());
				i++;
			}
			return listeCours;
		}

		System.out.println(emploiDuTemps.afficherCredits()); // eg. 12 credits (max 18 credits)
		return new ArrayList<Cours>();
	}
}

package ift1025Tp1;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

import ift1025Tp1.EmploiDuTemps.ValidationException;

public class Utils {
	// pour github commands: C:\Users\Deanna\Documents\UdeM\IFT1025 programmation 2\devoirs notés TP\tp1\Tp1\src\ift1025Tp1>
	
	
	// TODO 
	// verifier horaire coherent (pas 09:00 - 08:00)
	// verifier si conflit horaire au sein d'un meme cours
	// proposer modification d'une seule partie plutôt que parcourir tout le menu
	// permettre de skip la partie TP, si pas de TP, pas d'examen intra, etc 
	// ne pas numeroter les infos dans cours tostring 

	public static void menuModifierCours(TreeSet<Cours> repertoireCours, Scanner scanner) {
		boolean continuer = true;
		while (continuer) {

			//afficher repertoire cours
			System.out.println(coursToStringNumerote(repertoireCours));
			String optionsMenuAAfficher = "\nSaisir le numero correspondant au cours que vous souhaitez modifier."
					+ " \n(ou faites le 0 pour quitter)"
					+ "\n\n cours a modifier=> ";
			System.out.println(optionsMenuAAfficher);

			// recevoir input
			String input = scanner.nextLine().trim();

			try {
				// retrouver notre objet Cours a modifier
				Cours coursAModifier = permettreSelectionCours(repertoireCours, input);
				
				if (coursAModifier == null) { // cours va etre null si input = 0 (l'utilisateur veut quitter)
					continuer = false;
					break;
				}
				
				// lancer les menus modifier cours
				modifierCours(coursAModifier, repertoireCours, scanner); 
				
				// de retour dans menu 
				System.out.println(optionsMenuAAfficher);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Option invalide. Ce cours n'existe pas. Veuillez choisir une option valide.");
			} catch (NumberFormatException e) {
				System.out.println("Invalide. Veuillez saisir un choix (numéro)");
			}
			
		}
	}

	// afficher les cours en ARRAY numéroté, retourner Cours sélectionné
	private static Cours permettreSelectionCours(TreeSet<Cours> repertoireCours, String selection) throws IndexOutOfBoundsException, NumberFormatException {
		
		int inputInt = Integer.parseInt(selection); // convertir en int
		
		//afficher repertoire cours
		Cours[] arrayCours = repertoireCours.toArray(new Cours[repertoireCours.size()]);
		System.out.println(coursToStringNumerote(repertoireCours)); 
		
		if (inputInt==0) { // utiliser met 0 si veut quitter, fonction va renvoyer null pour signaler qu'il veut quitter 
			return null;
		}
		
		if (inputInt >= arrayCours.length || inputInt < 0) {
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
//					continue; // recommencer boucle depuis debut
				} 


			} 
			
			// prendre input emploi du temps hebdomadaire
			HashSet<HoraireSemaine> horaireSemaine = inputHoraireSemaine(scanner, estExam, horaireSession.getDateFin()); // eg. les lundis, 08:00 - 10:00
			horaireSession.setHoraireSemaine(horaireSemaine);
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
					HoraireHeure horaireHeure = inputHoraireHeure(inputJour, scanner); // eg. 08:00 debut, 10:00 fin  // DateTimeParse Exception possible
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
					System.out.println("invalide. doit etre entre 1-5 : \n1- lun, 2- mar, 3- mer, 4- jeu, 5- ven : "); 

				} catch(NumberFormatException e) {  
					System.out.println("invalide. reessayer ");
					e.printStackTrace();
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
	
	
	// afficher le repértoire des cours, instancier un objet EmploiDuTemps à partir du input de l'utilisateur
	public static EmploiDuTemps creerEmploiDuTemps(Scanner scanner, TreeSet<Cours> repertoireCours, EmploiDuTemps emploiDuTemps) {
		
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
				
		continuer = true;
		while(continuer) {
			//afficher repertoire
			System.out.println(coursToStringNumerote(repertoireCours));
			
			// afficher emploi du temps actuel
			System.out.println("Votre emploi du temps actuel: ");
			System.out.println(emploiDuTemps.toString());
			System.out.println("\n\nVeuillez sélectionner le numéro du cours que vous souhaitez ajouter (eg. \"1\") \n ou faites le 0 pour terminer");
			
			Cours coursSelectionne  = new Cours();
			String inputNumeroCours = scanner.nextLine();
			
			if (inputNumeroCours.equals("0")) {
				continuer = false;
				System.out.println("Merci. Voici votre emploi du temps:\n");
				System.out.println(emploiDuTemps.toString());
				break;
			}
			
			try {
				coursSelectionne = permettreSelectionCours(repertoireCours, inputNumeroCours); // retrouver l'objet Cours choisi
				System.out.println("Vous avez choisi : " + coursSelectionne.getSigle());
			} catch (NumberFormatException e) {
				System.out.println("Choix de cours invalide. Reessayer:");
				continue; // recommencer boucle
			} catch(IndexOutOfBoundsException e) {
				System.out.println("Ce cours n'existe pas. Ressayer:");
				continue; // recommencer boucle 
			}
	
			// seulement si tout va bien, on ajoute le cours 
			try {
				emploiDuTemps.ajouterCours(coursSelectionne);
			} catch (ValidationException e) {
				System.out.println("Conflit horaire! Le cours n'a pas été ajouté.");
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return emploiDuTemps;
	}
	
	public static EmploiDuTemps modifierEmploiDuTemps(Scanner scanner, EmploiDuTemps emploiDuTemps) {
		
		//afficher emploi du temps avec numerotation
		ArrayList<Cours> listCours = afficherEmploiDuTempsNumerote(emploiDuTemps);
		
		boolean continuer = true;

		while(continuer) {
			System.out.println("sélectionner le numéro du cours à supprimer \n(ou faites le 0 pour quitter/terminer) ");
			String input = scanner.nextLine();
			
			if (input.equals("0")) {
				continuer = false;
				break;
			}
			
			try {
				int inputInt = Integer.parseInt(input);
				Cours coursSelectionner = listCours.get(inputInt-1); // -1 pour ajuster index
				emploiDuTemps.supprimerCours(coursSelectionner);
				listCours = afficherEmploiDuTempsNumerote(emploiDuTemps); // nouvel affichage
			} catch (NumberFormatException e) {
				System.out.println("invalide. doit etre un numero. eg. 1\nressayer:");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("cours n'existe pas! reesayer:");
			}
		}
		
		return emploiDuTemps;
	}
	
	private static ArrayList<Cours> afficherEmploiDuTempsNumerote(EmploiDuTemps emploiDuTemps){
		int i = 1; //commencer numerotation a 1
		ArrayList<Cours> listCours = emploiDuTemps.getListeCours();
		for(Cours cours : listCours) {
			System.out.println(">>>(" + i + ")<<<");
			System.out.println(cours.toString());
			i++;
		}
		
		return listCours;
	}
}

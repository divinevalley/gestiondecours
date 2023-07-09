package ift1025Tp1;

/**
 * TP1 Système de gestion des cours
 * IFT 1025 Programmation 2 
 * Eté 2023  
 * Juillet 2023 
 * 
 * @author mimit, Deanna Wung
 * 
 */

import java.util.Scanner;
import java.util.TreeSet;

public class Tp1 {
    public static void main(String[] args) {
    	TreeSet<Cours> repertoireCoursDispo = new TreeSet<>();  // eg. <"IFT1025", objet Cours>
    	EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
    	
    	// exemple repertoire: 
    	HoraireSession sessionHiver = new HoraireSession("2023-01-01", "2023-05-05");
    	sessionHiver.addHoraireSemaine(1, "08:00", "10:00");
    	HoraireSession examDate = new HoraireSession("2023-03-03", "2023-03-03");
    	examDate.addHoraireSemaine(2, "08:00", "12:00");
    	
    	Cours cours1 = new Cours(sessionHiver, sessionHiver, examDate, examDate, "ift1025", 3);
    	repertoireCoursDispo.add(cours1);
    	
    	Cours cours2 = new Cours(sessionHiver, sessionHiver, examDate, examDate, "ift2015", 3);
    	repertoireCoursDispo.add(cours2);
    	
    	Cours cours3 = new Cours(sessionHiver, sessionHiver, examDate, examDate, "mat1000", 4);
    	repertoireCoursDispo.add(cours3);
    	
    	Cours cours4 = new Cours(sessionHiver, sessionHiver, examDate, examDate, "ift2125", 3);
    	repertoireCoursDispo.add(cours4);
    	
    	Cours cours5 = new Cours(sessionHiver, sessionHiver, examDate, examDate, "ift3090", 3);
    	repertoireCoursDispo.add(cours5);
    	
    	Cours cours6 = new Cours(sessionHiver, sessionHiver, examDate, examDate, "mat1600", 4);
    	repertoireCoursDispo.add(cours6);
    	
    	
        Scanner scanner = new Scanner(System.in);
        
        String menuMain = "Menu MAIN :\n" + 
        		"1. Visualiser tous les cours et/ou modifier un cours existant\n" +
        		"2. Creer un emploi du temps personnalisé\n" + 
        		"3. Créer un nouveau cours pour le repertoire de cours\n" + 
        		"4. Consulter/modifier emploi du temps\n" + 
        		"0. Quitter";
        
//        System.out.println(menuMain);
        
        boolean continuer = true;

        while (continuer) {
        	System.out.println(menuMain);
            System.out.print("Choisissez une option (ou faites le 0 pour quitter) : ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Modifier un cours dans le repertoire: ");
                    Utils.menuModifierCours(repertoireCoursDispo, scanner); // lancer menu modif
                    break;
                case "2":
                    // emploi du temps personnalisé
                    emploiDuTemps = Utils.creerEmploiDuTemps(scanner, repertoireCoursDispo, emploiDuTemps); 
                    
                    break;
                case "3":
                    System.out.println("créer un nouveau cours"); 
                    Utils.creerNvCours(repertoireCoursDispo, scanner);
                    
                   
                    break;
                case "4": 
                	// consulter / modifier emploi du temps
                	Utils.modifierEmploiDuTemps(scanner, emploiDuTemps);
                	break;
                case "0":
                    // Quitter
                    System.out.println("Quitter le programme...");
                    continuer = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
                    break;
            }

            System.out.println(); 
        }

        System.out.println("Fin de programme");
        scanner.close();
    }
}


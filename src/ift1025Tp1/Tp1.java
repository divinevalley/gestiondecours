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
    	
    	//variables pour stocker repertoire et emploi du temps 
    	TreeSet<Cours> repertoireCoursDispo = new TreeSet<>(); 
    	EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
    	
    	// exemple repertoire (pour tester): 
    	HoraireSession sessionHiver1 = new HoraireSession("2023-01-01", "2023-05-05");
    	HoraireSession sessionHiver2 = new HoraireSession("2023-01-01", "2023-05-05");
    	HoraireSession sessionHiver3 = new HoraireSession("2023-01-01", "2023-05-05");
    	HoraireSession sessionHiver4 = new HoraireSession("2023-01-01", "2023-05-05");
    	HoraireSession sessionHiver5 = new HoraireSession("2023-01-01", "2023-05-05");
    	
    	sessionHiver1.addHoraireSemaine(1, "08:00", "10:00");
    	sessionHiver2.addHoraireSemaine(2, "10:00", "12:00");
    	sessionHiver3.addHoraireSemaine(3, "13:00", "14:00");
    	sessionHiver4.addHoraireSemaine(4, "18:00", "19:00");
    	sessionHiver5.addHoraireSemaine(5, "20:00", "21:00");
    	
    	HoraireSession examDate1 = new HoraireSession("2023-03-03", "2023-03-03");
    	HoraireSession examDate2 = new HoraireSession("2023-03-04", "2023-03-04");
    	HoraireSession examDate3 = new HoraireSession("2023-03-05", "2023-03-05");
    	HoraireSession examDate4 = new HoraireSession("2023-03-10", "2023-03-10");
    	HoraireSession examDate5 = new HoraireSession("2023-03-15", "2023-03-15");
    	
    	
    	HoraireSession examDateFinal1 = new HoraireSession("2023-05-03", "2023-05-03");
    	HoraireSession examDateFinal2 = new HoraireSession("2023-05-05", "2023-05-05");
    	HoraireSession examDateFinal3 = new HoraireSession("2023-05-08", "2023-05-08");
    	HoraireSession examDateFinal4 = new HoraireSession("2023-05-10", "2023-05-10");
    	HoraireSession examDateFinal5 = new HoraireSession("2023-05-15", "2023-05-15");
    	examDateFinal1.addHoraireSemaine(2, "15:00", "16:00");
    	examDateFinal2.addHoraireSemaine(3, "11:00", "12:00");
    	examDateFinal3.addHoraireSemaine(4, "13:00", "14:00");
    	examDateFinal4.addHoraireSemaine(5, "18:00", "19:00");
    	examDateFinal5.addHoraireSemaine(2, "19:00", "20:00");
    	
    	Cours cours1 = new Cours(sessionHiver1, sessionHiver1, examDate1, examDateFinal1, "ift1025", 3);
    	repertoireCoursDispo.add(cours1);
    	
    	Cours cours2 = new Cours(sessionHiver2, sessionHiver2, examDate2, examDateFinal2, "ift2015", 3);
    	repertoireCoursDispo.add(cours2);
    	
    	Cours cours3 = new Cours(sessionHiver3, sessionHiver3, examDate3, examDateFinal3, "mat1000", 4);
    	repertoireCoursDispo.add(cours3);
    	
    	Cours cours4 = new Cours(sessionHiver4, sessionHiver4, examDate4, examDateFinal4, "ift2125", 3);
    	repertoireCoursDispo.add(cours4);
    	
    	Cours cours5 = new Cours(sessionHiver5, sessionHiver5, examDate5, examDateFinal5, "ift3090", 3);
    	repertoireCoursDispo.add(cours5);
    	
    	Cours cours6 = new Cours(sessionHiver1, sessionHiver1, examDate5, examDateFinal5, "mat1600", 4);
    	repertoireCoursDispo.add(cours6);
    	
    	
        Scanner scanner = new Scanner(System.in);
        
        String menuMain = "Menu MAIN :\n" + 
        		"1. (TGDE) Visualiser tous les cours du repertoire et/ou modifier un cours existant\n" +
        		"2. (TGDE) Créer un nouveau cours pour le repertoire de cours\n" +
        		"3. Creer et ajouter des cours a un emploi du temps personnalisé\n" + 
        		"4. Modifier (supprimer des cours) de l'emploi du temps\n" + 
        		"0. Quitter le programme";
        
        boolean continuer = true;

        while (continuer) {
        	System.out.println(menuMain);
            System.out.print("\nChoisissez une option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Modifier un cours dans le repertoire: ");
                    Utils.menuModifierCours(repertoireCoursDispo, scanner); // lancer menu modif
                    break;
                case "2":
                    System.out.println("créer un nouveau cours"); 
                    Utils.creerNvCours(repertoireCoursDispo, scanner);
                    break;
                case "3":
                    // emploi du temps personnalisé
                    emploiDuTemps = Utils.prendreInputCreerEmploiDuTemps(scanner, repertoireCoursDispo, emploiDuTemps); 
                    break;
                case "4": 
                	// modifier (supprimer) emploi du temps
                	Utils.modifierEmploiDuTemps(scanner, emploiDuTemps);
                	break;
                case "0":
                    // Quitter
                    System.out.println("Quitter le programme... au revoir.");
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


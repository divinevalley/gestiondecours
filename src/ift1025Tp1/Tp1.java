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
        Scanner scanner = new Scanner(System.in);
        
        String menuMain = "Menu interactif :\n" + 
        		"1. Visualiser tous les cours et/ou modifier un cours existant\n" +
        		"2. Creer un emploi du temps personnalisé\n" + 
        		"3. Créer un nouveau cours pour le repertoire de cours\n" + 
        		"0. Quitter";
        
        System.out.println(menuMain);
        
        boolean continuer = true;

        while (continuer) {


            System.out.print("Choisissez une option (ou faites le 0 pour quitter) : ");
            String option = scanner.nextLine();
//            scanner.nextLine(); // on vide la ligne de l'entier

            switch (option) {
                case "1":
                    System.out.println("Modifier un cours dans le repertoire: ");
                    
                    // code ici
                    Utils.menuModifierCours(repertoireCoursDispo, scanner); // lancer menu modif
                    
                    System.out.println("de retour dans menu MAIN");
                    break;
                case "2":
                    System.out.println("Option 2 : "); // emploi du temps personnalisé
                    
                    break;
                case "3":
                    System.out.println("Option 3 : créer un nouveau cours"); 
                    Utils.creerNvCours(repertoireCoursDispo, scanner);
                    System.out.println("de retour dans menu MAIN");
                    System.out.println(menuMain);
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

            System.out.println(); 
        }

        System.out.println("Fin de la modification");
        scanner.close();
    }
}


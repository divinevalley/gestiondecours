package ift1025Tp1;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
import java.util.TreeMap;

public class Tp1 {
    public static void main(String[] args) {
    	TreeMap<String, Cours> repertoireCoursDispo = new TreeMap<>();  // eg. <"IFT1025", objet Cours>
    	

    	
    	
        Scanner scanner = new Scanner(System.in);
        

        System.out.println("Menu interactif :");
        System.out.println("1. Modifier un cours existant dans le repertoire de cours");
        System.out.println("2. Creer un emploi du temps personnalisé");
        System.out.println("3. Créer un nouveau cours pour le repertoire de cours");
        
        System.out.println("0. Quitter");
        
        boolean continuer = true;

        while (continuer) {


            System.out.print("Choisissez une option (ou faites le 0 pour quitter) : ");
            String option = scanner.nextLine();
//            scanner.nextLine(); // on vide la ligne de l'entier

            switch (option) {
                case "1":
                    System.out.println("Modifier un cours dans le repertoire: ");
                    
                    // code ici
                    Utils.menuModifierCours(repertoireCoursDispo); // lancer menu modif
                    
                    System.out.println("de retour dans menu MAIN");
                    break;
                case "2":
                    System.out.println("Option 2 : "); // emploi du temps personnalisé
                    
                    break;
                case "3":
                    System.out.println("Option 3 : créer un nouveau cours"); 
                    Utils.creerNvCours(repertoireCoursDispo);
                    System.out.println("de retour dans menu MAIN");

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
    }
}


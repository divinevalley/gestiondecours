package ift1025Tp1;

import java.util.ArrayList;

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
        ArrayList<String> horaire = new ArrayList<String>(); // stocker l'horaire créé 

        System.out.println("Menu interactif :");
        System.out.println("1. Gestion le repertoire du cours (ajouter, modifier, etc)");
        System.out.println("2. Creer un emploi du temps personnalisé");
        System.out.println("3. Afficher l'horaire (emploi du temps) créé");
        
        System.out.println("0. Quitter");
        
        boolean continuer = true;

        while (continuer) {


            System.out.print("Choisissez une option (ou faites le 0 pour quitter) : ");
            String option = scanner.nextLine();
//            scanner.nextLine(); // on vide la ligne de l'entier

            switch (option) {
                case "1":
                    // Gestion de cours : supprimer, modifier, etc.
                    System.out.println("Option 1 : Gestion de repertoire de cours");
                    // code ici
                    
                    break;
                case "2":
                    System.out.println("Option 2 : ");
                    
                    break;
                case "3":
                    System.out.println("Option 3 : Afficher l'horaire (emploi du temps) créé");//?? set les diffenrents messages 
                    System.out.println("Horaire : " + horaire);

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


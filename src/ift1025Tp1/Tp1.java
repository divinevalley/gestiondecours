package ift1025Tp1;


import java.util.Scanner;
import java.util.ArrayList;

public class Tp1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> horaire = new ArrayList<String>(); // stocker l'horaire créé 

        boolean continuer = true;

        while (continuer) {
            System.out.println("Menu interactif :");
            System.out.println("1. Gestion de cours");
            System.out.println("2. Afficher l'horaire créé");
            System.out.println("3. Afficher les messages de conflits"); // ???
            System.out.println("4. Quitter");

            System.out.print("Choisissez une option : ");
            int option = scanner.nextInt();
            scanner.nextLine(); // on vide la ligne de l'entier

            switch (option) {
                case 1:
                    // Gestion de cours : supprimer, modifier, etc.
                    System.out.println("Option : Gestion de cours");
                    // code ici
                    break;
                case 2:
                    // Afficher l'horaire créé
                    System.out.println("Option : Afficher l'horaire créé");
                    System.out.println("Horaire : " + horaire);
                    break;
                case 3:
                    // Afficher messages de conflits
                    System.out.println("Option : Afficher les messages de conflits");//?? set les diffenrents messages 
                    // code ici
                    break;
                case 4:
                    // Quitter
                    System.out.println("Option : Quitter");
                    continuer = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
            }

            System.out.println(); 
        }

        System.out.println("Fin de la modification");
    }
}


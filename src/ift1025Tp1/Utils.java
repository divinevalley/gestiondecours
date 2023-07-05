package ift1025Tp1;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    private static ArrayList<String> listeCoursDispo = new ArrayList<String>();
   
    public static void ajouterCours(String Cours) {
        listeCoursDispo.add(Cours);
    }

    public static void modifierCours(String coursAncien, String coursNouveau) {
        int index = listeCoursDispo.indexOf(coursAncien);
        if (index != -1) {
            listeCoursDispo.set(index, coursNouveau);
            System.out.println("Le cours a été modifié avec succès.");
        } else {
            System.out.println("Le cours spécifié n'existe pas dans la liste.");
        }
    }

    public static void supprimerCours(String Cours) {
        boolean supprime = listeCoursDispo.remove(Cours);
        if (supprime) {
            System.out.println("Le cours a été supprimé avec succès.");
        } else {
            System.out.println("Le cours spécifié n'existe pas dans la liste.");
        }
    }

    public static void creerNvCours() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez le nom du cours : ");
        String nomCours = scanner.nextLine();

        // Demander d'autres informations sur le cours (horaires, etc.)

        // Ajouter le cours à la liste
        listeCoursDispo.add(nomCours);

        System.out.println("Le cours a été créé avec succès.");
    }


public Utils() {
    // TODO Auto-generated constructor stub
}

}

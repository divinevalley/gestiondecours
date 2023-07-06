package ift1025Tp1;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    private static ArrayList<String> listeCoursDispo = new ArrayList<String>();
    // ^ XXX : ah je voyais ça plutôt comme un truc dans le pgrm MAIN général, parce qu'on a pas 
    // besoin d'instancier plusieurs fois un objet ... genre une seule liste de tout le repertoire, 
    // donc pas besoin d'instancier quoi que ce soit tu vois. A mon avis. je vais déplacer ça dans main. 
    // Autre remarque : d'ailleurs si tu veux que ce soit alphabétisé, on pourrait mettre ça dans un TreeSet 
    // (les Trees sont triés hihi :D ) 
   
    public static void ajouterCours(String Cours) {
        listeCoursDispo.add(Cours);
    }
    // XXX: Ca marche bien dans la structure que tu as créée, c'est bien. 
    // Par contre au lieu de prendre un String cours, ça devrait être plutôt juste un objet Cours cours. 
    // càd : ajouterCours(Cours cours)

    public static void modifierCours(String coursAncien, String coursNouveau) {
        int index = listeCoursDispo.indexOf(coursAncien);
        if (index != -1) {
            listeCoursDispo.set(index, coursNouveau);
            System.out.println("Le cours a été modifié avec succès.");
        } else {
            System.out.println("Le cours spécifié n'existe pas dans la liste.");
        }
        // XXX : je vois la logique ici ! mais faudrait mobiliser l'Objet cours
        // je me demande ce qu'on devrait proposer comme modification dans le cours, peut être les horaires ?
        // dans ce cas, on peut appeler l'objet Cours pour pouvoir accéder à ses horaires, etc. 
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
        listeCoursDispo.add(nomCours);  // XXX : <-- pour la cohérence de ce que tu as fait, je suggererait lappel à la fonction ajouterCours() 
        // (si jamais il y a du code à l'intérieur de cette fonction par ex, pour vérifier que le cours est valide, etc, 
        // ce serait bien de tout garder en une fonction multi usage ;) )

        System.out.println("Le cours a été créé avec succès.");
    }


public Utils() {
    // TODO Auto-generated constructor stub
}

// XXX : ^pas besoin d'instancier, on va juste utiliser Utils pour "ranger" nos fonctions pour que ce soit plus joli 
// dans la partie main

}

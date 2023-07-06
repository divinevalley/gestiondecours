package ift1025Tp1;
/*
 * attributs : String matière, int numéro

(eg. matière: IFT, numéro: 1025) 
Q : numéros peut commencer par 0 ? (si oui, faudrait utiliser String) 
faudrait être en ordre alphabétique dans une liste String 

 * */
public class Sigle {
	//attributs
	private String matiere;
	private int numero;
	
	public Sigle(String matiere, int numero) {
		this.matiere = matiere;
		this.numero = numero;
	}

	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	} 
	
	

}

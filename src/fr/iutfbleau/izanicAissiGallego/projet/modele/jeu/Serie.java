package fr.iutfbleau.izanicAissiGallego.projet.modele.jeu;

import java.io.*;

/**
 * La classe Serie représente une série de tuiles.
 * Chaque instance de Serie contient un tableau de tuiles et un numéro de série.
 */
public class Serie implements Serializable {

	/** Tableau de tuiles dans la série. */
    private Tuile[] serie;
	
    /** Numéro de la série. */
    private String numSerie;

	 /**
     * Constructeur de la classe Serie
     * Initialise une nouvelle série avec un numéro de série donné et crée un tableau
     * de 50 tuiles.
     *
     * @param numSerie Le numéro de la série.
     */
	public Serie(String numSerie) {
		this.numSerie = numSerie;

		Tuile[] serie_tmp = new Tuile[50];

		for (int i = 0; i < 50; i++) {
			serie_tmp[i] = new Tuile();
		}
		this.serie = serie_tmp;

	}

	 /**
     * Retourne le numéro de la série.
     *
     * @return Le numéro de la série.
     */
	public String getNumSerie() {
		return this.numSerie;
	}

	/**
     * Retourne le tableau des tuiles dans la série.
     *
     * @return Un tableau de tuiles.
     */
	public Tuile[] getSerie() {
		return this.serie;
	}

	@Override
	public String toString() {
		String s = "";
		for (Tuile t : serie) {
			s = s + " " + t + "\n";
		}
		return s;
	}
}
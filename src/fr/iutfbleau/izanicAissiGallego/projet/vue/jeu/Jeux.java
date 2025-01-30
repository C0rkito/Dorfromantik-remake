package fr.iutfbleau.izanicAissiGallego.projet.vue.jeu;

import fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre.MaFenetreJeux;
import fr.iutfbleau.izanicAissiGallego.projet.modele.jeu.*;
import fr.iutfbleau.izanicAissiGallego.projet.modele.db.BaseDonnee;
import fr.iutfbleau.izanicAissiGallego.projet.controleur.PaysageListener;
import javax.swing.*;
import java.awt.*;

/**
 * La classe Jeux permet l'affichage de l'interface de jeu.
 * Elle gère la création de la fenêtre de jeu et l'affichage du paysage.
 */
public class Jeux {

	/**
	 * Constructeur de Jeux
	 */
	public Jeux(){}
	/**
	 * Affiche le jeu
	 * 
	 * @param numSerie Le numéro de série de la série à afficher.
	 */
	public static void afficher(String numSerie) {
		MaFenetreJeux fenetre_jeu = new MaFenetreJeux();
		fenetre_jeu.setLayout(new BorderLayout());


		Serie serie = BaseDonnee.getSerie(numSerie);


		Paysage paysage = new Paysage(serie);
		PaysageVue vue = new PaysageVue(paysage);
		PaysageListener obs = new PaysageListener(vue, fenetre_jeu);

		
		vue.addMouseListener(obs);
		vue.addMouseMotionListener(obs);
		vue.addMouseWheelListener(obs);
		vue.addKeyListener(obs);

		fenetre_jeu.add(vue, BorderLayout.CENTER);

	}

}
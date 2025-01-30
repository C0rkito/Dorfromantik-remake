package fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre;


import javax.swing.*;	
import java.awt.*;

/**
 * La classe MaFenetreJeux représente la fenêtre principale du jeu.
 */
public class MaFenetreJeux extends MaFenetre{
    
	/**
     * Constructeur de la classe MaFenetreJeux.
     * Configure la fenêtre du jeu avec un titre et une taille maximale.
     */
    public MaFenetreJeux() {
        this.setTitle("Jeu");
        this.setResizable(true);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int largeur = gd.getDisplayMode().getWidth();
        int hauteur = gd.getDisplayMode().getHeight();
        this.setSize(largeur, hauteur); 

        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setVisible(true); 
    }
}

	
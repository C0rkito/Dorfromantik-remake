package fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre;

import fr.iutfbleau.izanicAissiGallego.projet.vue.menu.MyWindowListener;
import javax.swing.*;
import java.awt.*;

/**
 * La classe MaFenetre représente la fenêtre du menu.
 * Elle configure le titre, la taille, et les événements de fermeture.
 */
public class MaFenetre extends JFrame {

    /**
     * Constructeur de la classe MaFenetre.
     */
    public MaFenetre() {
        this.setTitle("Menu"); 
        
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        
   
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int largeur = gd.getDisplayMode().getWidth();
        int hauteur = gd.getDisplayMode().getHeight();
        this.setLocation((largeur - 575) / 2, (hauteur - 595) / 2); 
        this.setSize(575, 595); 
        
       
        MyWindowListener winobs = new MyWindowListener(this);
        this.addWindowListener(winobs);
        this.addKeyListener(winobs);
        
        this.setVisible(true); 
    }
}
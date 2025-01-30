package fr.iutfbleau.izanicAissiGallego.projet.vue.menu;

import fr.iutfbleau.izanicAissiGallego.projet.vue.jeu.Jeux;
import fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre.*;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * La classe MenuListener gére les événements d'action du menu principal
 * Elle permet de naviguer entre différentes vues
 */
public class MenuListener implements ActionListener{
	
	/** La fenêtre principale de l'application. */
    private MaFenetre fenetre;
	
    /** Le CardLayout utilisé pour changer de vue. */
    private CardLayout card;
	
    /**
     * Constructeur de la classe MenuListener.
     *
     * @param fenetre La fenêtre principale.
     * @param card    CardLayout utilisé pour changer de vue.
     */
	public MenuListener(MaFenetre fenetre,CardLayout card){
		this.fenetre = fenetre;
		this.card = card;
	}
	
    /**
     * Détermine l'action à effectuer en fonction de l'événement.
     * @param evenement de classe ActionEvent
     */
	@Override 
	public void actionPerformed(ActionEvent evenement){
		if(evenement.getActionCommand().equals("Quitter")){
			fenetre.dispose();
		}
		if(evenement.getActionCommand().equals("Scores")){
			card.show(fenetre.getContentPane(),"Scores");
		}
		
		if(evenement.getActionCommand().equals("Jouer")){
			card.show(fenetre.getContentPane(),"Jouer");
		}
		if(evenement.getActionCommand().equals("Retour")){
			card.show(fenetre.getContentPane(),"Menu");

		}  
	}

		
}
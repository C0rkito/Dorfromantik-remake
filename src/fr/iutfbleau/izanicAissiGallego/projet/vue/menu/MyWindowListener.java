package fr.iutfbleau.izanicAissiGallego.projet.vue.menu;

import fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * La classe MyWindowListener gére les événements liés à la fenêtre
 * Elle permet de gérer la fermeture de la fenêtre et d'afficher un dialogue de confirmation 
 * lorsque l'utilisateur tente de quitter l'application.
 */
public class MyWindowListener implements WindowListener, KeyListener {

	/** La fenêtre principale de l'application. */
    private MaFenetre fenetre;

	/**
     * Constructeur de la classe MyWindowListener
     *
     * @param fenetre La fenêtre principale de l'application.
     */
	public MyWindowListener(MaFenetre fenetre) {
		this.fenetre = fenetre;
	}

	/**
     * Affiche un dialogue de confirmation pour quitter l'application.
     * @param evenement evenement WindowEvent
     */ 
	@Override
	public void windowClosing(WindowEvent evenement) {
		dialogQuitter();
	}

	@Override
	public void keyPressed(KeyEvent evenement) {
	}

	private void dialogQuitter() {
		JDialog dialog = new JDialog(fenetre, "Quitter le jeu", true);
		int menu = JOptionPane.showConfirmDialog(dialog, "Quitter le jeu ?", "Quitter le jeu ?",
				JOptionPane.YES_NO_OPTION);
		if (menu == JOptionPane.YES_OPTION) {
			fenetre.dispose();
		}
	}


	// Méthodes obligatoires.
	@Override
	public void windowOpened(WindowEvent evenement) {
	}

	@Override
	public void windowActivated(WindowEvent evenement) {
	}

	@Override
	public void windowClosed(WindowEvent evenement) {
	}

	@Override
	public void windowDeactivated(WindowEvent evenement) {
	}

	@Override
	public void windowDeiconified(WindowEvent evenement) {
	}

	@Override
	public void windowIconified(WindowEvent evenement) {
	}

	@Override
	public void keyReleased(KeyEvent evenement) {
	}

	@Override
	public void keyTyped(KeyEvent evenement) {
	}

}
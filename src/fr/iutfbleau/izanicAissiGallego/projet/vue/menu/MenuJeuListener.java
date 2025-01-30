package fr.iutfbleau.izanicAissiGallego.projet.vue.menu;

import fr.iutfbleau.izanicAissiGallego.projet.vue.jeu.Jeux;
import fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

/**
 * La classe MenuJeuListener gére les événements de la fenetre pour lancer le
 * jeu.
 */
public class MenuJeuListener implements ActionListener {

	/** Le menu déroulant pour sélectionner une série à lancer. */
    private JComboBox<String> dropdown;

	/**
     * Constructeur de la classe MenuJeuListener.
     * @param dropdown pour selectionner une serie à lancer.
     */
	public MenuJeuListener(JComboBox dropdown) {
		this.dropdown = dropdown;
	}

	/**
     * lance le jeu en fonction de la serie selectionnée.
     * @param evenement de classe ActionEvent
     */
	@Override
	public void actionPerformed(ActionEvent evenement) {
		if (evenement.getActionCommand().equals("Lancer")) {
			Jeux.afficher((String) dropdown.getSelectedItem());
		}
	}

}
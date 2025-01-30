package fr.iutfbleau.izanicAissiGallego.projet.vue.menu;

import fr.iutfbleau.izanicAissiGallego.projet.vue.jeu.Jeux;
import fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * La classe TableauScoreListener gére les événements du TableauScores.
 */
public class TableauScoreListener implements ActionListener {

    /** Le panneau qui contient les classements à afficher. */
    private JPanel pan;

    /** Le menu déroulant contenant les séries. */
    private JComboBox<String> dropdown;

    /** Le CardLayout utilisé pour changer les vues de classement. */
    private CardLayout cardScore;

    /** Le numéro de la série actuellement sélectionnée. */
    private String numSerie = "1";
	

	 /**
     * Constructeur de la classe TableauScoreListener.
     *
     * @param dropdown  Le menu déroulant contenant les séries.
     * @param cardScore Le CardLayout utilisé pour changer les vues de classement.
     * @param pan      Le panneau qui contient les classements à afficher.
     */
	public TableauScoreListener(JComboBox dropdown, CardLayout cardScore, JPanel pan) {
		this.dropdown = dropdown;
		this.cardScore = cardScore;
		this.pan = pan;
	}

	/**
     * Evénement de sélection d'une série dans le menu déroulant.
     * @param evenement ActionEvent
     */
	@Override
	public void actionPerformed(ActionEvent evenement) {
		numSerie = (String) dropdown.getSelectedItem();
		cardScore.show(pan, numSerie);
	}

	/**
	 * /**
     * Retourne le numéro de la série.
     *
     * @return String

	 */
	public String getSerie() {
		return numSerie;
	}

}
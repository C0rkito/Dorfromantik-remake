package fr.iutfbleau.izanicAissiGallego.projet.vue.menu;

import fr.iutfbleau.izanicAissiGallego.projet.modele.db.BaseDonnee;
import fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre.MaFenetre;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * La classe TableauScores permet l'affichage du classement des joueursdans
 * l'interface utilisateur.
 * Elle permet de sélectionner une série et d'afficher les scores associés à
 * cette série.
 */
public class TableauxScores {

	/** Le CardLayout utilisé pour la navigation entre les différentes vues. */
    private CardLayout card;
    /** La fenêtre principale de l'application. */
    private MaFenetre fenetre;

	/**
	 * Constructeur de la classe {@code TableauxScores}.
	 *
	 * @param fenetre La fenêtre principale de l'application.
	 * @param card    Le CardLayout utilisé pour la navigation entre les différentes
	 *                vues.
	 */
	public TableauxScores(MaFenetre fenetre, CardLayout card) {
		this.fenetre = fenetre;
		this.card = card;
	}

	/**
	 * permet d'afficher le classements des joueurs
	 * 
	 * @return JPanel qui contient le classement des joueurs
	 */
	public JPanel afficher() {

		JPanel panneau = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		panneau.setLayout(gbl);

		ArrayList<String> seriesList = BaseDonnee.getAllSeriesNum();
		JComboBox<String> dropdown = new JComboBox<>(seriesList.toArray(new String[0]));

		JButton menu = new JButton("Retour");

		JPanel choisirSerie = new JPanel();
		JPanel listeScore = new JPanel();
		listeScore.setBackground(Color.blue);
		CardLayout cardScore = new CardLayout();
		listeScore.setLayout(cardScore);

	
		for (String serie : seriesList) {
			JPanel classement = new JPanel(new BorderLayout());
			JLabel numSerie = new JLabel("Serie " + serie);
			numSerie.setForeground(Color.WHITE);
			numSerie.setFont(new Font("Arial", Font.BOLD, 24));
			classement.add(numSerie, BorderLayout.NORTH);

			ArrayList<String> scoreSerie = BaseDonnee.getScore(serie);
			int numLabels = scoreSerie.size();
			int numColumns = 2;
			int numRows = (int) Math.ceil((double) numLabels / numColumns);

			JPanel scoreSeriePanel = new JPanel(new GridLayout(numRows, numColumns, 0, 0));
			JLabel[] labels = new JLabel[scoreSerie.size()];
			for (int i = 0; i < labels.length; i++) {
				labels[i] = new JLabel(i + 1 + ": " + scoreSerie.get(i) + "points", SwingConstants.CENTER);
				labels[i].setFont(new Font("Arial", Font.BOLD, 20 + ((-numLabels / 2)) % 10));
			}
			for (int row = 0; row < numRows; row++) {
				for (int col = 0; col < numColumns; col++) {
					int labelIndex = col * numRows + row;
					if (labelIndex < labels.length) {
						scoreSeriePanel.add(labels[labelIndex]);
					} else {
						scoreSeriePanel.add(new JLabel(""));
					}
				}
			}
			JScrollPane scrollPane = new JScrollPane(scoreSeriePanel);

			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			classement.setBackground(Color.DARK_GRAY);
			classement.add(scrollPane, BorderLayout.CENTER);
			listeScore.add(classement, "" + serie);
		}


		TableauScoreListener tsl = new TableauScoreListener(dropdown, cardScore, listeScore);
		dropdown.addActionListener(tsl);

		MenuListener MenuObs = new MenuListener(fenetre, card);
		menu.addActionListener(MenuObs);


		choisirSerie.setLayout(new BorderLayout());
		choisirSerie.add(new JLabel("Serie : "), BorderLayout.NORTH);
		choisirSerie.add(dropdown, BorderLayout.CENTER);

	
		GridBagConstraints retourConstraint = new GridBagConstraints();
		retourConstraint.gridx = 0;
		retourConstraint.gridy = 0;
		retourConstraint.gridwidth = 1;
		retourConstraint.gridheight = 0;
		retourConstraint.fill = GridBagConstraints.NONE;
		retourConstraint.anchor = GridBagConstraints.NORTHWEST;
		retourConstraint.weightx = 0;
		retourConstraint.weighty = 0;
		retourConstraint.insets = new Insets(5, 5, 5, 5);
		panneau.add(menu, retourConstraint);

		GridBagConstraints dropdownConstraint = new GridBagConstraints();
		dropdownConstraint.gridx = 0;
		dropdownConstraint.gridy = 1;
		dropdownConstraint.gridwidth = 1;
		dropdownConstraint.gridheight = 0;
		dropdownConstraint.fill = GridBagConstraints.NONE;
		dropdownConstraint.anchor = GridBagConstraints.CENTER;
		dropdownConstraint.weightx = 0;
		dropdownConstraint.weighty = 0;
		dropdownConstraint.insets = new Insets(5, 5, 5, 5);
		panneau.add(choisirSerie, dropdownConstraint);

		GridBagConstraints listeConstraint = new GridBagConstraints();
		listeConstraint.gridx = 2;
		listeConstraint.gridy = 0;
		listeConstraint.gridwidth = 1;
		listeConstraint.gridheight = 3;
		listeConstraint.fill = GridBagConstraints.BOTH;
		listeConstraint.anchor = GridBagConstraints.CENTER;
		listeConstraint.weightx = 1;
		listeConstraint.weighty = 1;
		listeConstraint.insets = new Insets(10, 10, 10, 10);
		panneau.add(listeScore, listeConstraint);

		return panneau;
	}

}
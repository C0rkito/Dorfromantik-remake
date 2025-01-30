package fr.iutfbleau.izanicAissiGallego.projet.vue.menu;

import fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre.MaFenetre;
import fr.iutfbleau.izanicAissiGallego.projet.modele.db.BaseDonnee;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * La classe MenuJeu affiche le menu du jeu.
 */
public class MenuJeu {

    /** CardLayout utilisé pour changer de vue dans la fenêtre principale. */
    private CardLayout card;

    /** La fenêtre principale de l'application. */
    private MaFenetre fenetre;

	/**
	 * Constructeur de la classe MenuJeu.
	 *
	 * @param fenetre La fenêtre principale.
	 * @param card    CardLayout utilisé pour changer de vue.
	 */
	public MenuJeu(MaFenetre fenetre, CardLayout card) {
		this.fenetre = fenetre;
		this.card = card;
	}

	  /**
     * Elle crée un JPanel contenant les boutons "Lancer" et "Retour",
     * ainsi qu'un JComboBox pour sélectionner une série.
     *
     * @return JPanel 
     */
	public JLayeredPane afficher() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false); 
		buttonPanel.setLayout(new GridBagLayout());
		
		
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(500, 500));
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL imageUrl = loader.getResource("images/astuces.png");
		ImageIcon originalIcon = new ImageIcon(imageUrl);
		Image scaledImage = originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
		backgroundImage.setBounds(0, 0, 500, 500);

		JButton menu = new JButton("Retour");	
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
		buttonPanel.add(menu, retourConstraint);

	
		GridBagConstraints lancerConstraint = new GridBagConstraints();
		JButton lancer = new JButton("Lancer");
		lancerConstraint.gridx = 1;
		lancerConstraint.gridy = 0;
		lancerConstraint.gridwidth = 1;
		lancerConstraint.gridheight = 1;
		lancerConstraint.fill = GridBagConstraints.BOTH;
		lancerConstraint.anchor = GridBagConstraints.CENTER;
		lancerConstraint.weightx = 0;
		lancerConstraint.weighty = 0;
		lancerConstraint.insets = new Insets(5, 5, 5, 5);
		buttonPanel.add(lancer, lancerConstraint);

	
		GridBagConstraints dropdownConstraint = new GridBagConstraints();
		ArrayList<String> seriesList = BaseDonnee.getAllSeriesNum();
		JComboBox<String> dropdown = new JComboBox<>(seriesList.toArray(new String[0]));
		dropdownConstraint.gridx = 2;
		dropdownConstraint.gridy = 0;
		dropdownConstraint.gridwidth = 1;
		dropdownConstraint.gridheight = 1;
		dropdownConstraint.fill = GridBagConstraints.BOTH;
		dropdownConstraint.anchor = GridBagConstraints.CENTER;
		dropdownConstraint.weightx = 0;
		dropdownConstraint.weighty = 0;
		dropdownConstraint.insets = new Insets(5, 5, 5, 5);
		buttonPanel.add(dropdown, dropdownConstraint);

	
		MenuListener MenuObs = new MenuListener(fenetre, card);
		MenuJeuListener MenuJeuObs = new MenuJeuListener(dropdown);
		menu.addActionListener(MenuObs);
		lancer.addActionListener(MenuJeuObs);

		buttonPanel.setBounds(0, 200, 500, 500);
		layeredPane.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);
		return layeredPane;
	}
}
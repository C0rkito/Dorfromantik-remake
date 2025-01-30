package fr.iutfbleau.izanicAissiGallego.projet.vue.menu;

import fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre.MaFenetre;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

/**
 * La classe <code>MenuPrincipale</code> est utilisée pour afficher le menu
 * principal du jeu.
 */
public class MenuPrincipale {


	 /**
     * Constructeur de MenuPrincipale
     */
	public MenuPrincipale(){
	}
    /**
     * Affiche le menu principal du jeu.
     * Cette méthode initialise la fenêtre, le layout, et les composants visuels
     * tels que les boutons et l'image de fond.
     */
    public void afficher() {
        MaFenetre fenetre = new MaFenetre();
        CardLayout card = new CardLayout();
        fenetre.setLayout(card);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL imageUrl = loader.getResource("images/Menu_Principal.png");
        ImageIcon originalIcon = new ImageIcon(imageUrl);
        Image scaledImage = originalIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        JLabel backgroundImage = new JLabel(new ImageIcon(scaledImage));
         backgroundImage.setBounds(0, 0, 500, 500);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbcjeu = new GridBagConstraints();
        JButton btnjeu = new JButton("Jouer");
        gbcjeu.gridx = 0;
        gbcjeu.gridy = 0;
        gbcjeu.gridwidth = 0;
        gbcjeu.gridheight = 1;
        gbcjeu.fill = GridBagConstraints.NONE;
        gbcjeu.anchor = GridBagConstraints.CENTER;
        gbcjeu.weightx = 0;
        gbcjeu.weighty = 0;
        gbcjeu.insets = new Insets(5, 5, 5, 5);
        buttonPanel.add(btnjeu, gbcjeu);

        GridBagConstraints gbcscore = new GridBagConstraints();
        JButton btntab = new JButton("Scores");
        gbcscore.gridx = 0;
        gbcscore.gridy = 1;
        gbcscore.gridwidth = 0;
        gbcscore.gridheight = 1;
        gbcscore.fill = GridBagConstraints.NONE;
        gbcscore.anchor = GridBagConstraints.CENTER;
        gbcscore.weightx = 0;
        gbcscore.weighty = 0;
        gbcscore.insets = new Insets(5, 5, 5, 5);
        buttonPanel.add(btntab, gbcscore);

        GridBagConstraints gbcquitter = new GridBagConstraints();
        JButton btnquitter = new JButton("Quitter");
        gbcquitter.gridx = 0;
        gbcquitter.gridy = 2;
        gbcquitter.gridwidth = 0;
        gbcquitter.gridheight = 1;
        gbcquitter.fill = GridBagConstraints.NONE;
        gbcquitter.anchor = GridBagConstraints.CENTER;
        gbcquitter.weightx = 0;
        gbcquitter.weighty = 0;
        gbcquitter.insets = new Insets(5, 5, 5, 5);
        buttonPanel.add(btnquitter, gbcquitter);

        layeredPane.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);

        buttonPanel.setBounds(2, 150, 200, 200);

        fenetre.add(layeredPane, "Menu");

        MenuListener menuObs = new MenuListener(fenetre, card);
        btntab.addActionListener(menuObs);
        btnjeu.addActionListener(menuObs);
        btnquitter.addActionListener(menuObs);

        TableauxScores tab = new TableauxScores(fenetre, card);
        fenetre.add(tab.afficher(), "Scores");

        MenuJeu menujouer = new MenuJeu(fenetre, card);
        fenetre.add(menujouer.afficher(), "Jouer");
    }
}

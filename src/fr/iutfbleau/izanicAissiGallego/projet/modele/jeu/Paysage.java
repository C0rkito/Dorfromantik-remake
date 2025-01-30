package fr.iutfbleau.izanicAissiGallego.projet.modele.jeu;

import fr.iutfbleau.izanicAissiGallego.projet.vue.jeu.TuileVue;


import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

/**
 * La classe <code>Paysage</code> représente le paysage du jeu, gérant les tuiles,
 * le score du joueur et les interactions avec les tuiles.
 */
public class Paysage {

    /** Tableau 2D représentant le paysage avec des tuiles. */
    private TuileVue[][] paysage; 

    /** Matrice pour suivre les tuiles visitées lors du calcul du score. */
    private boolean[][] visited;

    /** La série de tuiles à utiliser dans le paysage. */
    private Serie serie;

    /** Nombre de tuiles restantes à placer. */
    private int tuileRestante = 52;

    /** La tuile actuellement sélectionnée pour placement. */
    private Tuile tuileCourante; 

    /** Score actuel du joueur. */
    private int score = 0; 

    /**
     * Constructeur de la classe <code>Paysage</code>.
     * Initialise un nouveau paysage avec une série de tuiles spécifiée.
     *
     * @param serie La série de tuiles à utiliser dans le paysage.
     */
    public Paysage(Serie serie) {
        this.serie = serie;
        visited = new boolean[101][101];
        TuileVue[][] p2 = new TuileVue[101][101];

        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                p2[i][j] = null; 
            }
        }

        this.paysage = p2; 
        this.tuileCourante = this.serie.getSerie()[0];
    }

    /**
     * Retourne le nombre de tuiles placées dans le paysage.
     *
     * @return Le nombre de tuiles placées.
     */
    public int getNbTuilePlacer() {
        return (51 - this.tuileRestante) + 1;
    }

    /**
     * Retourne le tableau représentant le paysage.
     *
     * @return Le tableau de tuiles du paysage.
     */
    public TuileVue[][] getPaysage() {
        return this.paysage; 
    }

    /**
     * Calcule et met à jour le score du joueur.
     * Incrémente le score en fonction de la taille des poches de terrain.
     */
    public void calculerScore() {
        this.score = 0;

        // Réinitialiser la matrice visited avant le calcul du score
        for (int i = 0; i < paysage.length; i++) {
            Arrays.fill(visited[i], false);
        }

        // Parcourir toutes les tuiles du plateau
        for (int i = 0; i < paysage.length; i++) {
            for (int j = 0; j < paysage[i].length; j++) {
                // Ignorer les tuiles déjà visitées
                if (paysage[i][j] != null && !visited[i][j]) {
                    if (paysage[i][j].getTuile() != null) {
                        String[] cotes = paysage[i][j].getCotes();
                        if (cotes != null) {
                            for (String terrain : cotes) {
                                int taillePoche = calculerTaillePoche(i, j, terrain);
                                this.score += taillePoche * taillePoche;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Calcule la taille de la poche de terrain à partir d'une tuile spécifiée.
     *
     * @param x       La position x de la tuile dans le paysage.
     * @param y       La position y de la tuile dans le paysage.
     * @param terrain Le type de terrain à rechercher dans la poche.
     * @return La taille de la poche de terrain.
     */
    private int calculerTaillePoche(int x, int y, String terrain) {
        if (x < 0 || y < 0 || x >= paysage.length || y >= paysage[0].length || visited[x][y] || paysage[x][y] == null) {
            return 0;
        }

        String[] cotes = paysage[x][y].getCotes();
        if (cotes == null) {
            return 0;
        }

        Set<String> terrains = new HashSet<>(Arrays.asList(cotes));
        if (!terrains.contains(terrain)) {
            return 0;
        }

        visited[x][y] = true;
        int taille = 1;

        int[][] offsets;
        if (y % 2 == 0) { // Colonne paire
            offsets = new int[][] {
                {-1, 0},    // Haut
                {1, 0},     // Bas
                {0, -1},    // Gauche
                {0, 1},     // Droite
                {-1, -1},   // Haut-gauche
                {-1, 1}     // Haut-droite
            };
        } else { // Colonne impaire
            offsets = new int[][] {
                {-1, 0},    // Haut
                {1, 0},     // Bas
                {0, -1},    // Gauche
                {0, 1},     // Droite
                {1, -1},    // Bas-gauche
                {1, 1}      // Bas-droite
            };
        }

        for (int[] offset : offsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];

            if (newX >= 0 && newY >= 0 && newX < paysage.length && newY < paysage[0].length) {
                if (paysage[newX][newY] != null && !visited[newX][newY]) {
                    taille += calculerTaillePoche(newX, newY, terrain);
                }
            }
        }

        return taille;
    }
	
    /**
     * Retourne le score actuel du joueur.
     *
     * @return Le score du joueur.
     */
    public int getScore() {
        return this.score; 
    }

    /**
     * Définit la tuile courante à la tuile spécifiée.
     *
     * @param t La tuile à définir comme tuile courante.
     */
    public void setTuileCourante(Tuile t) {
        this.tuileCourante = t; 
    }

    /**
     * Retourne la tuile courante.
     *
     * @return La tuile actuellement sélectionnée.
     */
    public Tuile getTuileCourante() {
        return this.tuileCourante; 
    }

    /**
     * Retourne la tuile suivante à placer dans le paysage.
     *
     * @return La tuile suivante à placer.
     */
    public Tuile getTuileSuivante() {
        this.tuileRestante -= 1;
        return this.serie.getSerie()[51 - tuileRestante]; 
    }

    /**
     * Retourne le numéro de la série de tuiles.
     *
     * @return Le numéro de la série.
     */
    public String getNumSerie() {
        return serie.getNumSerie(); 
    }
}

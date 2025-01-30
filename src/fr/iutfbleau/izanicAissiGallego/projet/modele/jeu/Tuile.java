package fr.iutfbleau.izanicAissiGallego.projet.modele.jeu;

import java.io.Serializable;
import java.util.Random;

/**
 * La classe <code>Tuile</code> représente une tuile dans le jeu, contenant des
 * informations sur les terrains qui la composent et leur disposition.
 */
public class Tuile implements Serializable {

    /** Le nombre de terrains sur la tuile. */
    private int nombreTerrains; 

    /** Tableau contenant les types de terrains. */
    private String[] terrains; 

    /** La division des terrains sur la tuile. */
    private String division;

    /** Type de terrain sur le côté 1 de la tuile. */
    private String cote_1; 

    /** Type de terrain sur le côté 2 de la tuile. */
    private String cote_2; 

    /** Type de terrain sur le côté 3 de la tuile. */
    private String cote_3; 

    /** Type de terrain sur le côté 4 de la tuile. */
    private String cote_4; 

    /** Type de terrain sur le côté 5 de la tuile. */
    private String cote_5; 

    /** Type de terrain sur le côté 6 de la tuile. */
    private String cote_6; 

    /**
     * Constructeur de la classe <code>Tuile</code>.
     * Initialise une nouvelle tuile avec des terrains aléatoires.
     */
    public Tuile() {
        this.terrains = new String[] { "mer", "champ", "pre", "foret", "montagne" };

        Random rand = new Random();

        if (rand.nextInt(3) == 0) {
            this.nombreTerrains = 1;
            int terrain = rand.nextInt(5);
            this.cote_1 = terrains[terrain];
            this.cote_2 = terrains[terrain];
            this.cote_3 = terrains[terrain];
            this.cote_4 = terrains[terrain];
            this.cote_5 = terrains[terrain];
            this.cote_6 = terrains[terrain];
        } else {
            String[] division_lst = new String[] { "1+5", "2+4", "3+3" };
            this.division = division_lst[rand.nextInt(3)];

            if (this.division.equals("1+5")) {
                String terrain_1 = this.terrains[rand.nextInt(5)];
                String terrain_2 = this.terrains[rand.nextInt(5)];
                while ((terrain_2.equals(terrain_1))) {
                    terrain_2 = this.terrains[rand.nextInt(5)];
                }
                this.cote_1 = terrain_1;
                this.cote_2 = terrain_2;
                this.cote_3 = terrain_2;
                this.cote_4 = terrain_2;
                this.cote_5 = terrain_2;
                this.cote_6 = terrain_2;
            }
            if (this.division.equals("2+4")) {
                String terrain_1 = this.terrains[rand.nextInt(5)];
                String terrain_2 = this.terrains[rand.nextInt(5)];
                while ((terrain_2.equals(terrain_1))) {
                    terrain_2 = this.terrains[rand.nextInt(5)];
                }
                this.cote_1 = terrain_1;
                this.cote_2 = terrain_1;
                this.cote_3 = terrain_2;
                this.cote_4 = terrain_2;
                this.cote_5 = terrain_2;
                this.cote_6 = terrain_2;
            }
            if (this.division.equals("3+3")) {
                String terrain_1 = this.terrains[rand.nextInt(5)];
                String terrain_2 = this.terrains[rand.nextInt(5)];
                while ((terrain_2.equals(terrain_1))) {
                    terrain_2 = this.terrains[rand.nextInt(5)];
                }

                this.cote_1 = terrain_1;
                this.cote_2 = terrain_1;
                this.cote_3 = terrain_1;
                this.cote_4 = terrain_2;
                this.cote_5 = terrain_2;
                this.cote_6 = terrain_2;
            }
        }
    }

    /**
     * Retourne les types de terrains sur les côtés de la tuile.
     *
     * @return String[] Un tableau contenant les types de terrains pour chaque côté de la tuile.
     */
    public String[] getCotes() {
        String[] t = { cote_1, cote_2, cote_3, cote_4, cote_5, cote_6 };
        return t;
    }

    /**
     * Tourne la tuile vers la gauche, changeant l'ordre des côtés.
     */
    public void tournerGauche() {
        String tmp1 = this.cote_1;
        String tmp2 = this.cote_2;
        String tmp3 = this.cote_3;
        String tmp4 = this.cote_4;
        String tmp5 = this.cote_5;
        String tmp6 = this.cote_6;

        this.cote_6 = tmp1;
        this.cote_5 = tmp6;
        this.cote_4 = tmp5;
        this.cote_3 = tmp4;
        this.cote_2 = tmp3;
        this.cote_1 = tmp2;
    }

    /**
     * Tourne la tuile vers la droite, changeant l'ordre des côtés.
     */
    public void tournerDroite() {
        String tmp1 = this.cote_1;
        String tmp2 = this.cote_2;
        String tmp3 = this.cote_3;
        String tmp4 = this.cote_4;
        String tmp5 = this.cote_5;
        String tmp6 = this.cote_6;

        this.cote_6 = tmp5;
        this.cote_5 = tmp4;
        this.cote_4 = tmp3;
        this.cote_3 = tmp2;
        this.cote_2 = tmp1;
        this.cote_1 = tmp6;
    }

    /**
     * Retourne une représentation textuelle de la tuile.
     *
     * @return Une chaîne de caractères décrivant la tuile, incluant sa division et ses côtés.
     */
    @Override
    public String toString() {
        return "div:" + this.division + " 1:" + this.cote_1 + " 2:" + this.cote_2 + " 3:" + this.cote_3 + " 4:"
                + this.cote_4 + " 5:" + this.cote_5 + " 6:" + this.cote_6;
    }
}

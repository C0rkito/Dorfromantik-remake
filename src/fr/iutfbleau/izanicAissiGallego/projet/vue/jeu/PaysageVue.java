package fr.iutfbleau.izanicAissiGallego.projet.vue.jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import fr.iutfbleau.izanicAissiGallego.projet.modele.jeu.*;

/**
 * La classe PaysageVue permet l'affichage d'un paysage.
 */
public class PaysageVue extends JPanel {

    /** Le paysage à afficher. */
    private Paysage paysage;

    /** La tuile courante à afficher. */
    private TuileVue tuileCourante;

    /** Le facteur de zoom appliqué à l'affichage. */
    private double zoomFactor = 1.3; 

    /** Décalage en X pour l'affichage du paysage. */
    private int decalerx = -2300; 

    /** Décalage en Y pour l'affichage du paysage. */
    private int decalery = -3500; 

    /**
     * Constructeur de la classe PaysageVue.
     *
     * @param paysage Le paysage à afficher.
     */
    public PaysageVue(Paysage paysage) {
        this.paysage = paysage;
        this.tuileCourante = new TuileVue(paysage.getTuileCourante());
        this.setFocusable(true);
        this.setLayout(null);
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

		paysage.setTuileCourante(paysage.getTuileSuivante());
        paysage.getPaysage()[50][50] = new TuileVue(this, 3000, 3966);
        this.add(paysage.getPaysage()[50][50]);
    }

    /**
     * Méthode pour l'affichage en jeu.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setBounds(0, 0, 8000, 8000); 

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score : " + paysage.getScore(), 200, 50);
        g2d.drawString("Serie: " + paysage.getNumSerie(), 960, 50);


        int centerX = getWidth() / 100;
        int centerY = getHeight() / 100;
        int radius = Math.min(getWidth(), getHeight()) / 100;
        Point[] vertices = new Point[6];
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            vertices[i] = new Point(x, y);
        }


        for (int i = 0; i < 6; i++) {
            switch (paysage.getTuileCourante().getCotes()[i]) {
                case "champ":
                    g2d.setColor(Color.ORANGE);
                    break;
                case "montagne":
                    g2d.setColor(Color.GRAY);
                    break;
                case "foret":
                    g2d.setColor(Color.GREEN);
                    break;
                case "mer":
                    g2d.setColor(Color.CYAN);
                    break;
                case "pre":
                    g2d.setColor(Color.YELLOW);
                    break;
                default:
                    g2d.setColor(Color.WHITE);
                    break;
            }


            Path2D segment = new Path2D.Double();
            segment.moveTo(centerX, centerY);
            segment.lineTo(vertices[i].x, vertices[i].y);
            segment.lineTo(vertices[(i + 1) % 6].x, vertices[(i + 1) % 6].y);
            segment.closePath();
            g2d.fill(segment);
        }

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);


        g2d.scale(zoomFactor, zoomFactor);
        g2d.translate(decalerx, decalery);
    }



    /**
     * Retourne le paysage associé à cette vue.
     *
     * @return Le paysage de la vue.
     */
    public Paysage getP() {
        return this.paysage;
    }

    /**
     * Retourne le décalage en Y pour l'affichage.
     *
     * @return Le décalage en Y.
     */
    public int getDecalerY() {
        return this.decalery;
    }

    /**
     * Retourne le décalage en X pour l'affichage.
     *
     * @return Le décalage en X.
     */
    public int getDecalerX() {
        return this.decalerx;
    }

    /**
     * Applique un décalage en X à la vue.
     *
     * @param ajout La valeur à ajouter au décalage en X.
     */
    public void decalex(int ajout) {
        this.decalerx += ajout;
        this.repaint();
    }

    /**
     * Applique un décalage en Y à la vue.
     *
     * @param ajout La valeur à ajouter au décalage en Y.
     */
    public void decaley(int ajout) {
        this.decalery += ajout;
        this.repaint();
    }

    /**
     * Modifie le facteur de zoom de la vue.
     *
     * @param ajout La valeur à ajouter au facteur de zoom.
     */
    public void setZoom(double ajout) {
        this.zoomFactor += ajout;
        this.repaint();
    }

    /**
     * Retourne le facteur de zoom actuel.
     *
     * @return Le facteur de zoom.
     */
    public double getZoom() {
        return this.zoomFactor;
    }
}

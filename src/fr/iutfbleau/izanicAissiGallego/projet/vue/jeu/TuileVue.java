package fr.iutfbleau.izanicAissiGallego.projet.vue.jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import fr.iutfbleau.izanicAissiGallego.projet.modele.jeu.*;

/**
 * La classe TuileVue permet de représenter une tuile dans le jeu, avec un dessin hexagonal
 * et des sections colorées pour chaque type de terrain.
 */
public class TuileVue extends JPanel {

    /**
     * Les types de terrain associés aux côtés de la tuile (ex. "champ", "montagne").
     */
    private String[] cotes; 

    /**
     * L'objet Tuile associé à cette vue, contenant les données du modèle de la tuile.
     */
    private Tuile laTuile;

    /**
     * La forme hexagonale utilisée pour dessiner la tuile.
     */
    private Polygon hexagone; 

    /**
     * Indicateur pour vérifier si la souris se trouve à l'intérieur de l'hexagone.
     */
    private boolean insideHexagon = false;

    /**
     * Position X de la tuile dans le panneau.
     */
    private int x; 

    /**
     * Position Y de la tuile dans le panneau.
     */
    private int y; 

    /**
     * La forme de l'hexagone dessiné, pour gérer les clics et les survols de souris.
     */
    private Shape shape;

    /**
     * Indicateur pour déterminer si la tuile est en position paire (utile pour son positionnement dans le paysage).
     */
    private boolean pair = false; 

    /**
     * La vue du paysage associée à cette tuile, permettant d'obtenir les informations nécessaires pour l'affichage.
     */
    private PaysageVue pvue; 

    /**
     * Taille standard de la tuile en pixels.
     */
    private static final int size = 80; 

    /**
     * Constructeur de la classe TuileVue.
     * 
     * @param pvue La vue du paysage associée à cette tuile.
     * @param x    La position X de la tuile dans le panneau.
     * @param y    La position Y de la tuile dans le panneau.
     */
    public TuileVue(PaysageVue pvue, int x, int y) {
        this.pvue = pvue;
        this.x = x;
        this.y = y;
        this.setLayout(null);
        this.setOpaque(false); // Rendre le panneau non opaque
        this.setBounds(this.x, this.y, this.size, this.size); // Définir la position et la taille du panneau
    }

    /**
     * Constructeur de la classe TuileVue avec une tuile existante.
     * 
     * @param tuile La tuile associée, contenant les informations de chaque côté.
     */
    public TuileVue(Tuile tuile) {
        this.laTuile = tuile;
        this.setOpaque(false); // Rendre le panneau non opaque
        this.setLayout(null);
        this.cotes = laTuile.getCotes();
        this.setBounds(this.x, this.y, this.size, this.size); // Définir la position et la taille du panneau
    }

    /**
     * Méthode pour peindre le composant en forme d'hexagone avec différentes couleurs
     * pour chaque type de terrain sur les côtés de la tuile.
     *
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        shape = createHexagon(getWidth() / 2, getHeight() / 2, Math.min(getWidth(), getHeight()) / 2);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 2;
        Point[] vertices = new Point[6];
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            vertices[i] = new Point(x, y);
        }

        g2d.setStroke(new BasicStroke(3));

        
        if (insideHexagon) {
            for (int i = 0; i < 6; i++) {
                switch (this.pvue.getP().getTuileCourante().getCotes()[i]) {
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
                }

           
                Path2D segment = new Path2D.Double();
                segment.moveTo(centerX, centerY);
                segment.lineTo(vertices[i].x, vertices[i].y);
                segment.lineTo(vertices[(i + 1) % 6].x, vertices[(i + 1) % 6].y);
                segment.closePath();
                g2d.fill(segment);
            }
            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(Color.RED);
        }


        if (this.laTuile != null) {
            for (int i = 0; i < 6; i++) {
                switch (this.cotes[i]) {
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
            g2d.setColor(Color.BLACK);
        }

        g2d.draw(shape);
    }

    /**
     * Méthode pour créer un polygone hexagonal.
     *
     * @param centerX La position X du centre de l'hexagone.
     * @param centerY La position Y du centre de l'hexagone.
     * @param radius  Le rayon de l'hexagone.
     * @return l'hexagone.
     */
    private Shape createHexagon(int centerX, int centerY, int radius) {
        Path2D path = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.closePath();
        return path;
    }

    /**
     * Retourne la position X de la tuile.
     *
     * @return La position X de la tuile.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retourne la position Y de la tuile.
     *
     * @return La position Y de la tuile.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Retourne l'hexagone de la tuile.
     *
     * @return L'hexagone de la tuile.
     */
    public Polygon getHex() {
        return this.hexagone;
    }

    /**
     * Indique que la souris est à l'intérieur de l'hexagone.
     */
    public void setIn() {
        this.insideHexagon = true;
        this.repaint();
	}

	/**
	 * Change l'état de l'objet pour indiquer qu'il est en dehors de l'hexagone.
	 * Cela met à jour la variable {@code insideHexagon} à {@code false} 
	 * et appelle la méthode {@code repaint()} pour redessiner l'objet.
	 */
	public void setOut() {
		this.insideHexagon = false;
		this.repaint();
	}

	/**
	 * Obtient l'état actuel de l'objet indiquant s'il est à l'intérieur de l'hexagone.
	 *
	 * @return {@code true} si l'objet est à l'intérieur de l'hexagone, 
	 *         {@code false} sinon.
	 */
	public boolean getIn() {
		return this.insideHexagon;
	}

	/**
	 * Récupère la tuile associée à cet objet.
	 *
	 * @return L'objet {@link Tuile} associé, ou {@code null} si aucune tuile
	 *         n'est associée.
	 */
	public Tuile getTuile() {
		return this.laTuile;
	}

	/**
	 * Définit la tuile associée à cet objet et met à jour les cotes en conséquence.
	 *
	 * @param tuile La nouvelle tuile à associer à cet objet.
	 */
	public void setTuile(Tuile tuile) {
		this.laTuile = tuile;
		this.cotes = this.laTuile.getCotes();
	}

	/**
	 * Récupère les cotes de la tuile associée à cet objet.
	 *
	 * @return Un tableau de {@code String} contenant les cotes de la tuile.
	 */
	public String[] getCotes() {
		return this.cotes;
	}

	/**
	 * Renvoie une représentation sous forme de chaîne de caractères de l'objet.
	 *
	 * @return Une chaîne contenant des informations sur la tuile associée, ou
	 *         "TuileVue: null" si aucune tuile n'est associée.
	 */
	@Override
	public String toString() {
		if (laTuile != null) {
			return "TuileVue de :" + laTuile.toString();
		}
		return "TuileVue: null";
	}

}
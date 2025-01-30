package fr.iutfbleau.izanicAissiGallego.projet.modele.db;

import fr.iutfbleau.izanicAissiGallego.projet.modele.jeu.*;
import org.mariadb.jdbc.*;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList; 
import java.io.InputStream;

/**
 * La classe BaseDonnee gère les interactions avec la base de données
 * pour le stockage et la récupération des scores et des séries de tuiles.
 */
public class BaseDonnee {
	
	
	 /**
     * Constructeur par défaut de la classe BaseDonnee.
     * <p>
     * Ce constructeur initialise une nouvelle instance de BaseDonnee
     * sans effectuer d'opérations spécifiques. Il peut être utilisé
     * pour établir une connexion à la base de données lors de l'appel
     * des méthodes associées, comme l'enregistrement ou la récupération
     * de données.
     * </p>
     */
	public BaseDonnee() {
		
	}

    /**
     * Obtient le mot de passe de la base de données à partir d'un fichier.
     *
     * @return Le mot de passe de la base de données.
     */
    private static String getMdp() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream dbpswStream = loader.getResourceAsStream("db/dbpswd");
        Properties props = new Properties();
        try {
            props.load(dbpswStream);
            return props.getProperty("db.password");
        } catch (IOException e) {
            System.exit(1);
        }
        return "";
    }

    /**
     * Récupère les scores associés à une série.
     * @param numSerie Le numéro de la série pour laquelle les scores sont récupérés.
     * @return Tous les score de numSerie
     */
    public static ArrayList<String> getScore(String numSerie) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur lors de l'importation de mariadb");
            System.exit(1);
        }

        try {
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/izanic", "izanic", getMdp());
            PreparedStatement pst = cnx.prepareStatement("SELECT points FROM Scores WHERE serie = ? ORDER BY points DESC");
            pst.setString(1, numSerie);
            ResultSet rs = pst.executeQuery();

            ArrayList<String> points = new ArrayList<String>();
            while (rs.next()) {
                points.add(rs.getString("points"));
            }
            rs.close();
            pst.close();
            cnx.close();

            return points;
        } catch (SQLException e) {
            System.exit(1);
        }
        return new ArrayList<String>();
    }

    /**
     * Enregistre un score pour une série.
     *
     * @param numSerie Le numéro de la série pour laquelle le score est enregistré.
     * @param points   Le score à enregistrer.
     */
    public static void setScore(String numSerie, int points) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.exit(1);
        }

        try {
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/izanic", "izanic", getMdp());
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO Scores (serie, points) VALUES (?, ?)");
            pst.setString(1, numSerie);
            pst.setInt(2, points);
            pst.executeUpdate();

            pst.close();
            cnx.close();
        } catch (SQLException e) {
            System.exit(1);
        }
    }

    /**
     * Récupère une série de tuiles à partir de la base de données.
     * @param numSerie Le numéro de la série à récupérer.
     * @return L'objet {@code Serie} correspondant à ce numéro.
     */
    public static Serie getSerie(String numSerie) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur lors de l'importation de mariadb");
            System.exit(1);
        }

        try {
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/izanic", "izanic", getMdp());
            PreparedStatement pst = cnx.prepareStatement("SELECT objet FROM Series WHERE num = ?");
            pst.setString(1, numSerie);
            ResultSet rs = pst.executeQuery();
            rs.next();
            byte[] data = rs.getBytes("objet");
            Serie objet = SerialSerie.deserialiser(data);

            rs.close();
            pst.close();
            cnx.close();

            return objet;
        } catch (SQLException e) {
            System.exit(1);
        }
        return new Serie("1");
    }

    /**
     * Enregistre une série de tuiles dans la base de données.
     * @param ser La série à enregistrer.
     */
    public static void setSerie(Serie ser) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.exit(1);
        }

        try {
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/izanic", "izanic", getMdp());
            PreparedStatement pst = cnx.prepareStatement("INSERT INTO Series (num, objet) VALUES (?, ?)");

            byte[] data = SerialSerie.serialiser(ser);

            pst.setString(1, ser.getNumSerie());
            pst.setBytes(2, data);
            pst.executeUpdate();

            pst.close();
            cnx.close();
        } catch (SQLException e) {
            System.exit(1);
        }
    }

    /**
     * Récupère la liste de tous les numéros de séries stockés dans la base de donnée.
     * @return Tous les series existante
     */
    public static ArrayList<String> getAllSeriesNum() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur lors de l'importation de mariadb");
            System.exit(1);
        }

        try {
            Connection cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/izanic", "izanic", getMdp());
            PreparedStatement pst = cnx.prepareStatement("SELECT DISTINCT num FROM Series ORDER BY num ASC");
            ResultSet rs = pst.executeQuery();

            ArrayList<String> seriesNum = new ArrayList<String>();
            while (rs.next()) {
                seriesNum.add(rs.getString("num"));
            }
            rs.close();
            pst.close();
            cnx.close();

            return seriesNum;
        } catch (SQLException e) {
            System.exit(1);
        }
        return new ArrayList<String>();
    }
}
package fr.iutfbleau.izanicAissiGallego.projet.modele.jeu;

import java.io.*;

/**
 * La classe SerialSerie fournit des méthodes pour sérialiser et désérialiser une série
 */
public class SerialSerie {

	/**
	* Constructeur de SerialSerie
    */
	public SerialSerie(){
		
	}
	
	/**
     * Sérialise un objet de type Serie en un tableau d'octets.
     *
     * @param ser L'objet Serie à sérialiser.
     * @return Un tableau d'octets représentant l'objet sérialisé, ou un tableau
     *         contenant un seul élément zéro en cas d'échec.
     */
	public static byte[] serialiser(Serie ser) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(ser);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] d = new byte[] { 0 };

		return d;
	}

	 /**
     * Désérialise un tableau d'octets en un objet de type Serie.
     *
     * @param data Le tableau d'octets à désérialiser.
     * @return L'objet Serie désérialisé, ou une nouvelle instance de
     *         Serie avec le numéro "1" en cas d'échec.
     */
	public static Serie deserialiser(byte[] data) {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(data)) {
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Serie) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new Serie("1");
	}

}
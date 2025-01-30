package fr.iutfbleau.izanicAissiGallego.projet.vue.main;

import fr.iutfbleau.izanicAissiGallego.projet.vue.menu.MenuPrincipale;

/**
 * La classe <code>Main</code> est le point d'entrée principal de l'application.
 * Elle initialise et affiche le menu principal du jeu.
 */
public class Main {


	/**
    * Constructeur de Main
    */
	 
	public Main(){}
    /**
     * Méthode principale qui est exécutée au démarrage de l'application.
     * Elle crée une instance de <code>MenuPrincipale</code> et l'affiche.
     *
     * @param args Les arguments de ligne de commande, non utilisés dans cette application.
     */
    public static void main(String[] args) {
        MenuPrincipale mp = new MenuPrincipale();
        mp.afficher(); 
    }
}

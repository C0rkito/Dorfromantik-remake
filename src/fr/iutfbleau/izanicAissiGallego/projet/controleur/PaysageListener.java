package fr.iutfbleau.izanicAissiGallego.projet.controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import fr.iutfbleau.izanicAissiGallego.projet.vue.jeu.*;
import fr.iutfbleau.izanicAissiGallego.projet.modele.jeu.*;
import fr.iutfbleau.izanicAissiGallego.projet.modele.db.*;
import fr.iutfbleau.izanicAissiGallego.projet.vue.fenetre.MaFenetreJeux;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import javax.swing.JPanel;


/**
 * La classe {@code PaysageListener} gère les événements liés à l'interaction 
 * de l'utilisateur avec le paysage du jeu, en traitant les actions de la souris 
 * et du clavier. Elle permet de placer des tuiles sur le paysage, d'ajuster 
 * le niveau de zoom et de mettre à jour l'affichage en fonction des mouvements 
 * et des clics de la souris.
 */
public class PaysageListener implements MouseListener, MouseMotionListener, MouseWheelListener,KeyListener{
	
	
/** La vue du paysage à contrôler. */
    private PaysageVue paysage;

    /** La tuile précédemment survolée par la souris. */
    private TuileVue precedent = null;

    /** Indique si la touche Ctrl est enfoncée. */
    private boolean ctrlAppuyer = false;

    /** Indique si la touche Shift est enfoncée. */
    private boolean majAppuyer = false;

    /** Indique si la touche Alt est enfoncée. */
    private boolean altAppuyer = false;

    /** La fenêtre principale du jeu. */
    private MaFenetreJeux fenetre;

	
	/**
     * Constructeur de la classe PaysageListener.
     *
     * @param paysage La vue du paysage.
     * @param fenetre La fenêtre de jeu.
     */
	public PaysageListener(PaysageVue paysage,MaFenetreJeux fenetre) {
		this.paysage = paysage;
		this.fenetre = fenetre;
		
	}

	/**
     * Gère le mouvement de la souris. Met à jour l'affichage de la tuile survolée.
     * @param evenement de Classe MouseEvent
     */
	@Override
	public void mouseMoved(MouseEvent evenement) {

	int mouseX = evenement.getX();

    int nouv_x = (int) (evenement.getX() / paysage.getZoom()-paysage.getDecalerX());
    int nouv_y = (int) (evenement.getY() / paysage.getZoom()-paysage.getDecalerY());
    Component comp = paysage.getComponentAt(nouv_x, nouv_y);

		if (comp instanceof TuileVue) {
			TuileVue LaTuileVue = (TuileVue) comp;
			
			
			if(LaTuileVue.getTuile() == null){
				if (precedent != LaTuileVue) {
					if (precedent != null) {
						precedent.setOut();
						precedent.repaint();
						LaTuileVue.setIn();
						LaTuileVue.repaint();
					}
				precedent = LaTuileVue;
				}
			}
			LaTuileVue.repaint();
			paysage.repaint();
			
		}
 
	}

	/**
     * Gère le clic de souris. Place la tuile courante sur la tuile cliquée et met à jour le paysage en conséquence.
     * @param evenement de classe MouseEvent
    */
	@Override
	public void mouseClicked(MouseEvent evenement) {
		
		
		int nouv_x = (int) (evenement.getX() / paysage.getZoom()-paysage.getDecalerX());
		int nouv_y = (int) (evenement.getY() / paysage.getZoom()-paysage.getDecalerY());

		Component comp = paysage.getComponentAt(nouv_x, nouv_y);
		
		if (comp instanceof TuileVue) {
			TuileVue LaTuileVue = (TuileVue) comp;
			
			if (LaTuileVue.getTuile() == null){
				
				
				int i_index = 0;
				int j_index = 0;
				for (int j = 0; j < 101; j++) {
					for (int i = 0; i < 101; i++) {
						if (paysage.getP().getPaysage()[i][j] == LaTuileVue) {
							i_index = i;
							j_index = j;
							break;
						}
					}
				}
				
				int x =  paysage.getP().getPaysage()[i_index][j_index].getX();
				int y =  paysage.getP().getPaysage()[i_index][j_index].getY();
				
				LaTuileVue.setTuile(paysage.getP().getTuileCourante());
				paysage.setZoom(-0.002);
				

				
				
				if(j_index%2 == 0){
					
					if(paysage.getP().getPaysage()[i_index-1][j_index] == null){
						paysage.getP().getPaysage()[i_index-1][j_index] = new TuileVue(this.paysage,x,y-68);
						paysage.add(paysage.getP().getPaysage()[i_index-1][j_index]);
					}
					
					if(paysage.getP().getPaysage()[i_index-1][j_index+1] == null){
						paysage.getP().getPaysage()[i_index-1][j_index+1] = new TuileVue(this.paysage,x+60,y-34);

						paysage.add(paysage.getP().getPaysage()[i_index-1][j_index+1]);
					}
					
					if(paysage.getP().getPaysage()[i_index][j_index+1] == null){
						paysage.getP().getPaysage()[i_index][j_index+1] = new TuileVue(this.paysage,x+60,y+34);
		
						paysage.add(paysage.getP().getPaysage()[i_index][j_index+1]);
					}
					
					if(paysage.getP().getPaysage()[i_index+1][j_index] == null){
						paysage.getP().getPaysage()[i_index+1][j_index] = new TuileVue(this.paysage,x,y+68);
					
						paysage.add(paysage.getP().getPaysage()[i_index+1][j_index]);
					}

					if(paysage.getP().getPaysage()[i_index][j_index-1] == null){
						paysage.getP().getPaysage()[i_index][j_index-1] = new TuileVue(this.paysage,x-60,y+34);
				
						paysage.add(paysage.getP().getPaysage()[i_index][j_index-1]);
					}
				
					if(paysage.getP().getPaysage()[i_index-1][j_index-1] == null){
						paysage.getP().getPaysage()[i_index-1][j_index-1] = new TuileVue(this.paysage,x-60,y-34);
	
						paysage.add(paysage.getP().getPaysage()[i_index-1][j_index-1]);
					}
				

				}else{
	
					
					if(paysage.getP().getPaysage()[i_index][j_index+1] == null){
						paysage.getP().getPaysage()[i_index][j_index+1] = new TuileVue(this.paysage,x+60,y-34);
		
						paysage.add(paysage.getP().getPaysage()[i_index][j_index+1]);
					}
					
					if(paysage.getP().getPaysage()[i_index+1][j_index+1] == null){
						paysage.getP().getPaysage()[i_index+1][j_index+1] = new TuileVue(this.paysage,x+60,y+34);
	
						paysage.add(paysage.getP().getPaysage()[i_index+1][j_index+1]);
					}
					
					if(	paysage.getP().getPaysage()[i_index+1][j_index]== null){
						paysage.getP().getPaysage()[i_index+1][j_index] = new TuileVue(this.paysage,x,y+68);

						paysage.add(paysage.getP().getPaysage()[i_index+1][j_index]);
					}
					
					if(paysage.getP().getPaysage()[i_index+1][j_index-1] == null){
						paysage.getP().getPaysage()[i_index+1][j_index-1] = new TuileVue(this.paysage,x-60,y+34);
						
						paysage.add(paysage.getP().getPaysage()[i_index+1][j_index-1]);
					}
					
					if(paysage.getP().getPaysage()[i_index][j_index-1] == null){
						paysage.getP().getPaysage()[i_index][j_index-1] = new TuileVue(this.paysage,x-60,y-34);
		
						paysage.add(paysage.getP().getPaysage()[i_index][j_index-1]);
					}
					
					if(paysage.getP().getPaysage()[i_index-1][j_index] == null){
						paysage.getP().getPaysage()[i_index-1][j_index] = new TuileVue(this.paysage,x,y-68);
						paysage.add(paysage.getP().getPaysage()[i_index-1][j_index]);
					}							
					
				}
				
				
			

				paysage.getP().setTuileCourante(paysage.getP().getTuileSuivante());
				paysage.getP().calculerScore();	
				
				if(paysage.getP().getNbTuilePlacer() == 50){
					finJeu();
				}

			}
		}
	}


	
	@Override
	public void mouseWheelMoved(MouseWheelEvent evenement) {
		
		
		if (evenement.getWheelRotation() > 0) {
			if(ctrlAppuyer){
				if(paysage.getZoom() > 0.3){
					paysage.setZoom(-0.01);
				}
				
			}
			else{
				if(majAppuyer){
					paysage.decaley(-30);
				}else{
					if(altAppuyer){
						paysage.decalex(-30);
					}
					else{
						paysage.getP().getTuileCourante().tournerGauche();
					}
					
				}

			}

		} else {
			if(ctrlAppuyer){
				if(paysage.getZoom() < 3){
					paysage.setZoom(0.01);
				}
			}
			else{
				if(majAppuyer){
					paysage.decaley(30);
				}
				else{
					if(altAppuyer){
						paysage.decalex(30);
					}
					else{
						paysage.getP().getTuileCourante().tournerDroite();
					}
					
					
				}

			}

		}
		paysage.repaint();
	}


	@Override
	public void	keyPressed(KeyEvent evenement){
		if(evenement.getKeyCode() == KeyEvent.VK_CONTROL){
			this.ctrlAppuyer = true;
		}
		if(evenement.getKeyCode() == KeyEvent.VK_SHIFT){
			this.majAppuyer = true;
		}
		
		if(evenement.getKeyCode() == KeyEvent.VK_ALT){
			this.altAppuyer = true;
		}
		
		if(evenement.getKeyCode() == KeyEvent.VK_DOWN){
			paysage.decaley(-40);
		}
		if(evenement.getKeyCode() == KeyEvent.VK_UP){
			paysage.decaley(40);
		}
		if(evenement.getKeyCode() == KeyEvent.VK_RIGHT){
			paysage.decalex(-40);
		}
		if(evenement.getKeyCode() == KeyEvent.VK_LEFT){
			paysage.decalex(40);
		}		
		
	}
	
	@Override
	public void	keyReleased(KeyEvent evenement){
		if(evenement.getKeyCode() == KeyEvent.VK_CONTROL){
			this.ctrlAppuyer = false;
		}
		if(evenement.getKeyCode() == KeyEvent.VK_SHIFT){
			this.majAppuyer = false;
		}
		if(evenement.getKeyCode() == KeyEvent.VK_ALT){
			this.altAppuyer = false;
		}

	}
	
	
	private void finJeu(){
		fenetre.remove(paysage);


		String serie = paysage.getP().getNumSerie();
		String monScore = ""+paysage.getP().getScore();
		BaseDonnee.setScore(paysage.getP().getNumSerie(),Integer.valueOf(monScore));
		ArrayList<String> scoreSerie = BaseDonnee.getScore(serie);
		
		fenetre.setExtendedState(JFrame.NORMAL);
		
		
		fenetre.setTitle("Recapitulatif Serie "+serie);
		
		
		
		
		
		
		fenetre.setLayout(new BorderLayout());
		fenetre.pack();



		int classement = 0;
		for (int i = scoreSerie.size() - 1; i >= 0; i--) {
			if (scoreSerie.get(i).equals(monScore)) {
				classement = i;
				break;
			}
		}
		
		Font font = new Font("Arial", Font.BOLD, 18);
		JPanel recap = new JPanel(new GridLayout(2,2));
		JLabel pointxt = new JLabel("Point: ",javax.swing.SwingConstants.CENTER);
		JLabel point =  new JLabel(monScore,javax.swing.SwingConstants.CENTER);
		
		
		JLabel positontxt = new JLabel("Position: ",javax.swing.SwingConstants.CENTER);
		JLabel positon = new JLabel(Integer.valueOf(classement+1)+"/"+scoreSerie.size(),javax.swing.SwingConstants.CENTER);
		
		
		point.setFont(font);
		positon.setFont(font);
		point.setForeground(Color.RED);
		positon.setForeground(Color.RED);
		
		
		
		recap.add(pointxt);
		recap.add(point);
		recap.add(positontxt);
		recap.add(positon);
		
		
		JPanel scoreSeriePanel = new JPanel(new GridLayout(12,1));
		

		
		
		

		
		


	List<JPanel> lignesTemp = new ArrayList<>();


	int nb = 0;
		for (int i = classement - 1; nb < 5 && i >= 0; i--) {
			if (scoreSerie.get(i) != monScore) { 
				JPanel ligne = new JPanel(new GridLayout(1, 1));
				ligne.add(new JLabel("" + (i + 1),javax.swing.SwingConstants.CENTER)); 
				ligne.add(new JLabel(" " + scoreSerie.get(i) + " points",javax.swing.SwingConstants.CENTER)); 

				lignesTemp.add(ligne); 
				nb++;
			}
		}

		Collections.reverse(lignesTemp);
		for (JPanel ligne : lignesTemp) {
			scoreSeriePanel.add(ligne); 
		}

		JPanel maLigne = new JPanel(new GridLayout(1,1));
		JLabel monScoreLabel = new JLabel(Integer.valueOf(classement+1)+"",javax.swing.SwingConstants.CENTER);
		JLabel monClassementLabel = new JLabel(scoreSerie.get(classement)+" points",javax.swing.SwingConstants.CENTER);
		monScoreLabel.setForeground(Color.RED);
		maLigne.add(monScoreLabel);
		maLigne.add(monClassementLabel);
		
		scoreSeriePanel.add(maLigne);
		
		
		nb = 0;
		for (int i = classement+1;nb<5 && i < scoreSerie.size(); i++) {
			if(scoreSerie.get(i) != monScore){
				JPanel ligne = new JPanel(new GridLayout(1,1));
				ligne.add(new JLabel(""+(i+1),javax.swing.SwingConstants.CENTER));
				ligne.add(new JLabel(scoreSerie.get(i)+" points",javax.swing.SwingConstants.CENTER));
				scoreSeriePanel.add(ligne);
				nb++;
			}

		}
		
		
		

		

		fenetre.add(recap,BorderLayout.NORTH);
		fenetre.add(scoreSeriePanel,BorderLayout.CENTER);
	
	
		fenetre.setMaximumSize(new Dimension(500,500));
		fenetre.setSize(500,500);
		fenetre.setPreferredSize(new Dimension(500,500));
		
		
		
		
		
		return;
	}
	
	@Override
	public void	keyTyped(KeyEvent evenement){}
	
	@Override
	public void mousePressed(MouseEvent evenement) {}

	@Override
	public void mouseReleased(MouseEvent evenement) {}

	@Override
	public void mouseEntered(MouseEvent evenement) {
	}

	@Override
	public void mouseExited(MouseEvent evenement) {}

	@Override
	public void mouseDragged(MouseEvent evenement) {}

}
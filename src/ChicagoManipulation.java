import java.awt.Component;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.print.attribute.TextSyntax;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChicagoManipulation implements MouseMotionListener, MouseListener{
	ChicagoPlayer player;
	ChicagoView view;
	ChicagoController control;
	
	public ChicagoManipulation(ChicagoView view, ChicagoPlayer player,ChicagoController control) {
		this.player = player;
		this.view = view;
		this.control = control;
		
		for(Card card : player.allCards){
			card.addMouseMotionListener(this);
			card.addMouseListener(this);
		}
		view.buttons.AcceptButton.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource().equals(view.buttons.AcceptButton)){
			
			int numberOfCardsToChange = 0;
			Card cardToChange=null;
			//See how many cards that should be given to the player
			for(Card card: player.allCards){
				if(card.isVisible() == false){
					numberOfCardsToChange++;
					cardToChange=card;
				}
			}
			
			if(numberOfCardsToChange==1){
				//Change to an new card
				cardToChange.changeCard(control.deckOfCards[control.getcounterOfUsedCards()]);
				control.incrementcounterOfUsedCards();
				final Card cardToChangeFinal = cardToChange;
				final ChicagoView viewFinal = view;
				final ChicagoPlayer playerFinal = player;
				final ChicagoController controlFinal = control;

				
				for(Component comp : view.oneUp(cardToChange).getComponents()){
					comp.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub	
					}
					@Override
					public void mousePressed(MouseEvent e) {
						cardToChangeFinal.setVisible(false);
						e.getComponent().getParent().setVisible(false);
						if((((JButton) e.getSource()).getText().equals("Yes"))){
							viewFinal.setLocationOfCard(playerFinal, cardToChangeFinal);
						}
						else{
							cardToChangeFinal.changeCard(controlFinal.deckOfCards[controlFinal.getcounterOfUsedCards()]);
							controlFinal.incrementcounterOfUsedCards();
							viewFinal.setLocationOfCard(playerFinal, cardToChangeFinal);
						}	
						cardToChangeFinal.setVisible(true);

					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});;
				}
				
				
				return;
			}

			for(Card card: player.allCards){
				if(card.isVisible()==false){
					card.changeCard(control.deckOfCards[control.getcounterOfUsedCards()]);
					control.incrementcounterOfUsedCards();;
					view.setLocationOfCard(player, card);
					card.showCard(true);
				}
			}
		}
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (Card card : player.allCards){
			if(view.trash.getBounds().intersects(card.getBounds()))
				card.setVisible(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Card clickedObject = (Card) e.getSource();
		PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
		int x = (int) b.getX()- view.getLocationOnScreen().x - clickedObject.getWidth()/2; 
		int y = (int) b.getY() - view.getLocationOnScreen().y - clickedObject.getHeight()/2;
		Dimension size = clickedObject.getPreferredSize();

		clickedObject.setBounds(x, y, size.width, size.height);
		view.repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

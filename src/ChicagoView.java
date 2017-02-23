import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.List;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChicagoView extends JFrame{
	Trash trash;
	ButtonBar buttons;
	ResultBar resultBar;
	Thread waitForPlayer = new Thread();
	
	ChicagoView(ChicagoPlayer player){
		this.setTitle("Chicago");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(0, 153, 0));		
		
		this.setLocation(0, 0);
		this.setSize(550, 300);
		
		trash = new Trash(System.getProperty("user.dir")+"/trash.png");
		resultBar = new ResultBar(new String[]{"Marc", "Carl", "Oscar", "Greta"});
		buttons = new ButtonBar();

		this.setLocationRelativeTo(null);
		
		Dimension size = trash.getPreferredSize();
		trash.setBounds((int) (30+ this.getWidth()/2.5), this.getHeight()/4, size.width, size.height);
		size = resultBar.getPreferredSize();
		resultBar.setBounds(0,0,size.width, size.height);
		size = buttons.getPreferredSize();
		buttons.setBounds(400,80,size.width, size.height);

		size = player.allCards[0].getPreferredSize();
		for(int i = 0; i<player.allCards.length;i++){
			player.allCards[i].setBounds(350-i*60,200,size.width, size.height);
			this.add(player.allCards[i]);
		}
	
		this.add(resultBar);
		this.add(trash);
		this.add(buttons);

		this.setVisible(true);
	}
	void setLocationOfCard(ChicagoPlayer player, Card card){
		int i=0;
		int[] pos = new int[5];
		
		for(Card c : player.allCards){
			if(c.isVisible()==true){
				pos[i]=player.allCards[i].getLocation().x;
			}
			else{
				pos[i]=0;
			}
			i++;
		}
		Arrays.sort(pos);
		i=1;
		for(i=1;i<5;i++)
			if(pos[i] == 0){
				i++;
			}
			else{
				if(pos[i]>119 && pos[i-1]==0){
					Dimension size = card.getPreferredSize();
					card.setBounds(pos[i]-60, 200, size.width, size.height);
					return;
					}
				if(pos[i]-pos[i-1]>119){
					Dimension size = card.getPreferredSize();
					card.setBounds(pos[i-1]+60, 200, size.width, size.height);
					return;
				}
			}
		Dimension size = card.getPreferredSize();
		card.setBounds(pos[4]+60, 200, size.width, size.height);
	}
	JPanel oneUp(Card card, int numberOfRuns){
		JPanel panel = new JPanel();
		panel.setSize(41, 21);
		panel.setOpaque(false);
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		panel.add(yes);
		panel.add(no);
		this.buttons.setVisible(false);		
		panel.setVisible(true);
		
		this.waitForPlayer = new Thread(new Runnable() {
		    private ButtonBar buttons;
		    private JPanel panel;
		    
		    //Make sure that the other button returns when buttons is pressed 
		    public Runnable init(ButtonBar buttons, JPanel panel, int numberOfRuns) {
		        this.buttons = buttons;
		        this.panel = panel;
		        return this;
		    }
		    @Override
		    public void run() {
		        while(panel.isVisible()){
		        	try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		        if(numberOfRuns !=1)
		        	buttons.setVisible(true);
		    }
		}.init(this.buttons, panel, numberOfRuns));
		this.waitForPlayer.start();
		
		this.add(panel);
		panel.setBounds(this.buttons.getBounds().x,this.buttons.getBounds().y,100,100);
		
		Dimension size = card.getPreferredSize();
		card.setBounds(this.buttons.getBounds().x-60,this.buttons.getBounds().y, size.width, size.height);
		card.showCard(true);
		
		return panel;
		
	}
}

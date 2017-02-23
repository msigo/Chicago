import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

public class ResultBar extends JPanel{

    private String[] playerNames;
    private int[] score;

    ResultBar(String[] players) {
    	playerNames = players;
    	score =  new int[playerNames.length];
    	for(int i = 0; i<playerNames.length; i++){
    		score[i]=0;
    	}
    }
    
    void updateScore(String player, int scoreToInsert){
    	for(int i =0;i<playerNames.length;i++){
    		if(playerNames[i].equals(player)){
    			score[i] = scoreToInsert;
    		}
    	}
    	this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(139, 69, 19));
        g.fillRect(0, 0, 550, 25);
        this.setSize(550,25);
    	for(int i = 0; i<playerNames.length; i++){
    		g.setColor(Color.WHITE);
    		g.drawString(playerNames[i]+"   " + score[i], 10+i*495/playerNames.length, 18);
    		
    	}
    }
        
}


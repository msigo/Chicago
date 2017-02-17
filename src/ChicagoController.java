import java.util.Arrays;
import java.util.Collections;

public class ChicagoController {
	private String[] typeOfCard = new String[]{"hearts","diamonds","clubs","spades"};
	private String[] values = new String[]{"ace","2","3","4","5","6","7","8","9","10","jack","queen","king"};
	String[] deckOfCards = new String[52];
	private int counterOfUsedCards = 0;
		
	public ChicagoController(){
		createCards();
		shuffleCards();
		String[] paths = new String[]{deckOfCards[0],deckOfCards[1],deckOfCards[2],deckOfCards[3],deckOfCards[4]};
		counterOfUsedCards = 4;
		ChicagoPlayer player = new ChicagoPlayer(paths);
		ChicagoView view = new ChicagoView(player);
		ChicagoManipulation manipulation = new ChicagoManipulation(view, player,this);
		
	}
	void incrementcounterOfUsedCards(){
		if(counterOfUsedCards == 51){
			shuffleCards();
			counterOfUsedCards = 0;
			return;
		}
		counterOfUsedCards++;
	}
	int getcounterOfUsedCards(){
		return counterOfUsedCards;
	}
	
	void createCards(){
		int i=0;
		for(String type:typeOfCard){
			for(String val:values){
				deckOfCards[i] = val + "_of_"+type+".png";
				i++;
			}
		}
		
	}
	void shuffleCards(){
		Collections.shuffle(Arrays.asList(deckOfCards));
		}
	public static void main(String[] args) {
		new ChicagoController();
	}
}

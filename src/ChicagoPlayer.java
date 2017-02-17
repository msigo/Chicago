
public class ChicagoPlayer {
	Card[] allCards = new Card[5];
	public ChicagoPlayer(String[] paths) {
		for(int i = 0; i<5; i++){
			allCards[i] = new Card(paths[i]);
		}
	}
}

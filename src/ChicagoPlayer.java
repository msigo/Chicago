import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ChicagoPlayer {
	Card[] allCards = new Card[5];
	Integer[] values = new Integer[5];
	String[] CardColor =new String[5];

	public ChicagoPlayer(String[] paths) {
		for(int i = 0; i<5; i++){
			allCards[i] = new Card(paths[i]);
		}
	}
	public int evaluateCards(){
		boolean containsKing=false;
		for(int i=0; i<5;i++){
			CardColor[i]= allCards[i].getPath().split("_")[2].split("\\.")[0];
			
			switch(allCards[i].getPath().split("_")[0]){
				case "ace": 	values[i]=1; break;
				case "2": 		values[i]=2; break;
				case "3":		values[i]=3; break;
				case "4":		values[i]=4; break;
				case "5":		values[i]=5; break;
				case "6":		values[i]=6; break;
				case "7":		values[i]=7; break;
				case "8":		values[i]=8; break;
				case "9":		values[i]=9; break;
				case "10":		values[i]=10; break;
				case "jack":	values[i]=11; break;
				case "queen":	values[i]=12; break;
				case "king":	values[i]=13; containsKing = true; break;			
			}
		}
		if(containsKing){
			Arrays.sort(values);
			int t=0;
			while(t<5){
				if(values[t]==1){
					values[t] = 14;
					t++;
				}
				else{
					break;
				}
			}
		}
		Arrays.sort(values);
		if(checkStraightFlush()) return 8;
		if(check4OfAKind()) return 7;
		if(checkFullHouse()) return 6;
		if(checkFlush()) return 5;
		if(checkStraight()) return 4;
		if(check3OfAKind()) return 3;
		if(check2pairs()) return 2;
		if(checkPair()) return 1;
		return 0;
		//System.out.print(check4OfAKind()?"Four Of A Kind":"");
		//System.out.print(checkFlush()?"Flush":"");
		//System.out.print(checkFullHouse()?"FullHouse":"");
		//System.out.print(checkStraight()?"Straight":"");
		//System.out.print(check3OfAKind()?"Three Of A kind":"");
		//System.out.print(check2pairs()?"Two pairs":"");
		//System.out.print(checkPair()?"Pair":"");
		//System.out.println();
		}
	private boolean checkFlush(){
		String[] array = {CardColor[0], CardColor[1], CardColor[2], CardColor[3], CardColor[4]};
		Set<String> set   = new HashSet<String>(Arrays.asList(array));
		return (set.size()==1)? true: false;
	}
	private boolean checkStraight(){
		return (values[0]+1 == values[1] && values[1]+1 == values[2] 
				&& values[2]+1 == values[3] && values[3]+1 ==values[4])?true:false;
		
	}
	private boolean checkStraightFlush(){
		return (checkFlush() && checkStraight())? true: false;
	}
	private boolean check4OfAKind(){
		return (values[0]==values[3] || values[1]==values[4])?true:false;
	}
	private boolean checkFullHouse(){
		Set<Integer> set   = new HashSet<Integer>(Arrays.asList(values));
		return (!check4OfAKind() && set.size() == 2)?true:false;
	}
	private boolean check3OfAKind(){
		return (values[0] == values[2] || values[2] == values[4])? true:false;
	}
	private boolean check2pairs(){
		Set<Integer> set   = new HashSet<Integer>(Arrays.asList(values));
		return (!check3OfAKind() && set.size()==3)? true:false;
	}
	private boolean checkPair(){
		Set<Integer> set   = new HashSet<Integer>(Arrays.asList(values));
		return (!check3OfAKind() && set.size()==4)? true:false;
	}
		
		
}

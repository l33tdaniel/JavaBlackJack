import java.util.*;

public class Hand {
  private ArrayList<Card> hand = new ArrayList<Card>();
 
  public Hand() {
    
  }

//Deals a card from the deck and adds it to the current hand
  public void add(Card card) {
    hand.add(card);
  }

//Returns the total value of a hand 
  public int check() {
    int total = 0; 
    for(int i = 0; i < hand.size(); i++) {
      total += hand.get(i).getNumber();
    }

    if(total > 21) {
      total = 0; 
      for(int i = 0; i < hand.size(); i++) {
        if (hand.get(i).getNumber() == 11) {
          //convert aces to one if total too big  
          hand.get(i).setNumber(1);
        }
        total += hand.get(i).getNumber();
      }
    }

    return total;
  }

	void Clear(){
		hand = new ArrayList<Card>();
	}


  //Prints out the Hand 
  public String toString() {
    String toReturn = "";
    // String[][] cardStrings = new int[hand.size];
    for(int j = 0; j < 5;j++){
      for(int i = 0; i < hand.size(); i++) {
        toReturn += hand.get(i).asString(j);
        toReturn += " ";
      }

      if(j != 4){toReturn += "\n";}
    }
    return toReturn; 
  }

}
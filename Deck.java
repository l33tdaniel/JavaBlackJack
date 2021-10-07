import java.util.*;

public class Deck {
  private ArrayList<Card> cards = new ArrayList<Card>();

//Construct a deck from an Array List of cards 
  public Deck(Card[] cardArray) {
    for(Card card : cardArray){
			cards.add(card);
		}
  }

//Construct a standard deck of 52 cards 
	public Deck(){
		String[] suits = new String[]{"hearts", "diamonds", "spades", "clubs"};
    
		for(String suit : suits){
			for(int i = 2; i < 15;i++){
				Card card = new Card(suit, i);
				cards.add(card);
			}
		}
	}

  //Randomly shuffles the deck
  public void shuffle() {
    Collections.shuffle(cards);
  }

  //Deals top card(First Index) 
  //Returns the card
  public Card deal() {
    Card dealt = cards.get(0);
    cards.remove(0);
    return dealt;
  }

  //Prints out the Deck 
  public String toString() {
    String toReturn = "";
    for(int i = 0; i < cards.size(); i++) {
      toReturn += cards.get(i).toString();
    }
    return toReturn; 
  }

}
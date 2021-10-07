public class Card{
	private String suit;
	private int number;

//Initialize Card with a suit and a number
  public Card(String suit, int number){
			this.suit = suit;
			this.number = number;
	}

//Getter for number variable
  public int getNumber() {
		if(number <= 11){
    return this.number; 
		}else{
			return 10;
		}
  }

//Set number
  public void setNumber(int number) {
    this.number = number; 
  }

//Print Method  
	public String toString(){
    String suitSymbol = "";
    if(suit == "spades"){
			suitSymbol = "♠ ";
		} else if(suit == "hearts"){
			suitSymbol = "♥ ";
		} else if(suit == "diamonds"){
			suitSymbol = "♦ ";
		}else{
			suitSymbol = "♣ ";
		}
    String numberString = "";

  	if(number < 10){
			numberString = "" + number + " ";
      
    }else if(number == 10){
      numberString = "" + number;
    
		}else if(number == 11){
			numberString = "A ";
		}else if(number == 12){
			numberString = "J ";
		}else if(number == 13){
			numberString = "K ";
		}else if(number == 14){
			numberString = "Q ";
		}

    String toReturn = "┌──────┐\n";
    toReturn += "│ " + numberString + " " + suitSymbol + "│\n";
    toReturn += "│      │\n";
    toReturn += "│ " + suitSymbol + " " + numberString + "│\n";
    toReturn += "└──────┘\n";
    return toReturn;
	}
}
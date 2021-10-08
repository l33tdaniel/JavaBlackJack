import java.util.*;

public class Game {
  public Scanner input = new Scanner(System.in);

  int balance = 1000;
  int bet = 0;

  Deck game_deck = new Deck();
  Hand player = new Hand();
  Hand computer = new Hand();
  String command = "";
  boolean isPlaying = true;

//Game Initializer
  public Game() {
    Start();
    while (isPlaying) {
      Deal();
      int hasFinished = 1;
      if(player.check() == 21)  {
        Update("You hit 21!!");
        hasFinished = 3;
      }
      input.nextLine();
      Update("A new round has begun!!!");
      System.out.println("What would you like to do?");
      System.out.print("Hit or Stand?: ");
      while (hasFinished == 1) {
        command = input.nextLine();
        command = command.toLowerCase();
        hasFinished = Action(command);
      }
      if (hasFinished == 2) {
        Play_Computer();
      }
      System.out.print("press ENTER to continue");
      input.nextLine();
      clear();
    }
  }

//prints the welcome method and shuffles the deck
  public void Start() {
    System.out.println("♣ ♥ ♠ ♦ ♣ ♥ ♠ ♦ ♣ ♥ ♠ ♦ ♣ ♥ ♠ ♦");
    System.out.println("Welcome to blackjack!");
    System.out.println("♣ ♥ ♠ ♦ ♣ ♥ ♠ ♦ ♣ ♥ ♠ ♦ ♣ ♥ ♠ ♦");
    game_deck.shuffle();
  }

//Clear the console
  public void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  // returns 0 if the round is over(bust or 21)
  // returns 1 if we need to do the next loop(hit)
  // returns 2 if the computer needs to go(stand)
  public int Action(String command) {
    if (command.equals("hit")) {
      clear();
      player.add(game_deck.deal());
      if (player.check() > 21) {
        clear();
        Update("You Busted :( you lost " + bet + " credits...");
        balance -= bet;
        return 0;
      } else if (player.check() == 21) {
        clear();
        Update("You won because you hit 21: you won " + (bet * 2) + " credits!");
        balance += bet * 2;
        return 0;
      } else {
        clear();
        Update("You Hit!");
        System.out.println("What would you like to do?");
        System.out.print("Hit or Stand?: ");
        return 1;
      }
    } else if (command.equals("stand")) {
      clear();
      System.out.println("You turn is over. It is now the computer's turn");
      return 2;
    }
    return 2;
  }

  void Update(String update){
    System.out.println("You are betting " + bet);
    System.out.println("You have a total of " + player.check() + " with the cards " + "\n" + player);
    System.out.println("The computer has a total of " + computer.check() + " with the cards \n" + computer);
    System.out.println(update);
  }

  public void Play_Computer() {
    if (computer.check() == 21) {
      clear();
      Update("The Computer Hits BlackJack and you lose " + bet + " credits...");
      balance -= bet;
      return;
    } else if (computer.check() > 21) {
      clear();
      Update("The Computer Busts and You win " + bet + " credits!");
      balance += bet;
      return;
    }

    if (computer.check() > 17) {
      if (computer.check() == player.check()) {
        clear();
        Update("TiE!!!! you lost 0 credits");
        return;
      } else if (computer.check() > player.check()) {
        clear();
        Update("Computer has a higher score! you lose " + bet + " credits XD");
        balance -= bet;
        return;
      } else {
        clear();
        Update("You WIN! You won " + bet + " credits");
        balance += bet;
        return;
      }
    }
    while (computer.check() <= 17) {
      computer.add(game_deck.deal());

      if (computer.check() == 21) {
        clear();
        Update("The Computer Hits Blackjack and you lose " + bet + " credits...");
        balance -= bet;
        return;
      } else if (computer.check() > 21) {
        clear();
        Update("The Computer Busts and you win " + bet + " credits!");
        balance += bet;
        return;
      }
    }
    if (computer.check() == player.check()) {
      clear();
      Update("TiE!!!! you lost 0 credits");
      return;
    } else if (computer.check() > player.check()) {
      clear();
      Update("Computer has a higher score! you lose " + bet + " credits XD");
      balance -= bet;
      return;
    } else {
      clear();
      Update("You WIN! You won " + bet + " credits");
      balance += bet;
      return;
    }
  }

  public boolean Validate(int bet) {
    if (bet > balance || bet < 0) {
      return false;
    }
    return true;
  }

  public void Deal() {
    player.Clear();
    computer.Clear();
    // Initial Hands
    System.out.println("You have " + balance + " credits!");
    System.out.print("How much would you like to bet: ");

    bet = input.nextInt();
    while (!Validate(bet)) {
      System.out.print("Invalid Amount... Try Again: ");
      bet = input.nextInt();
    }
    clear();

    player.add(game_deck.deal());
    computer.add(game_deck.deal());
    player.add(game_deck.deal());
    computer.add(game_deck.deal());

    // System.out.println("Your cards \n" + player);
    // System.out.println("The computer has \n" + computer);
  }

}
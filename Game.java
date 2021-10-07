import java.util.*;
import java.util.concurrent.TimeUnit;

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
      input.nextLine();
      while (hasFinished == 1) {

        System.out.println("What would you like to do?");
        System.out.print("Hit or Stand?: ");
        command = input.nextLine();
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

//Start the game
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
    if (command.equals("Hit")) {
      player.add(game_deck.deal());
      System.out.println("You now have \n" + player);
      System.out.println("The computer has " + computer);
      System.out.println("Your Score: " + player.check());
      System.out.println("Computer Score: " + computer.check());
      if (player.check() > 21) {
        System.out.println("You Busted :( you lost " + bet + " credits...");
        balance -= bet;
        return 0;
      } else if (player.check() == 21) {
        System.out.println("You won because you hit 21: you won " + (bet * 2) + " credits!");
        balance += bet * 2;
        return 0;
      } else {
        return 1;
      }
    } else if (command.equals("Stand")) {
      return 2;
    }

    return 4;
  }

  public void Play_Computer() {
    if (computer.check() == 21) {
      System.out.println("The Computer Hits BlackJack and you lose " + bet + " credits...");
      balance -= bet;
      return;
    } else if (computer.check() > 21) {
      System.out.println("The Computer Busts and You win " + bet + " credits!");
      balance += bet;
      return;
    }

    if (computer.check() > 17) {
      if (computer.check() == player.check()) {
        System.out.println("TiE!!!! you lost 0 credits");
        return;
      } else if (computer.check() > player.check()) {
        System.out.println("Computer has a higher score! you lose " + bet + " credits XD");
        balance -= bet;
        return;
      } else {
        System.out.println("You WIN! You won " + bet + " credits");
        balance += bet;
        return;
      }
    }
    while (computer.check() <= 17) {
      computer.add(game_deck.deal());
      System.out.println("The Computer Hits! his deck is \n" + computer);

      if (computer.check() == 21) {
        System.out.println("The Computer Hits Blackjack and you lose " + bet + " credits...");
        balance -= bet;
        return;
      } else if (computer.check() > 21) {
        System.out.println("The Computer Busts and you win " + bet + " credits!");
        balance += bet;
        return;
      }
    }
    if (computer.check() == player.check()) {
      System.out.println("TiE!!!! you lost 0 credits");
      return;
    } else if (computer.check() > player.check()) {
      System.out.println("Computer has a higher score! you lose " + bet + " credits XD");
      balance -= bet;
      return;
    } else {
      System.out.println("You WIN! You won " + bet + " credits");
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

    System.out.println("Okay... Let's Do This!");

    player.add(game_deck.deal());
    computer.add(game_deck.deal());
    player.add(game_deck.deal());
    computer.add(game_deck.deal());

    System.out.println("Your cards \n" + player);
    System.out.println("The computer has \n" + computer);
  }

}
import java.util.Scanner;

public class BlackJack {
	
	public static void main(String[] args){
		
		//welcome message
		System.out.println("Welcome to blackjack!");
		
		//create our playing deck and shuffling the cards
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		
		//create empty decks for the player and dealer
		Deck playerDeck = new Deck();
		Deck dealerDeck = new Deck();
		
		double playerMoney = 100.00;
		
		Scanner userInput = new Scanner(System.in);
		
		//game loop
		while(playerMoney>0){
			//Play on!
			System.out.println("You have $"+playerMoney+", how much would you like to bet?");
			
			double playerBet = userInput.nextDouble();
			if(playerBet>playerMoney){
				System.out.println("You can't bet that much!");
				break;
			}
			boolean endRound = false;
			//start dealing
			//player gets 2 cards
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			
			//dealer's 2 cards
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			while(true){
				System.out.println("Your hand:");
				System.out.println(playerDeck.toString());
				System.out.println("Your hand is valued at "+playerDeck.cardsValue());
				
				//display dealer hand
				System.out.println("Dealer hand: "+dealerDeck.getCard(0).toString()+" and [Hidden]");
				
				//what does the player want to do?
				System.out.println("What would you like to do? (1)Hit (2)Stand");
				int response = userInput.nextInt();
				if(response==1){
					playerDeck.draw(playingDeck);
					System.out.println("You draw a "+playerDeck.getCard(playerDeck.deckSize()-1).toString());
					//Bust if over 21
					if(playerDeck.cardsValue()>21){
						System.out.println("Bust. Currently valued at: "+playerDeck.cardsValue());
						playerMoney -= playerBet;
						endRound=true;
						break;
					}
				}
				if(response == 2){
					break;
				}
			}
			//reveal dealer's cards
			System.out.println("Dealer Cards: "+dealerDeck.toString());
			//see if dealer has more points than player
			if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && (endRound==false)){
				System.out.println("Dealer beats you.");
				playerMoney-=playerBet;
				endRound=true;
			}
			//dealer draws at 16, stands at 17
			while((dealerDeck.cardsValue() < 17) && (endRound==false)){
				dealerDeck.draw(playingDeck);
				System.out.println("Dealer draws: "+dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			//display total value for dealer
			System.out.println("Dealer's hand is valued at "+dealerDeck.cardsValue());
			//determine if dealer busted
			if((dealerDeck.cardsValue()>21)&&(endRound==false)){
				System.out.println("Dealer busts! You win.");
				playerMoney+=playerBet;
				endRound=true;
			}
			//determine if push also called a "tie"
			if((playerDeck.cardsValue()==dealerDeck.cardsValue())&&(endRound==false)){
				System.out.println("Push");
				endRound=true;
			}
			//the last option is the player beat the standing dealer
			if((playerDeck.cardsValue() > dealerDeck.cardsValue())&&(endRound==false)){
				System.out.println("You win the hand!");
				playerMoney+=playerBet;
				endRound=true;
			}
			else if(endRound==false){
				System.out.println("You lose the hand.");
				playerMoney-=playerBet;
				endRound=true;
			}
			//put player and dealer cards back into the deck
			playerDeck.moveAllToDeck(playingDeck);
			dealerDeck.moveAllToDeck(playingDeck);
			System.out.println("End of hand.");
		}
		System.out.println("Game over! You're out of money!");
		
	}

}

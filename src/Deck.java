import java.util.ArrayList;

import java.util.Random;

public class Deck {

	//instance variables
	private ArrayList<Card> cards;
	
	//construct
	public Deck(){
		this.cards = new ArrayList<Card>();
	}
	
	public void createFullDeck(){
		//Generate Cards
		for(Suit cardSuit : Suit.values()){
			//making 52 cards via both suit and value enum
			for(Value cardValue : Value.values()){
				//add a new card to the mix
				this.cards.add(new Card(cardSuit, cardValue));
			}
		}
	}
	public void shuffle(){
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		//use random
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = this.cards.size();
		for(int i=0;i<originalSize;i++){
			//generate random indexrand.nextInt((max-min)+1)+min;
			randomCardIndex = random.nextInt((this.cards.size()-1-0)+1)+0;
			//put old cards into new deck
			tempDeck.add(this.cards.get(randomCardIndex));
			//remove from original index
			this.cards.remove(randomCardIndex);
		}
		
		this.cards=tempDeck;
	}
	
	public String toString(){
		String cardListOutput = "";
		for(Card aCard : this.cards){
			cardListOutput+="\n"+ aCard.toString();
		}
		return cardListOutput;
	}
	public void removeCard(int i){
		this.cards.remove(i);
	}
	public Card getCard(int i){
		return this.cards.get(i);
	}
	public void addCard(Card addCard){
		this.cards.add(addCard);
	}
	//draws from the deck
	public void draw(Deck comingFrom){
		this.cards.add(comingFrom.getCard(0));
		comingFrom.removeCard(0);
	}
	public int deckSize(){
		return this.cards.size();
	}
	public void moveAllToDeck(Deck moveTo){
		int thisDeckSize = this.cards.size();
		//put cards into moveTo deck
		for(int i=0;i<thisDeckSize;i++){
			moveTo.addCard(this.getCard(i));
		}
		for(int i=0; i<thisDeckSize;i++){
			this.removeCard(0);
		}
	}
	//return value of cards in deck
	public int cardsValue(){
		int totalValue = 0;
		int aces=0;
		for(Card aCard:this.cards){
			switch(aCard.getValue()){
			case TWO: totalValue+=2; break;
			case THREE: totalValue+=3; break;
			case FOUR: totalValue+=4; break;
			case FIVE: totalValue+=5; break;
			case SIX: totalValue+=6; break;
			case SEVEN: totalValue+=7; break;
			case EIGHT: totalValue+=8; break;
			case NINE: totalValue+=9; break;
			case TEN: totalValue+=10; break;
			case JACK: totalValue+=10; break;
			case QUEEN: totalValue+=10; break;
			case KING: totalValue+=10; break;
			case ACE: aces+=1; break;
			}
			//aces can't change value when you draw more cards
		for(int i=0;i<aces;i++){
			if(totalValue>10){
				totalValue += 1;
			}else{
				totalValue += 11;
			}
		}
		}
		return totalValue;
	}
	
	
}
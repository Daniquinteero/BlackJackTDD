package blackjack;

import static blackjack.Card.randomCard;
import java.util.Arrays;
import java.util.Random;

public class Deck {
    
    private Card[] cards;
    
    public Deck(){
        cards = new Card[new Random().nextInt(3,6)];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = randomCard();
        }
    }
    
    public Card[] getCards(){
        return cards;
    }
    
    public Card takeCard(){
        if (cards.length == 0) return null;
        Card card = cards[cards.length-1];
        cards = Arrays.copyOf(cards, cards.length-1);
        return card;
    }
    
    @Override
    public String toString(){
        String deck = "";
        for (Card card : cards) {
            deck+=card.toString()+", ";
        }
        return deck.substring(0, deck.length()-2);
    }
}

package blackjack;

import java.util.Arrays;

public class Hand {
    private Card[] cards;

    public Hand(Card... cards) {
        this.cards = cards;
    }
    
    public int value() {
        int sum = 0;
        int AceCount = 0;
        for (Card card : cards) {
            if(card.isAce()) AceCount+=1;
            else sum+=card.value();
        }

        while (AceCount > 0){
            if(sum < 11) {
                sum+=11;
            } else {
                sum+=1;
            }
            AceCount-=1;
        }


        return sum;
    }

            
    public boolean isBlackJack() {
        return this.value() == 21 & cards.length == 2;
    }

    public boolean isBusted() {
        return this.value() > 21;
    }
    
    public void addCard(Card card){
        cards = Arrays.copyOf(cards, cards.length+1);
        cards[cards.length-1] = card;
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

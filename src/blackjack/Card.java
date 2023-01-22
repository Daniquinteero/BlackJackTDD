package blackjack;

import java.util.Random;

public enum Card {
        Ace, _2, _3, _4, _5, _6, _7, _8, _9, _10, Jack, Queen, King;
        
        private static final Random PRNG = new Random();
        
        private boolean isFace(){
            return this == Jack || this == Queen || this == King;
        }
        
        public int value(){
            return isFace() ? 10 : ordinal() + 1;
        }

        public boolean isAce() {
            return this == Ace;
        }
        

    public static Card randomCard()  {
        Card[] cards = values();
        return cards[PRNG.nextInt(cards.length)];
    }
}

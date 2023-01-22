package blackjack;

import static blackjack.Card.*;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Player1", new Hand(_4,_3, _3));
        Player player2 = new Player("Player2", new Hand(Ace,_10));
        Player player3 = new Player("Player3", new Hand(_2,_3, _4, _2, _9));
        Croupier croupier = new Croupier(new Hand(King, _9));
        
        // decks generated randomly every time.
        Deck deck = new Deck();
        
        
        BlackJack blackjackGame = new BlackJack(deck, croupier, player1, player2, player3);
        
        blackjackGame.addCard("Player1");
        
        System.out.println(blackjackGame.getWinners());
    }
    
    
}

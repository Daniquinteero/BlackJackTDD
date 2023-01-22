package blackjack;

import java.util.Arrays;

public class BlackJack {
    
    private Player[] winners = new Player[0];
    private Player[] players;
    private Croupier croupier;
    private Deck deck;

    public BlackJack(Deck deck, Croupier croupier, Player... players) {
        this.players = players;
        this.croupier = croupier;
        this.deck = deck;
    }
    
            
    public String getWinners() {
        for (Player player : players) {
            if ((player.hand().value() > croupier.hand().value() & ! player.hand().isBusted()) || (player.hand().isBlackJack() & ! croupier.hand().isBlackJack()) || ( ! player.hand().isBusted() & croupier.hand().isBusted())){
                winners = Arrays.copyOf(winners, winners.length+1);
                winners[winners.length-1] = player;
            }

        }
        return this.toString();
    }
    
    public Player getUser(String name){
        for (Player player : players) {
            if (player.getName().equals(name)) return player;
        }
        return null;
    }
    
    
    @Override
    public String toString(){
        String winnersToString = "[";
        if (winners.length == 0) return "[]";
        for (Player player : winners) {
            winnersToString += player.getName() + ",";
        }
        return winnersToString.substring(0, winnersToString.length()-1) + "]";
    }

    public void addCard(String u) {
        User user = getUser(u);
        Card card = deck.takeCard();
        if ( user != null & card != null) {
            user.addCard(card);
        }
    }
    
    public Deck getDeck(){
        return deck;
    }
}

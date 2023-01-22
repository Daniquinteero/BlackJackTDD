package blackjack;

public class Croupier implements User{
    private Hand hand;

    public Croupier(Hand hand) {
        this.hand = hand;
    }
    
    public Hand hand() {
        return hand;
    }

    public String getName() {
        return "croupier";
    }

    @Override
    public void addCard(Card card) {
        this.hand.addCard(card);
    }
}

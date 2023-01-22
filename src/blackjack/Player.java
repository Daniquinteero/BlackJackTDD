package blackjack;

public class Player implements User{
    private Hand hand;
    private String name;

    public Player(String name, Hand hand) {
        this.hand = hand;
        this.name = name;
    }
    
    public Hand hand() {
        return hand;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public void addCard(Card card){
        this.hand.addCard(card);
    }
    

}

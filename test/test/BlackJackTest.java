package test;

import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static test.BlackJackTest.Card.*;

public class BlackJackTest {
    @Test
    public void test_value_of_individual_Cards() {
        
        assertEquals(1, Ace.value());
        assertEquals(2, _2.value());
        assertEquals(3, _3.value());
        assertEquals(10, _10.value());
        assertEquals(10, Jack.value());
        assertEquals(10, Queen.value());
        assertEquals(10, King.value());
    }
    
    @Test
    public void test_value_of_hand_with_2_Cards(){
        assertEquals(13, createHand(Ace, _2).value());
        assertEquals(21, createHand(_10, Ace).value());
        assertEquals(21, createHand(Queen, Ace).value());
        assertEquals(9, createHand(_5, _4).value());
        assertEquals(16, createHand(_5, Ace).value());
    }
    
    @Test
    public void test_if_value_is_busted_for_2_cards(){
        assertEquals(false, createHand(Ace, Queen).isBusted());
        assertEquals(false, createHand(_9, _8).isBusted());

    }
    
    @Test
    public void test_if_value_is_black_jack_for_2_cards(){
        assertEquals(false, createHand(Ace, _2).isBlackJack());
        assertEquals(true, createHand(Ace, Queen).isBlackJack());
        assertEquals(false, createHand(_5, _3).isBlackJack());
        assertEquals(false, createHand(Ace, Ace).isBlackJack());
        assertEquals(true, createHand(Ace, King).isBlackJack());
        assertEquals(false, createHand(Ace, King, King).isBlackJack());
    }
    
    @Test
    public void test_value_of_hand_with_3_Cards(){
        assertEquals(13, createHand(Ace, Ace, Ace).value());
        assertEquals(16, createHand(_5, Queen, Ace).value());
        assertEquals(21, createHand(Queen, Queen, Ace).value());
        assertEquals(19, createHand(Ace, _9, _9).value());
        
    }
    
    @Test
    public void test_if_value_is_busted_for_3_cards(){
        assertEquals(false, createHand(Ace, _9, Queen).isBusted());
        assertEquals(false, createHand(Ace, Ace, Ace).isBusted());
        assertEquals(true, createHand(_9, _9, _4).isBusted());
        assertEquals(false, createHand(_10, _10, Ace).isBusted());
        assertEquals(true, createHand(_9, _9, _6).isBusted());
    }
    
    public void test_if_value_is_black_jack_for_3_Cards(){
        assertEquals(false, createHand(_10, Queen, Ace).isBlackJack());
        assertEquals(false, createHand(_8, _7, _2).isBlackJack());
    }
    
    
    @Test
    public void test_value_of_hand_with_4_Cards(){
        assertEquals(24, createHand(_4, _5, Queen, _5).value());
        assertEquals(19, createHand(_4, _9, Ace, _5).value());
        assertEquals(20, createHand(_2, _3, Queen, _5).value());
    }
    
    @Test
    public void test_if_value_is_busted_for_4_Cards(){
        assertEquals(true, createHand(_2, Queen, _8, _2).isBusted());
    }
    
    @Test
    public void test_value_of_hand_with_5_Cards(){
        assertEquals(18, createHand(_2, _3, Ace, Ace, Ace).value());
    }
    
    @Test
    public void test_hand_value_of_player(){
        assertEquals(21, createPlayer("Player1" ,createHand(Ace, Queen)).hand().value());
    }
    
    @Test
    public void test_if_player_is_busted(){
        assertEquals(true, createPlayer("Player1" ,createHand(_10, Queen, _2)).hand().isBusted());
        assertEquals(false, createPlayer("Player1" ,createHand(_3, _3, _2, _5, _4)).hand().isBusted());
    }
    
    @Test
    public void test_if_player_is_black_jack(){
        
        assertEquals(true, createPlayer("Player1" ,createHand(Ace, Queen)).hand().isBlackJack());
        assertEquals(false, createPlayer("Player1" ,createHand(_9, Queen)).hand().isBlackJack());
    }
    
    @Test
    public void test_if_1_player_loses_against_higher_value_croupier(){
        assertEquals("[]", createBlackJack(createCroupier(createHand(Ace,Queen)), createPlayer("Player1" ,createHand(_10, _7))).getWinners());
    }
    
    @Test
    public void test_if_1_player_with_higher_value_winns_against_croupier(){
        assertEquals("[Player1]", createBlackJack(createCroupier(createHand(_7,_10)), createPlayer("Player1" ,createHand(_10, _8))).getWinners());
    }
    
    @Test
    public void test_if_1_busted_player_loses_against_croupier(){
        assertEquals("[]", createBlackJack(createCroupier(createHand(_7,_10)), createPlayer("Player1" ,createHand(_10, _8, _9))).getWinners());
    }
    
    @Test
    public void test_if_1_player_wins_against_busted_croupier(){
        assertEquals("[Player1]", createBlackJack(createCroupier(createHand(_6,_10, Queen)), createPlayer("Player1" ,createHand(_10, _9))).getWinners());
    }
    
    @Test
    public void test_if_1_player_matches_croupier_value(){
        assertEquals("[]", createBlackJack(createCroupier(createHand(_7,Queen)), createPlayer("Player1" ,createHand(_7, King))).getWinners());
    }
    
    @Test
    public void test_if_1_player_loses_with_21_against_croupier_with_black_jack(){
        assertEquals("[]", createBlackJack(createCroupier(createHand(Ace,Queen)), createPlayer("Player1" ,createHand(Jack, King, Ace))).getWinners());
    }
    
    @Test
    public void test_if_1_player_wins_with_black_jack_against_croupier_with_less_value(){
        assertEquals("[Player1]", createBlackJack(createCroupier(createHand(_8,Queen)), createPlayer("Player1" ,createHand(Jack, Ace))).getWinners());
    }
    
    @Test
    public void test_if_2_players_win_with_black_jack_against_croupier_with_less_value(){
        assertEquals("[Player1,Player2]", createBlackJack(createCroupier(createHand(_8,Queen)), createPlayer("Player1" ,createHand(Jack, Ace)), createPlayer("Player2" ,createHand(Jack, Ace))).getWinners());
    }
    
    @Test
    public void test_for_2_players_1_wins_other_one_gets_busted(){
        assertEquals("[Player1]", createBlackJack(createCroupier(createHand(_8,Queen)), createPlayer("Player1" ,createHand(Jack, Ace)), createPlayer("Player2" ,createHand(_9, _9,_4))).getWinners());
    }
    
    @Test
    public void test_for_3_players_croupier_and_1_player_gets_busted_the_rest_win(){
        assertEquals("[Player1,Player3]", createBlackJack(createCroupier(createHand(_6,Queen, King)), createPlayer("Player1" ,createHand(Jack, Ace)), createPlayer("Player2" ,createHand(_9, _9,_4)), createPlayer("Player3",createHand(_9,_3))).getWinners());
    }
    
    
    
    public interface BlackJack {
        String getWinners();

    }
    
    public BlackJack createBlackJack(Croupier croupier, Player... players){
        return new BlackJack(){
            
            Player[] winners = new Player[0];
            
            @Override
            public String getWinners() {
                for (Player player : players) {
                    if ((player.hand().value() > croupier.hand().value() & ! player.hand().isBusted()) || (player.hand().isBlackJack() & ! croupier.hand().isBlackJack()) || ( ! player.hand().isBusted() & croupier.hand().isBusted())){
                        winners = Arrays.copyOf(winners, winners.length+1);
                        winners[winners.length-1] = player;
                    }
                    
                }
                return this.toString();
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
            
        };
    }
    
    public interface User {
        Hand hand();
        String getName();
    }
    
    public interface Player extends User {
        @Override
        Hand hand();
        @Override
        String getName();
    }
    
    public interface Croupier extends User {
        @Override
        Hand hand();
        @Override
        String getName();
    }
    
    public Player createPlayer(String name, Hand hand){
        return new Player(){
            @Override
            public Hand hand() {
                return hand;
            }

            @Override
            public String getName() {
                return name;
            }
            
            
        };
    }
    
    public Croupier createCroupier(Hand hand){
        return new Croupier(){
            @Override
            public Hand hand() {
                return hand;
            }

            @Override
            public String getName() {
                return "Croupier";
            }
        };
    }
    
    
    public interface Hand {
        int value();
        boolean isBlackJack();
        boolean isBusted();
    }
    
    public Hand createHand(Card... cards){
        return new Hand() {
            @Override
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

            @Override
            public boolean isBlackJack() {
                return this.value() == 21 & cards.length == 2;
            }

            @Override
            public boolean isBusted() {
                return this.value() > 21;
            }
            
        };
    }
    
    public enum Card {
        Ace, _2, _3, _4, _5, _6, _7, _8, _9, _10, Jack, Queen, King;
        
        private boolean isFace(){
            return this == Jack || this == Queen || this == King;
        }
        
        public int value(){
            return isFace() ? 10 : ordinal() + 1;
        }

        private boolean isAce() {
            return this == Ace;
        }
    }
}

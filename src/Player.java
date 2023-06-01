import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Player {

    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(List<Card> drawPile, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            if (drawPile.isEmpty())
                break;

            int index = random.nextInt(drawPile.size());
            Card card = drawPile.remove(index);
            hand.add(card);
        }
    }

    public void playCard(Card card, List<Card> discardPile) {
        hand.remove(card);
        discardPile.add(card);
    }

    public boolean hasPlayableCard(Card topCard) {
        for (Card card : hand) {
            if (card.getRank().equals(topCard.getRank()) || card.getSuit().equals(topCard.getSuit()))
                return true;
        }
        return false;
    }

}

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

}

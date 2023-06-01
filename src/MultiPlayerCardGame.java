
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MultiPlayerCardGame {

    private static final int INITIAL_HAND_SIZE = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Card> deck = createDeck();
        List<Card> discardPile = new ArrayList<>();
        List<Player> players = createPlayers(scanner);

        dealInitialCards(deck, players);

        Card topCard = drawTopCard(deck, discardPile);
        System.out.println("Top card: " + topCard);

        int currentPlayerIndex = 0;
        boolean gameEnd = false;

        while (!gameEnd) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + currentPlayer.getName() + "'s turn");

            if (!currentPlayer.hasPlayableCard(topCard)) {
                System.out.println("No playable cards. Drawing a card...");
                currentPlayer.drawCard(deck, 1);
                if (!currentPlayer.hasPlayableCard(topCard)) {
                    System.out.println("Drawn card: " + currentPlayer.getHand().get(currentPlayer.getHand().size() - 1));
                    System.out.println("No playable cards. Skipping turn...");
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    continue;
                }
            }

            displayPlayerHand(currentPlayer);

            int cardIndex = getCardIndexToPlay(scanner, currentPlayer.getHand().size());
            Card cardToPlay = currentPlayer.getHand().get(cardIndex);
            if (isActionCard(cardToPlay)) {
                handleActionCard(cardToPlay, players, currentPlayerIndex);
            }

            currentPlayer.playCard(cardToPlay, discardPile);
            topCard = cardToPlay;

            if (currentPlayer.getHand().isEmpty()) {
                gameEnd = true;
                System.out.println("\n" + currentPlayer.getName() + " wins!");
            } else {
                currentPlayerIndex = getNextPlayerIndex(currentPlayerIndex, players.size(), cardToPlay);
            }
        }

        scanner.close();
    }


}

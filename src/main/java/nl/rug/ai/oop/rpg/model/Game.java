package nl.rug.ai.oop.rpg.model;

import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.Beginning;
import nl.rug.ai.oop.rpg.view.Main;

import javax.swing.*;

/**
 * This class represents the main game logic for the RPG.
 * It controls the game flow, starting the game and transitioning between the beginning and main parts of the game.
 *
 * @author Alexander Müller
 */
public class Game {

    private Player player;

    /**
     * The main method that starts the game.
     *
     * @param args command line arguments, not used in this application
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    /**
     * Starts the game by showing the beginning part.
     * When the beginning part is done, it continues to the main part of the game.
     */
    public void start() {
        // Start with the Beginning view
        Beginning beginning = new Beginning(new Runnable() {
            @Override
            public void run() {
                // This code will be run when the Beginning part is done
                continueGame();
            }
        });
        beginning.show();
    }

    /**
     * Continues the game after the beginning part is done.
     * It retrieves the player instance and starts the main part of the game.
     */
    private void continueGame() {
        // After the beginning part is done, we should have a player instance now
        this.player = Player.getInstance();

        // Now you can proceed to the Main part
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().start(); // Call the main method of your Main class
            }
        });
    }
}

package nl.rug.ai.oop.rpg.model;

import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.Beginning;
import nl.rug.ai.oop.rpg.view.Main;

import javax.swing.*;

public class Game {

    private Player player;

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

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

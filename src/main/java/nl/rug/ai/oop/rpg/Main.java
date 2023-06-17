package nl.rug.ai.oop.rpg;

import nl.rug.ai.oop.rpg.model.Game;

/**
 * The Main class of the RPG game. This is the entry point of the application.
 * It creates a new Game instance and starts the game.
 *
 * @author Alexander Müller & Robert Hielkema & Victoria Polaka & Kyriakos Hjikakou
 */
public class Main {
    /**
     * The main method that is executed when the application starts.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start(game);
    }
}

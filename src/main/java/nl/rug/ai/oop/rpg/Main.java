package nl.rug.ai.oop.rpg;

import nl.rug.ai.oop.rpg.controller.GameController;
import nl.rug.ai.oop.rpg.model.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start(game);
    }

}

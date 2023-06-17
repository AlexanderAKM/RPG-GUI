package nl.rug.ai.oop.rpg.model;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.RoomStateManager;
import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.npc.NpcManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.Beginning;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

/**
 * This class represents the main game logic for the RPG.
 * It controls the game flow, starting the game and transitioning between the beginning and main parts of the game.
 *
 * @author Alexander Müller
 */
public class Game {
    private Player player;

    /**
     * Starts the game by showing the beginning part.
     * When the beginning part is done, it continues to the main part of the game.
     */
    public void start(Game game) {
        // Start with the Beginning view
        Beginning beginning = new Beginning(game);
        //RoomLanguageManager.loadLanguage("English");

        beginning.show();
    }

    public void initialise(){

    }


    /**
     * Continues the game after the beginning part is done.
     * It retrieves the player instance and starts the main part of the game.
     */
    private void continueGame() {
        // After the beginning part is done, we should have a player instance now
        this.player = Player.getInstance();
    }
}

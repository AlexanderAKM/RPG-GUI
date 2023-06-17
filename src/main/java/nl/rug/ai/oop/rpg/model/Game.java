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
     * Saves the current game state, including room state, player state, and game panel state (description).
     *
     * @param locManager the LocationManager object managing the game's locations
     * @param gamePanelGUI the GamePanelGUI object representing the game panel GUI
     * @author Victoria Polaka
     */
    public void saveGame(LocationManager locManager, GamePanelGUI gamePanelGUI ) {
        RoomStateManager.saveRoomState(locManager, "room_state.ser");
        Player.getInstance().save("player");
        gamePanelGUI.saveGamePanelState("gamePanelState");
    }

    /**
     * Loads a previously saved game state, including player state, room state, and game panel state.
     *
     * @param locManager the LocationManager object managing the game's locations
     * @param gamePanelGUI the GamePanelGUI object representing the game panel GUI
     * @author Victoria Polaka
     */
    public void loadSavedGame(LocationManager locManager, GamePanelGUI gamePanelGUI){
        Player.getInstance().loadSaveFile("player");
        locManager = RoomStateManager.loadRoomState("room_state.ser");
        gamePanelGUI.loadGamePanelState("gamePanelState");

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

package nl.rug.ai.oop.rpg.model;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.RoomStateManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

/**
 * The GameManager class is responsible for managing the state of the game.
 * It provides methods to save and load the game state.
 *
 * @author Victoria Polaka
 * @author Alexander Müller & Robert Hielkema (only restructuring and Javadocs)
 */
public class GameManager {
    /**
     * Saves the current game state, including room state, player state, and game panel state (description).
     *
     * @param locManager the LocationManager object managing the game's locations
     * @param gamePanelGUI the GamePanelGUI object representing the game panel GUI
     *
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
     *
     */
    public void loadSavedGame(LocationManager locManager, GamePanelGUI gamePanelGUI){
        Player.getInstance().loadSaveFile("player");
        locManager = RoomStateManager.loadRoomState("room_state.ser");
        gamePanelGUI.loadGamePanelState("gamePanelState");
    }
}

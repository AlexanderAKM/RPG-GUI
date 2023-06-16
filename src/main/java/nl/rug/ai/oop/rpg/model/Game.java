package nl.rug.ai.oop.rpg.model;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.RoomStateManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.Beginning;

/**
 * This class represents the main game logic for the RPG.
 * It controls the game flow, starting the game and transitioning between the beginning and main parts of the game.
 *
 * @author Alexander Müller
 */
public class Game {

    private Player player;
    public Game(){

    }


    /**
     * The main method that starts the game.
     *
     * @param args command line arguments, not used in this application
     */


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

    public void saveGame(LocationManager locManager) {
        RoomStateManager.saveRoomState(locManager, "room_state.ser");
        Player.getInstance().save("player");
    }

    public void loadSavedGame(LocationManager locManager){
        Player.getInstance().loadSaveFile("player");
        locManager = RoomStateManager.loadRoomState("room_state.ser");
    }

    /**
     * Continues the game after the beginning part is done.
     * It retrieves the player instance and starts the main part of the game.
     */
    private void continueGame() {
        // After the beginning part is done, we should have a player instance now
        this.player = Player.getInstance();


        // Now you can proceed to the Main part
        /* SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().start(); // Call the main method of your Main class
            }
        });*/
    }
}

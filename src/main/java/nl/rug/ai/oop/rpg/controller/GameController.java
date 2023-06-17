package nl.rug.ai.oop.rpg.controller;

import nl.rug.ai.oop.rpg.model.Game;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GameController class is responsible for handling user actions in the game, such as saving and loading the game.
 *
 * @author Victoria Polaka
 */
public class GameController implements ActionListener {
    private Game model;
    private LocationManager locManager;
    private GamePanelGUI gamePanelGUI;

    /**
     *
     * Constructs a new GameController instance.
     * @param model           the Game object representing the game
     * @param locManager      the LocationManager object managing the game's locations
     * @param gamePanelGUI    the GamePanelGUI object representing the game panel GUI
     */
    public GameController(Game model, LocationManager locManager, GamePanelGUI gamePanelGUI) {
        this.model = model;
        this.locManager = locManager;
        this.gamePanelGUI = gamePanelGUI;
    }

    /**
     * Handles the action performed by the user.
     *
     * @param e the ActionEvent object representing the user action
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                System.out.println("game saved!");
                model.saveGame(locManager, gamePanelGUI);
                break;
            case "load":
                System.out.println("game loaded!");
                model.loadSavedGame(locManager, gamePanelGUI);
                break;

        }
    }
}

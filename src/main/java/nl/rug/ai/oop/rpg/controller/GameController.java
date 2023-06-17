package nl.rug.ai.oop.rpg.controller;

import nl.rug.ai.oop.rpg.model.GameManager;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GameController class is responsible for handling user actions in the game,
 * such as saving and loading the game. It delegates these actions to the GameManager.
 * This class implements the ActionListener interface to respond to user actions.
 * @author Victoria Polaka
 * @author Alexander Müller & Robert Hielkema (only restructuring & Javadocs)
 */
public class GameController implements ActionListener {
    private GameManager gameManager;
    private LocationManager locManager;
    private GamePanelGUI gamePanelGUI;

    /**
     * Constructs a new GameController instance.
     *
     * @param gameManager the GameManager object responsible for managing the game state
     * @param locManager the LocationManager object managing the game's locations
     * @param gamePanelGUI the GamePanelGUI object representing the game panel GUI
     */
    public GameController(GameManager gameManager, LocationManager locManager, GamePanelGUI gamePanelGUI) {
        this.gameManager = gameManager;
        this.locManager = locManager;
        this.gamePanelGUI = gamePanelGUI;
    }

    /**
     * Handles the action performed by the user.
     * If the user action is to save the game, it delegates the action to the GameManager's saveGame method.
     * If the user action is to load the game, it delegates the action to the GameManager's loadSavedGame method.
     *
     * @param e the ActionEvent object representing the user action
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                System.out.println("game saved!");
                gameManager.saveGame(locManager, gamePanelGUI);
                break;
            case "load":
                System.out.println("game loaded!");
                gameManager.loadSavedGame(locManager, gamePanelGUI);
                break;
        }
    }
}


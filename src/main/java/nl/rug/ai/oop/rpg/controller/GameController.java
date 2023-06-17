package nl.rug.ai.oop.rpg.controller;

import nl.rug.ai.oop.rpg.model.GameManager;
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
    private GameManager gameManager;
    private LocationManager locManager;
    private GamePanelGUI gamePanelGUI;

    public GameController(GameManager gameManager, LocationManager locManager, GamePanelGUI gamePanelGUI) {
        this.gameManager = gameManager;
        this.locManager = locManager;
        this.gamePanelGUI = gamePanelGUI;
    }

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

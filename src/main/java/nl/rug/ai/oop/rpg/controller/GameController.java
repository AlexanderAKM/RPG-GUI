package nl.rug.ai.oop.rpg.controller;

import nl.rug.ai.oop.rpg.model.Game;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController implements ActionListener {
        private Game model;
        private LocationManager locManager;
        private GamePanelGUI gamePanelGUI;

        public GameController(Game model, LocationManager locManager, GamePanelGUI gamePanelGUI) {
            this.model = model;
            this.locManager = locManager;
            this.gamePanelGUI = gamePanelGUI;
        }

        /**
         * Handles the actionPerformed event.
         * Determines what the action command is and triggers the corresponding location change in the model.
         *
         * @param e The ActionEvent object representing the user's action.
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

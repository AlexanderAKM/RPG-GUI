package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.GameController;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;

import javax.swing.*;

/**
 * A panel for saving and loading the game.
 *
 * @author Victoria Polaka
 */
public class SavePanel extends JPanel {
    private JButton saveGameButton;
    private JButton loadGameButton;

    /**
     * Constructs a SavePanel object.
     * @param controller the GameController object for handling save and load actions
     */
    public SavePanel(GameController controller, LanguageManager languageManager){
        saveGameButton = new JButton(languageManager.getTranslation("save_game"));
        saveGameButton.addActionListener(controller);
        saveGameButton.setActionCommand("save");
        loadGameButton = new JButton(languageManager.getTranslation("load_game"));
        loadGameButton.addActionListener(controller);
        loadGameButton.setActionCommand("load");
        add(saveGameButton);
        add(loadGameButton);
    }
}

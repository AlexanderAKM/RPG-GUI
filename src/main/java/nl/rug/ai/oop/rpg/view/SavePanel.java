package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.GameController;

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
    public SavePanel(GameController controller){
        saveGameButton = new JButton("Save Game");
        saveGameButton.addActionListener(controller);
        saveGameButton.setActionCommand("save");
        loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(controller);
        loadGameButton.setActionCommand("load");
        add(saveGameButton);
        add(loadGameButton);
    }
}

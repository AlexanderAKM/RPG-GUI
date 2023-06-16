package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.GameController;
import nl.rug.ai.oop.rpg.model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavePanel extends JPanel {
    //JPanel savePanel;
    JButton saveGameButton;
    JButton loadGameButton;

    public SavePanel(GameController controller){
        //savePanel = new JPanel();

        saveGameButton = new JButton("Save Game");
        saveGameButton.addActionListener(controller);
        saveGameButton.setActionCommand("save");
        loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(controller);
        loadGameButton.setActionCommand("load");
        //savePanel.add(saveGameButton);
        //savePanel.setVisible(true);
        add(saveGameButton);
        add(loadGameButton);
    }



}

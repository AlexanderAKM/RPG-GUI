package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    //Main class for the JFrame which should include everyone's panes
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(new GamePanelGUI(), BorderLayout.CENTER); // adds the game panel
        frame.setVisible(true);
    }
}

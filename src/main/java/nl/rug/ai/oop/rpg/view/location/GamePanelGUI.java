package nl.rug.ai.oop.rpg.view.location;

import javax.swing.*;

public class GamePanelGUI extends JFrame {
    private JFrame gamePanel;
    JButton searchItemButton;
    JButton interactNpcButton;
    JButton moveRoomsButton;

    JPanel roomItemsPanel;
    JPanel roomNpcsPanel;
    JPanel roomsPanel;

    JLabel textLabel;


    public GamePanelGUI(){
        JPanel gamePanel = new JPanel();
        JPanel roomItemsPanel = new JPanel();
        JPanel roomNpcsPanel = new JPanel();
        JPanel roomsPanel = new JPanel();

        JButton searchItemButton = new JButton("Search for items in the room");
        JButton interactNpcButton = new JButton("Interact with NPCs in the room");
        JButton moveRoomsButton = new JButton("Move to a different room");

        JLabel textLabel = new JLabel("Game text goes here");

    }

}

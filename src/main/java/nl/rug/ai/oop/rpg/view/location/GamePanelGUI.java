package nl.rug.ai.oop.rpg.view.location;

import javax.swing.*;
import java.awt.*;

public class GamePanelGUI extends JFrame {
    private JFrame gamePanel;
    private JLabel textLabel;

    private JButton searchItemButton;
    private JPanel roomItemsPanel;

    private JButton interactNpcButton;
    private JPanel roomNpcsPanel;

    private JButton moveRoomsButton;
    private JPanel roomsPanel;
    private JButton north;
    private JButton east;
    private JButton west;
    private JButton south;


    public GamePanelGUI(){
        JPanel gamePanel = new JPanel();
        JPanel roomItemsPanel = new JPanel();
        JPanel roomNpcsPanel = new JPanel();
        JPanel roomsPanel = new JPanel();

        JButton searchItemButton = new JButton("Search for items in the room");
        //searchItemButton.setActionCommand("items");
        JButton interactNpcButton = new JButton("Interact with NPCs in the room");
        //interactNpcButton.setActionCommand("npcs");
        JButton moveRoomsButton = new JButton("Move to a different room");
        //moveRoomsButton.setActionCommand("rooms");

        JButton north = new JButton();
        north.setActionCommand("n");
        JButton east = new JButton();
        east.setActionCommand("e");
        JButton south = new JButton();
        south.setActionCommand("s");
        JButton west = new JButton();
        west.setActionCommand("w");



        JLabel textLabel = new JLabel("Game text goes here");

        gamePanel.add(textLabel);
        gamePanel.add(searchItemButton);
        gamePanel.add(interactNpcButton);
        gamePanel.add(moveRoomsButton);
        gamePanel.setVisible(true);

        roomsPanel.add(north);
        roomsPanel.add(east);
        roomsPanel.add(south);
        roomsPanel.add(west);

        //model.addListener(evt -> {if (Objects.equals(evt.getPropertyName(), "pickSuit")) {
        //    pickingSuit();
        //}
        //});

    }

    /*
    public void updateOptions(LocationManager manager, LocationController controller) {
        System.out.println("updating options");

        for (Card card :model.getPlayersHand()){
            playerHand.add(hand.addCardButton(card));
        }

        for (InteractiveCard playCard : playerHand) {
            playCard.addActionListener(controller);
            playCard.setActionCommand("chosenCard");
        }
    }
    public JButton addCardButton() {;
        JButton newButton = new JButton(); //
        add(newButton);
        revalidate();
        return newButton;
    }

     */

}

package nl.rug.ai.oop.rpg.view.location;

import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GamePanelGUI {
    //private JFrame frame;
    private JPanel panel;
    private JPanel gamePanel;
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

    private NpcView npcView;


    public GamePanelGUI(LocationManager manager, LocationController controller){
        panel = new JPanel();
        gamePanel = new JPanel(new GridLayout(10, 1, 10, 5));
        gamePanel.setBackground(Color.LIGHT_GRAY);
        roomItemsPanel = new JPanel();
        roomNpcsPanel = new JPanel();
        roomsPanel = new JPanel();

        searchItemButton = new JButton("Search for items in the room");
        //searchItemButton.setActionCommand("items");
        interactNpcButton = new JButton("Interact with NPCs in the room");
        interactNpcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRoomNpcPanel();
            }
        });
        //interactNpcButton.setActionCommand("npcs");
        moveRoomsButton = new JButton("Move to a different room");
        moveRoomsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createRoomButtons(manager, controller);
                npcView.updateNpcView(Player.getInstance().getCurrentRoom().getAvailableNpcs());
                showRoomsPanel();
            }
        });

        north = new JButton("North");
        north.addActionListener(controller);
        north.setActionCommand("n");
        east = new JButton("East");
        east.addActionListener(controller);
        east.setActionCommand("e");
        south = new JButton("South");
        south.addActionListener(controller);
        south.setActionCommand("s");
        west = new JButton("West");
        west.addActionListener(controller);
        west.setActionCommand("w");

        //getContentPane().add(gamePanel);

        textLabel = new JLabel("Game text goes here");


        panel.add(gamePanel);
        gamePanel.add(textLabel, BorderLayout.NORTH);
        gamePanel.add(searchItemButton);
        gamePanel.add(interactNpcButton);
        gamePanel.add(moveRoomsButton);
        gamePanel.setVisible(true);

        //roomsPanel.add(north);
        //roomsPanel.add(east);
        //roomsPanel.add(south);
        //roomsPanel.add(west);
        //roomsPanel.setVisible(true);

        manager.addListener(evt -> {if (Objects.equals(evt.getPropertyName(), "direction")) {
            setTextLabel((String)evt.getNewValue());
            showGamePanel();  // returns back to the beginning
        }
        });

    }

    public void setNpcPanel(JPanel npcPanel){
        roomNpcsPanel = npcPanel;
    }

    public JPanel returnLocationView(){
        return panel;
    }

    private void setTextLabel(String newText){
        textLabel.setText("Room Description :" + newText);
        gamePanel.revalidate();
    }

    public void setNpcView(NpcView npcView){
        this.npcView = npcView;
    }

    public void createRoomButtons(LocationManager model, LocationController controller) {
        roomsPanel.removeAll(); // Clear the existing buttons ArrayList<Room>

        for (Room room : model.roomsAvailable(Player.getInstance().getCurrentRoom())) {
            JButton roomButton = new JButton(room.getRoomName());
            roomButton.addActionListener(controller);

            roomButton.setActionCommand(room.getRoomName()); // Set the action command as the room name
            roomsPanel.add(roomButton);
        }

        panel.revalidate();
        panel.repaint();
    }


public void showGamePanel() {
    panel.removeAll();
    panel.add(gamePanel);
    gamePanel.setVisible(true);
    roomsPanel.setVisible(false);
    roomNpcsPanel.setVisible(false);
    panel.revalidate();
    panel.repaint();
}

    public void showRoomNpcPanel() {
        panel.removeAll();
        panel.add(roomNpcsPanel);
        gamePanel.setVisible(false);
        roomNpcsPanel.setVisible(true);
        roomsPanel.setVisible(false);
        panel.revalidate();
        panel.repaint();
    }

    public void showRoomsPanel() {
        panel.removeAll();
        panel.add(roomsPanel);
        gamePanel.setVisible(false);
        roomNpcsPanel.setVisible(false);
        roomsPanel.setVisible(true);
        panel.revalidate();
        panel.repaint();
    }
    public void frameSetUp(){

    }

    //public JFrame getMainFrame(){
    //    return frame;
    //}

    /*public void updateGamePanel(){
     * here it updates the options in the text buttons and also the text label
     * }
     * */
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

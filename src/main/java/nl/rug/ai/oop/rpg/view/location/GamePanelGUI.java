package nl.rug.ai.oop.rpg.view.location;

import nl.rug.ai.oop.rpg.controller.inventory.ItemListener;
import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GamePanelGUI {
    //private JFrame frame;
    private JPanel panel;
    private JPanel gamePanel;
    private JLabel textLabel;

    private JButton searchItemButton;
    private JPanel roomItemsPanel;
    private JButton backButton;

    private JButton interactNpcButton;
    private JPanel roomNpcsPanel;

    private JButton moveRoomsButton;
    private JPanel roomsPanel;

    private NpcView npcView;
    private JLabel mapLabel;

    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public GamePanelGUI(LocationManager manager, LocationController controller){
        panel = new JPanel();
        gamePanel = new JPanel(new GridLayout(10, 1, 10, 5));
        gamePanel.setBackground(Color.LIGHT_GRAY);
        roomItemsPanel = new JPanel();
        roomNpcsPanel = new JPanel();
        roomsPanel = new JPanel();

        searchItemButton = new JButton("Search for items in the room");
        searchItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createItemButtons(manager);
                showRoomItemPanel();

            }
        });
        backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showGamePanel();
            }
        });

        interactNpcButton = new JButton("Interact with NPCs in the room");
        interactNpcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRoomNpcPanel();
            }
        });
        moveRoomsButton = new JButton("Move to a different room");
        moveRoomsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createRoomButtons(manager, controller);
                showRoomsPanel();
            }
        });


        textLabel = new JLabel("Game text goes here");

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/main/resources/map_final.png")); //C:/Coding/OOP-assignment/2023_Project_006/src/main/resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapLabel = new JLabel(new ImageIcon(image));

        panel.add(gamePanel);
        gamePanel.add(textLabel, BorderLayout.NORTH);
        gamePanel.add(searchItemButton);
        gamePanel.add(interactNpcButton);
        gamePanel.add(moveRoomsButton);
        gamePanel.setVisible(true);


        manager.addListener(evt -> {if (Objects.equals(evt.getPropertyName(), "direction")) {
            setTextLabel((String)evt.getNewValue());
            showGamePanel();  // returns back to the default screen
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

        roomsPanel.add(mapLabel);
        panel.revalidate();
        panel.repaint();
    }


    public void showGamePanel() {
        panel.removeAll();
        panel.add(gamePanel);
        gamePanel.setVisible(true);
        roomsPanel.setVisible(false);
        roomNpcsPanel.setVisible(false);
        roomItemsPanel.setVisible(false);
        panel.revalidate();
        panel.repaint();
    }

    public void showRoomItemPanel(){
        panel.removeAll();
        panel.add(roomItemsPanel);
        gamePanel.setVisible(false);
        roomItemsPanel.setVisible(true);
        roomsPanel.setVisible(false);
        roomNpcsPanel.setVisible(false);
        panel.revalidate();
        panel.repaint();
    }

    public void showRoomNpcPanel() {
        panel.removeAll();
        panel.add(roomNpcsPanel);
        gamePanel.setVisible(false);
        npcView.updateNpcView(Player.getInstance().getCurrentRoom().getAvailableNpcs());
        roomNpcsPanel.setVisible(true);
        roomsPanel.setVisible(false);
        roomItemsPanel.setVisible(false);
        panel.revalidate();
        panel.repaint();
    }

    public void showRoomsPanel() {
        panel.removeAll();
        panel.add(roomsPanel);
        gamePanel.setVisible(false);
        roomNpcsPanel.setVisible(false);
        roomsPanel.setVisible(true);
        roomItemsPanel.setVisible(false);
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Creates buttons for each available item in the current room.
     *
     * @param model the LocationManager object representing the game model
     * @author Alexander Müller
     */
    public void createItemButtons(LocationManager model) {
        roomItemsPanel.removeAll();

        for (Item item : model.getAvailableItemsList(Player.getInstance().getCurrentRoom())) {
            JButton itemButton = new JButton(item.getName());
            itemButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (itemListener != null) {
                        itemListener.onItemClicked(item);
                    }
                }
            });
            itemButton.setActionCommand("item");
            roomItemsPanel.add(itemButton);
        }
        
        roomItemsPanel.add(backButton);
        panel.revalidate();
        panel.repaint();
    }


}

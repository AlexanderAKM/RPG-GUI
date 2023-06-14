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

    public GamePanelGUI(LocationManager manager, LocationController controller) {
        panel = new JPanel();
        gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 250), 3)); // Set the light blue border
        //panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();

        roomItemsPanel = new JPanel();
        roomNpcsPanel = new JPanel();
        roomsPanel = new JPanel();

        searchItemButton = new JButton("Search for items in the room");
        searchItemButton.setPreferredSize(new Dimension(230, 30));
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
        interactNpcButton.setPreferredSize(new Dimension(230, 30));
        interactNpcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRoomNpcPanel();
            }
        });

        moveRoomsButton = new JButton("Move to a different room");
        moveRoomsButton.setPreferredSize(new Dimension(230, 30));
        moveRoomsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createRoomButtons(manager, controller);
                showRoomsPanel();
            }
        });

        textLabel = new JLabel("<html>You find yourself standing in the comfort of your Dutch home. The cozy atmosphere embraces you, with the scent of freshly baked stroopwafels wafting through the air. The sun is out for once, casting a warm glow in the room. It's time to embark on your adventure as a University of Groningen Student.</html>");
        textLabel.setPreferredSize(new Dimension(350, 100));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 20, 20, 20); // Add some vertical spacing
        gamePanel.add(textLabel, gbc);

        gbc.gridy = 1;
        gamePanel.add(searchItemButton, gbc);

        gbc.gridy = 2;
        gamePanel.add(interactNpcButton, gbc);

        gbc.gridy = 3;
        gamePanel.add(moveRoomsButton, gbc);

        panel.add(gamePanel);
        gamePanel.setVisible(true);

        manager.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "direction")) {
                setTextLabel((String) evt.getNewValue());
                showGamePanel();
            }
        });

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/main/resources/map_final.png")); //C:/Coding/OOP-assignment/2023_Project_006/src/main/resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapLabel = new JLabel(new ImageIcon(image));

        // Set the button colors
        Color buttonColor = new Color(135, 206, 250); // Blue color
        Color textColor = Color.WHITE; // White text color

        moveRoomsButton.setBackground(buttonColor);
        moveRoomsButton.setForeground(textColor);
        searchItemButton.setBackground(buttonColor);
        searchItemButton.setForeground(textColor);
        interactNpcButton.setBackground(buttonColor);
        interactNpcButton.setForeground(textColor);

    }


    public void setNpcPanel(JPanel npcPanel){
        roomNpcsPanel = npcPanel;
    }

    public JPanel returnLocationView(){
        return panel;
    }

    private void setTextLabel(String newText) {
        textLabel.setText("<html>"+newText +"</hmtl>");
        textLabel.setPreferredSize(new Dimension(350, 100)); // Set the preferred size as per your requirements
        //textLabel.setHorizontalAlignment(SwingConstants.CENTER); // Align the text horizontally in the center
        //textLabel.setVerticalAlignment(SwingConstants.TOP); // Align the text vertically at the top
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
            Color buttonColor = new Color(135, 206, 250); // Blue color
            Color textColor = Color.WHITE; // White text color

            roomButton.setBackground(buttonColor);
            roomButton.setForeground(textColor);

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
            Color buttonColor = new Color(135, 206, 250); // Blue color
            Color textColor = Color.WHITE; // White text color

            itemButton.setBackground(buttonColor);
            itemButton.setForeground(textColor);

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

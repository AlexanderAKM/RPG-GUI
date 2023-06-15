package nl.rug.ai.oop.rpg.view.location;

import nl.rug.ai.oop.rpg.controller.inventory.ItemListener;
import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;
import nl.rug.ai.oop.rpg.model.location.RoomLanguageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents the game panel GUI that displays the game elements, including rooms, items, NPCs, and text labels.
 *
 * @author Victoria Polaka
 */
public class GamePanelGUI {
    private JPanel panel;
    private JPanel gamePanel;
    private JLabel textLabel;

    private JButton searchItemButton;
    private JPanel roomItemsPanel;
    private JButton backButton;

    private JButton interactNpcButton;
    private JPanel roomNpcsPanel;
    private NpcView npcView;

    private JButton moveRoomsButton;
    private JPanel roomsPanel;

    private JLabel mapLabel;
    private ItemListener itemListener;
    private JFrame frame;
    private final RoomLanguageManager roomLanguageManager;

    /**
     * Constructs a new GamePanelGUI instance.
     *
     * @param manager             the LocationManager object representing the game model
     * @param controller          the LocationController object for handling location-related actions
     * @param roomLanguageManager the RoomLanguageManager object for managing room translations
     */
    public GamePanelGUI(LocationManager manager, LocationController controller, RoomLanguageManager roomLanguageManager) {
        this.frame = frame;
        this.roomLanguageManager = roomLanguageManager;
        panel = new JPanel();
        gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 250), 3)); // Set the light blue border
        //panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();

        roomItemsPanel = new JPanel();
        roomNpcsPanel = new JPanel();
        roomsPanel = new JPanel();

        searchItemButton = new JButton(roomLanguageManager.getTranslation("search_item_button"));
        searchItemButton.setPreferredSize(new Dimension(230, 30));
        searchItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createItemButtons(manager);
                showRoomItemPanel();
            }
        });


        interactNpcButton = new JButton(roomLanguageManager.getTranslation("interact_npc_button"));
        interactNpcButton.setPreferredSize(new Dimension(230, 30));
        interactNpcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRoomNpcPanel();
            }
        });

        moveRoomsButton = new JButton(roomLanguageManager.getTranslation("move_room_button"));
        moveRoomsButton.setPreferredSize(new Dimension(230, 30));
        moveRoomsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createRoomButtons(manager, controller);
                showRoomsPanel();
            }
        });

        textLabel = new JLabel("<html>"+ roomLanguageManager.getTranslation("start_text_label")+"</html>");
        textLabel.setPreferredSize(new Dimension(350, 100));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 20, 20, 20);
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

        manager.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "popUp")) {
                showRoomNotAccessiblePopup(frame, roomLanguageManager);
            }
        });

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/main/resources/map_final.png"));
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

    /**
     * Sets the panel for displaying NPC interactions within the room.
     *
     * @param npcPanel the JPanel containing the NPC interactions
     * @author Kikis Hjikakou
     */
    public void setNpcPanel(JPanel npcPanel){
        roomNpcsPanel = npcPanel;
    }

    /**
     * Returns the view panel.
     *
     * @return the JPanel representing the locations view (GamePanelGUI)
     */
    public JPanel returnLocationView(){
        return panel;
    }

    /**
     * Sets the text of the text label in the game panel.
     *
     * @param newText the new text to set
     */
    private void setTextLabel(String newText) {
        textLabel.setText("<html>"+newText +"</hmtl>");
        textLabel.setPreferredSize(new Dimension(350, 100));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.TOP);
        gamePanel.revalidate();
    }

    /**
     * Sets the NPC view for displaying NPC information in the room.
     *
     * @param npcView the NpcView object representing the NPC view
     * @author Kikis Hjikakou
     */
    public void setNpcView(NpcView npcView){
        this.npcView = npcView;
    }

    /**
     * Creates buttons for each available room in the current location.
     *
     * @param model     the LocationManager object representing the location game model
     * @param controller the LocationController object for handling room button actions
     */
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

    /**
     * Displays the game panel in the main panel, hiding other panels.
     * This method shows the main gameplay interface.
     */
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

    /**
     * Displays the room item panel in the main panel, hiding other panels.
     * This method shows the panel where the player can interact with items in the room.
     */
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

    /**
     * Displays the room NPC panel in the main panel, hiding other panels.
     * This method shows the panel where the player can interact with NPCs in the room.
     */
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

    /**
     * Displays the rooms panel in the main panel, hiding other panels.
     * This method shows the panel where the player can navigate between available rooms.
     */
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
     * @author Alexander Müller & Victoria Polaka
     */
    public void createItemButtons(LocationManager model) {
        roomItemsPanel.removeAll();

        for (Item item : model.getAvailableItemsList(Player.getInstance().getCurrentRoom())) {
            JButton itemButton = new JButton(roomLanguageManager.getTranslation(item.getName()));
            Color buttonColor = new Color(135, 206, 250); // Blue color
            Color textColor = Color.WHITE; // White text color

            itemButton.setBackground(buttonColor);
            itemButton.setForeground(textColor);
            itemButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (itemListener != null) {
                        itemListener.onItemClicked(item);
                        showGamePanel();
                    }
                }
            });
            itemButton.setActionCommand("item");
            roomItemsPanel.add(itemButton);
        }
        backButton = new JButton(roomLanguageManager.getTranslation("Go_Back"));
        roomItemsPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGamePanel();
            }
        });
        panel.revalidate();
        panel.repaint();
    }

    /**
     *
     * @param itemListener
     * @author Alexander Müller
     */
    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    /**
     * Displays a popup dialog indicating that the current room is not accessible.
     *
     * @param frame               the JFrame object representing the parent frame
     * @param roomLanguageManager the RoomLanguageManager object for translation purposes
     */
    private void showRoomNotAccessiblePopup(JFrame frame, RoomLanguageManager roomLanguageManager) {
        RoomPopup popup = new RoomPopup(frame, roomLanguageManager.getTranslation("popUp_warning"));
        popup.showDialog();
    }
}

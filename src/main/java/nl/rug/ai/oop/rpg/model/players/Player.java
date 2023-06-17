package nl.rug.ai.oop.rpg.model.players;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.items.*;
import nl.rug.ai.oop.rpg.model.location.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;

/**
 * Represents a Player in the game.
 * The Player class manages the player's attributes, inventory, and game progress.
 * It provides methods for interacting with the player's stats and performing game-related actions.
 * This class follows the Singleton design pattern to ensure only one instance of the Player exists.
 * It also implements the Serializable interface to support saving and loading player data.
 *
 * @author RobertHielkema
 */
public class Player implements Serializable {

    private int intelligence;
    private int social;
    private int wellbeing;
    private int money;
    private Room currentRoom;
    private Inventory inventory;
    private String programme;
    private String language;
    private static Player player;
    private PropertyChangeSupport support;

    /**
     * Constructs a new Player instance with default attribute values.
     * The player's attributes are initialized to 0, and the inventory is created.
     */
    private Player() {
        intelligence = 0;
        social = 0;
        wellbeing = 0;
        money = 0;
        inventory = new Inventory();
        support = new PropertyChangeSupport(this);
    }

    /**
     * Returns the singleton instance of the Player class.
     * If the player instance is null, a new instance is created and assigned to the player variable.
     *
     * @return the singleton instance of the Player
     */
    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    /**
     * Sets the player's programme and updates attributes and inventory accordingly.
     * Depending on the chosen programme, the player's intelligence, social, wellbeing, and inventory are modified.
     *
     * @param programme the programme chosen by the player
     */
    public void chooseProgramme(String programme) {
        player.programme = programme;
        if (programme.equals("AI") || programme.equals("Artificial Intelligence")) {
            player.changeIntelligence(70);
            player.changeSocial(60);
            player.changeWellbeing(100);
            player.changeMoney(0);
            player.inventory.addItem(new Alcohol(5, 10));
        } else if (programme.equals("AP") || programme.equals("Applied Physics")) {
            player.changeIntelligence(85);
            player.changeSocial(40);
            player.changeWellbeing(100);
            player.changeMoney(0);
            player.inventory.addItem(new Coffee(5, 5));
            player.inventory.addItem(new Coffee(5, 5));
        } else if (programme.equals("CS") || programme.equals("Computing Science")) {
            player.changeIntelligence(100);
            player.changeSocial(20);
            player.changeWellbeing(100);
            player.changeMoney(0);
            player.inventory.addItem(new Books(10, 5));
        }
    }

    /**
     * Sets the player's custom programme and updates attributes and inventory accordingly.
     * The player's programme, intelligence and social are modified based on the provided values.
     * The player's other values are set to presets.
     *
     * @param programme    the custom programme name chosen by the player
     * @param intelligence the intelligence value for the custom programme
     * @param social       the social value for the custom programme
     */
    public void customProgramme(String programme, int intelligence, int social) {
        player.programme = programme;
        player.changeIntelligence(intelligence);
        player.changeSocial(social);
        player.changeWellbeing(100);
        player.changeMoney(0);
        player.inventory.addItem(new Alcohol(5, 5));
        player.inventory.addItem(new Coffee(5, 5));
    }

    /**
     * Saves the player's data to a file with the specified filename.
     *
     * @param filename the name of the file to save the player's data to
     */
    public void save(String filename) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            output.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Loads the player's data from the specified save file.
     * The existing player instance is replaced with the deserialized instance from the file.
     * The "playerstat" property change event is fired after updating the player.
     *
     * @param filename the name of the save file to load player data from
     */
    public void loadSaveFile(String filename) {
        player = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
            player = (Player) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        support.firePropertyChange("playerstat", null, null);
    }

    /**
     * Changes the player's intelligence value by the specified amount.
     * If the change results in a negative intelligence value, an ArithmeticException is thrown.
     * The "playerstat" property change event is fired after updating the value to update the value in the view.
     *
     * @param change the amount to change the intelligence value by
     * @throws ArithmeticException if the change results in a negative intelligence value
     */
    public void changeIntelligence(int change) {
        if (-change > this.intelligence) {
            throw new ArithmeticException("Intelligence can't go lower than 0");
        }
        this.intelligence += change;
        support.firePropertyChange("playerstat", null, null);
    }

    /**
     * Changes the player's social value by the specified amount.
     * If the change results in a negative social value, an ArithmeticException is thrown.
     * The "playerstat" property change event is fired after updating the value to update the value in the view.
     *
     * @param change the amount to change the social value by
     * @throws ArithmeticException if the change results in a negative social value
     */
    public void changeSocial(int change) {
        if (-change > this.social) {
            throw new ArithmeticException("Social can't go lower than 0");
        }
        this.social += change;
        support.firePropertyChange("playerstat", null, null);
    }

    /**
     * Changes the player's wellbeing value by the specified amount.
     * If the change results in a negative wellbeing value, a "lowWellbeing" property change event is fired.
     * The "playerstat" property change event is always fired after updating the value to update the value in the view.
     *
     * @param change the amount to change the wellbeing value by
     */
    public void changeWellbeing(int change) {
        if (-change > this.wellbeing) {
            support.firePropertyChange("lowWellbeing", null, null);
        }
        this.wellbeing += change;
        support.firePropertyChange("playerstat", null, null);
    }

    /**
     * Changes the player's money value by the specified amount.
     * If the change results in a negative money value, an ArithmeticException is thrown.
     * The "playerstat" property change event is fired after updating the value to update the value in the view.
     *
     * @param change the amount to change the money value by
     * @throws ArithmeticException if the change results in a negative money value
     */
    public void changeMoney(int change) {
        if (-change > this.money) {
            throw new ArithmeticException("Money can't go lower than 0");
        }
        this.money += change;
        support.firePropertyChange("playerstat", null, null);
    }

    /**
     * @return the player's intelligence value
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * @return the player's social value
     */
    public int getSocial() {
        return social;
    }

    /**
     * @return the player's wellbeing value
     */
    public int getWellbeing() {
        return wellbeing;
    }

    /**
     * @return the player's money value
     */
    public int getMoney() {
        return money;
    }

    /**
     * @return the current room of the player
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @param currentRoom the room to set as the current room for the player
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * @return the player's inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set for the player
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * @return the player's language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set for the player
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @param pcl the PropertyChangeListener to add
     */
    public void addChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}
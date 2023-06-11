package nl.rug.ai.oop.rpg.model.inventory;

import nl.rug.ai.oop.rpg.model.players.Player;

import java.io.Serializable;

/**
 * This abstract class represents a general item in the game.
 * @author Alexander Müller
 */
public abstract class Item implements Serializable {
    private String name;

    /**
     * Constructs a new Item with the given name.
     *
     * @param name the name of the item
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of this item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Abstract method for using the item on a player. To be implemented by subclasses.
     *
     * @param player the player to use the item on
     */
    public abstract void use(Player player);
}

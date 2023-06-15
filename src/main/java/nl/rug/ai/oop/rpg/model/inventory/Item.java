package nl.rug.ai.oop.rpg.model.inventory;

import nl.rug.ai.oop.rpg.model.players.Player;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item otherItem = (Item) obj;
        return Objects.equals(name, otherItem.name);
        // Add more comparisons for other fields as needed
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
        // Hash other fields as needed
    }

}

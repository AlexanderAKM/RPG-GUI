package nl.rug.ai.oop.rpg.model.inventory;

import nl.rug.ai.oop.rpg.model.players.Player;

public abstract class Item {
    private String name;
    // Constructor
    public Item(String name) {
        this.name = name;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    // Common method
    public abstract void use(Player player);

}

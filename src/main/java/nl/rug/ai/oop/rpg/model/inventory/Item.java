package nl.rug.ai.oop.rpg.model.inventory;

import nl.rug.ai.oop.rpg.model.players.Player;

public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void use(Player player);
}
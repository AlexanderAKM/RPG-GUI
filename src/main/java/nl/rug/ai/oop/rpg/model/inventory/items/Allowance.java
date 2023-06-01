package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

public class Allowance extends Item {
    private int allowance;

    public Allowance(int initialAllowance) {
        super("Allowance");
        this.allowance = initialAllowance;
    }

    public void changeAllowance(int change) {
        allowance += change;
    }

    public void use(Player player) {
        player.changeMoney(allowance);
    }
}

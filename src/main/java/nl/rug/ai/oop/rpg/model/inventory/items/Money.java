package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

import java.io.Serializable;

/**
 * This class represents a Money item, which can be used to increase a player's money.
 * @author Alexander Müller
 */
public class Money extends Item implements Serializable {
    private final int money;

    /**
     * Constructs a new Money item with the given initial money value.
     *
     * @param initialMoney the initial money this item provides
     */
    public Money(int initialMoney) {
        super("Money");
        this.money = initialMoney;
    }

    /**
     * Uses this Money item on a player, increasing their money.
     *
     * @param player the player to use the item on
     */
    @Override
    public void use(Player player) {
        player.changeMoney(money);
    }
}

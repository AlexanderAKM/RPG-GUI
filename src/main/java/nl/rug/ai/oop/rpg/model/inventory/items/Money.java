package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

public class Money extends Item {
    private final int money;

    public Money(int initialMoney) {
        super("Money");
        this.money = initialMoney;
    }

    @Override
    public void use(Player player) {
        player.changeMoney(money);
    }
}
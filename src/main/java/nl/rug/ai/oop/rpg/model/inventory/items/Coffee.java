package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

public class Coffee extends Item {
    private final int intelligenceIncrease;
    private final int wellbeingIncrease;

    public Coffee(int initialIntelligenceIncrease, int initialWellbeingIncrease) {
        super("Coffee");
        this.intelligenceIncrease = initialIntelligenceIncrease;
        this.wellbeingIncrease = initialWellbeingIncrease;
    }

    @Override
    public void use(Player player) {
        player.changeIntelligence(intelligenceIncrease);
        player.changeWellbeing(wellbeingIncrease);
    }
}
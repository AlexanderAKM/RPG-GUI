package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

public class Books extends Item {
    private final int intelligenceIncrease;
    private final int wellbeingIncrease;

    public Books(int initialIntelligenceIncrease, int initialWellbeingIncrease) {
        super("Books");
        this.intelligenceIncrease = initialIntelligenceIncrease;
        this.wellbeingIncrease = initialWellbeingIncrease;
    }

    @Override
    public void use(Player player) {
        player.changeIntelligence(intelligenceIncrease);
        player.changeWellbeing(wellbeingIncrease);
    }
}
package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

public class Coffee extends Item {
    private int intelligenceIncrease;
    private int wellbeingIncrease;

    public Coffee(int initialIntelligenceIncrease, int initialWellbeingIncrease) {
        super("Coffee");
        this.intelligenceIncrease = initialIntelligenceIncrease;
        this.wellbeingIncrease = initialWellbeingIncrease;
    }

    public void changeIntelligence(int change) {
        intelligenceIncrease += change;
    }

    public void changeWellbeing(int change) {
        wellbeingIncrease += change;
    }
    public void use(Player player) {
        player.changeIntelligence(intelligenceIncrease);
        player.changeWellbeing.increase(wellbeingIncrease);
    }
}

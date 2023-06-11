package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

import java.io.Serializable;

/**
 * This class represents a Books item, which can increase intelligence and wellbeing of a player.
 * @author Alexander Müller
 */
public class Books extends Item implements Serializable {
    private final int intelligenceIncrease;
    private final int wellbeingIncrease;

    /**
     * Constructs a new Books item with the given values for intelligence increase and wellbeing increase.
     *
     * @param initialIntelligenceIncrease the initial intelligence increase this item provides
     * @param initialWellbeingIncrease the initial wellbeing increase this item provides
     */
    public Books(int initialIntelligenceIncrease, int initialWellbeingIncrease) {
        super("Books");
        this.intelligenceIncrease = initialIntelligenceIncrease;
        this.wellbeingIncrease = initialWellbeingIncrease;
    }

    /**
     * Uses this Books item on a player, changing their intelligence and wellbeing.
     *
     * @param player the player to use the item on
     */
    @Override
    public void use(Player player) {
        player.changeIntelligence(intelligenceIncrease);
        player.changeWellbeing(wellbeingIncrease);
    }
}

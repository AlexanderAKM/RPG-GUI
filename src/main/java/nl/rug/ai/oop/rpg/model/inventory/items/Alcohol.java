package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

import java.io.Serializable;

/**
 * This class represents an Alcohol item, which can decrease intelligence and increase social skills of a player.
 * @author Alexander Müller
 */
public class Alcohol extends Item implements Serializable {
    private final int intelligenceDecrease;
    private final int socialIncrease;

    /**
     * Constructs a new Alcohol item with the given values for intelligence decrease and social increase.
     *
     * @param initialIntelligenceDecrease the initial intelligence decrease this item provides
     * @param initialSocialIncrease the initial social increase this item provides
     */
    public Alcohol(int initialIntelligenceDecrease, int initialSocialIncrease) {
        super("Alcohol");
        this.intelligenceDecrease = initialIntelligenceDecrease;
        this.socialIncrease = initialSocialIncrease;
    }

    /**
     * Uses this Alcohol item on a player, changing their intelligence and social skills.
     *
     * @param player the player to use the item on
     */
    @Override
    public void use(Player player) {
        player.changeIntelligence(-intelligenceDecrease);
        player.changeSocial(socialIncrease);
    }
}

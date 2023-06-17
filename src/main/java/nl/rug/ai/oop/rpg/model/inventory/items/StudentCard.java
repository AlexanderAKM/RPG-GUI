package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

/**
 * The StudentCard class represents a student card item in the RPG game.
 *
 * @author Alexander Müller
 */
public class StudentCard extends Item {
    /**
     * Constructs a new StudentCard instance.
     */
    public StudentCard() {
        super("StudentCard");
    }

    /**
     * Uses the StudentCard. This method currently has no effect on the Player.
     * This is intentional, as the StudentCard is for identification purposes only (to enter Aletta Jacobs Hall).
     *
     * @param player the Player using the StudentCard
     */
    @Override
    public void use(Player player) {
    }
}

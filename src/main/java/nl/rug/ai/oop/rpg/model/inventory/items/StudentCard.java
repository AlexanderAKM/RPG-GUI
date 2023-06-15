package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

public class StudentCard extends Item {
    public StudentCard() {
        super("StudentCard");
    }

    @Override
    public void use(Player player) {
        // Define what happens when a player uses a student card
        // Maybe something like room.getunlocked? for the exam hall?
    }
}

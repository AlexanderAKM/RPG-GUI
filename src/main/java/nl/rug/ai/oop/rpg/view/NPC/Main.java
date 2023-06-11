package nl.rug.ai.oop.rpg.view.NPC;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.model.NPC.Enemy;
import nl.rug.ai.oop.rpg.model.NPC.Interactions;
import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;

public class Main {
    public Enemy Bob;
    public static void main(String[] args) {
        NpcManager model = new NpcManager();

        NpcController controller = new NpcController(model);
        NpcView view = new NpcView(model);
        view.setup(model, controller);
    }

}

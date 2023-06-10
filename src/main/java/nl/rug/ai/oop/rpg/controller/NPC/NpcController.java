package nl.rug.ai.oop.rpg.controller.NPC;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class NpcController implements ActionListener {
    private NpcManager model;
    public NpcController(NpcManager model) {
        this.model = model;
    }

    /**
     * Method to determine which action has been performed and delegate tasks to model.
     * @param e The event to be processed.
     */

    // TO DO FIGURE OUT HOW TO PASS AN INTERACTION NAME
    @Override
    public void actionPerformed(ActionEvent e) {
        Object npc = e.getSource();
        switch (e.getActionCommand()) {
            case "NPC Introduction":
                model.playInteraction((Npc)npc, "");
                break;
            case "C0o":
                break;
        }
    }
}

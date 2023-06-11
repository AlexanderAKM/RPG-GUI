package nl.rug.ai.oop.rpg.controller.NPC;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
                //(Npc)npc
               //model.playInteraction((NpcButton)npc, "Intro");
                break;
            default:
                // Go through each one
                for(Npc computer : model.allNpcs){
                    if(Objects.equals(e.getActionCommand(), computer.getName())){
                        model.playInteraction(computer, "Intro");
                    }
                }
                break;
        }
    }
}

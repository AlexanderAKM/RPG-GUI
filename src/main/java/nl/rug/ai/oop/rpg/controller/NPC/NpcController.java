package nl.rug.ai.oop.rpg.controller.NPC;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class NpcController implements NpcActionListener {
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
    public void actionPerformed(ActionEvent e){

    }
    @Override
    public void actionPerformed(NpcActionEvent e) {
       // Object npc = e.getSource();
        NpcButton npcButton = e.getSourceNpcButton();
        switch (e.getActionCommand()) {
            case "NPC Interaction":
                //(Npc)npc
                model.npcInteraction(npcButton, "Interaction");
                break;
            case "Battle Answer":
                model.checkAnswer(npcButton, -10, -10);
                break;
            case "Continue World Event":
                model.checkWorldEventCondition(npcButton);
                break;
            case "Continue Conversation":
                // I need the event that ran it as well
                model.continueConversation(npcButton, e.getEventName(), npcButton.getText());
                break;
            default:
                // Go through each one
                break;
        }
    }
}

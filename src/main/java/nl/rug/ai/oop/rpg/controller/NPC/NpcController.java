package nl.rug.ai.oop.rpg.controller.NPC;

import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.npc.NpcManager;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

import javax.swing.*;

public class NpcController implements NpcActionListener {
    private NpcManager model;


    public NpcController(NpcManager model) {
        this.model = model;
    }

    /**
     * Method to determine which action has been performed and delegate tasks to model.
     * @param e The event to be processed.
     */

    @Override
    public void actionPerformed(NpcActionEvent e) {
       // Object npc = e.getSource();
        Npc sourceNpc = e.getSourceNpc();
        String eventName = e.getEventName();
        String selectedText = e.getSelectedText();

        switch (e.getActionCommand()) {
            case "NPC Interaction":
                model.npcInteraction(sourceNpc, "Interaction");
                break;
            case "Battle Answer":
                model.checkAnswer(sourceNpc, selectedText, -10, -10);
                break;
            case "Continue World Event":
                model.checkWorldEventCondition(sourceNpc);
                break;
            case "Continue Conversation":
                model.continueConversation(sourceNpc, eventName, selectedText);
                break;
            default:
                // Go through each one
                break;
        }
    }
}

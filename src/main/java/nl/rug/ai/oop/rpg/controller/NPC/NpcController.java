package nl.rug.ai.oop.rpg.controller.NPC;

import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.npc.NpcManager;

/**
 * The controller for any interaction with NPCs.
 * It then delegates them to the model (NpcManager).
 *
 * @author Kyriakos Hjikakou
 */
public class NpcController implements NpcActionListener {
    private final NpcManager model;

    /**
     * Constructs a new NpcController with the given NpcManager model.
     *
     * @param model The NpcManager model to associate with the controller
     */
    public NpcController(NpcManager model) {
        this.model = model;
    }

    /**
     * Handles NPC action events.
     *
     * @param e The NPC action event
     */
    @Override
    public void actionPerformed(NpcActionEvent e) {
        Npc sourceNpc = e.getSourceNpc();
        String eventName = e.getEventName();
        String selectedText = e.getSelectedText();

        switch (e.getActionCommand()) {
            case "NPC Interaction":
                model.npcInteraction(sourceNpc, "Interaction");
                break;
            case "Battle Answer":
                model.checkBattleAnswer(sourceNpc, selectedText, -10, -10);
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

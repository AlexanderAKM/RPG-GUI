package nl.rug.ai.oop.rpg.controller.NPC;

import java.util.EventListener;

/**
 * A custom Npc Action Listener that allows processing of the NpcActionEvent
 *
 * @author Kyriakos Hjikakou
 */
public interface NpcActionListener extends EventListener {

    public void actionPerformed(NpcActionEvent e);
}

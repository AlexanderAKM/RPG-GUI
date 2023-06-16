package nl.rug.ai.oop.rpg.controller.NPC;

import java.awt.event.ActionEvent;
import java.util.EventListener;

public interface NpcActionListener extends EventListener {

    public void actionPerformed(NpcActionEvent e);
}

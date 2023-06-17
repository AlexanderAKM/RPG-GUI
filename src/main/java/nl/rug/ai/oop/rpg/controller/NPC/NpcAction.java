package nl.rug.ai.oop.rpg.controller.NPC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NpcAction implements NpcActionListener {
    NpcActionListener listener;

    @Override
    public void actionPerformed(NpcActionEvent e){
            //NpcActionEvent actionEvent = new NpcActionEvent(this, 0, "Command", "", 0, 0, null);
            //if (listener != null) listener.actionPerformed(actionEvent);
    }
}


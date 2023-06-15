package nl.rug.ai.oop.rpg.controller.NPC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface NpcActionListener extends ActionListener {

    public void actionPerformed(NpcActionEvent e);
}

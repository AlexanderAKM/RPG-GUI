package nl.rug.ai.oop.rpg.view.NPC;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class NpcView {
    NpcButton testButton;

    //Instead of just an npcManager, we should get it
    public NpcView(NpcManager npcManager){
        // Call the setup
        JFrame frame = new JFrame("Test");
        frame.setSize(1820,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Okay so the NPC Manager should have a list and we should make buttons based on that
        testButton = new NpcButton("Bob's Introduction","NPC Introduction", npcManager.allNpcs.get(0));


        JPanel leftPanel  = new JPanel(new GridBagLayout());
        leftPanel.add(testButton);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.setVisible(true);
    }
    public void setup(NpcManager model, NpcController controller) {
        testButton.addActionListener(controller);
        testButton.setActionCommand("NPC Introduction");
        // Add a listener a button press that passes the info of what NPC is needed
        model.addListener(evt ->{ if(Objects.equals(evt.getPropertyName(), "")) {
            //updateTopCard(model);
        }
        });
    }
}

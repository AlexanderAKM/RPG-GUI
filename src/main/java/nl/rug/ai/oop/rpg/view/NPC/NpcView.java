package nl.rug.ai.oop.rpg.view.NPC;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;

import javax.swing.*;
import java.awt.*;

public class NpcView {

    public NpcView(){
        // Call the setup
        JFrame frame = new JFrame("Test");
        frame.setSize(1820,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NpcButton testButton = new NpcButton("Bob's Introduction","NPC Introduction");

        JPanel leftPanel  = new JPanel(new GridBagLayout());
        leftPanel.add(testButton);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.setVisible(true);
    }
    public void setup(NpcManager model, NpcController controller) {
        // Add a listener a button press that passes the info of what NPC is needed
        model.addListener(evt ->{ if(Objects.equals(evt.getPropertyName(), "topcard")) {
            updateTopCard(model);
        }
        });
    }
}

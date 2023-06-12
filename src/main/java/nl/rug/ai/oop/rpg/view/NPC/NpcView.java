package nl.rug.ai.oop.rpg.view.NPC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class NpcView {
    NpcButton testButton;
    JButton backButton;
    private JTextArea textArea;
    private JTextField textField;

    private JPanel npcView;

    // Temp
    NpcController cont;

    public NpcView(NpcManager npcManager) {
        /*JFrame frame = new JFrame("Test");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());*/

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Set the border
        textArea.append("There's three NPCs here, Herman, Bob and L. Who do you want to talk to!??? \n");

        textField = new JTextField();
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Set the border

        backButton = new JButton("Go back");


        testButton = new NpcButton("Bob's Introduction", "NPC Introduction", npcManager.allNpcs.get(0));

        npcView = new JPanel(new GridLayout(10, 1, 10, 5));
        npcView.add(backButton);
        npcView.add(textArea, BorderLayout.CENTER);
        //npcView.add(textField, BorderLayout.PAGE_END);
        //frame.add(centerPanel, BorderLayout.CENTER);
        //frame.setVisible(true);
    }

    private void setUpNpcs(ArrayList<Npc> npcs){

        for (Npc npc : npcs) {
            NpcButton npcInteractionButton = new NpcButton(npc.getName(), "Intro", npc);
            npcInteractionButton.addActionListener(cont);

            npcInteractionButton.setActionCommand("NPC Introduction"); // Set the action command as the room name
            npcView.add(npcInteractionButton);
        }

        npcView.revalidate();
        npcView.repaint();
    }

    public void updateNpcView(ArrayList<Npc> npcs){
        npcView.removeAll();
        setUpNpcs(npcs);
    }

    public JPanel returnNpcView(){
        return npcView;
    }

    private void processCommand(String command) {
        // Check if NPC is in this room with the help of @Vic
        if(Objects.equals(command, "Bob")){
            //cont.actionPerformed(new ActionEvent(testButton, 0, "NPC Introduction"));
        }
        //textArea.append("You entered: " + command + "\n");
    }

    private  void outputSpeech(String command){
        textArea.append(command + "\n");
    }

    public void setup(NpcManager model, ArrayList<Npc> npcs, NpcController controller, GamePanelGUI home, JFrame frame) {
        testButton.addActionListener(controller);
        testButton.setActionCommand("NPC Introduction");
       backButton.addActionListener(new ActionListener() {
           @Override
               public void actionPerformed(ActionEvent e) {
                   //npcView.removeAll();
                   //npcView.revalidate();
                   //npcView.repaint();
                   home.showGamePanel();
                   //frame.add(home);
               }
           });

        textField.addActionListener(controller);
        cont = controller;

        setUpNpcs(npcs);

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "Speech")) {
                outputSpeech((String)evt.getNewValue());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "Enter Room")) {
                outputSpeech((String)evt.getNewValue());
            }
        });
    }
}
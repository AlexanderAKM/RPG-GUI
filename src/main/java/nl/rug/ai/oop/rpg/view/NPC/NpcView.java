package nl.rug.ai.oop.rpg.view.NPC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.rug.ai.oop.rpg.controller.NPC.NpcActionEvent;
import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.model.npc.*;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;
import nl.rug.ai.oop.rpg.view.players.PlayerStatsPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class NpcView {
    private final JButton backButton;
    private final JTextArea textArea;

    private final JPanel npcView;

    private final LocationManager locationManager;
    private JTextField textField;

    private PlayerStatsPane playerStatsPane;

    private ArrayList<Npc> npcList;

    // Temp
    NpcController cont;

    public NpcView(NpcManager npcManager, LocationManager locationManager, PlayerStatsPane playerStatsPane) {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Set the border
        textArea.append("There's three NPCs here, Herman, Bob and L. Who do you want to talk to!??? \n");

        textField = new JTextField();
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Set the border

        backButton = new JButton("Go back");

        npcView = new JPanel(new GridLayout(10, 1, 10, 5));

        this.locationManager = locationManager;
    }

    private void setUpNpcs(ArrayList<Npc> npcs, String eventName, Player player){

        npcView.removeAll();
        textArea.setText("");
        npcView.add(backButton);
        npcView.add(textArea, BorderLayout.CENTER);
        for (Npc npc : npcs) {
            String npcName = npc.getName();
            JButton npcInteractionButton = new JButton(npcName);
            npcInteractionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), npcName, eventName, -10, - 1, npc);
                    updateNpcView(eventName, player);
                    cont.actionPerformed(customEvent);
                    System.out.println("This worked");
                }
            });

            npcInteractionButton.setActionCommand("NPC Interaction");
            npcView.add(npcInteractionButton);
        }

        npcView.revalidate();
        npcView.repaint();
    }

    private void setupBattle(ArrayList<String> answers, String speech, String eventName, Npc npc){
        npcView.removeAll();
        textArea.setText("");
        npcView.add(textArea, BorderLayout.CENTER);
        textArea.append(speech);
        for (String answer : answers) {
            JButton answerButton = new JButton(answer);
            answerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), answer, eventName, -10, - 1, npc);
                    cont.actionPerformed(customEvent);
                }
            });
            answerButton.setActionCommand("Battle Answer");

            npcView.add(answerButton);
        }

        npcView.revalidate();
        npcView.repaint();
    }

    private void setUpConversation(ArrayList<String> options, String text, String eventName,Npc npcSource, GamePanelGUI home){
        boolean isNotFinal = true;
        npcView.removeAll();
        textArea.setText("");
        npcView.add(textArea, BorderLayout.CENTER);

        textArea.append(text);
        System.out.println(options);

        for (String option : options) {

            JButton answerButton = new JButton(option);
            answerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), option, eventName, -10, -1, npcSource);
                    cont.actionPerformed(customEvent);
                }
            });
            answerButton.setActionCommand("Continue Conversation");


            npcView.add(answerButton);
        }


        npcView.revalidate();
        npcView.repaint();
    }

    private void setupWorldEvent(String eventName, String speech, int cost, Npc npcSource){
        String costText = Integer.toString(cost);

        npcView.removeAll();
        //textArea.setText("");
        npcView.add(backButton);
        npcView.add(textArea, BorderLayout.CENTER);
        textArea.append(speech);

        JButton interactionButton = new JButton(Integer.toString(cost));
        // If statement here
        interactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), costText, eventName, -10, - 1, npcSource);
                cont.actionPerformed(customEvent);
                //updateNpcView(Player.getInstance().getCurrentRoom().getAvailableNpcs(), worldEvent.getName());
                System.out.println("This worked");
            }
        });
        interactionButton.setActionCommand("Continue World Event");

        npcView.add(interactionButton);

        npcView.revalidate();
        npcView.repaint();
    }

    public void updateNpcView(String eventName, Player player){
        npcList = locationManager.getNpcList(player.getCurrentRoom());
        npcView.removeAll();
        setUpNpcs(npcList, eventName, player);
    }

    public void returnToHomePanel(GamePanelGUI home){
        home.showGamePanel();
    }

    public JPanel returnNpcView(){
        return npcView;
    }


    public void finalResponseSetup(String text, GamePanelGUI home, String toolTip){
        npcView.removeAll();
        npcView.add(textArea);
        textArea.setText("");
        textArea.append(text);
        if(toolTip != null) {
            backButton.setToolTipText(toolTip);
        }
        npcView.add(backButton);
    }

    public void setup(NpcManager model, NpcController controller, GamePanelGUI home, JFrame frame) {
        Player player = Player.getInstance();
        //testButton.addActionListener(controller);
       // testButton.setActionCommand("NPC Interaction");
        npcList = locationManager.getNpcList(player.getCurrentRoom());
        backButton.addActionListener(new ActionListener() {
           @Override
               public void actionPerformed(ActionEvent e) {
               returnToHomePanel(home);
               }
        });

        //textField.addActionListener(controller);
        cont = controller;

        setUpNpcs(npcList,"", player);

        model.addListener(evt -> {
            if (Objects.equals(evt.getEventType(), Npc.EventType.BATTLE)) {
                setupBattle(evt.getDialogueText(), evt.getText(), evt.getEventName(), evt.getSourceNpc());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getEventType(), Npc.EventType.RESET)) {
                updateNpcView(evt.getEventName(), player);
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getEventType(), Npc.EventType.INTRODUCTION)) {
                setUpConversation(evt.getDialogueText(), evt.getText() , evt.getEventName(), evt.getSourceNpc(), home);
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getEventType(), Npc.EventType.WORLD_EVENT)) {
                setupWorldEvent(evt.getEventName(), evt.getText(), evt.getCost(),evt.getSourceNpc());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getEventType(), Npc.EventType.RESPONSE)){
                finalResponseSetup(evt.getText(), home, evt.getToolTipText());
            }
        });
    }
}
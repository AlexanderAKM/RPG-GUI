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
    NpcButton testButton;
    JButton backButton;
    private JTextArea textArea;
    private JTextField textField;

    private JPanel npcView;

    private LocationManager locationManager;

    private PlayerStatsPane playerStatsPane;

    private ArrayList<Npc> npcList;


    // Temp
    NpcController cont;

    public NpcView(NpcManager npcManager, LocationManager locationManager, PlayerStatsPane playerStatsPane) {

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


        //testButton = new NpcButton("Bob's Introduction", "NPC Introduction", npcManager.allNpcs.get(0));

        npcView = new JPanel(new GridLayout(10, 1, 10, 5));

        this.locationManager = locationManager;
        //npcView.add(textField, BorderLayout.PAGE_END);
        //frame.add(centerPanel, BorderLayout.CENTER);
        //frame.setVisible(true);
    }

    private void setUpNpcs(ArrayList<Npc> npcs, String eventName){

        npcView.removeAll();
        textArea.setText("");
        npcView.add(backButton);
        npcView.add(textArea, BorderLayout.CENTER);
        for (Npc npc : npcs) {
            String npcName = npc.getName();
            NpcButton npcInteractionButton = new NpcButton(npcName, "Interaction", npc);
            npcInteractionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), npcName, eventName, -10, - 1, npc);
                    updateNpcView(Player.getInstance().getCurrentRoom().getAvailableNpcs(), eventName);
                    cont.actionPerformed(customEvent);
                    System.out.println("This worked");
                }
            });


            // Implement The NPC stuff
            npcInteractionButton.setActionCommand("NPC Interaction"); // Set the action command as the room name
            /*npcInteractionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });*/

            npcView.add(npcInteractionButton);
        }

        npcView.revalidate();
        npcView.repaint();
    }

    private void setUpNpcs(ArrayList<Npc> npcs, String additonalText, String eventName){
        npcView.removeAll();
        textArea.setText("");
        npcView.add(backButton);
        npcView.add(textArea, BorderLayout.CENTER);
        for (Npc npc : npcs) {
            String npcName = npc.getName();
            NpcButton npcInteractionButton = new NpcButton(npcName, "Interaction", npc);
            npcInteractionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(),npcName, eventName, -10, - 1, npc);
                    cont.actionPerformed(customEvent);
                    updateNpcView(Player.getInstance().getCurrentRoom().getAvailableNpcs(), eventName);
                    System.out.println("This worked");
                }
            });


            // Implement The NPC stuff
            npcInteractionButton.setActionCommand("NPC Interaction"); // Set the action command as the room name
            /*npcInteractionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                }
            });*/

            npcView.add(npcInteractionButton);
        }
        textArea.append(additonalText);
        npcView.revalidate();
        npcView.repaint();
    }

    private void setupBattle(ArrayList<String> answers, String speech, String eventName, Npc npc){
        npcView.removeAll();
        textArea.setText("");
        npcView.add(textArea, BorderLayout.CENTER);
        textArea.append(speech);
        for (String answer : answers) {
            NpcButton answerButton = new NpcButton(answer, eventName,npc);
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
        //ConversationChain conversationChain = introductionEvent.getConversationChain();

        //System.out.println(introductionEvent.getCurrentKey());
        System.out.println(options);

        /*for (String option : options){
            for(String finalText : conversationChain.getFinalTexts()){
                if(Objects.equals(option, finalText)) {
                    isNotFinal = false;
                    NpcButton answerButton = new NpcButton(option, introductionEvent.getName(), introductionEvent.getNpcSource());
                    answerButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            returnToHomePanel(home);
                        }
                    });
                    npcView.add(answerButton);
                }
            }
        }*/

        for (String option : options) {

            NpcButton answerButton = new NpcButton(option, eventName, npcSource);
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

        NpcButton interactionButton = new NpcButton(Integer.toString(cost), eventName, npcSource);
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

    public void updateNpcView(ArrayList<Npc> npcs, String eventName){
        npcView.removeAll();
        npcList = npcs;
        setUpNpcs(npcs, eventName);
    }

    public void returnToHomePanel(GamePanelGUI home){
        home.showGamePanel();
    }

    public JPanel returnNpcView(){
        return npcView;
    }

    private  void outputSpeech(String command){
        textArea.append(command + "\n");
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

    public void setup(NpcManager model, ArrayList<Npc> npcs, NpcController controller, GamePanelGUI home, JFrame frame) {
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

        setUpNpcs(npcs, "");

        model.addListener(evt -> {
            if (Objects.equals(evt.getEventType(), Npc.EventType.BATTLE)) {
                setupBattle(evt.getDialogueText(), evt.getText(), evt.getEventName(), evt.getSourceNpc());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getEventType(), Npc.EventType.RESET)) {
                updateNpcView(npcList, evt.getEventName());
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
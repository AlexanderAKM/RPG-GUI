package nl.rug.ai.oop.rpg.view.NPC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.model.NPC.*;
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

    private LocationManager locationManager;

    // Temp
    NpcController cont;

    public NpcView(NpcManager npcManager, LocationManager locationManager) {

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

        this.locationManager = locationManager;
        //npcView.add(textField, BorderLayout.PAGE_END);
        //frame.add(centerPanel, BorderLayout.CENTER);
        //frame.setVisible(true);
    }

    private void setUpNpcs(ArrayList<Npc> npcs){
        npcView.removeAll();
        textArea.setText("");
        npcView.add(backButton);
        npcView.add(textArea, BorderLayout.CENTER);
        for (Npc npc : npcs) {
            NpcButton npcInteractionButton = new NpcButton(npc.getName(), "Interaction", npc);
            npcInteractionButton.addActionListener(cont);


            // Implement The NPC stuff
            npcInteractionButton.setActionCommand("NPC Interaction"); // Set the action command as the room name
            npcInteractionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                 updateNpcView(Player.getInstance().getCurrentRoom().getAvailableNpcs());
                }
            });

            npcView.add(npcInteractionButton);
        }

        npcView.revalidate();
        npcView.repaint();
    }

    private void setUpNpcs(ArrayList<Npc> npcs, String additonalText){
        npcView.removeAll();
        textArea.setText("");
        npcView.add(backButton);
        npcView.add(textArea, BorderLayout.CENTER);
        for (Npc npc : npcs) {
            NpcButton npcInteractionButton = new NpcButton(npc.getName(), "Interaction", npc);
            npcInteractionButton.addActionListener(cont);


            // Implement The NPC stuff
            npcInteractionButton.setActionCommand("NPC Interaction"); // Set the action command as the room name
            npcInteractionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateNpcView(Player.getInstance().getCurrentRoom().getAvailableNpcs());
                }
            });

            npcView.add(npcInteractionButton);
        }
        textArea.append(additonalText);
        npcView.revalidate();
        npcView.repaint();
    }

    private void setupBattle(ArrayList<String> answers, String speech, BattleEvent battleEvent){
        npcView.removeAll();
        textArea.setText("");
        npcView.add(textArea, BorderLayout.CENTER);
        textArea.append(speech);
        for (String answer : answers) {

            NpcButton answerButton = new NpcButton(answer, battleEvent.getName(), battleEvent.getNpcSource());
            answerButton.addActionListener(cont);
            answerButton.setActionCommand("Battle Answer");

            // Implement The NPC stuff
            // Set the action command as the room name
            /*npcInteractionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateNpcView(Player.getInstance().getCurrentRoom().getAvailableNpcs());
                }
            });*/

            npcView.add(answerButton);
        }

        npcView.revalidate();
        npcView.repaint();
    }

    private void setupWorldEvent(String speech, int condition, boolean isFinalInteraction, WorldEvent worldEvent){
        npcView.removeAll();
        //textArea.setText("");
        npcView.add(textArea, BorderLayout.CENTER);
        textArea.append(speech);

        NpcButton interactionButton = new NpcButton(Integer.toString(condition), worldEvent.getName(), worldEvent.getNpcSource());
        interactionButton.addActionListener(cont);
        interactionButton.setActionCommand("Continue World Event");

        npcView.add(interactionButton);

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

    private  void outputSpeech(String command){
        textArea.append(command + "\n");
    }

    public void setup(NpcManager model, ArrayList<Npc> npcs, NpcController controller, GamePanelGUI home, JFrame frame) {
        Player player = Player.getInstance();
        testButton.addActionListener(controller);
        testButton.setActionCommand("NPC Interaction");
       backButton.addActionListener(new ActionListener() {
           @Override
               public void actionPerformed(ActionEvent e) {
                   home.showGamePanel();
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
            if (Objects.equals(evt.getPropertyName(), "Battle")) {
                BattleEvent battleEvent = (BattleEvent)evt.getNewValue();

                // Ensure this is a clone a deep clone
                BattleQuestions battleQuestions = battleEvent.getBattleQuestions();
                ArrayList<String> answers = battleQuestions.getAnswers();
                String speech = (String)evt.getOldValue();
                setupBattle(answers, speech, battleEvent);
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "Enter Room")) {
                outputSpeech((String)evt.getNewValue());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "Correct")) {
                BattleEvent interaction = (BattleEvent)evt.getNewValue();
                Npc source = interaction.getNpcSource();
                locationManager.removeNpcs("", source, player.getCurrentRoom());
                setUpNpcs(locationManager.getNpcList(player.getCurrentRoom()), (String)evt.getOldValue());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "World Event")) {
                WorldEvent worldEvent = (WorldEvent)evt.getNewValue();
                String speech = (String)evt.getOldValue();
                setupWorldEvent(speech, worldEvent.getCondition(),worldEvent.gethasFinishedEvent(), worldEvent);
            }
        });
    }
}
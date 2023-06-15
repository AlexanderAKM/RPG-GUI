package nl.rug.ai.oop.rpg.view.NPC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.rug.ai.oop.rpg.controller.NPC.NpcAction;
import nl.rug.ai.oop.rpg.controller.NPC.NpcActionEvent;
import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.model.NPC.*;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
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


        testButton = new NpcButton("Bob's Introduction", "NPC Introduction", npcManager.allNpcs.get(0));

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
            NpcButton npcInteractionButton = new NpcButton(npc.getName(), "Interaction", npc);
            npcInteractionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(),eventName, -10, - 1, npcInteractionButton );
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
            NpcButton npcInteractionButton = new NpcButton(npc.getName(), "Interaction", npc);
            npcInteractionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), eventName, -10, - 1, npcInteractionButton);
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

    private void setupBattle(ArrayList<String> answers, String speech, BattleEvent battleEvent, String eventName){
        npcView.removeAll();
        textArea.setText("");
        npcView.add(textArea, BorderLayout.CENTER);
        textArea.append(speech);
        for (String answer : answers) {

            NpcButton answerButton = new NpcButton(answer, battleEvent.getName(), battleEvent.getNpcSource());
            answerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(),eventName, -10, - 1, answerButton);
                    cont.actionPerformed(customEvent);
                }
            });
            answerButton.setActionCommand("Battle Answer");

            npcView.add(answerButton);
        }

        npcView.revalidate();
        npcView.repaint();
    }

    private void setUpConversation(ArrayList<String> options, IntroductionEvent introductionEvent, String eventName, GamePanelGUI home){
        boolean isNotFinal = true;
        npcView.removeAll();
        textArea.setText("");
        npcView.add(textArea, BorderLayout.CENTER);

        textArea.append(introductionEvent.getCurrentKey());
        ConversationChain conversationChain = introductionEvent.getConversationChain();

        System.out.println(introductionEvent.getCurrentKey());
        System.out.println(options);

        for (String option : options){
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
        }

        if(isNotFinal) {
            for (String option : options) {

                NpcButton answerButton = new NpcButton(option, introductionEvent.getName(), introductionEvent.getNpcSource());
                answerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), eventName, -10, -1, answerButton);
                        cont.actionPerformed(customEvent);
                    }
                });
                answerButton.setActionCommand("Continue Conversation");


                npcView.add(answerButton);
            }
        }

        npcView.revalidate();
        npcView.repaint();
    }

    private void setupWorldEvent(String speech, int condition, boolean isFinalInteraction, WorldEvent worldEvent){
        npcView.removeAll();
        //textArea.setText("");
        npcView.add(backButton);
        npcView.add(textArea, BorderLayout.CENTER);
        textArea.append(speech);

        NpcButton interactionButton = new NpcButton(Integer.toString(condition), worldEvent.getName(), worldEvent.getNpcSource());
        // If statement here
        interactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), worldEvent.getName(), -10, - 1, interactionButton);
                cont.actionPerformed(customEvent);
                updateNpcView(Player.getInstance().getCurrentRoom().getAvailableNpcs(), worldEvent.getName());
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

    public void setup(NpcManager model, ArrayList<Npc> npcs, NpcController controller, GamePanelGUI home, JFrame frame) {
        Player player = Player.getInstance();
        testButton.addActionListener(controller);
        testButton.setActionCommand("NPC Interaction");
       backButton.addActionListener(new ActionListener() {
           @Override
               public void actionPerformed(ActionEvent e) {
               returnToHomePanel(home);
               }
           });

        textField.addActionListener(controller);
        cont = controller;

        setUpNpcs(npcs, "");

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "-Introduction")) {
                //returnToHomePanel(home);
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "Battle")) {
                BattleEvent battleEvent = (BattleEvent)evt.getNewValue();

                // Ensure this is a clone a deep clone
                BattleQuestions battleQuestions = battleEvent.getBattleQuestions();
                ArrayList<String> answers = battleQuestions.getAnswers();
                String speech = (String)evt.getOldValue();
                setupBattle(answers, speech, battleEvent, battleEvent.getName());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "Introduction")) {
                IntroductionEvent introductionEvent = (IntroductionEvent)evt.getNewValue();
                ArrayList<String> options = (ArrayList<String>) evt.getOldValue();
                // Ensure this is a clone a deep clone
                ConversationChain conversationChain = introductionEvent.getConversationChain();
                conversationChain.getKey();

                //ArrayList<String> answers = battleQuestions.getAnswers();
                //String speech = (String)evt.getOldValue();
                //setupBattle(answers, speech, battleEvent);
                setUpConversation(options, introductionEvent, introductionEvent.getName(), home);
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
                setUpNpcs(locationManager.getNpcList(player.getCurrentRoom()), (String)evt.getOldValue(), interaction.getName());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "World Event")) {
                WorldEvent worldEvent = (WorldEvent)evt.getNewValue();
                String speech = (String)evt.getOldValue();
                setupWorldEvent(speech, worldEvent.getCondition(),worldEvent.gethasFinishedEventChain(), worldEvent);
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "Accepted")) {
                WorldEvent worldEvent = (WorldEvent)evt.getNewValue();
                String speech = (String)evt.getOldValue();
                setUpNpcs(locationManager.getNpcList(player.getCurrentRoom()), worldEvent.getSuccessText(), worldEvent.getName());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getPropertyName(), "Declined")) {
                WorldEvent worldEvent = (WorldEvent)evt.getNewValue();
                String speech = (String)evt.getOldValue();
                setupWorldEvent(speech, worldEvent.getCondition(),worldEvent.gethasFinishedEventChain(), worldEvent);
                setUpNpcs(locationManager.getNpcList(player.getCurrentRoom()), (String) evt.getOldValue(), worldEvent.getName());
            }
        });
    }
}
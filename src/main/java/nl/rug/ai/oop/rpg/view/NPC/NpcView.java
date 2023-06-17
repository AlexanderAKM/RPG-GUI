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

/**
 * This class is responsible for rendering the view of the NPC interactions.
 * It also provides various setup methods to handle different types of interactions with NPCs and
 * allows to update the NPC view according to the player's actions as locations change.
 *
 * @author Kyriakos Hjikakou
 */
public class NpcView {
    private final JButton backButton;
    private final JTextArea textArea;

    private final JPanel npcView;

    private final LocationManager locationManager;

    private PlayerStatsPane playerStatsPane;

    private ArrayList<Npc> npcList;

    private NpcController cont;

    /**
     * Constructs a new NPC view.
     *
     * @param npcManager        the NPC manager
     * @param locationManager   the location manager
     * @param playerStatsPane   the player stats pane
     */
    public NpcView(NpcManager npcManager, LocationManager locationManager, PlayerStatsPane playerStatsPane) {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Set the border
        textArea.append("There's three NPCs here, Herman, Bob and L. Who do you want to talk to!??? \n");

        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Set the border

        backButton = new JButton("<-");

        npcView = new JPanel(new GridLayout(10, 1, 10, 5));

        this.locationManager = locationManager;
    }

    /**
     * Sets up the NPC view for the given list of NPCs. Uses the player to get an updated list.
     *
     * @param npcs        the list of NPCs
     * @param player      the player
     */
    private void setUpNpcs(ArrayList<Npc> npcs, Player player){

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
                    NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), npcName, "", -10, - 1, npc);
                    updateNpcView(player);
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

    /**
     * Creates buttons and a view appropriate for a battle (Multiple choice questions).
     * Also adds a custom event to keep track of answers.
     *
     * @param answers     the list of answers
     * @param speech      the speech text
     * @param eventName   the event name
     * @param npc         the NPC
     */
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

    /**
     * Sets up and creates a view appropriate for a conversation chain.
     * Buttons that are setup with events for different dialogue options down the line.
     *
     * @param options     the list of options
     * @param text        the text
     * @param eventName   the event name
     * @param npcSource   the NPC source
     * @param home        the game panel
     */
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

    /**
     * Sets up view for a world event, and the setup for its effect to take place through the controller.
     *
     * @param eventName   the event name
     * @param speech      the speech text
     * @param cost        the cost of the event
     * @param npcSource   the NPC source
     */
    private void setupWorldEvent(String eventName, String speech, int cost, Npc npcSource){
        String costText = Integer.toString(cost);

        npcView.removeAll();
        npcView.add(backButton);
        npcView.add(textArea, BorderLayout.CENTER);
        textArea.append(speech);

        JButton interactionButton = new JButton(Integer.toString(cost));

        interactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NpcActionEvent customEvent = new NpcActionEvent(e.getSource(), e.getID(), e.getActionCommand(), costText, eventName, -10, - 1, npcSource);
                cont.actionPerformed(customEvent);
                System.out.println("This worked");
            }
        });
        interactionButton.setActionCommand("Continue World Event");

        npcView.add(interactionButton);

        npcView.revalidate();
        npcView.repaint();
    }

    /**
     * Updates the NPC view with the NPCs in the player's current room. Used to refresh the view basically.
     *
     * @param player The player whose current room's NPCs are to be displayed.
     */
    public void updateNpcView(Player player){
        npcList = locationManager.getNpcList(player.getCurrentRoom());
        npcView.removeAll();
        setUpNpcs(npcList, player);
    }

    /**
     * Returns to the home panel view.
     *
     * @param home The home panel to which the player is to be returned.
     */
    public void returnToHomePanel(GamePanelGUI home){
        home.showGamePanel();
    }

    /**
     * Returns the NPC view panel.
     *
     * @return The NPC view panel.
     */
    public JPanel returnNpcView(){
        return npcView;
    }

    /**
     * Final response setup occurs at the end of events that need to have an ending slide and a button to go back.
     * Sets that up with appropriately outputted text and a button to go back.
     *
     * @param text The text to be displayed in the final response.
     * @param home The home panel.
     * @param toolTip The tool tip to be displayed on the back button.
     */
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

    /**
     *  Sets up the NpcView. Exists seperately from the constructor in order to be easily called from Main.
     *  Also adds listeners to the model, and filters them based on custom events.
     *
     * @param model The NPC manager.
     * @param controller The NPC controller.
     * @param home The home panel.
     * @param frame The frame.
     */
    public void setup(NpcManager model, NpcController controller, GamePanelGUI home, JFrame frame) {
        Player player = Player.getInstance();
        npcList = locationManager.getNpcList(player.getCurrentRoom());
        backButton.addActionListener(new ActionListener() {
           @Override
               public void actionPerformed(ActionEvent e) {
               returnToHomePanel(home);
               }
        });

        cont = controller;

        setUpNpcs(npcList, player);

        model.addListener(evt -> {
            if (Objects.equals(evt.getEventType(), Npc.EventType.BATTLE)) {
                setupBattle(evt.getDialogueText(), evt.getText(), evt.getEventName(), evt.getSourceNpc());
            }
        });

        model.addListener(evt -> {
            if (Objects.equals(evt.getEventType(), Npc.EventType.RESET)) {
                updateNpcView(player);
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
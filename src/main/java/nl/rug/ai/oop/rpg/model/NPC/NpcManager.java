package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class NpcManager {
    ArrayList<PropertyChangeListener> listeners = new ArrayList<>();

    // Fix this, shouldn't be public, should be encapsulated
    public ArrayList<Npc> allNpcs;
    private LocationManager locationManager;

    public NpcManager(LocationManager locationManager) {
        allNpcs  = new ArrayList<Npc>();
        this.locationManager = locationManager;
        initialiseNpcs();
    }

    private void notifyListeners(PropertyChangeEvent payload) {
        Iterator<PropertyChangeListener> allListeners = listeners.iterator();

        while (allListeners.hasNext()) {
            allListeners.next().propertyChange(payload);
        }
    }

    public void initialiseNpcs(){
        // Turn this all into a builder type class

        // Move this to a new event manager that creates a bunch of pre-determined ones
        ArrayList<String> answers =  new ArrayList<>();
        answers.add("Cover");
        answers.add("1");
        answers.add("2");
        answers.add("3");

        Npc Bob = new Npc("Bob", 90);
        Npc Harmen = new Npc("Harmen", 90);
        Npc Michael = new Npc("Michael",2);

        Npc humanMan = new Npc("Human man", 2);

        // Harmen - > Yo I'm in your house

        // Yo I'm in your house -> Dang. Why?
        // Connection: Dang. - > Hehe
        // Hehe -> Ok. (Home)

        // Connection: Why? - > I need friends.
        // I need fiends. -> Ok. (Home)



        // The first key is always the NPC's name
        ArrayList<String> ZeroOption = new ArrayList<String>();
        ArrayList<String> FirstOption = new ArrayList<String>();
        ArrayList<String> home = new ArrayList<String>();
        ArrayList<String> thirdOption = new ArrayList<String>();
        home.add("Ok");

        ZeroOption.add("Yo I'm in your house");
        FirstOption.add("Dang.");
        FirstOption.add("Why?");
        thirdOption.add("Hehe.");
        thirdOption.add("I need fiends.");

        ConversationChain conversationChain = new ConversationChain();

        conversationChain.addToConversationChain(Harmen.getName(), ZeroOption);

        // Yo I'm in your house -> Dang. Why?
        conversationChain.addToConversationChain(ZeroOption.get(0), FirstOption);
        // Connection: Dang. - > Hehe
        conversationChain.addToDialogueConnections(FirstOption.get(0), thirdOption.get(0));
        // Hehe -> Ok. (Home)
        conversationChain.addToConversationChain(thirdOption.get(0),home);
        conversationChain.setFinalText(home);

        // Connection: Why? - > I need friends.
        conversationChain.addToDialogueConnections(FirstOption.get(1), thirdOption.get(1));
        // I need fiends. -> Ok. (Home)
        conversationChain.addToConversationChain(thirdOption.get(1), home);




        EventBuilder eventBuilder3 = new EventBuilder()
                .setInteractionName("introduction")
                .setNpcSource(Harmen)
                .setSpeechText("Yeah, it's me. In your home.");
        IntroductionEvent introductionEvent = eventBuilder3.buildIntroductionEvent("Bye", conversationChain);
        //introductionEvent.initialSetup();
        Harmen.setEvent(introductionEvent);
        Harmen.setNpcIntroductionEvents(introductionEvent);

        EventBuilder eventBuilder = new EventBuilder()
                .setInteractionName("interactionName")
                .setNpcSource(humanMan)
                .setSpeechText("Hey kiddo. Want a cover membership? \n Psst, of course you do! Who doesn't. Well you got to pay up!");
        WorldEvent worldEvent = eventBuilder.buildWorldEvent(WorldEvent.effectOnWorld.UNLOCK, 20, "Great", "Bad", locationManager);
        humanMan.setEvent(worldEvent);
        humanMan.setNpcWorldEvents(worldEvent);


        Npc EvilMan = new Npc("Evil Man", 2);
        EventBuilder eventBuilder1 = new EventBuilder()
                .setInteractionName("EvilBattle")
                .setNpcSource(EvilMan)
                .setSpeechText("Death be upon you! \n");

        BattleQuestions coverQuestion = new BattleQuestions("What is the best association?", answers, "Cover", "Yo congrats!", "Boo");
        BattleEvent coverBattle = eventBuilder1.buildBattleEvent(coverQuestion);
        EvilMan.setEvent(coverBattle);
        EvilMan.setNpcBattleEvents(coverBattle);

        allNpcs.add(Bob);
        allNpcs.add(Harmen);
        allNpcs.add(Michael);
        allNpcs.add(EvilMan);
        allNpcs.add(humanMan);
    }

    public Npc getNpc(String npcName){
        for(Npc npc : allNpcs){
            if(Objects.equals(npc.getName(), npcName)){
                return npc;
            }
        }
        return null;
    }
    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    public void npcInteraction(NpcButton target, String interactionName){
        Npc npc = target.getNpc();
        Events event = npc.findNpcEvent();

        switch (event.getEventType()) {
            case BATTLE:
                ArrayList<String> answers = new ArrayList<String>();
                BattleEvent battleEvent = npc.getNpcBattleEvents(event.getName());
                BattleQuestions battleQuestions = battleEvent.getBattleQuestions();

                answers = battleQuestions.getAnswers();

                String battleSpeech = event.getSpeechText() + "\n" + battleQuestions.getQuestion();
                PropertyChangeEvent battlePayload = new PropertyChangeEvent(this, "Battle", battleSpeech, battleEvent);
                notifyListeners(battlePayload);
                break;
            case INTRODUCTION:
                ArrayList<String> options = new ArrayList<String>();
                IntroductionEvent introductionEvent = npc.getIntroductionEvent(event.getName());
                introductionEvent.initialSetup();
                ConversationChain conversationChain = introductionEvent.getConversationChain();

                // We get the starting Key from the event itself, it starts of with the NPCs name

                options = conversationChain.getOptions(introductionEvent.getCurrentKey());
                //  Whenever button gets pressed key is updated

                String introductionEventSpeech = event.getSpeechText() + "\n";
                PropertyChangeEvent introductionPayload = new PropertyChangeEvent(this, "Introduction", options, introductionEvent);
                notifyListeners(introductionPayload);
                break;
            case WORLD_EVENT:
                WorldEvent worldEvent = npc.getWorldEvent(event.getName());
                String worldEventSpeech = event.getSpeechText() + "\n";
                PropertyChangeEvent worldPayload = new PropertyChangeEvent(this, "World Event", worldEventSpeech, worldEvent);
                notifyListeners(worldPayload);
        }
    }

    public void checkAnswer(NpcButton target, int wellBeingEffect, int socialEffect){
        Npc npc = target.getNpc();
        Events event = npc.findNpcEvent();

        BattleEvent battleEvent = npc.getNpcBattleEvents(event.getName());
        BattleQuestions battleQuestions = battleEvent.getBattleQuestions();

        ArrayList<String> answers = battleQuestions.getAnswers();

        // Check if it was the correct answer
        if(Objects.equals(target.getText(), battleQuestions.correctAnswer)){
            // Yes correct answer, appropriate effects
            // Reduce NPC stats
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "Correct", battleQuestions.getVictoryText(), battleEvent);
            notifyListeners(payload);
        } else {
            // Wrong answer appropriate effects
            // Idk reduce player stats
            Player player = Player.getInstance();
            player.changeWellbeing(wellBeingEffect);
            player.changeIntelligence(socialEffect);
            String effectsTooltip = "Also you lost: " + Integer.toString(wellBeingEffect) + " Wellbeing, & " + Integer.toString(socialEffect) + " Social.";
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "Wrong", null, battleQuestions.getLosingText()  + effectsTooltip);
            notifyListeners(payload);
        }

    }

    public void continueConversation(NpcButton target, String eventName, String optionSelected){
        Npc npc = target.getNpc();

        IntroductionEvent introductionEvent = npc.getIntroductionEvent(eventName);

        ArrayList<String> options = new ArrayList<String>();
        ConversationChain conversationChain = introductionEvent.getConversationChain();
        String nextKey = conversationChain.getNextKey(optionSelected);
        introductionEvent.setCurrentKey(nextKey);

        options = conversationChain.getOptions(nextKey);

        PropertyChangeEvent introductionPayload = new PropertyChangeEvent(this, "Introduction", options, introductionEvent);
        notifyListeners(introductionPayload);
    }

    public void checkWorldEventCondition(NpcButton target){
        Npc npc = target.getNpc();
        Events event = npc.findNpcEvent();
        WorldEvent worldEvent = npc.getWorldEvent(event.getName());

        Player player = Player.getInstance();
        if(player.getMoney() >= worldEvent.getCondition()){
            worldEvent.unlockRoom();
            player.changeMoney(-20);
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "Accepted", worldEvent.getSuccessText(), worldEvent);
            worldEvent.setHasFinishedEventChain(false);
            notifyListeners(payload);
        } else {
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "Declined", worldEvent.getFailText(), worldEvent);
            notifyListeners(payload);
        }
    }
}

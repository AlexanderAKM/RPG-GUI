package nl.rug.ai.oop.rpg.model.npc;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.ItemManager;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.npc.conversation.ConversationChain;
import nl.rug.ai.oop.rpg.model.npc.conversation.ConversationEvent;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

import java.util.*;


public class NpcManager {
    ArrayList<NpcPropertyChangeListener> listeners = new ArrayList<>();

    // Fix this, shouldn't be public, should be encapsulated
    public ArrayList<Npc> allNpcs;
    private LocationManager locationManager;

    private ItemManager itemManager;

    NpcLanguageManager npcLanguageManager;

    public NpcManager(LocationManager locationManager, String directory, ItemManager itemManager) {
        allNpcs  = new ArrayList<Npc>();
        this.locationManager = locationManager;
        //System.out.println(language);
        //npcLanguageManager = new NpcLanguageManager(language, directory);
        this.npcLanguageManager = npcLanguageManager;
        this.itemManager = itemManager;
        initialiseNpcs();
    }

    private void notifyListeners(NpcPropertyEvent payload) {
        Iterator<NpcPropertyChangeListener> allListeners = listeners.iterator();

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
        ArrayList<String> ZeroOption = new ArrayList<>(List.of("Yo I'm in your house"));
        ArrayList<String> FirstOption = new ArrayList<>(List.of("Dang.", "Why?"));
        ArrayList<String> ThirdOption = new ArrayList<>(List.of("Hehe.", "Why not?"));
        ArrayList<String> home = new ArrayList<>(List.of("Ok."));

        ConversationChain conversationChain = new ConversationChain();

        conversationChain.addToConversationChain(Harmen.getName(), ZeroOption);

        // Yo I'm in your house -> Dang. Why?
        conversationChain.addToConversationChain(ZeroOption.get(0), FirstOption);
        // Connection: Dang. - > Hehe
        conversationChain.addToDialogueConnections(FirstOption.get(0), ThirdOption.get(0));
        // Hehe -> Ok. (Home)
        conversationChain.addToConversationChain(ThirdOption.get(0),home);
        conversationChain.setFinalText(home);

        // Connection: Why? - > I need friends.
        conversationChain.addToDialogueConnections(FirstOption.get(1), ThirdOption.get(1));
        // I need fiends. -> Ok. (Home)
        conversationChain.addToConversationChain(ThirdOption.get(1), home);




        EventBuilder eventBuilder3 = new EventBuilder()
                .setInteractionName("introduction")
                .setNpcSource(Harmen)
                .setSpeechText("Yeah, it's me. In your home.");
        ConversationEvent conversationEvent = eventBuilder3.buildIntroductionEvent("Bye", conversationChain);
        //introductionEvent.initialSetup();
        Harmen.setEvent(conversationEvent);
        Harmen.setNpcIntroductionEvents(conversationEvent);

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
    public void addListener(NpcPropertyChangeListener listener) {
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
                NpcPropertyEvent payload = new NpcPropertyEvent(Npc.EventType.BATTLE, battleEvent.getName(), battleSpeech, answers, 0, npc);

                notifyListeners(payload);
                break;
            case INTRODUCTION:
                //PropertyChangeListener();
                ConversationEvent conversationEvent = npc.getIntroductionEvent(event.getName());
                conversationEvent.initialSetup();
                ConversationChain conversationChain = conversationEvent.getConversationChain();

                ArrayList<String> options = conversationChain.getOptions(conversationEvent.getCurrentKey());

                String introductionEventSpeech = event.getSpeechText() + "\n";
                NpcPropertyEvent introductionPayload = new NpcPropertyEvent(Npc.EventType.INTRODUCTION, conversationEvent.getName(), conversationEvent.getCurrentKey(), options, 0, npc);
                notifyListeners(introductionPayload);
                break;
            case WORLD_EVENT:
                WorldEvent worldEvent = npc.getWorldEvent(event.getName());
                String worldEventSpeech = event.getSpeechText() + "\n";
                NpcPropertyEvent worldPayload = new NpcPropertyEvent(Npc.EventType.WORLD_EVENT, worldEvent.getName(), worldEventSpeech, null, worldEvent.getCondition(), npc);
                notifyListeners(worldPayload);
                break;
        }
    }

    public void checkAnswer(NpcButton target, int wellBeingEffect, int socialEffect){
        NpcPropertyEvent payload;
        Npc npc = target.getNpc();
        Events event = npc.findNpcEvent();

        BattleEvent battleEvent = npc.getNpcBattleEvents(event.getName());
        BattleQuestions battleQuestions = battleEvent.getBattleQuestions();

        ArrayList<String> answers = battleQuestions.getAnswers();

        // Check if it was the correct answer
        if(Objects.equals(target.getText(), battleQuestions.correctAnswer)){
            //ADD Items to room
            // We increase Player stats
            // Reduce NPC stats
            //payload = new NpcPropertyEvent(this, "Correct", battleQuestions.getVictoryText(), battleEvent);
            // A random item gets added
            Player player = Player.getInstance();
            Inventory inventory = player.getInventory();
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Alcohol");
            arrayList.add("Coffee");
            arrayList.add("Books");
            arrayList.add("Money");

            Random rand = new Random();

            String randomItem = arrayList.get(rand.nextInt(arrayList.size()));
            ArrayList<Item> item = itemManager.getItemsForRoom(randomItem);
            inventory.addItem(item.get(0));
            String itemText = "\n Congrats you were witty and won: " + item.get(0).getName();

            locationManager.removeNpcs("", npc, player.getCurrentRoom());

            payload = new NpcPropertyEvent(Npc.EventType.RESPONSE, battleEvent.getName(), battleQuestions.victoryText + itemText, null,0, npc);
            payload.setToolTipText("Enjoy your prize.");
            notifyListeners(payload);
        } else {
            Player player = Player.getInstance();
            player.changeWellbeing(wellBeingEffect);
            player.changeIntelligence(socialEffect);
            //String effectsTooltip = "Also you lost: " + Integer.toString(wellBeingEffect) + " Wellbeing, & " + Integer.toString(socialEffect) + " Social.";
            payload = new NpcPropertyEvent(Npc.EventType.RESPONSE, battleEvent.getName(), battleQuestions.losingText, null,0, npc);
            payload.setToolTipText("Well. You messed up. Wellbeing " +  Integer.toString(wellBeingEffect) + " Social " + Integer.toString(socialEffect));
            notifyListeners(payload);
        }
    }

    public void continueConversation(NpcButton target, String eventName, String optionSelected){
        NpcPropertyEvent introductionPayload;
        Boolean isFinal = false;
        Npc npc = target.getNpc();

        ConversationEvent conversationEvent = npc.getIntroductionEvent(eventName);
        ConversationChain conversationChain = conversationEvent.getConversationChain();


        String nextKey = conversationChain.getNextKey(optionSelected);
        conversationEvent.setCurrentKey(nextKey);

        ArrayList<String> options = conversationChain.getOptions(nextKey);

        for(String finalText : conversationChain.getFinalTexts()){
            if (Objects.equals(optionSelected, finalText)) {
                isFinal = true;
                break;
            }
        }

        if(isFinal) {
            introductionPayload= new NpcPropertyEvent(Npc.EventType.RESET, eventName, null, null, 0, npc);
        } else {
            introductionPayload = new NpcPropertyEvent(Npc.EventType.INTRODUCTION, eventName, conversationEvent.getCurrentKey(), options, 0, npc);
        }

        notifyListeners(introductionPayload);
    }

    public void checkWorldEventCondition(NpcButton target){
        NpcPropertyEvent payload;
        Npc npc = target.getNpc();
        Events event = npc.findNpcEvent();
        WorldEvent worldEvent = npc.getWorldEvent(event.getName());

        Player player = Player.getInstance();
        if(player.getMoney() >= worldEvent.getCondition()){
            worldEvent.unlockRoom(5);
            player.changeMoney(-20);
            payload = new NpcPropertyEvent(Npc.EventType.RESPONSE, worldEvent.getName(), worldEvent.getSuccessText(), null, 0, npc);
            worldEvent.setHasFinishedEventChain(false);
        } else {
            payload = new NpcPropertyEvent(Npc.EventType.RESPONSE, worldEvent.getName(), worldEvent.getFailText(), null, 0, npc);
        }

        notifyListeners(payload);
    }
}

package nl.rug.ai.oop.rpg.model.npc;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.ItemManager;
import nl.rug.ai.oop.rpg.model.listeners.NpcPropertyChangeEvent;
import nl.rug.ai.oop.rpg.model.listeners.NpcPropertyChangeListener;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.npc.events.battles.BattleEvent;
import nl.rug.ai.oop.rpg.model.npc.events.battles.BattleQuestions;
import nl.rug.ai.oop.rpg.model.npc.events.conversations.ConversationBuilder;
import nl.rug.ai.oop.rpg.model.npc.events.conversations.ConversationChain;
import nl.rug.ai.oop.rpg.model.npc.events.conversations.ConversationEvent;
import nl.rug.ai.oop.rpg.model.npc.events.EventBuilder;
import nl.rug.ai.oop.rpg.model.npc.events.Events;
import nl.rug.ai.oop.rpg.model.npc.events.worldevents.WorldEvent;
import nl.rug.ai.oop.rpg.model.players.Player;

import java.util.*;

/**
 * This class manages all non-player character (NPC) related activities such as
 * initialising NPCs, creating and placing NPCs, and managing NPC interactions.
 *
 * Each NPC can have different types of events, hence this acts as a bridge tlo both the model and controller,
 * to correctly manage those events.
 *
 * @author Kyriakos Hjikakou
 */
public class NpcManager {
    private ArrayList<NpcPropertyChangeListener> listeners = new ArrayList<>();

    public ArrayList<Npc> allNpcs;
    private final LocationManager locationManager;

    private final ItemManager itemManager;

    private LanguageManager languageManager;

    /**
     * Constructs a new NPC manager with given location manager, item manager, and language manager.
     *
     * @param locationManager  the location manager
     * @param itemManager      the item manager
     * @param languageManager  the language manager
     */

    public NpcManager(LocationManager locationManager, ItemManager itemManager, LanguageManager languageManager) {
        this.locationManager = locationManager;
        this.languageManager = languageManager;
        this.itemManager = itemManager;
        allNpcs  = new ArrayList<Npc>();
        initialiseNpcs();
    }

    /**
     * Notifies all registered listeners about changes.
     *
     * @param payload  the NPC property change event
     */
    private void notifyListeners(NpcPropertyChangeEvent payload) {
        Iterator<NpcPropertyChangeListener> allListeners = listeners.iterator();
        while (allListeners.hasNext()) {
            allListeners.next().propertyChange(payload);
        }
    }

    /**
     * Initialises all NPCs in the game. And their associated events.
     */
    public void initialiseNpcs(){
        Npc roomMate = createAndPlaceNpc(languageManager.getTranslation("npc.roommate.name"), 90, locationManager.getRoom(0));
        Npc coverHead = createAndPlaceNpc("Head of Cover", 90, locationManager.getRoom(1));
        Npc coverGuy = createAndPlaceNpc("That one Cover guy", 90, locationManager.getRoom(5));
        Npc eugene = createAndPlaceNpc("Eugene", 90, locationManager.getRoom(2));
        Npc vlad = createAndPlaceNpc("Vlad", 90, locationManager.getRoom(3));
        Npc max = createAndPlaceNpc("Max", 90, locationManager.getRoom(4));
        Npc securityGuard = createAndPlaceNpc("Security Guard", 90, locationManager.getRoom(2));
        Npc averageCoverMember = createAndPlaceNpc("Average Cover Member", 90, locationManager.getRoom(5));


        // Conversation Chains
        // The first key is always the NPC's name
        ArrayList<String> ZeroOption = new ArrayList<>(List.of("Hello I am your roommate that you 100% do not hate. \n Wanna party tonight? Oh wait you can't because I've got a lot of free-time unlike you."));
        ArrayList<String> FirstOption = new ArrayList<>(List.of("You forgot to do the dishes.", "Why are you like this."));
        ArrayList<String> home = new ArrayList<>(List.of("Eh. Nevermind."));

        ConversationBuilder conversationBuilder = new ConversationBuilder()
                .addToConversationChain(roomMate.getName(), ZeroOption)
                .addToConversationChain("Hello I am your roommate that you 100% do not hate. \n Wanna party tonight? Oh wait you can't because I've got a lot of free-time unlike you.", FirstOption)
                .addToDialogueConnections("You forgot to do the dishes.", "....")
                .addToConversationChain("....", home)
                .addToDialogueConnections("Why are you like this.", "I pitty you.")
                .addToConversationChain("I pitty you.", home)
                .setFinalText(home);

        ArrayList<String> firstOptionMax = new ArrayList<>(List.of("Hello, I'm Max. Nice to meet you."));
        ArrayList<String> secondOptionMax = new ArrayList<>(List.of("How are you today?", "What brings you here?"));
        ArrayList<String> endOptionMax = new ArrayList<>(List.of("Good to hear!", "Interesting..."));

        ConversationBuilder conversationBuilderMax = new ConversationBuilder()
                .addToConversationChain(max.getName(), firstOptionMax)
                .addToConversationChain("Hello, I'm Max. Nice to meet you.", secondOptionMax)
                .addToDialogueConnections("How are you today?", "I'm doing well, thank you.")
                .addToConversationChain("I'm doing well, thank you.", endOptionMax)
                .addToDialogueConnections("What brings you here?", "Just passing by.")
                .addToConversationChain("Just passing by.", endOptionMax)
                .setFinalText(endOptionMax);

        ConversationChain conversationChainMax = conversationBuilderMax.build();

        EventBuilder eventBuilderMax = new EventBuilder()
                .setInteractionName("conversation")
                .setNpcSource(max)
                .setSpeechText("Hello, there!");

        ConversationEvent conversationEventMax = eventBuilderMax.buildConversationEvent(conversationChainMax);
        max.setEvent(conversationEventMax);
        max.setNpcIntroductionEvents(conversationEventMax);

        ArrayList<String> firstOptionSecurityGuard = new ArrayList<>(List.of("Hmph?"));
        ArrayList<String> secondOptionSecurityGuard = new ArrayList<>(List.of("Can I ask a question?", "Where are the bathrooms?"));
        ArrayList<String> endOptionSecurityGuard = new ArrayList<>(List.of(";("));

        ConversationBuilder conversationBuilderSecurityGuard = new ConversationBuilder()
                .addToConversationChain(securityGuard.getName(), firstOptionSecurityGuard)
                .addToConversationChain("Hmph?", secondOptionSecurityGuard)
                .addToDialogueConnections("Can I ask a question?", "NO TALKING IN THE HALLS")
                .addToConversationChain("NO TALKING IN THE HALLS", endOptionSecurityGuard)
                .addToDialogueConnections("Where are the bathrooms?", "NO TALKING IN THE HALLS")
                .addToConversationChain("NO TALKING IN THE HALLS", endOptionSecurityGuard)
                .setFinalText(endOptionSecurityGuard);

        ConversationChain conversationChainSecurityGuard = conversationBuilderSecurityGuard.build();

        EventBuilder eventBuilderSecurityGuard = new EventBuilder()
                .setInteractionName("conversation")
                .setNpcSource(securityGuard)
                .setSpeechText("...");

        ConversationEvent conversationEventSecurityGuard = eventBuilderSecurityGuard.buildConversationEvent(conversationChainSecurityGuard);
        securityGuard.setEvent(conversationEventSecurityGuard);
        securityGuard.setNpcIntroductionEvents(conversationEventSecurityGuard);

        ArrayList<String> firstOptionAverageCoverMember = new ArrayList<>(List.of("Hello there, I'm an average Cover member. You look like a fellow board game enthusiast!"));
        ArrayList<String> secondOptionAverageCoverMember = new ArrayList<>(List.of("Do you enjoy playing Catan?", "Ever heard of Munchkin?"));
        ArrayList<String> thirdOptionAverageCoverMemberCatan = new ArrayList<>(List.of("Do you prefer the Cities & Knights expansion or the Seafarers expansion?", "Do you enjoy trading or building roads more in Catan?"));
        ArrayList<String> thirdOptionAverageCoverMemberMunchkin = new ArrayList<>(List.of("Do you like the fantasy genre of the original Munchkin or do you prefer one of its other versions?", "What's your strategy in Munchkin? Do you save up your cards or use them right away?"));
        ArrayList<String> endOptionAverageCoverMember = new ArrayList<>(List.of("That's interesting!", "Nice! I'll have to try that next time."));

        ConversationBuilder conversationBuilderAverageCoverMember = new ConversationBuilder()
                .addToConversationChain(averageCoverMember.getName(), firstOptionAverageCoverMember)
                .addToConversationChain("Hello there, I'm an average Cover member. You look like a fellow board game enthusiast!", secondOptionAverageCoverMember)
                .addToDialogueConnections("Do you enjoy playing Catan?", "Yes, I love it!")
                .addToConversationChain("Yes, I love it!", thirdOptionAverageCoverMemberCatan)
                .addToDialogueConnections("Ever heard of Munchkin?", "Yeah, it's a great game!")
                .addToConversationChain("Yeah, it's a great game!", thirdOptionAverageCoverMemberMunchkin)
                .addToDialogueConnections("Do you prefer the Cities & Knights expansion or the Seafarers expansion?", "I prefer Cities & Knights.")
                .addToConversationChain("I prefer Cities & Knights.", endOptionAverageCoverMember)
                .addToDialogueConnections("Do you enjoy trading or building roads more in Catan?", "I enjoy building roads.")
                .addToConversationChain("I enjoy building roads.", endOptionAverageCoverMember)
                .addToDialogueConnections("Do you like the fantasy genre of the original Munchkin or do you prefer one of its other versions?", "I prefer the original Munchkin.")
                .addToConversationChain("I prefer the original Munchkin.", endOptionAverageCoverMember)
                .addToDialogueConnections("What's your strategy in Munchkin? Do you save up your cards or use them right away?", "I save up my cards.")
                .addToConversationChain("I save up my cards.", endOptionAverageCoverMember)
                .setFinalText(endOptionAverageCoverMember);

        ConversationChain conversationChainAverageCoverMember = conversationBuilderAverageCoverMember.build();

        EventBuilder eventBuilderAverageCoverMember = new EventBuilder()
                .setInteractionName("conversation")
                .setNpcSource(averageCoverMember)
                .setSpeechText("Ah, a newcomer! I hope you like board games.");

        ConversationEvent conversationEventAverageCoverMember = eventBuilderAverageCoverMember.buildConversationEvent(conversationChainAverageCoverMember);
        averageCoverMember.setEvent(conversationEventAverageCoverMember);
        averageCoverMember.setNpcIntroductionEvents(conversationEventAverageCoverMember);


        ConversationChain conversationChain = conversationBuilder.build();

        EventBuilder eventBuilder3 = new EventBuilder()
                .setInteractionName("introduction")
                .setNpcSource(roomMate)
                .setSpeechText("Yeah, it's me. In your home.");
        ConversationEvent conversationEvent = eventBuilder3.buildConversationEvent(conversationChain);
        roomMate.setEvent(conversationEvent);
        roomMate.setNpcIntroductionEvents(conversationEvent);

        EventBuilder eventBuilder = new EventBuilder()
                .setInteractionName("interactionName")
                .setNpcSource(coverHead)
                .setSpeechText("Hey kiddo. Want a cover membership? \n Psst, of course you do! Who doesn't. You get free coffee, free hangouts, endless drama and if you're lucky... cirrhosis of liver!");
        WorldEvent worldEvent = eventBuilder.buildWorldEvent(WorldEvent.effectOnWorld.UNLOCK, 20, "Congrats! You're not poor.", "Oh sorry. You're poor.", locationManager);
        coverHead.setEvent(worldEvent);
        coverHead.setNpcWorldEvents(worldEvent);

        // BATTLES
        // Multiple Choice Questions
        ArrayList<String> answers =  new ArrayList<>();
        answers.add("Cover.");
        answers.add("Ibn Battuta.");
        answers.add("Sirius A.");
        answers.add("De Chemische Binding.");

        ArrayList<String> answers1 = new ArrayList<>();
        answers1.add("Neural Networks");
        answers1.add("Decision Trees");
        answers1.add("K-Nearest Neighbors");
        answers1.add("Support Vector Machines");

        ArrayList<String> answers2 = new ArrayList<>();
        answers2.add("Deep Learning");
        answers2.add("Machine Learning");
        answers2.add("Natural Language Processing");
        answers2.add("Computer Vision");

        ArrayList<String> answers3 = new ArrayList<>();
        answers3.add("Gradient Descent");
        answers3.add("Backpropagation");
        answers3.add("Overfitting");
        answers3.add("Underfitting");

        EventBuilder eventBuilder1 = new EventBuilder()
                .setInteractionName("EvilBattle")
                .setNpcSource(coverGuy)
                .setSpeechText("Death be upon you! \n");

        BattleQuestions coverQuestion = new BattleQuestions("What is the best association?", answers, "Cover", "Yo congrats!", "Boo");
        BattleEvent coverBattle = eventBuilder1.buildBattleEvent(coverQuestion);
        coverGuy.setEvent(coverBattle);
        coverGuy.setNpcBattleEvents(coverBattle);

        EventBuilder eventBuilderEugene = new EventBuilder()
                .setInteractionName("AIQuiz")
                .setNpcSource(eugene)
                .setSpeechText("Yo yo yo I have no friends. But I do have knowledge!");

        BattleQuestions eugeneQuestion = new BattleQuestions("What is considered a supervised learning model?", answers1, "Neural Networks", languageManager.getTranslation("eugeneQuestion.responseLongClaps="), languageManager.getTranslation("eugeneQuestion.responseFastClaps"));
        BattleEvent eugeneBattle = eventBuilderEugene.buildBattleEvent(eugeneQuestion);
        eugene.setEvent(eugeneBattle);
        eugene.setNpcBattleEvents(eugeneBattle);

        EventBuilder eventBuilderVlad = new EventBuilder()
                .setInteractionName(languageManager.getTranslation("eventBuilderVlad.interactionName"))
                .setNpcSource(vlad)
                .setSpeechText(languageManager.getTranslation("eventBuilderVlad.speechText"));

        BattleQuestions vladQuestion = new BattleQuestions(languageManager.getTranslation("vladQuestion.question"), answers2, languageManager.getTranslation("responseRight"), languageManager.getTranslation("vladQuestion.responseRight"), languageManager.getTranslation("vladQuestion.responseWrong"));
        BattleEvent vladBattle = eventBuilderVlad.buildBattleEvent(vladQuestion);
        vlad.setEvent(vladBattle);
        vlad.setNpcBattleEvents(vladBattle);
    }

    /**
     * Creates a new NPC with the given name and money, and places it in the specified room.
     *
     * @param npcName  the name of the NPC
     * @param money    the amount of money the NPC has
     * @param room     the room to place the NPC
     * @return the created NPC
     */
    public Npc createAndPlaceNpc(String npcName, int money, Room room) {
        Npc newNpc = new Npc(npcName, money);
        locationManager.addNPCs(npcName, newNpc, room);
        return newNpc;
    }

    /**
     * Adds a new listener to the list of NPC property change listeners.
     *
     * @param listener  the listener to add
     */
    public void addListener(NpcPropertyChangeListener listener) {
        listeners.add(listener);
    }


    /**
     * Handles the interaction with the given NPC.
     *
     * @param targetNpc         the target NPC
     * @param interactionName   the name of the interaction
     */
    public void npcInteraction(Npc targetNpc, String interactionName){
        Events event = targetNpc.findNpcEvent();

        switch (event.getEventType()) {
            case BATTLE:
                ArrayList<String> answers = new ArrayList<String>();
                BattleEvent battleEvent = targetNpc.getNpcBattleEvents(event.getName());
                BattleQuestions battleQuestions = battleEvent.getBattleQuestions();

                answers = battleQuestions.getAnswers();

                String battleSpeech = event.getSpeechText() + "\n" + battleQuestions.getQuestion();
                NpcPropertyChangeEvent payload = new NpcPropertyChangeEvent(Npc.EventType.BATTLE, battleEvent.getName(), battleSpeech, answers, 0, targetNpc);

                notifyListeners(payload);
                break;
            case INTRODUCTION:
                ConversationEvent conversationEvent = targetNpc.getIntroductionEvent(event.getName());
                conversationEvent.initialSetup();
                ConversationChain conversationChain = conversationEvent.getConversationChain();

                ArrayList<String> options = conversationChain.getOptions(conversationEvent.getCurrentKey());

                String introductionEventSpeech = event.getSpeechText() + "\n";
                NpcPropertyChangeEvent introductionPayload = new NpcPropertyChangeEvent(Npc.EventType.INTRODUCTION, conversationEvent.getName(), conversationEvent.getCurrentKey(), options, 0, targetNpc);
                notifyListeners(introductionPayload);
                break;
            case WORLD_EVENT:
                WorldEvent worldEvent = targetNpc.getWorldEvent(event.getName());
                String worldEventSpeech = event.getSpeechText() + "\n";
                NpcPropertyChangeEvent worldPayload = new NpcPropertyChangeEvent(Npc.EventType.WORLD_EVENT, worldEvent.getName(), worldEventSpeech, null, worldEvent.getCost(), targetNpc);
                notifyListeners(worldPayload);
                break;
        }
    }

    /**
     * Checks the answer provided by the player in a battle event.
     *
     * @param targetNpc         the target NPC
     * @param selectedText      the selected text (answer) by the player
     * @param wellBeingEffect   the effect on wellbeing
     * @param socialEffect      the effect on social
     */
    public void checkBattleAnswer(Npc targetNpc, String selectedText, int wellBeingEffect, int socialEffect){
        System.out.println(selectedText);
        NpcPropertyChangeEvent payload;
        Events event = targetNpc.findNpcEvent();

        BattleEvent battleEvent = targetNpc.getNpcBattleEvents(event.getName());
        BattleQuestions battleQuestions = battleEvent.getBattleQuestions();

        ArrayList<String> answers = battleQuestions.getAnswers();

        if(Objects.equals(selectedText, battleQuestions.getCorrectAnswer())){
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

            locationManager.removeNPCs("", targetNpc, player.getCurrentRoom());

            payload = new NpcPropertyChangeEvent(Npc.EventType.RESPONSE, battleEvent.getName(), battleQuestions.getVictoryText() + itemText, null,0, targetNpc);
            payload.setToolTipText("Enjoy your prize.");
            notifyListeners(payload);
        } else {
            Player player = Player.getInstance();
            player.changeWellbeing(wellBeingEffect);
            player.changeIntelligence(socialEffect);
            payload = new NpcPropertyChangeEvent(Npc.EventType.RESPONSE, battleEvent.getName(), battleQuestions.getLosingText(), null,0, targetNpc);
            payload.setToolTipText("Well. You messed up. Wellbeing " +  Integer.toString(wellBeingEffect) + " Social " + Integer.toString(socialEffect));
            notifyListeners(payload);
        }
    }

    /**
     * Continues the conversation with the given NPC.
     *
     * @param npcTarget         the target NPC
     * @param eventName         the name of the event
     * @param optionSelected    the selected option in the conversation
     */
    public void continueConversation(Npc npcTarget, String eventName, String optionSelected){
        NpcPropertyChangeEvent introductionPayload;
        Boolean isFinal = false;

        ConversationEvent conversationEvent = npcTarget.getIntroductionEvent(eventName);
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
            introductionPayload= new NpcPropertyChangeEvent(Npc.EventType.RESET, eventName, null, null, 0, npcTarget);
        } else {
            introductionPayload = new NpcPropertyChangeEvent(Npc.EventType.INTRODUCTION, eventName, conversationEvent.getCurrentKey(), options, 0, npcTarget);
        }

        notifyListeners(introductionPayload);
    }

    /**
     *Checks the conditions for a world event to go through with the given NPC.
     *
     * @param targetNpc the target NPC
     */
    public void checkWorldEventCondition(Npc targetNpc){
        NpcPropertyChangeEvent payload;
        Events event = targetNpc.findNpcEvent();
        WorldEvent worldEvent = targetNpc.getWorldEvent(event.getName());

        Player player = Player.getInstance();
        if(player.getMoney() >= worldEvent.getCost()){
            worldEvent.unlockRoom(5);
            player.changeMoney(-20);
            payload = new NpcPropertyChangeEvent(Npc.EventType.RESPONSE, worldEvent.getName(), worldEvent.getSuccessText(), null, 0, targetNpc);
            worldEvent.setHasFinishedEventChain(false);
        } else {
            payload = new NpcPropertyChangeEvent(Npc.EventType.RESPONSE, worldEvent.getName(), worldEvent.getFailText(), null, 0, targetNpc);
        }
        notifyListeners(payload);
    }
}

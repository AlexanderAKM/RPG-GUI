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
        Npc coverHead = createAndPlaceNpc(languageManager.getTranslation("npc.coverhead.name"), 90, locationManager.getRoom(1));
        Npc coverGuy = createAndPlaceNpc(languageManager.getTranslation("npc.coverguy.name"), 90, locationManager.getRoom(5));
        Npc eugene = createAndPlaceNpc(languageManager.getTranslation("npc.eugene.name"), 90, locationManager.getRoom(2));
        Npc vlad = createAndPlaceNpc(languageManager.getTranslation("npc.vlad.name"), 90, locationManager.getRoom(3));
        Npc vlad2 = createAndPlaceNpc(languageManager.getTranslation("npc.vlad2.name"), 90, locationManager.getRoom(4));
        Npc max = createAndPlaceNpc(languageManager.getTranslation("npc.max.name"), 90, locationManager.getRoom(4));
        Npc securityGuard = createAndPlaceNpc(languageManager.getTranslation("npc.securityguard.name"), 90, locationManager.getRoom(2));
        Npc averageCoverMember = createAndPlaceNpc(languageManager.getTranslation("npc.averagecovermember.name"), 90, locationManager.getRoom(5));


        // Conversation Chains
        // The first key is always the NPC's name
        ArrayList<String> ZeroOption = new ArrayList<>(List.of(languageManager.getTranslation("roommate.conversation.zerooption")));
        ArrayList<String> FirstOption = new ArrayList<>(List.of(languageManager.getTranslation("roommate.conversation.firstoption1"), languageManager.getTranslation("roommate.conversation.firstoption2")));
        ArrayList<String> home = new ArrayList<>(List.of(languageManager.getTranslation("roommate.conversation.home")));

        ConversationBuilder conversationBuilder = new ConversationBuilder()
                .addToConversationChain(roomMate.getName(), ZeroOption)
                .addToConversationChain(languageManager.getTranslation("roommate.conversation.zerooption"), FirstOption)
                .addToDialogueConnections(languageManager.getTranslation("roommate.conversation.firstoption1"), languageManager.getTranslation("roommate.conversation.response1") )
                .addToConversationChain(languageManager.getTranslation("roommate.conversation.response1") , home)
                .addToDialogueConnections(languageManager.getTranslation("roommate.conversation.firstoption2"), languageManager.getTranslation("roommate.conversation.response2") )
                .addToConversationChain(languageManager.getTranslation("roommate.conversation.response2") , home)
                .setFinalText(home);

        ArrayList<String> firstOptionMax = new ArrayList<>(List.of(languageManager.getTranslation("max.conversation.firstoption")));
        ArrayList<String> secondOptionMax = new ArrayList<>(List.of(languageManager.getTranslation("max.conversation.secondoption1"), languageManager.getTranslation("max.conversation.secondoption2")));
        ArrayList<String> endOptionMax = new ArrayList<>(List.of(languageManager.getTranslation("max.conversation.endoption1") , languageManager.getTranslation("max.conversation.endoption2")));

        ConversationBuilder conversationBuilderMax = new ConversationBuilder()
                .addToConversationChain(max.getName(), firstOptionMax)
                .addToConversationChain(languageManager.getTranslation("max.conversation.firstoption"), secondOptionMax)
                .addToDialogueConnections(languageManager.getTranslation("max.conversation.secondoption1"), languageManager.getTranslation("max.conversation.response1"))
                .addToConversationChain(languageManager.getTranslation("max.conversation.response1") , endOptionMax)
                .addToDialogueConnections(languageManager.getTranslation("max.conversation.secondoption2"), languageManager.getTranslation("max.conversation.response2"))
                .addToConversationChain(languageManager.getTranslation("max.conversation.response2"), endOptionMax)
                .setFinalText(endOptionMax);

        ConversationChain conversationChainMax = conversationBuilderMax.build();

        EventBuilder eventBuilderMax = new EventBuilder()
                .setInteractionName("conversation")
                .setNpcSource(max)
                .setSpeechText(languageManager.getTranslation("max.introduction"));

        ConversationEvent conversationEventMax = eventBuilderMax.buildConversationEvent(conversationChainMax);
        max.setEvent(conversationEventMax);
        max.setNpcIntroductionEvents(conversationEventMax);

        ArrayList<String> firstOptionSecurityGuard = new ArrayList<>(List.of(languageManager.getTranslation("securityguard.conversation.firstoption")));
        ArrayList<String> secondOptionSecurityGuard = new ArrayList<>(List.of(languageManager.getTranslation("securityguard.conversation.secondoption1"), languageManager.getTranslation("securityguard.conversation.secondoption2")));
        ArrayList<String> endOptionSecurityGuard = new ArrayList<>(List.of(languageManager.getTranslation("securityguard.conversation.endoption")));

        ConversationBuilder conversationBuilderSecurityGuard = new ConversationBuilder()
                .addToConversationChain(securityGuard.getName(), firstOptionSecurityGuard)
                .addToConversationChain(languageManager.getTranslation("securityguard.conversation.firstoption"), secondOptionSecurityGuard)
                .addToDialogueConnections(languageManager.getTranslation("securityguard.conversation.secondoption1"), languageManager.getTranslation("securityguard.conversation.response1") )
                .addToConversationChain(languageManager.getTranslation("securityguard.conversation.response1") , endOptionSecurityGuard)
                .addToDialogueConnections(languageManager.getTranslation("securityguard.conversation.secondoption2"), languageManager.getTranslation("securityguard.conversation.response1") )
                .addToConversationChain(languageManager.getTranslation("securityguard.conversation.response1") , endOptionSecurityGuard)
                .setFinalText(endOptionSecurityGuard);

        ConversationChain conversationChainSecurityGuard = conversationBuilderSecurityGuard.build();

        EventBuilder eventBuilderSecurityGuard = new EventBuilder()
                .setInteractionName("conversation")
                .setNpcSource(securityGuard)
                .setSpeechText(languageManager.getTranslation("securityguard.introduction"));

        ConversationEvent conversationEventSecurityGuard = eventBuilderSecurityGuard.buildConversationEvent(conversationChainSecurityGuard);
        securityGuard.setEvent(conversationEventSecurityGuard);
        securityGuard.setNpcIntroductionEvents(conversationEventSecurityGuard);

        ArrayList<String> firstOptionAverageCoverMember = new ArrayList<>(List.of(languageManager.getTranslation("averagecovermember.conversation.firstoption")));
        ArrayList<String> secondOptionAverageCoverMember = new ArrayList<>(List.of(languageManager.getTranslation("averagecovermember.conversation.secondoption1"), languageManager.getTranslation("averagecovermember.conversation.secondoption2")));
        ArrayList<String> thirdOptionAverageCoverMemberCatan = new ArrayList<>(List.of(languageManager.getTranslation("averagecovermember.conversation.thirdoption1"), languageManager.getTranslation("averagecovermember.conversation.thirdoption2")));
        ArrayList<String> thirdOptionAverageCoverMemberMunchkin = new ArrayList<>(List.of(languageManager.getTranslation("averagecovermember.conversation.thirdoption3"), languageManager.getTranslation("averagecovermember.conversation.thirdoption4")));
        ArrayList<String> endOptionAverageCoverMember = new ArrayList<>(List.of(languageManager.getTranslation("averagecovermember.conversation.endoption1") , languageManager.getTranslation("averagecovermember.conversation.endoption2") ));

        ConversationBuilder conversationBuilderAverageCoverMember = new ConversationBuilder()
                .addToConversationChain(averageCoverMember.getName(), firstOptionAverageCoverMember)
                .addToConversationChain(languageManager.getTranslation("averagecovermember.conversation.firstoption") , secondOptionAverageCoverMember)
                .addToDialogueConnections(languageManager.getTranslation("averagecovermember.conversation.secondoption1"), languageManager.getTranslation("averagecovermember.conversation.response1"))
                .addToConversationChain(languageManager.getTranslation("averagecovermember.conversation.response1") , thirdOptionAverageCoverMemberCatan)
                .addToDialogueConnections(languageManager.getTranslation("averagecovermember.conversation.secondoption2"), languageManager.getTranslation("averagecovermember.conversation.response2"))
                .addToConversationChain(languageManager.getTranslation("averagecovermember.conversation.response2"), thirdOptionAverageCoverMemberMunchkin)
                .addToDialogueConnections(languageManager.getTranslation("averagecovermember.conversation.thirdoption1"), languageManager.getTranslation("averagecovermember.conversation.response3"))
                .addToConversationChain(languageManager.getTranslation("averagecovermember.conversation.response3") , endOptionAverageCoverMember)
                .addToDialogueConnections(languageManager.getTranslation("averagecovermember.conversation.thirdoption2"), languageManager.getTranslation("averagecovermember.conversation.response4") )
                .addToConversationChain(languageManager.getTranslation("averagecovermember.conversation.response4"), endOptionAverageCoverMember)
                .addToDialogueConnections(languageManager.getTranslation("averagecovermember.conversation.thirdoption3") , languageManager.getTranslation("averagecovermember.conversation.response5"))
                .addToConversationChain(languageManager.getTranslation("averagecovermember.conversation.response5"), endOptionAverageCoverMember)
                .addToDialogueConnections(languageManager.getTranslation("averagecovermember.conversation.thirdoption4"), languageManager.getTranslation("averagecovermember.conversation.response6") )
                .addToConversationChain(languageManager.getTranslation("averagecovermember.conversation.response6") , endOptionAverageCoverMember)
                .setFinalText(endOptionAverageCoverMember);

        ConversationChain conversationChainAverageCoverMember = conversationBuilderAverageCoverMember.build();

        EventBuilder eventBuilderAverageCoverMember = new EventBuilder()
                .setInteractionName("conversation")
                .setNpcSource(averageCoverMember)
                .setSpeechText(languageManager.getTranslation("averagecovermember.introduction"));

        ConversationEvent conversationEventAverageCoverMember = eventBuilderAverageCoverMember.buildConversationEvent(conversationChainAverageCoverMember);
        averageCoverMember.setEvent(conversationEventAverageCoverMember);
        averageCoverMember.setNpcIntroductionEvents(conversationEventAverageCoverMember);


        ConversationChain conversationChain = conversationBuilder.build();

        EventBuilder eventBuilder3 = new EventBuilder()
                .setInteractionName("introduction")
                .setNpcSource(roomMate)
                .setSpeechText(languageManager.getTranslation("roommate.introduction"));
        ConversationEvent conversationEvent = eventBuilder3.buildConversationEvent(conversationChain);
        roomMate.setEvent(conversationEvent);
        roomMate.setNpcIntroductionEvents(conversationEvent);

        EventBuilder eventBuilder = new EventBuilder()
                .setInteractionName("interactionName")
                .setNpcSource(coverHead)
                .setSpeechText(languageManager.getTranslation("coverhead.introduction"));
        WorldEvent worldEvent = eventBuilder.buildWorldEvent(WorldEvent.effectOnWorld.UNLOCK, 20, languageManager.getTranslation("worldevent.success1"), languageManager.getTranslation("worldevent.fail1"), locationManager);
        coverHead.setEvent(worldEvent);
        coverHead.setNpcWorldEvents(worldEvent);

        // BATTLES
        // Multiple Choice Questions
        ArrayList<String> answers =  new ArrayList<>();
        answers.add(languageManager.getTranslation("answer.cover") );
        answers.add(languageManager.getTranslation("answer.ibnBattuta"));
        answers.add(languageManager.getTranslation("answer.siriusA") );
        answers.add(languageManager.getTranslation("answer.deChemischeBinding"));

        ArrayList<String> answers1 = new ArrayList<>();
        answers1.add(languageManager.getTranslation("answer.neuralNetworks"));
        answers1.add(languageManager.getTranslation("answer.decisionTrees") );
        answers1.add(languageManager.getTranslation("answer.kNearestNeighbors"));
        answers1.add(languageManager.getTranslation("answer.supportVectorMachines") );

        ArrayList<String> answers2 = new ArrayList<>();
        answers2.add(languageManager.getTranslation("answer.deepLearning"));
        answers2.add(languageManager.getTranslation("answer.machineLearning"));
        answers2.add(languageManager.getTranslation("answer.naturalLanguageProcessing"));
        answers2.add(languageManager.getTranslation("answer.computerVision"));

        ArrayList<String> answers3 = new ArrayList<>();
        answers3.add(languageManager.getTranslation("answer.gradientDescent") );
        answers3.add(languageManager.getTranslation("answer.backpropagation") );
        answers3.add(languageManager.getTranslation("answer.overfitting"));
        answers3.add(languageManager.getTranslation("answer.underfitting") );

        EventBuilder eventBuilder1 = new EventBuilder()
                .setInteractionName("EvilBattle")
                .setNpcSource(coverGuy)
                .setSpeechText(languageManager.getTranslation("interaction.evilBattle"));

        BattleQuestions coverQuestion = new BattleQuestions(languageManager.getTranslation("battleQuestion.bestAssociation"), answers, languageManager.getTranslation("answer.cover"), languageManager.getTranslation("feedback.positive1"), languageManager.getTranslation("feedback.negative1"));
        BattleEvent coverBattle = eventBuilder1.buildBattleEvent(coverQuestion);
        coverGuy.setEvent(coverBattle);
        coverGuy.setNpcBattleEvents(coverBattle);

        EventBuilder eventBuilderEugene = new EventBuilder()
                .setInteractionName("AIQuiz")
                .setNpcSource(eugene)
                .setSpeechText(languageManager.getTranslation("interaction.aiQuiz") );

        BattleQuestions eugeneQuestion = new BattleQuestions(languageManager.getTranslation("battleQuestion.supervisedLearningModel") , answers1, languageManager.getTranslation("answer.neuralNetworks") , languageManager.getTranslation("feedback.positive2"), languageManager.getTranslation("feedback.negative2"));
        BattleEvent eugeneBattle = eventBuilderEugene.buildBattleEvent(eugeneQuestion);
        eugene.setEvent(eugeneBattle);
        eugene.setNpcBattleEvents(eugeneBattle);

        EventBuilder eventBuilderVlad = new EventBuilder()
                .setInteractionName("AIQuiz")
                .setNpcSource(vlad)
                .setSpeechText(languageManager.getTranslation("interaction.aiQuizVlad") );

        BattleQuestions vladQuestion = new BattleQuestions(languageManager.getTranslation("battleQuestion.subfieldOfAI"), answers2, languageManager.getTranslation("answer.deepLearning"), languageManager.getTranslation("feedback.positive3"), languageManager.getTranslation("feedback.negative3"));
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

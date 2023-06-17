package nl.rug.ai.oop.rpg.model;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.controller.players.ChooseProgrammeController;
import nl.rug.ai.oop.rpg.controller.players.CreateCustomProgrammeController;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.RoomStateManager;
import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.npc.NpcManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.Beginning;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;
import nl.rug.ai.oop.rpg.view.SetUp;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import java.util.Set;

/**
 * This class represents the main game logic for the RPG.
 * It controls the game flow, starting the game and transitioning between the beginning and main parts of the game.
 *
 * @author Alexander Müller
 */
public class Game {
    private Player player;
    Beginning beginning;

    /**
     * Starts the game by showing the beginning part.
     * When the beginning part is done, it continues to the main part of the game.
     */
    public void start(Game game) {
        // Start with the Beginning view
        beginning = new Beginning(game);
        //RoomLanguageManager.loadLanguage("English");
        beginning.show();
    }

    public void chooseProgramme(){
        ChooseProgrammeController chooseProgrammeController = new ChooseProgrammeController(this);
        beginning.chooseProgramme(chooseProgrammeController);
    }

    public void createCustomProgramme(){
        CreateCustomProgrammeController createCustomProgrammeController = new CreateCustomProgrammeController(this);
        beginning.createCustomProgramme(createCustomProgrammeController);
    }

    public void initialise(){
        beginning.disposeFrame();
        SetUp setUp = new SetUp();
        setUp.start(Player.getInstance().getLanguage(), this);
    }


    /**
     * Continues the game after the beginning part is done.
     * It retrieves the player instance and starts the main part of the game.
     */
    private void continueGame() {
        // After the beginning part is done, we should have a player instance now
        this.player = Player.getInstance();
    }
}

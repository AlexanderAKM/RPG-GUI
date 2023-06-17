package nl.rug.ai.oop.rpg.model;

import nl.rug.ai.oop.rpg.controller.players.ChooseProgrammeController;
import nl.rug.ai.oop.rpg.controller.players.CreateCustomProgrammeController;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.Beginning;
import nl.rug.ai.oop.rpg.view.SetUp;

/**
 * The Game class represents the main game logic for the RPG.
 * It controls the game flow, starting the game and transitioning between the beginning and main parts of the game.
 *
 * @author Alexander Müller & Robert Hielkema
 */
public class Game {
    private Player player;
    Beginning beginning;

    /**
     * Starts the game by showing the beginning part.
     * When the beginning part is done, it continues to the main part of the game.
     */
    public void start(Game game) {
        beginning = new Beginning(game);
        beginning.show();
    }

    /**
     * Initiates the process of choosing a programme for the player.
     */
    public void chooseProgramme(){
        ChooseProgrammeController chooseProgrammeController = new ChooseProgrammeController(this);
        beginning.chooseProgramme(chooseProgrammeController);
    }

    /**
     * Initiates the process of creating a custom programme for the player.
     */
    public void createCustomProgramme(){
        CreateCustomProgrammeController createCustomProgrammeController = new CreateCustomProgrammeController(this);
        beginning.createCustomProgramme(createCustomProgrammeController);
    }

    /**
     * Initialises the game setup after the player has chosen or created a programme.
     */
    public void initialise(){
        beginning.disposeFrame();
        SetUp setUp = new SetUp();
        setUp.start(Player.getInstance().getLanguage(), this);
    }
}

package nl.rug.ai.oop.rpg.controller.players;

import nl.rug.ai.oop.rpg.model.Game;
import nl.rug.ai.oop.rpg.model.players.ChooseProgramme;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.Beginning;
import nl.rug.ai.oop.rpg.view.players.ChooseProgrammeView;
import nl.rug.ai.oop.rpg.view.players.CreateCustomProgrammeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CreateCustomProgrammeController class is a controller that handles user actions for creating a custom programme.
 * It implements the ActionListener interface.
 *
 * @author RobertHielkema
 */
public class CreateCustomProgrammeController implements ActionListener {

    Game model;

    /**
     * Constructs a CreateCustomProgrammeController object.
     *
     * @param model the Beginning object
     */
    public CreateCustomProgrammeController(Game model) {
        this.model = model;
    }

    /**
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        String[] parts = actionCommand.split("\\.");
        String programme = parts[0];
        int intelligence = Integer.parseInt(parts[1]);
        int social = Integer.parseInt(parts[2]);
        Player.getInstance().customProgramme(programme, intelligence, social);
        model.initialise();
    }
}
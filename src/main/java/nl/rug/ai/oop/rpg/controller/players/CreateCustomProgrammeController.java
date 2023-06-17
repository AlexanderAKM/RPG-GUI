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
 * @author Robert Hielkema
 */
public class CreateCustomProgrammeController implements ActionListener {

    Game model;
    CreateCustomProgrammeView view;

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
        if (actionCommand.equals("create")){
            String programmeName = view.getCustomProgrammetextField();
            int intelligence = view.getCustomIntelligence();
            int social = view.getCustomSocial();
            Player.getInstance().customProgramme(programmeName, intelligence, social);
            model.initialise();
        }
    }

    /**
     * @param createCustomProgrammeView set the view to be able to get the stats from the player input
     */
    public void setCreateCustomProgrammeView(CreateCustomProgrammeView createCustomProgrammeView) {
        this.view = createCustomProgrammeView;
    }
}
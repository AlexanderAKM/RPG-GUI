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
 * The ChooseProgrammeController class is a controller that handles user actions for choosing a program.
 * It implements the ActionListener interface.
 *
 * @author RobertHielkema
 */
public class CreateCustomProgrammeController implements ActionListener {

    Game model;

    /**
     * Constructs a ChooseProgrammeController object.
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
        if (actionCommand.equals("create custom")) {
            //String programme = model.getCustomProgrammeName();
            //int intelligence = model.getCustomIntelligence();
            //int social = model.getCustomSocial();
            //Player.getInstance().customProgramme(programme, intelligence, social);
            model.initialise();
        }
    }
}
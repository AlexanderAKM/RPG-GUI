package nl.rug.ai.oop.rpg.controller.location;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LocationController class handles the actions related to location selection.
 * It implements the ActionListener interface to respond to user actions.
 *
 * @author Victoria Polaka
 */
public class LocationController implements ActionListener {
    private LocationManager model;
    private LanguageManager languageManager;

    /**
     * Constructs a LocationController.
     *
     * @param model                The LocationManager instance.
     * @param languageManager  The RoomLanguageManager instance.
     */
    public LocationController(LocationManager model, LanguageManager languageManager) {
        this.model = model;
        this.languageManager = languageManager;
    }

    /**
     * Handles the actionPerformed event.
     * Determines what the action command is and triggers the corresponding location change in the model.
     *
     * @param e The ActionEvent object representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals(languageManager.getTranslation("home"))) {
            model.movePlayer(0);
        } else if (actionCommand.equals(languageManager.getTranslation("outside"))) {
            model.movePlayer(1);
        } else if (actionCommand.equals(languageManager.getTranslation("Aletta_Jacobs_Hall"))) {
            model.movePlayer(2);
        } else if (actionCommand.equals(languageManager.getTranslation("bernoulliborg"))) {
            model.movePlayer(3);
        } else if (actionCommand.equals(languageManager.getTranslation("TheBBCanteen"))) {
            model.movePlayer(4);
        } else if (actionCommand.equals(languageManager.getTranslation("cover_room"))) {
            model.movePlayer(5);
        }
    }
}

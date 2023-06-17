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

    private static final int HOME = 0;
    private static final int OUTSIDE = 1;
    private static final int ALETTA_JACOBS_HALL = 2;
    private static final int BERNOULLIBORG = 3;
    private static final int THE_BB_CANTEEN = 4;
    private static final int COVER_ROOM = 5;

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
            model.movePlayer(HOME);
        } else if (actionCommand.equals(languageManager.getTranslation("outside"))) {
            model.movePlayer(OUTSIDE);
        } else if (actionCommand.equals(languageManager.getTranslation("Aletta_Jacobs_Hall"))) {
            model.movePlayer(ALETTA_JACOBS_HALL);
        } else if (actionCommand.equals(languageManager.getTranslation("bernoulliborg"))) {
            model.movePlayer(BERNOULLIBORG);
        } else if (actionCommand.equals(languageManager.getTranslation("TheBBCanteen"))) {
            model.movePlayer(THE_BB_CANTEEN);
        } else if (actionCommand.equals(languageManager.getTranslation("cover_room"))) {
            model.movePlayer(COVER_ROOM);
        }
    }
}

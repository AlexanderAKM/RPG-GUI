package nl.rug.ai.oop.rpg.controller.location;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.RoomLanguageManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationController implements ActionListener {
    private LocationManager model;
    private RoomLanguageManager roomLanguageManager;

    public LocationController(LocationManager model, RoomLanguageManager roomLanguageManager) {
        this.model = model;
        this.roomLanguageManager = roomLanguageManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals(roomLanguageManager.getTranslation("home"))) {
            model.movePlayer(0);
        } else if (actionCommand.equals(roomLanguageManager.getTranslation("outside"))) {
            model.movePlayer(1);
        } else if (actionCommand.equals(roomLanguageManager.getTranslation("Aletta_Jacobs_Hall"))) {
            model.movePlayer(2);
        } else if (actionCommand.equals(roomLanguageManager.getTranslation("bernoulliborg"))) {
            model.movePlayer(3);
        } else if (actionCommand.equals(roomLanguageManager.getTranslation("TheBBCanteen"))) {
            model.movePlayer(4);
        } else if (actionCommand.equals(roomLanguageManager.getTranslation("cover_room"))) {
            model.movePlayer(5);
        }
    }
}


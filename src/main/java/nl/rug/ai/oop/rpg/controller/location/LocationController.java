package nl.rug.ai.oop.rpg.controller.location;
import nl.rug.ai.oop.rpg.model.location.LocationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationController implements ActionListener {
    private LocationManager model;
    public LocationController(LocationManager model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object card = e.getSource();
        switch (e.getActionCommand()) {
            case "n":
                model.movePlayer("n");
                break;
            case "e":
                model.movePlayer("e");
                break;
            case "s":
                model.movePlayer("s");
                break;
            case "w":
                model.movePlayer("w");
                break;
        }
    }
}



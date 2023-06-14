package nl.rug.ai.oop.rpg.model.location;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class RoomLanguageManager {
    private ResourceBundle resourceBundle;

    //public RoomLanguageManager(String languageCode) {
    //    loadLanguage(languageCode);
    //}

    public void loadLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
            resourceBundle = ResourceBundle.getBundle("roomTranslations.roomTranslations", locale);
        }

    public String getTranslation(String key) {
        return resourceBundle.getString(key);
    }
}

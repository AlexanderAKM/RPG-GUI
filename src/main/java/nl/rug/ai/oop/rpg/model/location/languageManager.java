package nl.rug.ai.oop.rpg.model.location;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manages the language translations for the rooms and some of the gamepanel view in the RPG game.
 *
 * @author Victoria Polaka
 */
public class languageManager implements Serializable {
    private transient ResourceBundle resourceBundle;

    //private transient ResourceBundle resourceBundle;


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Serialize necessary data from resourceBundle, e.g., locale and translations
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // Deserialize necessary data and reconstruct the resourceBundle
    }


    /**
     * Loads the language translations for the specified language code.
     *
     * @param languageCode the language code (e.g either "en" or "english")
     */
    public void loadLanguage(String languageCode, String directoryName) {
        Locale locale = new Locale(languageCode);
        resourceBundle = ResourceBundle.getBundle(directoryName, locale);
    }

    /**
     * Retrieves the translated string for the given key.
     *
     * @param key the translation key
     * @return the translated string
     */
    public String getTranslation(String key) {
        return resourceBundle.getString(key);
    }
}

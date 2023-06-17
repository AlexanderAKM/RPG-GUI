package nl.rug.ai.oop.rpg.model.location;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manages the language translations.
 *
 * @author Victoria Polaka
 */
public class LanguageManager implements Serializable {
    private transient ResourceBundle resourceBundle;

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

package nl.rug.ai.oop.rpg.model.NPC;

import java.util.Locale;
import java.util.ResourceBundle;

public class NpcLanguageManager {
        private ResourceBundle resourceBundle;

        /**
         * Loads the language translations for the specified language code.
         *
         * @param languageCode the language code (e.g either "en" or "english")
         */
        public void loadLanguage(String languageCode) {
            Locale locale = new Locale(languageCode);
            resourceBundle = ResourceBundle.getBundle("roomTranslations.roomTranslations", locale);
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

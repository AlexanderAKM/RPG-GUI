package nl.rug.ai.oop.rpg.model.npc;

import java.util.Locale;
import java.util.ResourceBundle;

public class NpcLanguageManager {
        private ResourceBundle resourceBundle;

        public NpcLanguageManager(String languageCode, String directory) {
            System.out.println(languageCode);
            Locale locale = new Locale(languageCode);
            resourceBundle = ResourceBundle.getBundle(directory, locale);
        }

        public String getTranslation(String key) {
            return resourceBundle.getString(key);
        }

}

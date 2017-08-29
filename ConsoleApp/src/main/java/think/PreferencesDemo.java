package think;

import java.util.prefs.Preferences;

public class PreferencesDemo {

    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "ZH");
        prefs.putInt("Code", 86);

        for (String key : prefs.keys()) {
            System.out.println(key + ": " + prefs.get(key, null));
        }

    }
}

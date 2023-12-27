package MusicComposition;

public class RockSong extends Song {
    private static final String style = "Rock";
    /**
     * Constructs a PopSong object using parent class constructor and own style parameter.
     */
    public RockSong(String title, int duration) {
        super(title, duration, style);
    }
}

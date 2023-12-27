package MusicComposition;

public class JazzSong extends Song {
    private static final String style = "Jazz";

    /**
     * Constructs a PopSong object using parent class constructor and own style parameter.
     */
    public JazzSong(String title, int duration) {
        super(title, duration, style);
    }
}

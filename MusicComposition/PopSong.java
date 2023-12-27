package MusicComposition;

public class PopSong extends Song{
    private static final String style = "Pop";
    /**
     * Constructs a PopSong object using parent class constructor and own style parameter.
     */
    public PopSong(String title, int duration) {
        super(title, duration, style);
    }
}

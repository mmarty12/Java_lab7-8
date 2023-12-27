package MusicComposition;

public class Song {
     String title;
     int duration;
     String style;

    /**
     * Constructs a Song object.
     *
     * @param title represents the song title.
     * @param duration represents the song duration.
     * @param style represents the song style.
     */
    public Song(String title, int duration, String style) {
        this.title = title;
        this.duration = duration;
        this.style = style;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getStyle() {
        return style;
    }

    /**
     * Overridden toString method that represents the object as a string.
     *
     * @return A string that represents the song.
     */
    @Override
    public String toString() {
        return "Title: " + title + ", Duration: " + duration + "s" + ", Style: " + style;
    }
}
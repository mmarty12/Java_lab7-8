package MusicComposition;
import Set.MusicCompositionSet;

import java.util.*;

public class Album {
    private MusicCompositionSet compositions = new MusicCompositionSet();

    /**
     * Method to add a song to the album.
     *
     * @param composition represents a song to add.
     */
    public void addComposition(Song composition) {
        if(composition == null) throw new MusicCompositionException("Invalid input. Composition can't be a null");
        if(composition.duration <= 0) throw new MusicCompositionException("Invalid input. Duration can't be less/equal 0");
        this.compositions.add(composition);
    }

    /**
     * Method to calculate the general length of the album by summing up the duration property.
     *
     * @return the total duration of the album.
     */
    public int calculateAlbumDuration() {
        int totalDuration = 0;
        for (Song composition : compositions) {
            totalDuration += composition.getDuration();
        }
        return totalDuration;
    }

    /**
     * Sorting method by style field.
     */
    public Song[] sortByStyle() {
        List<Song> songList = new ArrayList<>(compositions);
        songList.sort(Comparator.comparing(Song::getStyle));
        Song[] sortedSongs = new Song[songList.size()];
        return songList.toArray(sortedSongs);
    }

    /**
     * Find compositions within a specified duration range.
     *
     * @param min minimum duration
     * @param max maximum duration
     * @return a list of compositions within the specified duration range.
     */
    public MusicCompositionSet findCompositionsInLengthRange(int min, int max) {
        MusicCompositionSet result = new MusicCompositionSet();
        for (Song composition : compositions) {
            if (composition.getDuration() >= min && composition.getDuration() <= max) {
                result.add(composition);
            }
        }
        return result;
    }

    /**
     * Method to display each song of the album.
     */
    public void displayAlbumContent() {
        for (Song composition : compositions) {
            System.out.println(composition);
        }
    }
}

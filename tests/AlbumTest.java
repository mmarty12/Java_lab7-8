package tests;

import MusicComposition.*;
import Set.MusicCompositionSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AlbumTest {
    private Album album;

    @BeforeEach
    public void setUp() {
        album = new Album();
    }

    @Test
    public void testAddComposition() {
        Song song = new Song("Test Song", 200, "unknown");
        album.addComposition(song);

        assertDoesNotThrow(Album::new);
    }

    @Test
    public void testAddCompositionNull() {
        assertThrows(MusicCompositionException.class, () -> {
            album.addComposition(null);;
        });
    }

    @Test
    public void testAddCompositionInvalidDuration() {
        Song song = new Song("Test Song", 0, "unknown");

        assertThrows(MusicCompositionException.class, () -> {
            album.addComposition(song);
        });
    }

    @Test
    public void testCalculateAlbumDuration() {
        Song song1 = new Song("Song 1", 300, "unknown");
        Song song2 = new Song("Song 2", 240, "unknown");
        album.addComposition(song1);
        album.addComposition(song2);

        assertEquals(540, album.calculateAlbumDuration());
    }

    @Test
    public void testSortByStyle() {
        Song song1 = new Song("Song 1", 300, "Pop");
        Song song2 = new Song("Song 2", 240, "Rock");
        Song song3 = new Song("Song 3", 180, "Jazz");
        album.addComposition(song1);
        album.addComposition(song2);
        album.addComposition(song3);

        Song[] sortedSongs = album.sortByStyle();

        assertArrayEquals(sortedSongs, new Song[]{song3, song1, song2});
    }

    @Test
    public void testFindCompositionsInLengthRange() {
        Song song1 = new Song("Song 1", 300, "unknown");
        Song song2 = new Song("Song 2", 240, "unknown");
        Song song3 = new Song("Song 3", 180, "unknown");
        album.addComposition(song1);
        album.addComposition(song2);
        album.addComposition(song3);

        MusicCompositionSet result = album.findCompositionsInLengthRange(200, 300);
        assertEquals(2, result.size());
        assertTrue(result.contains(song1));
        assertTrue(result.contains(song2));
        assertFalse(result.contains(song3));
    }

    @Test
    public void testDisplayAlbumContent() {
        Album album = new Album();
        Song song1 = new Song("Song 1", 300, "Rock");
        Song song2 = new Song("Song 2", 240, "Pop");
        album.addComposition(song1);
        album.addComposition(song2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        album.displayAlbumContent();

        System.setOut(System.out);

        String expectedOutput = "Title: Song 1, Duration: 300s, Style: Rock" + System.lineSeparator()
                + "Title: Song 2, Duration: 240s, Style: Pop" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }
}

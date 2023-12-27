package tests;
import Set.*;
import MusicComposition.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class SetTest {
    private MusicCompositionSet musicSet;

    @BeforeEach
    public void setUp() {
        musicSet = new MusicCompositionSet();
    }

    //constructors
    @Test
    void testEmptyConstructor() {
        musicSet = new MusicCompositionSet();
        assertTrue(musicSet.isEmpty());
        assertEquals(0, musicSet.size());
    }

    @Test
    void testConstructorWithSingleSong() {
        Song song = new Song("Song", 200, "unknown");
        musicSet = new MusicCompositionSet(song);

        assertFalse(musicSet.isEmpty());
        assertEquals(1, musicSet.size());
        assertTrue(musicSet.contains(song));
    }

    @Test
    void testConstructorWithCollection_NoDuplicates() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        List<Song> compositions = new ArrayList<>();
        compositions.add(song1);
        compositions.add(song2);
        compositions.add(song3);

        MusicCompositionSet musicSet = new MusicCompositionSet(compositions);
        assertEquals(3, musicSet.size());
        assertTrue(musicSet.contains(song2));
    }

    @Test
    void testConstructorWithCollection_Duplicates() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        List<Song> compositions = new ArrayList<>();
        compositions.add(song1);
        compositions.add(song1);
        compositions.add(song2);
        compositions.add(song2);
        compositions.add(song3);
        compositions.add(song3);

        MusicCompositionSet musicSet = new MusicCompositionSet(compositions);
        assertEquals(3, musicSet.size());
        assertTrue(musicSet.contains(song2));
    }

    //size
    @Test
    void testSizeEmpty() {
        assertEquals(0, musicSet.size());
    }

    @Test
    void testSizeAfterAddingElements() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        musicSet.add(song1);
        musicSet.add(song2);
        musicSet.add(song3);
        assertEquals(3, musicSet.size());
    }

    @Test
    void testSizeAfterAddingElementTwice() {
        Song song1 = new Song("Song A", 300, "unknown");
        musicSet = new MusicCompositionSet(song1);
        musicSet.add(song1);

        assertEquals(1, musicSet.size());
    }

    //isEmpty
    @Test
    void testIsEmptyTrue() {
        assertTrue(new MusicCompositionSet().isEmpty());
    }

    @Test
    void testIsEmptyFalse() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        musicSet.add(song1);
        musicSet.add(song2);
        musicSet.add(song3);

        assertFalse(musicSet.isEmpty());
    }

    //contains
    @Test
    void testContainsTrue() {
        Song song1 = new Song("Song A", 300, "unknown");
        musicSet.add(song1);

        assertTrue(musicSet.contains(song1));
    }

    @Test
    void testContainsFalse() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        musicSet.add(song1);

        assertFalse(musicSet.contains(song2));
    }

    //containsAll
    @Test
    void testContainsAllTrue() {
        List<Song> songs = Arrays.asList(
                new Song("Song A", 300, "unknown"),
                new Song("Song B", 240, "unknown"),
                new Song("Song C", 180, "unknown")
        );

        assertTrue(musicSet.addAll(songs));
        assertTrue(musicSet.containsAll(songs));
        assertEquals(songs.size(), musicSet.size());
    }

    @Test
    void testContainsAllFalse() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 210, "unknown");

        musicSet.add(song1);
        musicSet.add(song2);

        assertFalse(musicSet.containsAll(Arrays.asList(song1, song2, song3)));
    }

    //add
    @Test
    void testAdd() {
        Song song = new Song("New Song", 180, "unknown");

        assertTrue(musicSet.add(song));
        assertEquals(1, musicSet.size());
    }

    @Test
    void testAddDuplicate() {
        Song song = new Song("Duplicate Song", 240, "unknown");

        musicSet.add(song);
        musicSet.add(song);

        assertEquals(1, musicSet.size());
    }

    //addAll
    @Test
    void testAddAll() {
        List<Song> songs = Arrays.asList(
                new Song("Song A", 300, "unknown"),
                new Song("Song B", 240, "unknown"),
                new Song("Song C", 180, "unknown")
        );

        assertTrue(musicSet.addAll(songs));
        assertEquals(songs.size(), musicSet.size());
    }

    @Test
    void testAddAll_EmptyCollection() {
        assertFalse(musicSet.addAll(new ArrayList<>()));
    }

    @Test
    void testAddAll_SomeElementsAreInTheSet() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        List<Song> existing = Arrays.asList(song1, song2);
        List<Song> toAdd = Arrays.asList(song2, song3);

        musicSet.addAll(existing);

        assertTrue(musicSet.addAll(toAdd));
        assertTrue(musicSet.containsAll(toAdd));
    }

    //toArray
    @Test
    void testToArrayReturnsArrayContainingAllElements() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        musicSet.add(song1);
        musicSet.add(song2);
        musicSet.add(song3);

        Object[] result = musicSet.toArray();

        assertEquals(3, result.length);

        assertEquals(song1, result[0]);
        assertEquals(song2, result[1]);
        assertEquals(song3, result[2]);
    }

    @Test
    void testToArrayReturnsEmptyArray() {
        assertEquals(0, musicSet.toArray().length);
    }

    @Test
    void testToArrayReturnsEmptyArrayWhileGivenAnotherArray() {
        Song[] given = new Song[3];
        Song[] result = musicSet.toArray(given);

        assertArrayEquals(new Song[]{null, null, null}, result);
    }

    @Test
    void testToArrayLargerArrayGiven() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        musicSet.add(song1);
        musicSet.add(song2);
        musicSet.add(song3);

        Song[] given = new Song[5]; // Larger array than the number of elements added
        Song[] result = musicSet.toArray(given);

        assertArrayEquals(new Song[]{song1, song2, song3, null, null}, result);
    }

    @Test
    void testToArraySmallerArrayGiven() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        musicSet.add(song1);
        musicSet.add(song2);
        musicSet.add(song3);

        Song[] given = new Song[1]; // Smaller array than the number of elements added
        Song[] result = musicSet.toArray(given);

        assertArrayEquals(new Song[]{song1, song2, song3}, result);
    }

    //iterator
    @Test
    void testIteratorNoElements() {
        assertFalse(musicSet.iterator().hasNext());
    }

    @Test
    void testIterator() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        musicSet.add(song1);
        musicSet.add(song2);
        musicSet.add(song3);

        Iterator<Song> iterator = musicSet.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(song1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(song2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(song3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorNoNext() {
        Song song1 = new Song("Song A", 300, "unknown");
        musicSet.add(song1);

        Iterator<Song> iterator = musicSet.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(song1, iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    //remove
    @Test
    void testRemove() {
        Song song = new Song("Song to Remove", 300, "unknown");

        musicSet.add(song);
        assertTrue(musicSet.remove(song));
        assertFalse(musicSet.contains(song));
        assertEquals(0, musicSet.size());
    }

    @Test
    void testRemoveNonExistent() {
        Song song = new Song("Non-Existent Song", 240, "unknown");

        assertFalse(musicSet.remove(song));
        assertFalse(musicSet.contains(song));
        assertEquals(0, musicSet.size());
    }

    @Test
    void testRemoveExistingElementRemoved() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        musicSet.add(song1);
        musicSet.add(song2);
        musicSet.add(song3);

        Iterator<Song> iterator = musicSet.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.equals(song1)) {
                iterator.remove();
            }
        }

        assertFalse(musicSet.contains(song1));
        assertTrue(musicSet.contains(song2));
        assertTrue(musicSet.contains(song3));
    }

    @Test
    void testRemoveMultipleRemoveCalls() {
        Song song1 = new Song("Song A", 300, "unknown");

        musicSet.add(song1);

        Iterator<Song> iterator = musicSet.iterator();
        iterator.next();
        iterator.remove();

        assertThrows(IllegalStateException.class, iterator::remove);
    }

    //retainAll
    @Test
    void testRetainAllHasOnlySpecifiedElements() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        musicSet.addAll(Arrays.asList(song1, song2, song3));

        assertTrue(musicSet.retainAll(Arrays.asList(song2, song3)));

        assertTrue(musicSet.contains(song2));
        assertTrue(musicSet.contains(song3));
        assertFalse(musicSet.contains(song1));
    }

    @Test
    void testRetainAllEmptyCollection() {
        Song song1 = new Song("Song A", 300, "unknown");
        Song song2 = new Song("Song B", 240, "unknown");
        Song song3 = new Song("Song C", 180, "unknown");

        musicSet.addAll(Arrays.asList(song1, song2, song3));

        assertTrue(musicSet.retainAll(Arrays.asList()));

        assertFalse(musicSet.contains(song1));
        assertFalse(musicSet.contains(song2));
        assertFalse(musicSet.contains(song3));
    }

    //clear
    @Test
    void testClear() {
        musicSet.add(new Song("Song 1", 300, "unknown"));
        musicSet.add(new Song("Song 2", 240, "unknown"));
        musicSet.add(new Song("Song 3", 180, "unknown"));

        assertFalse(musicSet.isEmpty());
        musicSet.clear();
        assertTrue(musicSet.isEmpty());
        assertEquals(0, musicSet.size());
    }

    //increaseCapacity
    @Test
    void testIncreaseCapacity() {
        int n = musicSet.INITIAL_CAPACITY;

        for (int i = 1; i <= n+1; i++) {
            musicSet.add(new Song("Song 1", 300, "unknown"));
        }

        assertEquals(n+1, musicSet.size());
    }

    //toString
    @Test
    void testToString() {
        Song song1 = new Song("Song A", 300, "unknown");

        musicSet.add(song1);

        String expectedString = "MusicCompositionSet: { Title: Song A, Duration: 300s, Style: unknown }";
        assertEquals(expectedString, musicSet.toString());
    }
}

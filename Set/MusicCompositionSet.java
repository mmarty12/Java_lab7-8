package Set;

import MusicComposition.*;
import java.util.*;

public class MusicCompositionSet implements Set<Song> {
    public int INITIAL_CAPACITY = 15;
    final private double CAPACITY_INCREMENT = 0.3;
    private Song[] elements;
    private int size;

    /**
     * Constructs an empty MusicCompositionSet with an initial number of elements.
     */
    public MusicCompositionSet() {
        this.elements = new Song[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Constructs a MusicCompositionSet with the specified song.
     *
     * @param song the song to be added to the set
     */
    public MusicCompositionSet(Song song) {
        this.elements = new Song[INITIAL_CAPACITY];
        this.size = 0;
        add(song);
    }

    /**
     * Constructs a MusicCompositionSet containing elements of the specified collection.
     *
     * @param collection the collection of elements to be placed into this set
     */
    public MusicCompositionSet(Collection<Song> collection) {
        this.elements = new Song[INITIAL_CAPACITY];
        this.size = 0;
        if(collection.isEmpty()) return;
        addAll(collection);
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if this set contains no elements.
     *
     * @return {@code true} if this set contains no elements, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if this set contains the specified object.
     *
     * @param object the object to be checked for presence in this set
     * @return {@code true} if this set contains the specified object, {@code false} otherwise
     */
    @Override
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an array containing all the elements in this set.
     *
     * @return an array containing all the elements in this set
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, size);
    }

    /**
     * Returns an array containing all the elements in this set; the runtime type
     * of the returned array is that of the specified array.
     *
     * @param a the array into which the elements of this set are to be stored, if it is large enough;
     *          otherwise, a new array of the same runtime type is allocated for this purpose
     * @param <T> the runtime type of the array to contain the collection
     * @return an array containing all the elements in this set
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return Arrays.copyOf(elements, size, (Class<? extends T[]>) a.getClass());
        } else {
            System.arraycopy(elements, 0, a, 0, size);
            if (a.length > size) {
                a[size] = null;
            }
            return a;
        }
    }

    /**
     * Returns an iterator over the elements in this set.
     *
     * @return an iterator over the elements in this set
     */
    @Override
    public Iterator<Song> iterator() {
        return new MusicCompositionIterator();
    }

    /**
     * Private inner class that implements the Iterator interface for MusicCompositionSet.
     */
    private class MusicCompositionIterator implements Iterator<Song> {
        private int currentIndex = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Song next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the set");
            }
            return elements[currentIndex++];
        }

        /**
         * Removes the last element returned by the iterator from the underlying collection.
         *
         * @throws IllegalStateException  if the {@code next} method has not yet been called, or
         *                                  if the {@code remove} method has already been called
         *                                  after the last call to the {@code next} method
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public void remove() {
            if (currentIndex <= 0) {
                throw new IllegalStateException("next() has not been called, or remove() has been called more than once for the current element");
            }
            MusicCompositionSet.this.remove(elements[currentIndex - 1]);
            currentIndex--;
        }
    }

    /**
     * Adds the specified song to this set if it is not already present.
     *
     * @param song to add to this set
     * @return {@code true} if this set did not already contain the specified song,
     *         {@code false} if the song is already present in the set
     */
    @Override
    public boolean add(Song song) {
        if(contains(song)) return false;

        if(size != 0 && size == elements.length) increaseCapacity();

        if (song != null) this.elements[size] = song;
        size++;
        return true;
    }


    /**
     * Method to increase capacity of the set by the specified earlier CAPACITY_INCREMENT value.
     */
    private void increaseCapacity() {
        int newCapacity =  size + (int) (size * CAPACITY_INCREMENT);
        this.elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * Returns {@code true} if this set contains all the elements of the specified
     * collection.
     *
     * @param collection collection to be checked for containment in this set
     * @return {@code true} if this set contains all the elements of the specified
     * collection
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object obj : collection) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Adds all the elements in the specified collection to this set
     *
     * @param collection collection containing elements to be added to this set
     * @return {@code true} if this set changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends Song> collection) {
        boolean flag = false;
        for (Song song : collection) {
            if (add(song)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Retains only the elements in this set that are contained in the specified
     * collection.
     *
     * @param c collection containing elements to be retained in this set
     * @return {@code true} if this set changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean flag = false;
        Iterator<Song> iterator = iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (!c.contains(song)) {
                iterator.remove();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Removes the specified object from the set.
     *
     * @param object the object to be removed from the set
     * @return {@code true} if this set contained the specified object,
     *         {@code false} otherwise (if the object is not present in the set)
     */
    @Override
    public boolean remove(Object object) {
        if (object == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    removeElementAtIndex(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (object.equals(elements[i])) {

                    removeElementAtIndex(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes the specified object at the certain index from the set.
     *
     * @param index the index of the object to be removed from the set
     */
    private void removeElementAtIndex(int index) {
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
    }

    /**
     * Removes from this set all of its elements that are contained in the specified
     * collection.
     *
     * @param collection collection containing elements to be removed from this set
     * @return {@code true} if this set changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean flag = false;
        Iterator<Song> iterator = iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (collection.contains(song)) {
                iterator.remove();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Removes all the elements from this set. The set will be empty after this call returns.
     */
    @Override
    public void clear() {
        this.elements = new Song[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Returns a string representation of the elements in this set.
     *
     * @return a string representation of the elements in this set
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MusicCompositionSet: { ");
        for (int i = 0; i < size; i++) {
            result.append(elements[i]);
            if (i != size - 1) {
                result.append(", ");
            }
        }
        result.append(" }");
        return result.toString();
    }
};
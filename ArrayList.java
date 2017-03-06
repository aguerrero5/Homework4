// Class ArrayList<T> can be used to store a list of values of type T.

import java.util.*;

public class ArrayList<T> implements ListInterface<T>{
    private T[] elementData; // list of values
    private int size;        // current number of elements in the list

    public static final int DEFAULT_CAPACITY = 10;

    /*constructs an empty list of default capacity*/
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /* capacity >= 0 (throws IllegalArgumentException if not)
       post: constructs an empty list with the given capacity*/
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = (T[]) new Object[capacity];
        size = 0;
    }

    /* Gets the length of this list.
       @return  The integer number of entries currently in the list. */
    public int getLength() {
        return size;
    }

    /* Retrieves the entry at a given position in this list. */
    public T getEntry(int index) {
        checkIndex(index);
        return elementData[index];
    }

    /*returns the position of the first occurrence of the given
       value (-1 if not found)*/
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /* Sees whether this list is empty.
       @return  True if the list is empty, or false if not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /* Sees whether this list contains a given entry. */
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

   /* Adds a new entry to the end of this list.
       Entries currently in the list are unaffected.
       The list's size is increased by 1.
       @param newEntry  The object to be added as a new entry. */
    public void add(T value) {
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

     /* Adds a new entry at a specified position within this list. */
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ensureCapacity(size + 1);
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    /* Removes the entry at a given position from this list. */
    public T remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
		T item = elementData[size - 1];
        elementData[size - 1] = null;
        size--;
		return item;
    }
	
	/* Retrieves all entries that are in this list in the order in which
       they occur in the list.*/
	public T[] toArray(){
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[getLength()];
		
		for(int index = 0; index < getLength(); index++){
			result[index] = elementData[index];
		}
		return result;
	}

    /* Replaces the entry at a given position in this list. */
    public T replace(int index, T value) {
        checkIndex(index);
		T item = elementData[index];
        elementData[index] = value;
		
		return item;
    }

    /* Removes all entries from this list. */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /*ensures that the underlying array has the given capacity; if not,
       the size is doubled (or more if given capacity is even larger)*/
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /*throws an IndexOutOfBoundsException if the given index is
       not a legal index of the current list*/
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }
	
}
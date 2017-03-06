import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SortedLinkedList <T extends Comparable<? super T>> implements SortedListInterface<T> {

    private Node first;
    private int size;

    public SortedLinkedList() {
        this.first = null;
        this.size = 0;
    }

   /* Adds a new entry to this sorted list in its proper order.
       The list's size is increased by 1. */
    public void add(T newEntry) {
        if (newEntry == null) {
            throw new NullPointerException();
        }
        size++;
        if (first != null) {
            Node before = null;
            Node current = first;
            for (; newEntry.compareTo(current.getValue()) > 0; before = current, current = current
                    .getNext()) {
                if(current.getNext() == null) {
                    current.setNext(new Node(newEntry));
                   // return true;
                }
            }
            Node newNode = new Node(newEntry);
            if (before != null) {
                before.setNext(newNode);
                newNode.setNext(current);
            } else {
                newNode.setNext(first);
                first = newNode;
            }
        } else {
            first = new Node(newEntry);
        }
        //return true;
    }

	/* Adds a new entry at a specified position within this list. */
    public void add(int index, T newEntry) {
        throw new UnsupportedOperationException();
    }

	/* Removes all entries from this list. */
    public void clear() {
        this.first = null;
    }

	 /* Sees whether this list contains a given entry. */
    public boolean contains(T obj) {
        return obj == null ? false : getPosition(obj) != -1;
    }
	
    /* Retrieves the entry at a given position in this list.*/
    public T getEntry(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index " + index
                    + " is out of range.");
        }
        Node result = first;
        for (; index > 0; index--, result = result.getNext())
            ;
        return result.getValue();
    }

    /* Gets the position of an entry in this sorted list.*/
    public int getPosition(T obj) {
        Node result = first;
        for (int i = 0; result != null; i++, result = result.getNext()) {
            if (result.getValue().equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    /* Sees whether this list is empty.
       @return  True if the list is empty, or false if not. */
    public boolean isEmpty() {
        return first == null;
    }
    
	/* Removes the first or only occurrence of a specified entry
       from this sorted list.*/
    public boolean remove(T obj) {
        for (Node before = null, current = first; current != null; before = current, current = current
                .getNext()) {
            if (current.getValue().equals(obj)) {
                size--;
                if(before == null) {
                    first = current.getNext();
                } else {
                    before.setNext(current.getNext());
                }
                return true;
            }
        }
        return false;
    }

    /* Removes the entry at a given position from this list.*/
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index " + index
                    + " is out of range.");
        }
        Node removed = first;
        Node before = null;
        for (; index > 0; index--, before = removed, removed = removed
                .getNext())
            ;
        before.setNext(removed.getNext());
        size--;
        return removed.getValue();
    }
   /* Gets the length of this list.
       @return  The integer number of entries currently in the list. */
    public int getLength() {
        return size;
    }
	
    /* Retrieves all entries that are in this list in the order in which
       they occur in the list. */
    public T[] toArray() {
		@SuppressWarnings("unchecked")
        T[] result = (T[])new Object[size];
        int counter = 0;
        for (Node current = first; current != null; current = current.getNext(), counter++) {
            result[counter] = current.getValue();
        }
        return result;
    }

    
	 @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        T[] result = array.length < size ? Arrays.copyOf(array, size) : array;
        int counter = 0;
        for (Node current = first; current != null; current = current.getNext(), counter++) {
            result[counter] = (T) current.getValue();
        }
        return result;
    }

    class Node {

        private T value;
        private Node next = null;

        public Node(T value) {
            this.value = value;
        }

        public Node(Node node) {
            this.value = node.value;
            this.next = node.next == null ? null : new Node(node.next);
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
        
		@SuppressWarnings("rawtypes")
        public boolean equals(T obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SortedLinkedList<?>.Node)) {
                return false;
            }
            SortedLinkedList.Node other = (SortedLinkedList.Node) obj;
            return this.equals(other)
                    && (next == null ^ other.next == null)
                    && next.equals(other.next)
                    && (value == null ^ other.value == null)
                    && value.equals(other.value);
        }

    }
}
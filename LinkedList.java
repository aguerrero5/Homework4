/***************************************************************************
 * List implementation using Nodes
 *****************************************************************************/

import java.util.*;

public class LinkedList<T> implements ListInterface<T>{
   private Node<T> head;
   private int size = 0;

 /*  Constructs an empty list*/
   public LinkedList(){
      head = null;
   }
   
 /* Returns true if the list is empty*/
   public boolean isEmpty(){
      return head == null;
   }
   
 /* Inserts a new node at the beginning of this list.*/
   public void addFirst(T item){
      head = new Node<T>(item, head);
	  size++;
   }
   
 /* Returns the first element in the list.*/
   public T getFirst(){
      if(head == null) throw new NoSuchElementException();

      return head.data;
   }
   
 /* Removes the first element in the list.*/
   public T remove(int givenPosition){
      T tmp = getEntry(givenPosition);
      head = head.next;
	  size--;
      return tmp;
   }
   
 /* Inserts a new node to the end of this list.*/
   public void addLast(T item){
      if( head == null){
         addFirst(item);
	     size++;
	  }
      else
      {
         Node<T> tmp = head;
         while(tmp.next != null) tmp = tmp.next;

         tmp.next = new Node<T>(item, null);
      }
   }
    /* Sees whether this list contains a given entry. */
    public boolean contains(T obj) {
        return;
    }
	
	/*throws an IndexOutOfBoundsException if the given index is
       not a legal index of the current list*/
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }
	
	/* Replaces the entry at a given position in this list. */
    public T replace(int index, T value) {
        checkIndex(index);
		T item = elementData[index];
        elementData[index] = value;
		
		return item;
    }
   
 /*  Returns the last element in the list.*/
   public T getLast(){
      if(head == null) throw new NoSuchElementException();

      Node<T> tmp = head;
      while(tmp.next != null) tmp = tmp.next;

      return tmp.data;
   }
   
 /*  Removes all nodes from the list.*/
   public void clear(){
      head = null;
	  size = 0;
   }
   /* Retrieves all entries that are in this list in the order in which
       they occur in the list. */
    public T[] toArray() {
		@SuppressWarnings("unchecked")
        T[] result = (T[])new Object[size];
        int counter = 0;
        for (Node current = head; current != null; current = current.getNext(), counter++) {
            result[counter] = current.getValue();
        }
        return result;
    }

 /*  Returns the data at the specified position in the list.*/
   public T getEntry(int pos){
      if (head == null) throw new IndexOutOfBoundsException();

      Node<T> tmp = head;
      for (int k = 0; k < pos; k++) tmp = tmp.next;

      if( tmp == null) throw new IndexOutOfBoundsException();

      return tmp.data;
   }
   
 /* Returns a string representation*/
   public String toString() {
    Node<T> node = head;
    StringBuffer result = new StringBuffer();
    while (node != null) {
      node = node.next;
      if (node != null) {
        result.append (" ==> ");
      }
    }
    return result.toString();
  }
  
  
 /*  Inserts a new node after a node containing the anEntry.*/
   public void insertAfter(T anEntry, T toInsert){
      Node<T> tmp = head;

      while(tmp != null && !tmp.data.equals(anEntry)) tmp = tmp.next;

      if(tmp != null)
         tmp.next = new Node<T>(toInsert, tmp.next);
   }
   
 /* Inserts a new node before a node containing the anEntry.*/
   public void insertBefore(T anEntry, T toInsert){
      if(head == null) return;

      if(head.data.equals(anEntry))
      {
         addFirst(toInsert);
		 size++;
         return;
      }

      Node<T> prev = null;
      Node<T> cur = head;

      while(cur != null && !cur.data.equals(anEntry))
      {
         prev = cur;
         cur = cur.next;
      }
      //insert between cur and prev
      if(cur != null)
         prev.next = new Node<T>(toInsert, cur);
   }
   
 /* Removes the first occurrence of the specified element in this list.*/
   public T remove(int anEntry){
      if(head == null)
         throw new RuntimeException("cannot delete");

      if( head.data.getEntry(anEntry) )
      {
         head = head.next;
		 size--;
         return;
      }

      Node<T> cur  = head;
      Node<T> prev = null;

      while(cur != null && !cur.data.getEntry(anEntry))
      {
         prev = cur;
         cur = cur.next;
      }

      if(cur == null)
         throw new RuntimeException("cannot delete");

      //delete cur node
	  @SuppressWarnings("unchecked")
      prev.next = cur.next;
   }
   /* Gets the length of this list.
       @return  The integer number of entries currently in the list. */
    public int getLength() {
        return size;
    }
/////////////////////////////////////////////////////////////////////
   class Node{
	   @SuppressWarnings("rawtypes")
      private T data;
      private Node next = null;

      public Node(T data, Node<T> next)
      {
         this.data = data;
         this.next = next;
      }
	  public T getValue() {
            return data;
      }
	  @SuppressWarnings("rawtypes")
	  public Node getNext() {
            return next;
      }
   }
}
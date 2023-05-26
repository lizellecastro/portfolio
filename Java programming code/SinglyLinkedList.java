/****************************************************************************************************
 * Created by Lizelle Castro and Nathan Egot
 * COSC 3420.501
 * Project #4
 * Due Date 4/13/2023
 * Purpose: This program is intended to expand the SinglyLinkedList class by providing the complete
 *          method definition for methods add(E element), add(int index, E element), get(int index),
 *          and set(int index, E element) and filling in the implementation details of each method.
 ****************************************************************************************************/
import java.util.*;

public class SinglyLinkedList<E> extends AbstractCollection<E> 
{
  protected Entry<E> head;  // reference to the first entry in the list                     
  
  /**
   * Initializes this SinglyLinkedList object to be empty, with elements to be of 
   * type E.
   */
  public SinglyLinkedList()
  {
    head = null;
  } // constructor
  
  
  /**
   *  Determines if this SinglyLinkedList object has no elements.
   *
   *  @return true -  if this SinglyLinkedList object has no elements; otherwise,
   *                          false.  
   */
  public boolean isEmpty ()  
  {
    return head == null;
  } // method isEmpty
  
  
  /**
   *  Adds a specified element to the front of this SinglyLinkedList object.
   *
   *  @param element - the element to be appended.   
   *
   */
  public void addToFront (E element) 
  {
    Entry<E> newEntry = new Entry<E>();
    newEntry.element = element;
    newEntry.next = head;
    head = newEntry;     
  } // method addToFront
  
  
  /**
   *  Returns a SinglyLinkedListIterator object to iterate over this
   *  SinglyLinkedList object.
   *
   */  
  public Iterator<E> iterator()
  {
    return new SinglyLinkedListIterator();
  } // method iterator
  
  
  /**  
   *  Determines the number of elements in this SinglyLinkedList object.
   *  The worstTime(n) is O(n).
   *
   *  @return the number of elements.
   */
  public int size() 
  {
    int count = 0;
    
    for (Entry<E> current = head; current != null; current = current.next)
      count++;
    return count;
  } // method size
  
  
  /** 
   *  Determines if this SinglyLinkedList object contains a specified element.
   *  The worstTime(n) is O(n).
   *
   *  @param obj - the specified element being sought.
   *
   *  @return true - if this SinglyLinkedList object contains element; otherwise,
   *                false. 
   *
   */
  public boolean contains (Object obj) 
  {
    if (obj == null)
    {
      for (Entry<E> current = head; current != null; current = current.next)
        if (obj == current.element)
          return true;
    } // if obj == null
    else   
      for (Entry<E> current = head; current != null; current = current.next)
        if (obj.equals (current.element))
          return true;
    return false;
  } // method contains    

 /////// new methods start here ////////

  public boolean add(E element) {
    Entry<E> newEntry = new Entry<>();
    newEntry.element = element;
    Entry<E> current = head; // current element starts at head
    if(head == null) { // if head is null then element becomes head
      head = newEntry;
      head.next = null;
      return true;    // true once this is complete
    }
    else { // if list is not empty then element is added at the very end
      while(current.next != null) {
        current = current.next;
      }
      current.next = newEntry;
      newEntry.next = null;
      return true;    // true once this is complete
    }
  } // boolean add method

  public void add(int index, E element)
  {    
    Entry<E> newEntry = new Entry<E>();
    Entry<E> current = head;
    int current_size = size();
    newEntry.element = element;
    // if at 0 index simply call addToFront method
    if(index == 0) { 
      addToFront(element);
    }
    // if index > 0 then insert element at specified index
    else { 
      if(head != null && index <= current_size) {
        for(int i = 1; i < index; i++) {
          current = current.next;
        }
        newEntry.next = current.next;
        current.next = newEntry;
      }
    }
  } // method add(int index, E element)
  
  public E set(int index, E element) {
      if (index < 0 || index >= size()) {
          throw new IndexOutOfBoundsException();
      }
      Entry<E> current = this.head;
      if (head != null && index <= size()) {
    	  for (int i = 0; i < index; i++) {
    		  current = current.next;
          }
      }
      E previousElement = current.element;
      current.element = element;
      return previousElement;
  } // method E set(int index, E element)

  public E get(int index) {
      if (index < 0 || index >= size()) {
          throw new IndexOutOfBoundsException();
      }
      Entry<E> current = this.head;
      if (head != null && index <= size()) {
    	  for (int i = 0; i < index; i++) {
    		  current = current.next;
          }
      }
      return current.element;
  } // method E get(int index)


  public void clear() 
  {
    head = null; 
  }
  
  protected class SinglyLinkedListIterator implements Iterator<E> 
  {
    protected Entry<E> next;
    
    /**
     *  The iterator has been initialized.
     */
    protected SinglyLinkedListIterator() 
    {
      next = head;
    } // constructor
    
    
    /** 
     *  Returns the element this Iterator object was (before this call) 
     *  positioned at, and advances this Iterator object.
     *                    
     *  @return - the element this Iterator object was positioned at.
     *
     *  @throws NoSuchElementException   if this Iterator object was
     *                 not postioned at an element before this call.
     */                            
    public E next() 
    {
      E theElement = next.element;
      next = next.next;
      return theElement;
    } // method next
    
    
    /**
     *  Determines if this Iterator object is positioned at an element in this
     *  Collection.
     *
     *  @return true - if this Iterator object is positioned at an element; 
     *                otherwise, false.                        
     */                   
    public boolean hasNext() 
    {       
      return next != null;
    } // method hasNext
    
    /**
     *  Removes the element returned by the most recent call to next().
     *  The behavior of this Iterator object is unspecified if the underlying 
     *  collection is modified ( while this iteration is in progress) other than 
     *   by calling this remove() method.
     *
     *  @throws IllegalStateException - if next() had not been called before
     *                 this call to remove(), or if there had been an intervening call 
     *                 to remove() between the most recent call to next() and this 
     *                 call.
     **/
    
  } // class SinglyLinkedListIterator
  
  protected class Entry<E>
  {
    protected E element;
    protected Entry<E> next;
    
  } // class Entry
  
} // class SinglyLinkedList
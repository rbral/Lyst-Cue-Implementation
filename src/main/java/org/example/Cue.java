package org.example;

/**
 * Cue - an implementation of a classic queue
 *
 * @param <T> generic class with parameter T
 * @author Rivka Bral
 */
public class Cue<T> implements ICue<T>
{
    protected Lyst<T> lyst = new Lyst<T>();

    protected Lyst.ForwardIterator forwardIterator() {
        return lyst.new ForwardIterator();
    }

    /**
     * encue - enqueue an element to the back of the queue
     * @param value : T - value to be added
     */
    @Override
    public void encue(T value)
    {
        lyst.addLast(value);
    }

    /**
     * decue - remove and return the element at the front of the queue or return null if the queue is empty
     * @return T - the value removed from the front of the queue, or null if the queue is empty
     */
    @Override
    public T decue()
    {
        return isEmpty() ? null : lyst.removeFirst();
    }

    /**
     * peek - return the element at the front of the queue without removing it, or null if the queue is empty
     * @return T - the value at the front of the queue, or null if the queue is empty
     */
    @Override
    public T peek()
    {
        return isEmpty() ? null : lyst.getFirst();
    }

    /**
     * isEmpty - check if the queue is empty
     * @return boolean - true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return lyst.getFirst() == null;
    }

    /**
     * toString - return a string representation of the elements in the Cue
     * @return String - a string representing the Cue
     */
    @Override
    public String toString()
    {
        return lyst.toString();
    }
}

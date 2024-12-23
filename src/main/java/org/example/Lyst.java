package org.example;

import java.util.*;

/**
 * Lyst - our implementation of a doubly linked list
 *
 * @param <T> generic class with parameter T
 * @author Rivka Bral
 */
public class Lyst<T> implements ILyst<T>
{
    /**
     * Node - represents an element in the Lyst
     * @param <T> generic class with parameter T
     */
    private class Node<T>
    {
        T value;
        Node<T> prev = null;
        Node<T> next = null;

        public Node(T val)
        {
            value = val;
        }

        @Override
        public String toString()
        {
            return value.toString();
        }
    }

    /**
     * ForwardIterator - an implementation of Iterator to iterate through the Lyst from first to last.
     */
    public class ForwardIterator implements Iterator<T>
    {
        private Node<T> current = first;
        private Node<T> lastReturned = null;

        /**
         * @return true if the current element is not null
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * @return T the next element in the Lyst
         */
        @Override
        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            else
            {
                lastReturned = current;
                T retVal = current.value;
                current = current.next;
                return retVal;
            }
        }

        /**
         * Removes the last element returned by this iterator.
         */
        @Override
        public void remove()
        {
            if (lastReturned == null)
            {
                throw new IllegalStateException();
            }
            Lyst.this.remove(lastReturned.value);
            lastReturned = null;
        }
    }

    /**
     * BackwardIterator - implementation of Iterator to iterate through the Lyst backward
     */
    public class BackwardIterator implements Iterator<T>
    {
        private Node<T> current = last;
        private Node<T> lastReturned = null;

        /**
         * @return true if the current element is not null
         */
        @Override
        public boolean hasNext()
        {
            return current != null;
        }

        /**
         * @return T the previous element in the Lyst
         */
        @Override
        public T next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            else
            {
                T retVal = current.value;
                current = current.prev;
                return retVal;
            }
        }

        /**
         * Removes the last element returned by this iterator.
         */
        @Override
        public void remove()
        {
            if (lastReturned == null)
            {
                throw new IllegalStateException();
            }
            Lyst.this.remove(lastReturned.value);
            lastReturned = null;
        }
    }


    private Node first = null;
    private Node last = null;

    HashMap<T, Node> elements = new HashMap<>();

    /**
     * addFirst - add a new element as the first element of our linked list
     *
     * @param value : T - value to be added as the first node on the list
     */
    @Override
    public void addFirst(T value)
    {
        Node node = new Node(value);
        if (first == null) // nothing in list yet
        {
            first = last = node;
        }
        else if (first == last) // only one node in list
        {
            first = node;
            first.next = last;
            last.prev = first;
        }
        else
        {
            first.prev = node;
            node.next = first;
            first = node;
        }
        elements.put(value, node);
    }

    /**
     * addLast - add an element to the tail of the Lyst
     * @param value : T - value to be added
     */
    @Override
    public void addLast(T value)
    {
        Node node = new Node(value);
        if (first == null)
        {
            addFirst(value); // this method puts it in hashmap already
        }
        else
        {
            last.next = node;
            node.prev = last;
            last = node;

            elements.put(value, node);
        }
    }

    /**
     * removeFirst - remove and return the first element in the Lyst or return null if the Lyst is empty
     * @return T - value of the first element
     */
    @Override
    public T removeFirst()
    {
        T removed = null;

        if (first != null) // ensuring there is something in the list
        {
            removed = (T) first.value;
            if (first == last)
            {
                // only one element in list,
                // so set everything to null
                first = last = null;
            } else
            {
                // more than one node in list;
                // update neighbors
                first = first.next;
                first.prev = null;
            }
        }

        // remove from hashmap
        if (removed != null)
        {
            elements.remove(removed);
        }

        return removed;
    }



    /**
     * removeLast - remove and return the last element in the Lyst or return null if the Lyst is empty
     * @return T - value of the last element
     */
    @Override
    public T removeLast()
    {
        T removed = null;

        if (last != null)
        {
            removed = (T) last.value;
            if (first == last)
            {
                // only one element in list,
                // so set everything to null
                first = last = null;
            } else
            {
                // more than one node in list;
                // update neighbors
                last = last.prev;
                last.next = null;
            }
        }
        // remove from hashmap
        if (removed != null)
        {
            elements.remove(removed);
        }

        return removed;
    }

    /**
     * remove - remove a certain element from the Lyst
     * @param value the element to be removed
     * @return true if removed, false if element was not in Lyst
     */
    @Override
    public boolean remove(T value)
    {
        boolean removed = false;

        // check if first or last:
        Node toRemove = elements.get(value);
        if (toRemove ==  first)
        {
            removeFirst();
        }
        else if (toRemove == last)
        {
            removeLast();
        }
        // make sure not null:
        else if (toRemove != null)
        {
            // update neighbors:
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
            elements.remove(toRemove);
            removed = true;
        }

        return removed;
    }

    /**
     * contains - check if the Lyst contains a certain element
     * @param value the value of the Node to check for
     * @return boolean : true if element is in the Lyst
     */
    @Override
    public Boolean contains(T value)
    {
        return elements.containsKey(value);
    }

    /**
     * addBefore - add a new element before an element if it exists in the Lyst
     * @param value - the new element to add
     * @param before - the element in the Lyst which the new element will precede
     * @return Boolean - true if param before exists in the Lyst
     */
    @Override
    public Boolean addBefore(T value, T before)
    {
        boolean found = elements.containsKey(before);
        if (found)
        {
            // check if before is first because otherwise
            // the next and prev would be tied differently
            if (before == first.value)
            {
                addFirst(value);
            }
            else
            {
                Node curr = elements.get(before);
                Node node = new Node(value);
                node.next = curr;
                node.prev = curr.prev;
                curr.prev.next = node;
                curr.prev = node;
                elements.put(value, node);
            }

        }
        return found;
    }

    /**
     * addBefore - add a new element after an element if it exists in the Lyst
     * @param value - the new element to add
     * @param after - the element in the Lyst which the new element will follow
     * @return Boolean - true if param after exists in the Lyst
     */
    @Override
    public Boolean addAfter(T value, T after)
    {
        boolean found = elements.containsKey(after);
        if (found)
        {
            // check if after is last because otherwise
            // the next and prev would be tied differently
            if (after == last.value)
            {
                addLast(value);
            }
            else
            {
                Node curr = elements.get(after);
                Node node = new Node(value);
                node.prev = curr;
                node.next = curr.next;
                curr.next.prev = node;
                curr.next = node;
                elements.put(value, node);
            }
        }
        return found;
    }

    /**
     * getFirst - get the first element in the Lyst or null if the Lyst is empty
     * @return T - value of the first element
     */
    @Override
    public T getFirst()
    {
        return first == null ? null : (T) first.value;
    }

    /**
     * getLast - get the last element in the Lyst or null if the Lyst is empty
     * @return T - value of the last element
     */
    @Override
    public T getLast()
    {
        return last == null ? null : (T) last.value;
    }

    /**
     * getNext - get the element following a particular element in the Lyst
     * @param value - the element of which to get the element following it
     * @return T - the value of the element following the param value
     */
    @Override
    public T getNext(T value)
    {
        T retVal = null;
        boolean found = elements.containsKey(value);
        if (found)
        {
            Node curr = elements.get(value);
            // no need to check if curr is last / has a next
            // since its next would already be initialized to null
            retVal = (T) curr.next.value;
        }
        return retVal;
    }

    /**
     * getPrev - get the element preceding a particular element in the Lyst
     * @param value - the element of which to get the element preceding it
     * @return T - the value of the element preceding the param value
     */
    @Override
    public T getPrev(T value)
    {
        T retVal = null;
        boolean found = elements.containsKey(value);
        if (found)
        {
            Node curr = elements.get(value);
            // no need to check if curr is first / has a prev
            // since its prev would already be initialized to null
            retVal = (T) curr.prev.value;
        }
        return retVal;
    }

    /**
     * toString - return a string representation of the elements in the Lyst
     * @return String - a string representing the Lyst
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("->");
        Node<T> curr = first;
        while (curr != null)
        {
            sb.append(curr.value.toString());
            sb.append(curr.next == null ? "->" : "<->");
            curr = curr.next;
        }
        return sb.toString();
    }

    @Override
    public int size()
    {
        ForwardIterator it = new ForwardIterator();
        int len = 0;
        while(it.hasNext())
        {
            ++len;
            it.next();
        }
        return len;
    }

}
